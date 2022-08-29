package com.example.jetbrains.presentation;

import com.example.jetbrains.businesslayer.Code;
import com.example.jetbrains.businesslayer.CodeService;

import java.util.Map;
import java.util.HashMap;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@RestController
@RequestMapping(path = "/api/code")
public class CodeRestController {
    private final CodeService codeService;

    @Autowired
    public CodeRestController(CodeService codeService) {
        this.codeService = codeService;
    }

    @GetMapping("/{n}")
    public Code getNthSnippet(@PathVariable Long n) {
        Optional<Code> opt = codeService.findById(n);
        if (opt.isPresent()) {
            return opt.get();
        }
        return new Code(); //
    }

    @PostMapping("/new")
    public Map<String, String> createSnippet(@RequestBody Code snippet) {
        long id = codeService.saveSnippet(snippet);
        Map<String, String> map = new HashMap<>();
        map.put("id", String.valueOf(id));
        return map;
    }

    @GetMapping("/latest")
    public Iterable<Code> getLatestSnippets() {
        return codeService.getLatestSnippets();
    }
}
