package com.twilio.sdk.resources.api.v2010.account;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.creators.api.v2010.account.ValidationRequestCreator;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.Resource;
import com.twilio.sdk.resources.RestException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ValidationRequest extends Resource {
    private static final long serialVersionUID = 227253393242231L;

    /**
     * create
     * 
     * @param accountSid The account_sid
     * @param phoneNumber The phone_number
     * @return ValidationRequestCreator capable of executing the create
     */
    public static ValidationRequestCreator create(final String accountSid, final com.twilio.types.PhoneNumber phoneNumber) {
        return new ValidationRequestCreator(accountSid, phoneNumber);
    }

    /**
     * Converts a JSON String into a ValidationRequest object using the provided
     * ObjectMapper
     * 
     * @param json Raw JSON String
     * @param objectMapper Jackson ObjectMapper
     * @return ValidationRequest object represented by the provided JSON
     */
    public static ValidationRequest fromJson(final String json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, ValidationRequest.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    /**
     * Converts a JSON InputStream into a ValidationRequest object using the
     * provided ObjectMapper
     * 
     * @param json Raw JSON InputStream
     * @param objectMapper Jackson ObjectMapper
     * @return ValidationRequest object represented by the provided JSON
     */
    public static ValidationRequest fromJson(final InputStream json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, ValidationRequest.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    private final String accountSid;
    private final com.twilio.types.PhoneNumber phoneNumber;
    private final String friendlyName;
    private final Integer validationCode;
    private final String callSid;

    @JsonCreator
    private ValidationRequest(@JsonProperty("account_sid")
                              final String accountSid, 
                              @JsonProperty("phone_number")
                              final com.twilio.types.PhoneNumber phoneNumber, 
                              @JsonProperty("friendly_name")
                              final String friendlyName, 
                              @JsonProperty("validation_code")
                              final Integer validationCode, 
                              @JsonProperty("call_sid")
                              final String callSid) {
        this.accountSid = accountSid;
        this.phoneNumber = phoneNumber;
        this.friendlyName = friendlyName;
        this.validationCode = validationCode;
        this.callSid = callSid;
    }

    /**
     * @return The account_sid
     */
    public final String getAccountSid() {
        return this.accountSid;
    }

    /**
     * @return The phone_number
     */
    public final com.twilio.types.PhoneNumber getPhoneNumber() {
        return this.phoneNumber;
    }

    /**
     * @return The friendly_name
     */
    public final String getFriendlyName() {
        return this.friendlyName;
    }

    /**
     * @return The validation_code
     */
    public final Integer getValidationCode() {
        return this.validationCode;
    }

    /**
     * @return The call_sid
     */
    public final String getCallSid() {
        return this.callSid;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        
        ValidationRequest other = (ValidationRequest) o;
        
        return Objects.equals(accountSid, other.accountSid) && 
               Objects.equals(phoneNumber, other.phoneNumber) && 
               Objects.equals(friendlyName, other.friendlyName) && 
               Objects.equals(validationCode, other.validationCode) && 
               Objects.equals(callSid, other.callSid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountSid,
                            phoneNumber,
                            friendlyName,
                            validationCode,
                            callSid);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                          .add("accountSid", accountSid)
                          .add("phoneNumber", phoneNumber)
                          .add("friendlyName", friendlyName)
                          .add("validationCode", validationCode)
                          .add("callSid", callSid)
                          .toString();
    }
}