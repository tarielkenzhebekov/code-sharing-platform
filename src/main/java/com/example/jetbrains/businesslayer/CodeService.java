package com.example.jetbrains.businesslayer;

import com.example.jetbrains.persistence.CodeRepository;

import java.util.List;
import java.util.ArrayList;
import java.util.UUID;
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

    public Optional<Code> findByUuid(UUID uuid) {
        return codeRepository.findByUuid(uuid);
    }

    public UUID saveSnippet(Code snippet) {
        LocalDateTime now = LocalDateTime.now();
        snippet.setEndTime(now.plusSeconds(snippet.getTime()));

        String date = now.format(formatter);
        snippet.setDate(date);

        UUID uuid = UUID.randomUUID();
        snippet.setUuid(uuid);

        if (snippet.getTime() < 0) {
            snippet.setTime(0);
        }

        if (snippet.getViews() < 0) {
            snippet.setViews(0);
        }

        Code savedSnippet = codeRepository.save(snippet);
        return savedSnippet.getUuid();
    }

    public void updateSnippet(Code snippet) {
        codeRepository.save(snippet);
    }

    public List<Code> getLatestSnippets() {
        List<Code> latestTen = new ArrayList<>();
        long last = codeRepository.count();

        while(latestTen.size() != 10 && last != 0) {
            Optional<Code> opt = codeRepository.findById(last);
            if (!opt.get().isDeleted()
                    && opt.get().getTime() == 0
                    && opt.get().getViews() == 0) {
                latestTen.add(opt.get());
            }
            last--;
        }

        return latestTen;
    }
}
