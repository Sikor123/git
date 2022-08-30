package com.bs.git.repos.dto;

import com.bs.git.httpclient.dto.GitRepoDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
public class ReposDto {
    private String repositoryName;
    private String ownerLogin;
    private List<BranchDto> branches;

    public static ReposDto map(GitRepoDto gitRepoDto) {
        return ReposDto.builder()
                .repositoryName(gitRepoDto.getName())
                .ownerLogin(gitRepoDto.getUsername())
                .build();
    }
}
