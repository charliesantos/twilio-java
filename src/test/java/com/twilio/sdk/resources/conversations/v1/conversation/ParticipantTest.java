package com.twilio.sdk.resources.conversations.v1.conversation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.twilio.sdk.Twilio;
import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.converters.MarshalConverter;
import com.twilio.sdk.exceptions.TwilioException;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import mockit.Mocked;
import mockit.NonStrictExpectations;
import org.junit.Before;
import org.junit.Test;

import java.net.URI;

import static com.twilio.sdk.TwilioTest.serialize;
import static org.junit.Assert.*;

public class ParticipantTest {
    @Mocked
    private TwilioRestClient twilioRestClient;

    @Before
    public void setUp() throws Exception {
        Twilio.init("AC123", "AUTH TOKEN");
    }

    @Test
    public void testReadRequest() {
        new NonStrictExpectations() {{
            Request request = new Request(HttpMethod.GET,
                                          TwilioRestClient.Domains.CONVERSATIONS,
                                          "/v1/Conversations/CVaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Participants",
                                          "AC123");
            
            request.addQueryParam("PageSize", "50");
            twilioRestClient.request(request);
            times = 1;
            result = new Response("", 500);
            twilioRestClient.getAccountSid();
            result = "AC123";
        }};
        
        try {
            Participant.read("CVaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa").execute();
            fail("Expected TwilioException to be thrown for 500");
        } catch (TwilioException e) {}
    }

    @Test
    public void testReadFullResponse() {
        new NonStrictExpectations() {{
            twilioRestClient.request((Request) any);
            result = new Response("{\"meta\": {\"first_page_url\": \"https://conversations.twilio.com/v1/Conversations/CVaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Participants?PageSize=50&Page=0\",\"key\": \"participants\",\"next_page_url\": null,\"page\": 0,\"page_size\": 50,\"previous_page_url\": null,\"url\": \"https://conversations.twilio.com/v1/Conversations/CVaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Participants?PageSize=50&Page=0\"},\"participants\": [{\"account_sid\": \"ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"address\": \"torkel2@ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa.endpoint.twilio.com\",\"conversation_sid\": \"CVaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"date_created\": \"2015-05-13T23:03:12Z\",\"duration\": 685,\"end_time\": \"2015-05-13T23:14:40Z\",\"sid\": \"PAaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"start_time\": \"2015-05-13T23:03:15Z\",\"status\": \"disconnected\",\"url\": \"https://conversations.twilio.com/v1/Conversations/CVaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Participants/PAaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\"}]}", TwilioRestClient.HTTP_STATUS_CODE_OK);
            twilioRestClient.getObjectMapper();
            result = new ObjectMapper();
        }};
        
        assertNotNull(Participant.read("CVaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa").execute());
    }

    @Test
    public void testReadEmptyResponse() {
        new NonStrictExpectations() {{
            twilioRestClient.request((Request) any);
            result = new Response("{\"meta\": {\"first_page_url\": \"https://conversations.twilio.com/v1/Conversations/CVaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Participants?PageSize=50&Page=0\",\"key\": \"participants\",\"next_page_url\": null,\"page\": 0,\"page_size\": 50,\"previous_page_url\": null,\"url\": \"https://conversations.twilio.com/v1/Conversations/CVaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Participants?PageSize=50&Page=0\"},\"participants\": []}", TwilioRestClient.HTTP_STATUS_CODE_OK);
            twilioRestClient.getObjectMapper();
            result = new ObjectMapper();
        }};
        
        assertNotNull(Participant.read("CVaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa").execute());
    }

    @Test
    public void testCreateRequest() {
                    new NonStrictExpectations() {{
                        Request request = new Request(HttpMethod.POST,
                                                      TwilioRestClient.Domains.CONVERSATIONS,
                                                      "/v1/Conversations/CVaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Participants",
                                                      "AC123");
                        request.addPostParam("To", serialize(new com.twilio.types.PhoneNumber("+123456789")));
        request.addPostParam("From", serialize(new com.twilio.types.PhoneNumber("+987654321")));
                        
                        twilioRestClient.request(request);
                        times = 1;
                        result = new Response("", 500);
                        twilioRestClient.getAccountSid();
                        result = "AC123";
                    }};
        
        try {
            Participant.create("CVaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", new com.twilio.types.PhoneNumber("+123456789"), new com.twilio.types.PhoneNumber("+987654321")).execute();
            fail("Expected TwilioException to be thrown for 500");
        } catch (TwilioException e) {}
    }

    @Test
    public void testCreateResponse() {
        new NonStrictExpectations() {{
            twilioRestClient.request((Request) any);
            result = new Response("{\"account_sid\": \"ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"address\": \"torkel2@ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa.endpoint.twilio.com\",\"conversation_sid\": \"CVaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"date_created\": \"2015-05-13T23:03:12Z\",\"duration\": 685,\"end_time\": \"2015-05-13T23:14:40Z\",\"sid\": \"PAaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"start_time\": \"2015-05-13T23:03:15Z\",\"status\": \"disconnected\",\"url\": \"https://conversations.twilio.com/v1/Conversations/CVaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Participants/PAaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\"}", TwilioRestClient.HTTP_STATUS_CODE_CREATED);
            twilioRestClient.getObjectMapper();
            result = new ObjectMapper();
        }};
        
        Participant.create("CVaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", new com.twilio.types.PhoneNumber("+123456789"), new com.twilio.types.PhoneNumber("+987654321")).execute();
    }

    @Test
    public void testFetchRequest() {
        new NonStrictExpectations() {{
            Request request = new Request(HttpMethod.GET,
                                          TwilioRestClient.Domains.CONVERSATIONS,
                                          "/v1/Conversations/CVaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Participants/PAaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
                                          "AC123");
            
            
            twilioRestClient.request(request);
            times = 1;
            result = new Response("", 500);
            twilioRestClient.getAccountSid();
            result = "AC123";
        }};
        
        try {
            Participant.fetch("CVaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "PAaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa").execute();
            fail("Expected TwilioException to be thrown for 500");
        } catch (TwilioException e) {}
    }

    @Test
    public void testFetchResponse() {
        new NonStrictExpectations() {{
            twilioRestClient.request((Request) any);
            result = new Response("{\"account_sid\": \"ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"address\": \"torkel2@ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa.endpoint.twilio.com\",\"conversation_sid\": \"CVaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"date_created\": \"2015-05-13T23:03:12Z\",\"duration\": 685,\"end_time\": \"2015-05-13T23:14:40Z\",\"sid\": \"PAaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"start_time\": \"2015-05-13T23:03:15Z\",\"status\": \"disconnected\",\"url\": \"https://conversations.twilio.com/v1/Conversations/CVaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Participants/PAaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\"}", TwilioRestClient.HTTP_STATUS_CODE_OK);
            twilioRestClient.getObjectMapper();
            result = new ObjectMapper();
        }};
        
        assertNotNull(Participant.fetch("CVaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "PAaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa").execute());
    }
}