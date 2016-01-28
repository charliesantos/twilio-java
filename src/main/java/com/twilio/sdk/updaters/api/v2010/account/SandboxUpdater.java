package com.twilio.sdk.updaters.api.v2010.account;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.converters.Promoter;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.RestException;
import com.twilio.sdk.resources.api.v2010.account.Sandbox;
import com.twilio.sdk.updaters.Updater;

import java.net.URI;

public class SandboxUpdater extends Updater<Sandbox> {
    private final String accountSid;
    private URI voiceUrl;
    private HttpMethod voiceMethod;
    private URI smsUrl;
    private HttpMethod smsMethod;
    private URI statusCallback;
    private HttpMethod statusCallbackMethod;

    /**
     * Construct a new SandboxUpdater
     * 
     * @param accountSid The account_sid
     */
    public SandboxUpdater(final String accountSid) {
        this.accountSid = accountSid;
    }

    /**
     * The voice_url
     * 
     * @param voiceUrl The voice_url
     * @return this
     */
    public SandboxUpdater setVoiceUrl(final URI voiceUrl) {
        this.voiceUrl = voiceUrl;
        return this;
    }

    /**
     * The voice_url
     * 
     * @param voiceUrl The voice_url
     * @return this
     */
    public SandboxUpdater setVoiceUrl(final String voiceUrl) {
        return setVoiceUrl(Promoter.uriFromString(voiceUrl));
    }

    /**
     * The voice_method
     * 
     * @param voiceMethod The voice_method
     * @return this
     */
    public SandboxUpdater setVoiceMethod(final HttpMethod voiceMethod) {
        this.voiceMethod = voiceMethod;
        return this;
    }

    /**
     * The sms_url
     * 
     * @param smsUrl The sms_url
     * @return this
     */
    public SandboxUpdater setSmsUrl(final URI smsUrl) {
        this.smsUrl = smsUrl;
        return this;
    }

    /**
     * The sms_url
     * 
     * @param smsUrl The sms_url
     * @return this
     */
    public SandboxUpdater setSmsUrl(final String smsUrl) {
        return setSmsUrl(Promoter.uriFromString(smsUrl));
    }

    /**
     * The sms_method
     * 
     * @param smsMethod The sms_method
     * @return this
     */
    public SandboxUpdater setSmsMethod(final HttpMethod smsMethod) {
        this.smsMethod = smsMethod;
        return this;
    }

    /**
     * The status_callback
     * 
     * @param statusCallback The status_callback
     * @return this
     */
    public SandboxUpdater setStatusCallback(final URI statusCallback) {
        this.statusCallback = statusCallback;
        return this;
    }

    /**
     * The status_callback
     * 
     * @param statusCallback The status_callback
     * @return this
     */
    public SandboxUpdater setStatusCallback(final String statusCallback) {
        return setStatusCallback(Promoter.uriFromString(statusCallback));
    }

    /**
     * The status_callback_method
     * 
     * @param statusCallbackMethod The status_callback_method
     * @return this
     */
    public SandboxUpdater setStatusCallbackMethod(final HttpMethod statusCallbackMethod) {
        this.statusCallbackMethod = statusCallbackMethod;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the update
     * 
     * @param client TwilioRestClient with which to make the request
     * @return Updated Sandbox
     */
    @Override
    public Sandbox execute(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.POST,
            TwilioRestClient.Domains.API,
            "/2010-04-01/Accounts/" + this.accountSid + "/Sandbox.json",
            client.getAccountSid()
        );
        
        addPostParams(request);
        Response response = client.request(request);
        
        if (response == null) {
            throw new ApiConnectionException("Sandbox update failed: Unable to connect to server");
        } else if (response.getStatusCode() != TwilioRestClient.HTTP_STATUS_CODE_OK) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null)
                throw new ApiException("Server Error, no content");
            throw new ApiException(
                restException.getMessage(),
                restException.getCode(),
                restException.getMoreInfo(),
                restException.getStatus(),
                null
            );
        }
        
        return Sandbox.fromJson(response.getStream(), client.getObjectMapper());
    }

    /**
     * Add the requested post parameters to the Request
     * 
     * @param request Request to add post params to
     */
    private void addPostParams(final Request request) {
        if (voiceUrl != null) {
            request.addPostParam("VoiceUrl", voiceUrl.toString());
        }
        
        if (voiceMethod != null) {
            request.addPostParam("VoiceMethod", voiceMethod.toString());
        }
        
        if (smsUrl != null) {
            request.addPostParam("SmsUrl", smsUrl.toString());
        }
        
        if (smsMethod != null) {
            request.addPostParam("SmsMethod", smsMethod.toString());
        }
        
        if (statusCallback != null) {
            request.addPostParam("StatusCallback", statusCallback.toString());
        }
        
        if (statusCallbackMethod != null) {
            request.addPostParam("StatusCallbackMethod", statusCallbackMethod.toString());
        }
    }
}