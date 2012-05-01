package com.acmerocket.chiron.provider.withings.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties("fatmethod")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private long id;
    private String firstName;
    private String lastName;
    private String shortName;
    private String publicKey;
    private Gender gender;
    private Date birthDate;
    private boolean isPublic;

    public User() {};

    public User(long id, String firstname, String lastname, String shortname, String publicKey, boolean isFemale,
            Date birthDate) {
        this(id, firstname, lastname, shortname, publicKey, isFemale, false, birthDate);
    }

    public User(long id, String firstname, String lastname, String shortname, String publicKey, boolean isFemale,
            boolean isPublic, Date birthDate) {

        this.id = id;
        this.firstName = firstname;
        this.lastName = lastname;
        this.shortName = shortname;
        this.publicKey = publicKey;
        this.gender = isFemale == true ? Gender.female : Gender.male;
        this.isPublic = isPublic;
        this.birthDate = birthDate;
    }

    public String getFirstname() {
        return this.firstName;
    }

    public void setFirstname(String firstname) {
        this.firstName = firstname;
    }

    /** */
    public String getLastname() {
        return this.lastName;
    }

    /** */
    public void setLastname(String lastname) {
        this.lastName = lastname;
    }

    /** */
    public String getShortname() {
        return this.shortName;
    }

    /** */
    public void setShortname(String shortname) {
        this.shortName = shortname;
    }

    public Gender getGender() {
        return this.gender;
    }

    /** */
    public Date getBirthDate() {
        return this.birthDate;
    }

    @JsonProperty("birthdate")
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
    
    @JsonProperty("ispublic")
    public void setIsPublic(boolean isPublic) {
        this.isPublic = isPublic;
    }

    /** */
    public boolean isPublic() {
        return this.isPublic;
    }

    @JsonProperty("publickey")
    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getPublicKey() {
        return this.publicKey;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return "User [id=" + this.id + ", firstname=" + this.firstName + ", lastname=" + this.lastName + ", shortname="
                + this.shortName + ", publicKey=" + this.publicKey + ", birthDate=" + this.birthDate + ", gender="
                + this.gender + ", isPublic=" + this.isPublic + "]";
    }
}
