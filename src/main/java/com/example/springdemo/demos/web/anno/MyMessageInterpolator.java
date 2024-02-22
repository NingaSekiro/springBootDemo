package com.example.springdemo.demos.web.anno;

import javax.validation.MessageInterpolator;
import java.util.Locale;

public class MyMessageInterpolator implements MessageInterpolator {

    @Override
    public String interpolate(String messageTemplate, Context context) {
        //...
        return "test";
    }

    @Override
    public String interpolate(String messageTemplate, Context context, Locale locale) {
        //...
        return null;
    }
}