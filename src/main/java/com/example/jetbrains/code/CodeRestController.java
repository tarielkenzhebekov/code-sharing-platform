package com.example.jetbrains.code;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@RestController
@RequestMapping(path = "/api/code", produces = "application/json")
public class CodeRestController {
    private CodeRepository repository;

    @Autowired
    public CodeRestController(CodeRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/{N}")
    public Code getNthCode(@PathVariable Integer N) {
        return repository.get(N);
    }

    @PostMapping("/new")
    public String newCode(@RequestBody Code code) {
        int id = this.repository.add(code.getCode());

        return "{ \"id\" : \"" + id + "\" }";
    }

    @GetMapping("/latest")
    public List<Code> getLatestCodes() {
        return repository.getLatestCodes();
    }
}
