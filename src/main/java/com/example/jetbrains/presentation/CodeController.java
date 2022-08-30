package com.example.jetbrains.presentation;

import com.example.jetbrains.businesslayer.Code;
import com.example.jetbrains.businesslayer.CodeService;

import java.util.Optional;
import java.util.UUID;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

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

    @GetMapping("/{uuidStr}")
    public Object getNthSnippet(@PathVariable String uuidStr, Model model) {
        model.addAttribute("found", false);
        model.addAttribute("time_hidden", false);
        model.addAttribute("views_hidden", false);

        UUID uuid;

        try {
            uuid = UUID.fromString(uuidStr);
        } 
        catch (Exception e) {
            return "code_snippet";
        }

        Optional<Code> opt = codeService.findByUuid(uuid);

        if (opt.isPresent() && !opt.get().isDeleted()) {
            Code snippet = opt.get();
            
            if (snippet.getTime() != 0) {
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
                    snippet.setDeleted(true);
                    codeService.updateSnippet(snippet);
                    return "code_snippet";
                }
            }
            else {
                model.addAttribute("time_hidden", true);
            }

            if (snippet.getViews() != 0) {
                snippet.setViews(snippet.getViews() - 1);
                if (snippet.getViews() > 0) {
                    codeService.updateSnippet(snippet);
                }
                else {
                    snippet.setDeleted(true);
                    codeService.updateSnippet(snippet);
                }
            }
            else {
                model.addAttribute("views_hidden", true);
            }

            model.addAttribute("found", true);
            model.addAttribute("snippet", snippet);
            return "code_snippet";
        } 

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
