package com.bs.git.httpclient.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;

@Data
public class GitRepoDto {
    private String name;
    private boolean fork;
    private String username;

    @JsonProperty("owner")
    private void unpackUsernameFromNestedObject(Map<String, String> map) {
        username = map.get("login");
    }

}
