package com.bs.git.httpclient.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;

@Data
public class GitBranchDto {

    private String name;
    private String sha;

    @JsonProperty("commit")
    private void unpackUsernameFromNestedObject(Map<String, String> map) {
        sha = map.get("sha");
    }
}
