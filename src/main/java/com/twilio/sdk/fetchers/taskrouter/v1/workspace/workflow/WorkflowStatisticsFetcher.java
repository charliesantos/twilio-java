package com.twilio.sdk.fetchers.taskrouter.v1.workspace.workflow;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.converters.MarshalConverter;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.fetchers.Fetcher;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.RestException;
import com.twilio.sdk.resources.taskrouter.v1.workspace.workflow.WorkflowStatistics;
import org.joda.time.DateTime;

public class WorkflowStatisticsFetcher extends Fetcher<WorkflowStatistics> {
    private final String workspaceSid;
    private final String workflowSid;
    private Integer minutes;
    private DateTime startDate;
    private DateTime endDate;

    /**
     * Construct a new WorkflowStatisticsFetcher
     * 
     * @param workspaceSid The workspace_sid
     * @param workflowSid The workflow_sid
     */
    public WorkflowStatisticsFetcher(final String workspaceSid, final String workflowSid) {
        this.workspaceSid = workspaceSid;
        this.workflowSid = workflowSid;
    }

    /**
     * The minutes
     * 
     * @param minutes The minutes
     * @return this
     */
    public WorkflowStatisticsFetcher setMinutes(final Integer minutes) {
        this.minutes = minutes;
        return this;
    }

    /**
     * The start_date
     * 
     * @param startDate The start_date
     * @return this
     */
    public WorkflowStatisticsFetcher setStartDate(final DateTime startDate) {
        this.startDate = startDate;
        return this;
    }

    /**
     * The end_date
     * 
     * @param endDate The end_date
     * @return this
     */
    public WorkflowStatisticsFetcher setEndDate(final DateTime endDate) {
        this.endDate = endDate;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the fetch
     * 
     * @param client TwilioRestClient with which to make the request
     * @return Fetched WorkflowStatistics
     */
    @Override
    public WorkflowStatistics execute(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            TwilioRestClient.Domains.TASKROUTER,
            "/v1/Workspaces/" + this.workspaceSid + "/Workflows/" + this.workflowSid + "/Statistics",
            client.getAccountSid()
        );
        
        Response response = client.request(request);
        
        if (response == null) {
            throw new ApiConnectionException("WorkflowStatistics fetch failed: Unable to connect to server");
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
        
        return WorkflowStatistics.fromJson(response.getStream(), client.getObjectMapper());
    }
}