package com.example.blog.services;
/*package com.example.blog.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TabNewsService {
    private static final String API_URL = "https://www.tabnews.com.br/api/v1";
    private String email = "alexanderalmeida3040@gmail.com";
    private String password = "Alex.2003";
    private final RestTemplate restTemplate;
    private String sessionId;

    public TabNewsService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        getSessionId(email, password);
    }

    public void getSessionId(String email, String password){
        String endpoint = API_URL + "/sessions";

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode authJson = objectMapper.createObjectNode();

        authJson.put("email", email);
        authJson.put("password", password);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> requestEntity = new HttpEntity<>(authJson.toString());

        ResponseEntity<String> responseEntity = restTemplate.exchange(endpoint, HttpMethod.POST, requestEntity, String.class);

        try{
            JsonNode responseJson = objectMapper.readTree(responseEntity.getBody());
            this.sessionId = responseJson.get("id").asText();
        }catch (Exception e){
            throw new RuntimeException("Error getting sessionId", e);
        }
    }

    public void sendPost(String title, String content){
        
    }
}*/