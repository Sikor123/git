package com.bs.git.repos;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "repos",
        produces = {APPLICATION_JSON_VALUE}
)
@AllArgsConstructor
public class ReposController {

    private ReposService reposService;

    @GetMapping("/{username}")
    public ResponseEntity<Object> getAll(@PathVariable String username, @RequestHeader(HttpHeaders.ACCEPT) String accept) {
        return ResponseEntity.ok(reposService.getRepos(username));
    }
}
