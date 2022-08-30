package com.bs.git.httpclient;

import com.bs.git.httpclient.dto.GitRepoDto;
import com.bs.git.httpclient.exceptions.NotFoundException;
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
public class GitReposClient {

    @Value("${reposUri}")
    private String gitUri;

    private final HttpClient client;
    private final ObjectMapper objectMapper;

    public GitReposClient(HttpClient client, ObjectMapper objectMapper) {
        this.client = client;
        this.objectMapper = objectMapper;
    }

    public List<GitRepoDto> getGitRepos(String username) {
        URI uri = UriComponentsBuilder
                .fromUriString(gitUri)
                .build(username);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .header("Accept", "application/json")
                .GET()
                .build();
        HttpResponse<String> response;
        List<GitRepoDto> reposDtoList;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 404) {
                throw new NotFoundException();
            }
            reposDtoList = objectMapper.readValue(response.body(), new TypeReference<List<GitRepoDto>>() {
            });
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return reposDtoList;
    }
}
