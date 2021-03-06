/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.chat.v2.service.channel;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.twilio.base.Resource;
import com.twilio.converter.Converter;
import com.twilio.converter.DateConverter;
import com.twilio.converter.Promoter;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;
import lombok.ToString;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.time.ZonedDateTime;
import java.util.Map;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class Webhook extends Resource {
    private static final long serialVersionUID = 142993163058456L;

    public enum Type {
        WEBHOOK("webhook"),
        TRIGGER("trigger"),
        STUDIO("studio");

        private final String value;

        private Type(final String value) {
            this.value = value;
        }

        public String toString() {
            return value;
        }

        /**
         * Generate a Type from a string.
         * @param value string value
         * @return generated Type
         */
        @JsonCreator
        public static Type forValue(final String value) {
            return Promoter.enumFromString(value, Type.values());
        }
    }

    public enum Method {
        GET("GET"),
        POST("POST");

        private final String value;

        private Method(final String value) {
            this.value = value;
        }

        public String toString() {
            return value;
        }

        /**
         * Generate a Method from a string.
         * @param value string value
         * @return generated Method
         */
        @JsonCreator
        public static Method forValue(final String value) {
            return Promoter.enumFromString(value, Method.values());
        }
    }

    /**
     * Create a WebhookReader to execute read.
     *
     * @param pathServiceSid The SID of the Service to read the resources from
     * @param pathChannelSid The SID of the Channel the resources to read belong to
     * @return WebhookReader capable of executing the read
     */
    public static WebhookReader reader(final String pathServiceSid,
                                       final String pathChannelSid) {
        return new WebhookReader(pathServiceSid, pathChannelSid);
    }

    /**
     * Create a WebhookFetcher to execute fetch.
     *
     * @param pathServiceSid The SID of the Service with the Channel to fetch the
     *                       Webhook resource from
     * @param pathChannelSid The SID of the Channel the resource to fetch belongs to
     * @param pathSid The SID of the Channel Webhook resource to fetch
     * @return WebhookFetcher capable of executing the fetch
     */
    public static WebhookFetcher fetcher(final String pathServiceSid,
                                         final String pathChannelSid,
                                         final String pathSid) {
        return new WebhookFetcher(pathServiceSid, pathChannelSid, pathSid);
    }

    /**
     * Create a WebhookCreator to execute create.
     *
     * @param pathServiceSid The SID of the Service with the Channel to create the
     *                       resource under
     * @param pathChannelSid The SID of the Channel the new resource belongs to
     * @param type The type of webhook
     * @return WebhookCreator capable of executing the create
     */
    public static WebhookCreator creator(final String pathServiceSid,
                                         final String pathChannelSid,
                                         final Webhook.Type type) {
        return new WebhookCreator(pathServiceSid, pathChannelSid, type);
    }

    /**
     * Create a WebhookUpdater to execute update.
     *
     * @param pathServiceSid The SID of the Service with the Channel that has the
     *                       Webhook resource to update
     * @param pathChannelSid The SID of the Channel the resource to update belongs
     *                       to
     * @param pathSid The SID of the resource
     * @return WebhookUpdater capable of executing the update
     */
    public static WebhookUpdater updater(final String pathServiceSid,
                                         final String pathChannelSid,
                                         final String pathSid) {
        return new WebhookUpdater(pathServiceSid, pathChannelSid, pathSid);
    }

    /**
     * Create a WebhookDeleter to execute delete.
     *
     * @param pathServiceSid The SID of the Service with the Channel to delete the
     *                       Webhook resource from
     * @param pathChannelSid The SID of the channel the resource to delete belongs
     *                       to
     * @param pathSid The SID of the Channel Webhook resource to delete
     * @return WebhookDeleter capable of executing the delete
     */
    public static WebhookDeleter deleter(final String pathServiceSid,
                                         final String pathChannelSid,
                                         final String pathSid) {
        return new WebhookDeleter(pathServiceSid, pathChannelSid, pathSid);
    }

    /**
     * Converts a JSON String into a Webhook object using the provided ObjectMapper.
     *
     * @param json Raw JSON String
     * @param objectMapper Jackson ObjectMapper
     * @return Webhook object represented by the provided JSON
     */
    public static Webhook fromJson(final String json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, Webhook.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    /**
     * Converts a JSON InputStream into a Webhook object using the provided
     * ObjectMapper.
     *
     * @param json Raw JSON InputStream
     * @param objectMapper Jackson ObjectMapper
     * @return Webhook object represented by the provided JSON
     */
    public static Webhook fromJson(final InputStream json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, Webhook.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    private final String sid;
    private final String accountSid;
    private final String serviceSid;
    private final String channelSid;
    private final String type;
    private final URI url;
    private final Map<String, Object> configuration;
    private final ZonedDateTime dateCreated;
    private final ZonedDateTime dateUpdated;

    @JsonCreator
    private Webhook(@JsonProperty("sid")
                    final String sid,
                    @JsonProperty("account_sid")
                    final String accountSid,
                    @JsonProperty("service_sid")
                    final String serviceSid,
                    @JsonProperty("channel_sid")
                    final String channelSid,
                    @JsonProperty("type")
                    final String type,
                    @JsonProperty("url")
                    final URI url,
                    @JsonProperty("configuration")
                    final Map<String, Object> configuration,
                    @JsonProperty("date_created")
                    final String dateCreated,
                    @JsonProperty("date_updated")
                    final String dateUpdated) {
        this.sid = sid;
        this.accountSid = accountSid;
        this.serviceSid = serviceSid;
        this.channelSid = channelSid;
        this.type = type;
        this.url = url;
        this.configuration = configuration;
        this.dateCreated = DateConverter.iso8601DateTimeFromString(dateCreated);
        this.dateUpdated = DateConverter.iso8601DateTimeFromString(dateUpdated);
    }

    /**
     * Returns The unique string that identifies the resource.
     *
     * @return The unique string that identifies the resource
     */
    public final String getSid() {
        return this.sid;
    }

    /**
     * Returns The SID of the Account that created the resource.
     *
     * @return The SID of the Account that created the resource
     */
    public final String getAccountSid() {
        return this.accountSid;
    }

    /**
     * Returns The SID of the Service that the Channel Webhook resource is
     * associated with.
     *
     * @return The SID of the Service that the Channel Webhook resource is
     *         associated with
     */
    public final String getServiceSid() {
        return this.serviceSid;
    }

    /**
     * Returns The SID of the Channel the Channel Webhook resource belongs to.
     *
     * @return The SID of the Channel the Channel Webhook resource belongs to
     */
    public final String getChannelSid() {
        return this.channelSid;
    }

    /**
     * Returns The type of webhook.
     *
     * @return The type of webhook
     */
    public final String getType() {
        return this.type;
    }

    /**
     * Returns The absolute URL of the Channel Webhook resource.
     *
     * @return The absolute URL of the Channel Webhook resource
     */
    public final URI getUrl() {
        return this.url;
    }

    /**
     * Returns The JSON string that describes the configuration object for the
     * channel webhook.
     *
     * @return The JSON string that describes the configuration object for the
     *         channel webhook
     */
    public final Map<String, Object> getConfiguration() {
        return this.configuration;
    }

    /**
     * Returns The ISO 8601 date and time in GMT when the resource was created.
     *
     * @return The ISO 8601 date and time in GMT when the resource was created
     */
    public final ZonedDateTime getDateCreated() {
        return this.dateCreated;
    }

    /**
     * Returns The ISO 8601 date and time in GMT when the resource was last updated.
     *
     * @return The ISO 8601 date and time in GMT when the resource was last updated
     */
    public final ZonedDateTime getDateUpdated() {
        return this.dateUpdated;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Webhook other = (Webhook) o;

        return Objects.equals(sid, other.sid) &&
               Objects.equals(accountSid, other.accountSid) &&
               Objects.equals(serviceSid, other.serviceSid) &&
               Objects.equals(channelSid, other.channelSid) &&
               Objects.equals(type, other.type) &&
               Objects.equals(url, other.url) &&
               Objects.equals(configuration, other.configuration) &&
               Objects.equals(dateCreated, other.dateCreated) &&
               Objects.equals(dateUpdated, other.dateUpdated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sid,
                            accountSid,
                            serviceSid,
                            channelSid,
                            type,
                            url,
                            configuration,
                            dateCreated,
                            dateUpdated);
    }
}