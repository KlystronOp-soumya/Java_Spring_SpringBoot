package com.demo.htmlToPdf;

import java.nio.file.Path;
import java.util.List;

import org.openqa.selenium.chrome.ChromeDriver;

import com.demo.htmlToPdf.scrapper.BookPageParser;
import com.demo.htmlToPdf.selenium.ChromeDriverFactory;
import com.demo.htmlToPdf.selenium.PdfPrinter;

/**
 * Hello world!
 */
public class App {

    public static void main(String[] args) throws Exception {

	if (args.length == 0) {
	    throw new IllegalArgumentException("Book URL required");
	}

	ChromeDriver driver = ChromeDriverFactory.create();

	try {
	    // 1. Fetch book page
	    String bookHtml = com.demo.htmlToPdf.selenium.fetcher.PageFetcher.fetchHtml(driver, args[0]);

	    // 2. Extract chapter links
	    List<String> chapters = BookPageParser.extractChapters(bookHtml, args[0]);

	    // 3. Print chapters
	    int index = 1;
	    for (String chapter : chapters) {

		Path pdf = Path.of("chapter-" + index++ + ".pdf");

		PdfPrinter.print(driver, chapter, pdf);
	    }

	} finally {
	    driver.quit();
	}
    }
}
