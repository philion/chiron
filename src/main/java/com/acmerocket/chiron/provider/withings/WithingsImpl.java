package com.acmerocket.chiron.provider.withings;

import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;

import com.acmerocket.chiron.provider.withings.model.MeasureType;
import com.acmerocket.chiron.provider.withings.model.MeasurementGroup;
import com.acmerocket.chiron.provider.withings.model.StatusCode;
import com.acmerocket.chiron.provider.withings.model.User;
import com.acmerocket.chiron.provider.withings.response.GetUserBody;
import com.acmerocket.chiron.provider.withings.response.GetUserResponse;
import com.acmerocket.chiron.provider.withings.response.GetUsersBody;
import com.acmerocket.chiron.provider.withings.response.GetUsersResponse;
import com.acmerocket.chiron.provider.withings.response.JsonResponse;
import com.acmerocket.chiron.provider.withings.response.MeasurementGroupResponse;
import com.acmerocket.chiron.provider.withings.response.MeasurementGroupResponseBody;
import com.acmerocket.chiron.provider.withings.response.ResponseObject;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/*
 * MAJOR INTERFACES
 * - credentials
 * - provider
 * -- pulse
 * -- blood pressure
 * -- user
 * -- height, weight, etc... "body"
 * -- activity... ?
 * - getdata
 * - putdata
 * - notification
 */

public class WithingsImpl implements WithingsAPI {
    private static final String URL_BASE = "http://wbsapi.withings.net/";
    
    private static final String URL_USER_UPDATE = URL_BASE + "user?action=update&userid=%s&publickey=%s&ispublic=%s";
    private static final String URL_NOTIFY_REVOKE = URL_BASE + "notify?action=revoke&userid=%s&publickey=%s&callbackurl=%s";
    private static final String URL_NOTIFY_GET = URL_BASE + "notify?action=get&userid=%s&publickey=%s&callbackurl=%s";
    private static final String URL_SUBSCRIBE = URL_BASE + "notify?action=subscribe&userid=%s&publickey=%s&callbackurl=%s";
    private static final String URL_GETUSERSLIST = URL_BASE + "account?action=getuserslist&email=%s&hash=%s";
    private static final String URL_GETBYUSERID = URL_BASE + "user?action=getbyuserid&userid=%s&publickey=%s";
    private static final String URL_GET_MEASURE_BASE = URL_BASE + "measure?action=getmeas&userid=%s&publickey=%s";
    private static final String URL_GET_ONCE = URL_BASE + "once?action=get";

    private final ObjectMapper mapper = new ObjectMapper();
    // TODO: Custom config?

    private String getOneTimeKey() throws WithingsException {
        JsonNode rootNode = this.requestResource(URL_GET_ONCE, JsonResponse.class);
        return rootNode.path("once").textValue();
    }

    private String getHash(String email, String password) throws WithingsException {
        String passwordMd5 = DigestUtils.md5Hex(password);
        return DigestUtils.md5Hex(email + ":" + passwordMd5 + ":" + this.getOneTimeKey());
    }

    public List<MeasurementGroup> getMeasureGroups(WithingsCredentials creds) throws WithingsException {
        return this.internalGetMeasureGroups(creds, null, null, null, null, -1, -1);
    }

    public List<MeasurementGroup> getMeasureGroups(WithingsCredentials creds, Date startDate, Date endDate,
            MeasureType type) throws WithingsException {
        return this.internalGetMeasureGroups(creds, startDate, endDate, null, type, -1, -1);
    }

    public List<MeasurementGroup> getMeasureGroups(WithingsCredentials creds, Date startDate, Date endDate,
            MeasureType type, int limit, int offset) throws WithingsException {
        return this.internalGetMeasureGroups(creds, startDate, endDate, null, type, limit, offset);
    }

    public List<MeasurementGroup> getMeasureGroups(WithingsCredentials creds, Date lastUpdateDate, MeasureType type)
            throws WithingsException {
        return this.internalGetMeasureGroups(creds, null, null, lastUpdateDate, type, -1, -1);
    }

    public List<MeasurementGroup> getMeasureGroups(WithingsCredentials creds, Date lastUpdateDate, MeasureType type,
            int limit, int offset) throws WithingsException {
        return this.internalGetMeasureGroups(creds, null, null, lastUpdateDate, type, limit, offset);
    }
    
    private <B, R extends ResponseObject<B>> B requestResource(String urlStr, Class<R> resourceType) throws WithingsException {
        try {
            URL url = new URL(urlStr);
            return requestResource(url, resourceType);
        }
        catch (MalformedURLException e) {
            throw new WithingsException(e);
        }
    }
    
