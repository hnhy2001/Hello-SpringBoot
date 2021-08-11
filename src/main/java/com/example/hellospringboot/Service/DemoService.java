package com.example.hellospringboot.Service;

import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class DemoService {

    public int sum(int a, int b) {
        int c = a + b;
        return c;
    }

    public String okela() {
        return "ok";
    }

    public String demoPass(String pass) {
        String patternStr = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
        if (Pattern.matches(patternStr, pass)) {
            return "ok";
        } else {
            return "no";
        }
    }
}
