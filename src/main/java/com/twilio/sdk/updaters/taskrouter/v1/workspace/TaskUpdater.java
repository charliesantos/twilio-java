package com.twilio.sdk.updaters.taskrouter.v1.workspace;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.RestException;
import com.twilio.sdk.resources.taskrouter.v1.workspace.Task;
import com.twilio.sdk.updaters.Updater;

public class TaskUpdater extends Updater<Task> {
    private final String workspaceSid;
    private final String sid;
    private String attributes;
    private Task.Status assignmentStatus;
    private String reason;
    private Integer priority;

    /**
     * Construct a new TaskUpdater
     * 
     * @param workspaceSid The workspace_sid
     * @param sid The sid
     */
    public TaskUpdater(final String workspaceSid, final String sid) {
        this.workspaceSid = workspaceSid;
        this.sid = sid;
    }

    /**
     * The attributes
     * 
     * @param attributes The attributes
     * @return this
     */
    public TaskUpdater setAttributes(final String attributes) {
        this.attributes = attributes;
        return this;
    }

    /**
     * The assignment_status
     * 
     * @param assignmentStatus The assignment_status
     * @return this
     */
    public TaskUpdater setAssignmentStatus(final Task.Status assignmentStatus) {
        this.assignmentStatus = assignmentStatus;
        return this;
    }

    /**
     * The reason
     * 
     * @param reason The reason
     * @return this
     */
    public TaskUpdater setReason(final String reason) {
        this.reason = reason;
        return this;
    }

    /**
     * The priority
     * 
     * @param priority The priority
     * @return this
     */
    public TaskUpdater setPriority(final Integer priority) {
        this.priority = priority;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the update
     * 
     * @param client TwilioRestClient with which to make the request
     * @return Updated Task
     */
    @Override
    public Task execute(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.POST,
            TwilioRestClient.Domains.TASKROUTER,
            "/v1/Workspaces/" + this.workspaceSid + "/Tasks/" + this.sid + "",
            client.getAccountSid()
        );
        
        addPostParams(request);
        Response response = client.request(request);
        
        if (response == null) {
            throw new ApiConnectionException("Task update failed: Unable to connect to server");
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
        
        return Task.fromJson(response.getStream(), client.getObjectMapper());
    }

    /**
     * Add the requested post parameters to the Request
     * 
     * @param request Request to add post params to
     */
    private void addPostParams(final Request request) {
        if (attributes != null) {
            request.addPostParam("Attributes", attributes);
        }
        
        if (assignmentStatus != null) {
            request.addPostParam("AssignmentStatus", assignmentStatus.toString());
        }
        
        if (reason != null) {
            request.addPostParam("Reason", reason);
        }
        
        if (priority != null) {
            request.addPostParam("Priority", priority.toString());
        }
    }
}