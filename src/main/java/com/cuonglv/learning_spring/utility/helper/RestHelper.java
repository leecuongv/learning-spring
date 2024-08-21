package com.cuonglv.learning_spring.utility.helper;

import com.google.gson.Gson;
import com.cuonglv.learning_spring.utility.model.msg.response.ResponseMessage;
import java.lang.reflect.Type;
import java.util.Arrays;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestHelper {

  public <T> ResponseMessage<T> postForObject(HttpEntity<?> request, String uri, Type responseType, String requestId) {
    HttpHeaders headers = new HttpHeaders();
    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
    headers.set("RequestID", requestId);

    RestTemplate restTemplate = new RestTemplate();
    String response = restTemplate.postForObject(uri, request, String.class);

    return new Gson().fromJson(response, responseType);
  }
}
