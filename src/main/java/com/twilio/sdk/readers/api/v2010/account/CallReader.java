package com.twilio.sdk.readers.api.v2010.account;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.readers.Reader;
import com.twilio.sdk.resources.Page;
import com.twilio.sdk.resources.ResourceSet;
import com.twilio.sdk.resources.RestException;
import com.twilio.sdk.resources.api.v2010.account.Call;

public class CallReader extends Reader<Call> {
    private final String accountSid;
    private com.twilio.types.PhoneNumber to;
    private com.twilio.types.PhoneNumber from;
    private String parentCallSid;
    private Call.Status status;
    private String startTime;
    private String endTime;

    /**
     * Construct a new CallReader
     * 
     * @param accountSid The account_sid
     */
    public CallReader(final String accountSid) {
        this.accountSid = accountSid;
    }

    /**
     * Only show calls to this phone number or Client identifier
     * 
     * @param to Phone number or Client identifier to filter `to` on
     * @return this
     */
    public CallReader byTo(final com.twilio.types.PhoneNumber to) {
        this.to = to;
        return this;
    }

    /**
     * Only show calls from this phone number or Client identifier
     * 
     * @param from Phone number or Client identifier to filter `from` on
     * @return this
     */
    public CallReader byFrom(final com.twilio.types.PhoneNumber from) {
        this.from = from;
        return this;
    }

    /**
     * Only show calls spawned by the call with this Sid
     * 
     * @param parentCallSid Parent Call Sid to filter on
     * @return this
     */
    public CallReader byParentCallSid(final String parentCallSid) {
        this.parentCallSid = parentCallSid;
        return this;
    }

    /**
     * Only show calls currently in this status
     * 
     * @param status Status to filter on
     * @return this
     */
    public CallReader byStatus(final Call.Status status) {
        this.status = status;
        return this;
    }

    /**
     * Only show calls that started on this date
     * 
     * @param startTime StartTime to filter on
     * @return this
     */
    public CallReader byStartTime(final String startTime) {
        this.startTime = startTime;
        return this;
    }

    /**
     * Only show call that ended on this date
     * 
     * @param endTime EndTime to filter on
     * @return this
     */
    public CallReader byEndTime(final String endTime) {
        this.endTime = endTime;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the read
     * 
     * @param client TwilioRestClient with which to make the request
     * @return Call ResourceSet
     */
    @Override
    public ResourceSet<Call> execute(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            TwilioRestClient.Domains.API,
            "/2010-04-01/Accounts/" + this.accountSid + "/Calls.json",
            client.getAccountSid()
        );
        
        addQueryParams(request);
        
        Page<Call> page = pageForRequest(client, request);
        
        return new ResourceSet<>(this, client, page);
    }

    /**
     * Retrieve the next page from the Twilio API
     * 
     * @param nextPageUri URI from which to retrieve the next page
     * @param client TwilioRestClient with which to make the request
     * @return Next Page
     */
    @Override
    public Page<Call> nextPage(final String nextPageUri, final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            nextPageUri,
            client.getAccountSid()
        );
        return pageForRequest(client, request);
    }

    /**
     * Generate a Page of Call Resources for a given request
     * 
     * @param client TwilioRestClient with which to make the request
     * @param request Request to generate a page for
     * @return Page for the Request
     */
    protected Page<Call> pageForRequest(final TwilioRestClient client, final Request request) {
        Response response = client.request(request);
        
        if (response == null) {
            throw new ApiConnectionException("Call read failed: Unable to connect to server");
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
        
        Page<Call> result = new Page<>();
        result.deserialize("calls", response.getContent(), Call.class, client.getObjectMapper());
        
        return result;
    }

    /**
     * Add the requested query string arguments to the Request
     * 
     * @param request Request to add query string arguments to
     */
    private void addQueryParams(final Request request) {
        if (to != null) {
            request.addQueryParam("To", to.toString());
        }
        
        if (from != null) {
            request.addQueryParam("From", from.toString());
        }
        
        if (parentCallSid != null) {
            request.addQueryParam("ParentCallSid", parentCallSid);
        }
        
        if (status != null) {
            request.addQueryParam("Status", status.toString());
        }
        
        if (startTime != null) {
            request.addQueryParam("StartTime", startTime);
        }
        
        if (endTime != null) {
            request.addQueryParam("EndTime", endTime);
        }
        
        request.addQueryParam("PageSize", Integer.toString(getPageSize()));
    }
}