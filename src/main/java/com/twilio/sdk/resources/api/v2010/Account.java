package com.twilio.sdk.resources.api.v2010;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.converters.MarshalConverter;
import com.twilio.sdk.creators.api.v2010.AccountCreator;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.fetchers.api.v2010.AccountFetcher;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.readers.api.v2010.AccountReader;
import com.twilio.sdk.resources.RestException;
import com.twilio.sdk.resources.SidResource;
import com.twilio.sdk.updaters.api.v2010.AccountUpdater;
import org.joda.time.DateTime;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Account extends SidResource {
    private static final long serialVersionUID = 68381032403131L;

    public enum Status {
        ACTIVE("active"),
        SUSPENDED("suspended"),
        CLOSED("closed");
    
        private final String value;
        
        private Status(final String value) {
            this.value = value;
        }
        
        public String toString() {
            return value;
        }
        
        @JsonCreator
        public static Status forValue(final String value) {
            String normalized = value.replace("-", "_").toUpperCase();
            return Status.valueOf(normalized);
        }
    }

    public enum Type {
        TRIAL("Trial"),
        FULL("Full");
    
        private final String value;
        
        private Type(final String value) {
            this.value = value;
        }
        
        public String toString() {
            return value;
        }
        
        @JsonCreator
        public static Type forValue(final String value) {
            String normalized = value.replace("-", "_").toUpperCase();
            return Type.valueOf(normalized);
        }
    }

    /**
     * Create a new Twilio Subaccount from the account making the request
     * 
     * @return AccountCreator capable of executing the create
     */
    public static AccountCreator create() {
        return new AccountCreator();
    }

    /**
     * Fetch the account specified by the provided Account Sid
     * 
     * @param sid Fetch by unique Account Sid
     * @return AccountFetcher capable of executing the fetch
     */
    public static AccountFetcher fetch(final String sid) {
        return new AccountFetcher(sid);
    }

    /**
     * Retrieves a collection of Accounts belonging to the account used to make the
     * request
     * 
     * @return AccountReader capable of executing the read
     */
    public static AccountReader read() {
        return new AccountReader();
    }

    /**
     * Modify the properties of a given Account
     * 
     * @param sid The sid
     * @return AccountUpdater capable of executing the update
     */
    public static AccountUpdater update(final String sid) {
        return new AccountUpdater(sid);
    }

    /**
     * Converts a JSON String into a Account object using the provided ObjectMapper
     * 
     * @param json Raw JSON String
     * @param objectMapper Jackson ObjectMapper
     * @return Account object represented by the provided JSON
     */
    public static Account fromJson(final String json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, Account.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    /**
     * Converts a JSON InputStream into a Account object using the provided
     * ObjectMapper
     * 
     * @param json Raw JSON InputStream
     * @param objectMapper Jackson ObjectMapper
     * @return Account object represented by the provided JSON
     */
    public static Account fromJson(final InputStream json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, Account.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    private final String authToken;
    private final DateTime dateCreated;
    private final DateTime dateUpdated;
    private final String friendlyName;
    private final String ownerAccountSid;
    private final String sid;
    private final Account.Status status;
    private final Map<String, String> subresourceUris;
    private final Account.Type type;
    private final String uri;

    @JsonCreator
    private Account(@JsonProperty("auth_token")
                    final String authToken, 
                    @JsonProperty("date_created")
                    final String dateCreated, 
                    @JsonProperty("date_updated")
                    final String dateUpdated, 
                    @JsonProperty("friendly_name")
                    final String friendlyName, 
                    @JsonProperty("owner_account_sid")
                    final String ownerAccountSid, 
                    @JsonProperty("sid")
                    final String sid, 
                    @JsonProperty("status")
                    final Account.Status status, 
                    @JsonProperty("subresource_uris")
                    final Map<String, String> subresourceUris, 
                    @JsonProperty("type")
                    final Account.Type type, 
                    @JsonProperty("uri")
                    final String uri) {
        this.authToken = authToken;
        this.dateCreated = MarshalConverter.dateTimeFromString(dateCreated);
        this.dateUpdated = MarshalConverter.dateTimeFromString(dateUpdated);
        this.friendlyName = friendlyName;
        this.ownerAccountSid = ownerAccountSid;
        this.sid = sid;
        this.status = status;
        this.subresourceUris = subresourceUris;
        this.type = type;
        this.uri = uri;
    }

    /**
     * @return The authorization token for this account
     */
    public final String getAuthToken() {
        return this.authToken;
    }

    /**
     * @return The date this account was created
     */
    public final DateTime getDateCreated() {
        return this.dateCreated;
    }

    /**
     * @return The date this account was last updated
     */
    public final DateTime getDateUpdated() {
        return this.dateUpdated;
    }

    /**
     * @return A human readable description of this account
     */
    public final String getFriendlyName() {
        return this.friendlyName;
    }

    /**
     * @return The unique 34 character id representing the parent of this account
     */
    public final String getOwnerAccountSid() {
        return this.ownerAccountSid;
    }

    /**
     * @return A 34 character string that uniquely identifies this resource.
     */
    public final String getSid() {
        return this.sid;
    }

    /**
     * @return The status of this account
     */
    public final Account.Status getStatus() {
        return this.status;
    }

    /**
     * @return Account Instance Subresources
     */
    public final Map<String, String> getSubresourceUris() {
        return this.subresourceUris;
    }

    /**
     * @return The type of this account
     */
    public final Account.Type getType() {
        return this.type;
    }

    /**
     * @return The URI for this resource, relative to `https://api.twilio.com`
     */
    public final String getUri() {
        return this.uri;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        
        Account other = (Account) o;
        
        return Objects.equals(authToken, other.authToken) && 
               Objects.equals(dateCreated, other.dateCreated) && 
               Objects.equals(dateUpdated, other.dateUpdated) && 
               Objects.equals(friendlyName, other.friendlyName) && 
               Objects.equals(ownerAccountSid, other.ownerAccountSid) && 
               Objects.equals(sid, other.sid) && 
               Objects.equals(status, other.status) && 
               Objects.equals(subresourceUris, other.subresourceUris) && 
               Objects.equals(type, other.type) && 
               Objects.equals(uri, other.uri);
    }

    @Override
    public int hashCode() {
        return Objects.hash(authToken,
                            dateCreated,
                            dateUpdated,
                            friendlyName,
                            ownerAccountSid,
                            sid,
                            status,
                            subresourceUris,
                            type,
                            uri);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                          .add("authToken", authToken)
                          .add("dateCreated", dateCreated)
                          .add("dateUpdated", dateUpdated)
                          .add("friendlyName", friendlyName)
                          .add("ownerAccountSid", ownerAccountSid)
                          .add("sid", sid)
                          .add("status", status)
                          .add("subresourceUris", subresourceUris)
                          .add("type", type)
                          .add("uri", uri)
                          .toString();
    }
}