    private <B, R extends ResponseObject<B>> B requestResource(URL url, Class<R> resourceType) throws WithingsException {
        URLConnection conn = null;
        try {
            conn = url.openConnection();
            conn.connect();
            
            R response = this.mapper.readValue(conn.getInputStream(), resourceType);
            StatusCode status = response.getStatus();
            if (StatusCode.success != status) {
                throw new WithingsException(status);
            }
            
            return response.getBody();
        }
        catch (Exception e) {
            throw new WithingsException(e);
        }
        finally {
            if ((conn != null) && (conn instanceof HttpURLConnection))
                ((HttpURLConnection) conn).disconnect();
        } 
    }

    private List<MeasurementGroup> internalGetMeasureGroups(WithingsCredentials creds, Date startDate, Date endDate,
            Date lastUpdateDate, MeasureType type, int limit, int offset) throws WithingsException {
        
        StringBuilder urlStr = new StringBuilder(String.format(URL_GET_MEASURE_BASE, creds.getId(), creds.getKey()));
        
        //StringBuilder urlStr = new StringBuilder(String.format(URL_GET_MEASURE_BASE, userId, publicKey));
        if (startDate != null) {
            urlStr.append("&startdate=" + startDate.getTime() / 1000);
        }
        if (endDate != null) {
            urlStr.append("&enddate=" + endDate.getTime() / 1000);
        }
        if (lastUpdateDate != null) {
            urlStr.append("&lastupdate=" + lastUpdateDate.getTime() / 1000);
        }
        if (type != null) {
            urlStr.append("&meastype=" + type); 
        }
        if (limit >= 0) {
            urlStr.append("&limit=" + limit);
        }
        if (offset >= 0) {
            urlStr.append("&offsetby=" + offset);
        }
                
        MeasurementGroupResponseBody body = this.requestResource(urlStr.toString(), MeasurementGroupResponse.class);
        return body.getGroups();         
    }

    public User getUser(WithingsCredentials creds) throws WithingsException {
        
        String urlStr = String.format(URL_GETBYUSERID, creds.getId(), creds.getKey());
        GetUserBody body = this.requestResource(urlStr.toString(), GetUserResponse.class);

        return body.getUser();
    }

    public List<User> getUsers(String email, String hash) throws WithingsException {
        String urlStr = String.format(URL_GETUSERSLIST, email, hash);
        
        GetUsersBody body = this.requestResource(urlStr, GetUsersResponse.class);
        return body.getUsers();
    }
    
    public void addSubscription(WithingsCredentials creds, String callbackUrl) throws WithingsException {        
        String urlStr = String.format(URL_SUBSCRIBE, creds.getId(), creds.getKey(), encode(callbackUrl));
        this.requestResource(urlStr, JsonResponse.class);
    }
    
    private static String encode(String urlStr) {
        try {
            return URLEncoder.encode(urlStr, "UTF-8");
        }
        catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public Date getSubscription(WithingsCredentials creds, String callbackUrl) throws WithingsException {
        String urlStr = String.format(URL_NOTIFY_GET, creds.getId(), creds.getKey(), encode(callbackUrl));
        JsonNode rootNode = this.requestResource(urlStr, JsonResponse.class);
        
        // FIXME
        return new Date(rootNode.path("body").path("expires").longValue() * 1000);
    }

    public void removeSubscription(WithingsCredentials creds, String callbackUrl) throws WithingsException {
        String urlStr = String.format(URL_NOTIFY_REVOKE, creds.getId(), creds.getKey(), encode(callbackUrl));
        this.requestResource(urlStr, JsonResponse.class);
    }

    public void setPublic(WithingsCredentials creds, boolean isPublic) throws WithingsException {
        // FIXME
        String publicFlag = isPublic ? "1" : "0";
        String urlStr = String.format(URL_USER_UPDATE, creds.getId(), creds.getKey(), publicFlag);
        this.requestResource(urlStr, JsonResponse.class);
    }
    
    public static void main(String[] args) throws WithingsException {
        WithingsImpl withings = new WithingsImpl();

        String hash = withings.getHash("philion@gmail.com", "d1sn3y");
        List<User> users = withings.getUsers("philion@gmail.com", hash);
        
        for (User user : users) {
            System.out.println("User: " + user);
            
            WithingsCredentials creds = new WithingsCredentials(user);

            List<MeasurementGroup> measures = withings.getMeasureGroups(creds);
            System.out.println("Measure: " + measures);
        }
    }
}
