package com.example.jetbrains.code;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping(path = "/code")
public class CodeController {
    private CodeRepository repository;

    @Autowired
    public CodeController(CodeRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/{n}")
    public String getSnippet(@PathVariable Integer n, Model model) {
        Code temp = repository.get(n);
        model.addAttribute("code", temp.getCode());
        model.addAttribute("date", temp.getDate());
        return "code_snippet";
    }

    @GetMapping("/new")
    public String setSnippet() {
        return "create_snippet";
    }

    @GetMapping("/latest")
    public String getLatestCodes(Model model) {
        model.addAttribute("latest_codes", repository.getLatestCodes());
        return "latest";
    }
}
