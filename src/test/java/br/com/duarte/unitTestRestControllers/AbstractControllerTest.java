package br.com.duarte.unitTestRestControllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterAll;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.util.ObjectUtils.isEmpty;

public abstract class AbstractControllerTest {

    protected static final int GET = 1;
    protected static final int POST = 2;
    protected static final int DELETE = 3;
    protected static final int PUT = 4;

    protected static Object controller;
    protected static MockMvc mockMvc;
    protected static ObjectMapper mapper;


    public ResultActions getAndExpectedStatusOk(String url) throws Exception {
        return requestAndExpected(get(url), status().isOk());
    }

    public ResultActions getAndExpectedStatusNotFound(String url) throws Exception {
        return requestAndExpected(get(url), status().isNotFound());
    }

    public ResultActions postAndExpectedStatusCreated(String url, Object body) throws Exception {
        return requestAndExpected(post(url, body), status().isCreated());
    }

    public ResultActions postAndExpectedStatusBadRequest(String url, Object body) throws Exception {
        return requestAndExpected(post(url, body), status().isBadRequest());
    }

    public ResultActions requestAndExpected(MockHttpServletRequestBuilder requestBuilder, ResultMatcher matcher) throws Exception {
        return mockMvc.perform(requestBuilder)
                .andExpect(matcher);
    }

    public MockHttpServletRequestBuilder get(String url) throws JsonProcessingException {
        return request(url, GET, null);
    }

    public MockHttpServletRequestBuilder post(String url, Object body) throws JsonProcessingException {
        return request(url, POST, body);
    }

    public MockHttpServletRequestBuilder delete(String url) throws JsonProcessingException {
        return request(url, DELETE, null);
    }

    public MockHttpServletRequestBuilder put(String url, Object body) throws JsonProcessingException {
        return request(url, PUT, body);
    }

    public MockHttpServletRequestBuilder request(String url, int typeRequest, Object body) throws JsonProcessingException {
        MockHttpServletRequestBuilder result = null;

        switch (typeRequest) {
            case GET:
                result = MockMvcRequestBuilders.get(url);
                break;
            case POST:
                result = MockMvcRequestBuilders.post(url);
                break;
            case DELETE:
                result = MockMvcRequestBuilders.delete(url);
                break;
            case PUT:
                result = MockMvcRequestBuilders.put(url);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + typeRequest);
        }

        if (!isEmpty(body)) {
            result.content(toJSON(body));
        }

        result.contentType(MediaType.APPLICATION_JSON);
        return result;
    }

    public String toJSON(Object object) throws JsonProcessingException {
        return mapper.writeValueAsString(object);
    }

    @AfterAll
    public static void afterAll() {
        controller = null;
        mockMvc = null;
        mapper = null;
    }
}
