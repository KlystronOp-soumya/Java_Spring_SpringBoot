package com.demo.htmlToPdf.scrapper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public record Chapter(String title, String url) {

    private static final Pattern NUMBER = Pattern.compile("(\\d+)");

    public int index() {

	Matcher matcher = NUMBER.matcher(title);

	return matcher.find() ? Integer.parseInt(matcher.group(1)) : Integer.MAX_VALUE;
    }

}
