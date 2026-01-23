package com.demo.htmlToPdf.scrapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public final class BookPageParser {

    private BookPageParser() {
    }

    public static List<String> extractChapters(String html, String baseUrl) throws IOException {

	Document doc = Jsoup.parse(html, baseUrl);

	List<String> links = new ArrayList<>();

	Elements anchors = doc.select("a[href]");

	for (Element a : anchors) {

	    String href = a.absUrl("href");

	    if (href.contains("/lessons/")) {
		links.add(href);
	    }
	}

	return links;
    }
}
