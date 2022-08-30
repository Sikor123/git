package com.bs.git.repos;

import com.bs.git.httpclient.GitBranchesClient;
import com.bs.git.httpclient.GitReposClient;
import com.bs.git.repos.dto.BranchDto;
import com.bs.git.repos.dto.ReposDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ReposService {
    private final GitReposClient reposClient;
    private final GitBranchesClient branchesClient;

    public List<ReposDto> getRepos(String username) {
        return reposClient.getGitRepos(username).parallelStream()
                .filter(repo -> !repo.isFork())
                .map(ReposDto::map)
                .map(this::fetchGitBranches)
                .collect(Collectors.toList());
    }

    private ReposDto fetchGitBranches(ReposDto reposDto) {
        reposDto.setBranches(branchesClient.getBranches(reposDto.getOwnerLogin(), reposDto.getRepositoryName()).stream()
                .map(BranchDto::map)
                .collect(Collectors.toList()));
        return reposDto;
    }
}
