package com.example.jetbrains.presentation;

import com.example.jetbrains.businesslayer.Code;
import com.example.jetbrains.businesslayer.CodeService;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping(path = "/code")
public class CodeController {
    private final CodeService codeService;

    @Autowired
    public CodeController(CodeService codeService) {
        this.codeService = codeService;
    }

    @GetMapping("/{n}")
    public String getNthSnippet(@PathVariable Long n, Model model) {
        Optional<Code> opt = codeService.findById(n);
        model.addAttribute("snippet", opt.get());
        return "code_snippet";
    }

    @GetMapping("/new")
    public String createSnippet() {
        return "create_snippet";
    }

    @GetMapping("/latest")
    public String getLatestSnippets(Model model) {
        model.addAttribute("latest_snippets", codeService.getLatestSnippets());
        return "latest_snippets";
    }
}
