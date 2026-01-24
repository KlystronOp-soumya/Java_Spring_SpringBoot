package com.demo.htmlToPdf;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

import org.openqa.selenium.chrome.ChromeDriver;

import com.demo.htmlToPdf.scrapper.BookPageParser;
import com.demo.htmlToPdf.selenium.ChromeDriverFactory;
import com.demo.htmlToPdf.selenium.PdfPrinter;
import com.demo.htmlToPdf.selenium.fetcher.PageFetcher;

/**
 * Hello world!
 */
public class App {

    private static String sanitize(String input) {
	return input.replaceAll("[\\\\/:*?\"<>|]", "").replaceAll("\\s+", "_");
    }

    public static void main(String[] args) throws Exception {

	if (args.length == 0) {
	    throw new IllegalArgumentException("Book URL required");
	}

	ChromeDriver driver = ChromeDriverFactory.create();

	try {
	    // 1. Fetch book page
	    String bookHtml = PageFetcher.fetchHtml(driver, args[0]);

	    // 2. Extract chapter links
	    Map<String, String> chapterLinks = BookPageParser.extractChapters(bookHtml, args[0]);

	    Path outputDir = Path.of("output", "chapters");
	    Files.createDirectories(outputDir);

	    chapterLinks.entrySet().stream().forEach(chapterLink -> {

		Path pdf = outputDir.resolve(String.format("%s.pdf", chapterLink.getKey()));

		try {

		    PdfPrinter.print(driver, chapterLink.getValue(), pdf);

		} catch (Exception ex) {

		    ex.printStackTrace();
		}

	    });

	} finally {
	    driver.quit();
	}
    }
}
