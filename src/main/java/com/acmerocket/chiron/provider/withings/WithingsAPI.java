package com.acmerocket.chiron.provider.withings;

import java.util.Date;
import java.util.List;

import com.acmerocket.chiron.provider.withings.model.MeasureType;
import com.acmerocket.chiron.provider.withings.model.MeasurementGroup;
import com.acmerocket.chiron.provider.withings.model.User;

/**
 * Main interface for dealing with the withings api.
 * 
 * @author jon
 */
public interface WithingsAPI {
    // special case. replace!
    public List<User> getUsers(String email, String hash) throws WithingsException;

    public List<MeasurementGroup> getMeasureGroups(WithingsCredentials creds) throws WithingsException;

    public List<MeasurementGroup> getMeasureGroups(WithingsCredentials creds, Date startDate, Date endDate,
            MeasureType type) throws WithingsException;

    public List<MeasurementGroup> getMeasureGroups(WithingsCredentials creds, Date startDate, Date endDate,
            MeasureType type, int limit, int offset) throws WithingsException;

    public List<MeasurementGroup> getMeasureGroups(WithingsCredentials creds, Date lastUpdateDate, MeasureType type)
            throws WithingsException;

    public List<MeasurementGroup> getMeasureGroups(WithingsCredentials creds, Date lastUpdateDate, MeasureType type,
            int limit, int offset) throws WithingsException;

    public User getUser(WithingsCredentials creds) throws WithingsException;

    public void setPublic(WithingsCredentials creds, boolean isPublic) throws WithingsException;

    public void addSubscription(WithingsCredentials creds, String callbackUrl) throws WithingsException;

    public void removeSubscription(WithingsCredentials creds, String callbackUrl) throws WithingsException;

    public Date getSubscription(WithingsCredentials creds, String callbackUrl) throws WithingsException;
}
