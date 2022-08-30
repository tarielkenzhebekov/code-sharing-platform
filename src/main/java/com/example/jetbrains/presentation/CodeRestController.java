package com.example.jetbrains.presentation;

import com.example.jetbrains.businesslayer.Code;
import com.example.jetbrains.businesslayer.CodeService;

import java.util.Map;
import java.util.HashMap;
import java.util.Optional;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/code")
public class CodeRestController {
    private final CodeService codeService;

    @Autowired
    public CodeRestController(CodeService codeService) {
        this.codeService = codeService;
    }

    @PostMapping("/new")
    public Map<String, UUID> createSnippet(@RequestBody Code snippet) {
        UUID uuid = codeService.saveSnippet(snippet);
        Map<String, UUID> map = new HashMap<>();
        map.put("id", uuid);
        return map;
    }

    @GetMapping("/{uuidStr}")
    public Object getNthSnippet(@PathVariable String uuidStr) {
        final String notFound = "404 Not Found";
        UUID uuid;

        try {
            uuid = UUID.fromString(uuidStr);
        } 
        catch (Exception e) {
            return notFound;
        }

        Optional<Code> opt = codeService.findByUuid(uuid);

        if (opt.isPresent()) {
            Code snippet = opt.get();

            if (snippet.isTimeRestricted()) {
                LocalDateTime now = LocalDateTime.now();
                long seconds = now.until(
                    snippet.getEndTime(), 
                    ChronoUnit.SECONDS
                );
                if (seconds > 0) {
                    snippet.setTime(seconds);
                    codeService.updateSnippet(snippet);
                }
                else {
                    return notFound;
                }
            }

            if (snippet.isViewsRestricted()) {
                if (snippet.getViews() > 0) {
                    snippet.setViews(snippet.getViews() - 1);
                    codeService.updateSnippet(snippet);
                }
                else {
                    return notFound;
                }
            }

            return snippet;
        } 

        return notFound;
    }

    @GetMapping("/latest")
    public List<Code> getLatestSnippets() {
        return codeService.getLatestSnippets();
    }
}
