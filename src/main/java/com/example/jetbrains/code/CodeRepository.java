package com.example.jetbrains.code;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

@Component
public class CodeRepository {
    private static List<Code> codes = new ArrayList<>();
    private static DateTimeFormatter formatter = 
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public Code get(Integer N) {
        return codes.get(N - 1);
    }

    public int add(String snippet) {
        String date = LocalDateTime.now().format(formatter);
        Code temp = new Code(snippet, date);
        codes.add(temp);

        return codes.size();
    }

    public List<Code> getLatestCodes() {
        List<Code> temp = new ArrayList<>();
        int len = codes.size();
        int end = len <= 10 ? 0 : len - 10; 

        for (int i = len - 1; i >= end; i--) {
            temp.add(codes.get(i));
        }

        return temp;
    }

}
