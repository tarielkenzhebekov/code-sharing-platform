package com.example.jetbrains.businesslayer;

import com.example.jetbrains.businesslayer.Code;
import com.example.jetbrains.persistence.CodeRepository;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class CodeService {
    private final DateTimeFormatter formatter = 
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private final CodeRepository codeRepository;

    @Autowired
    public CodeService(CodeRepository codeRepository) {
        this.codeRepository = codeRepository;
    }

    public Optional<Code> findById(Long id) {
        Optional<Code> opt = codeRepository.findById(id);
        return opt;
    }

    public long saveSnippet(Code snippet) {
        String date = LocalDateTime.now().format(formatter);
        snippet.setDate(date);
        codeRepository.save(snippet);
        return codeRepository.count();
    }

    public List<Code> getLatestSnippets() {
        List<Code> latestTen = new ArrayList<>();
        Long len = codeRepository.count();
        Long end = len <= 10 ? 1 : len - 9;

        for (Long i = len ; i >= end; i--) {
            Optional<Code> opt = codeRepository.findById(i);
            Code snippet = opt.get();
            latestTen.add(snippet);
        }

        return latestTen;
    }
}