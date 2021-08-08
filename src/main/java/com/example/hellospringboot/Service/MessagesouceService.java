package com.example.hellospringboot.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class MessagesouceService {

    @Autowired
    MessageSource messageSource;

    public String getMess(Locale locale, String code){
        if (code != "login.mess" && code != "update.mess" && code != "create.mess" && code != "delete.mess"){
            return code;
        }
        else{
            return messageSource.getMessage(code, null, locale);
        }
    }

}
