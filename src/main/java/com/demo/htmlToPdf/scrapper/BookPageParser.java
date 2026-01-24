package com.demo.htmlToPdf.scrapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class BookPageParser {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookPageParser.class);

    private BookPageParser() {
    }

    public static Map<String, String> extractChapters(String html, String baseUrl) throws IOException {

	Document doc = Jsoup.parse(html, baseUrl);

	String preface = parsePreface(doc);

	Map<String, String> chapterLinks = new HashMap<>();

	Elements anchors = doc.select("a.ld-item-name[href]");

	for (Element anchor : anchors) {

	    // Ensure this anchor represents a chapter item
	    if (anchor.selectFirst(".ld-status-icon.ld-status-incomplete") != null
		    && anchor.selectFirst(".ld-item-title") != null) {

		Element titleDiv = anchor.selectFirst(".ld-item-title");

		String title = titleDiv.text().trim();
		String link = anchor.absUrl("href");

		if (!title.isEmpty() && !link.isEmpty()) {

		    chapterLinks.put(title, link);
		}

	    }

	}

	return chapterLinks;
    }

    private static String parsePreface(Document doc) {

	Element bookTitleAuthor = doc.selectFirst("h2.wp-block-heading");

	Element preface = doc.selectFirst("div.ld-tab-content.ld-visible.entry-content");

	if (preface == null) {

	    throw new IllegalStateException("Preface section not found");
	}

	Elements paragraphs = preface.select("p");

	String prefaceContent = paragraphs.stream().collect(Collectors.mapping(Element::text, Collectors.joining()));

	// LOGGER.info(bookTitleAuthor.text() + "\n\n" + prefaceContent);

	return bookTitleAuthor.text() + "\n\n" + prefaceContent;
    }
}
