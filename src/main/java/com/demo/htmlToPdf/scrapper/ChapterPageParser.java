package com.demo.htmlToPdf.scrapper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class ChapterPageParser {

    private ChapterPageParser() {
    }

    public static String extractTitle(String html) {
	Document doc = Jsoup.parse(html);
	return doc.selectFirst("h1").text();
    }

    public static Element extractContent(String html) {
	Document doc = Jsoup.parse(html);
	return doc.selectFirst(".entry-content");
    }

}
