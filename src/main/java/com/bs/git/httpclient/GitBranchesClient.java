package com.bs.git.httpclient;

import com.bs.git.httpclient.dto.GitBranchDto;
import com.bs.git.httpclient.dto.GitRepoDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@Service
public class GitBranchesClient {

    @Value("${branchesUri}")
    private String gitUri;

    private final HttpClient client;
    private final ObjectMapper objectMapper;

    public GitBranchesClient(HttpClient client, ObjectMapper objectMapper) {
        this.client = client;
        this.objectMapper = objectMapper;
    }

    public List<GitBranchDto> getBranches(String username, String branchName){
        URI uri = UriComponentsBuilder
                .fromUriString(gitUri)
                .build(username, branchName);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .header("Accept", "application/json")
                .GET()
                .build();
        HttpResponse<String> response;
        List<GitBranchDto> branchDtoList;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            branchDtoList = objectMapper.readValue(response.body(), new TypeReference<List<GitBranchDto>>() {
            });
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return branchDtoList;
    }
}
