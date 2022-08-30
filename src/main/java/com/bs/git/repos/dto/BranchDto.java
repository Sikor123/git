package com.bs.git.repos.dto;

import com.bs.git.httpclient.dto.GitBranchDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class BranchDto {
    private String name;
    private String lastCommitSha;

    public static BranchDto map(GitBranchDto gitBranchDto){
        return BranchDto.builder()
                .name(gitBranchDto.getName())
                .lastCommitSha(gitBranchDto.getSha())
                .build();
    }
}
