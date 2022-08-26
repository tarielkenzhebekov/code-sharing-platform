package com.example.jetbrains.code;

public class Code {
    private String code;
    private String date;

    public Code() {}

    public Code(String code, String date) {
        this.code = code;
        this.date = date;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCode() {
        return this.code;
    }

    public String getDate() {
        return this.date;
    }
}
