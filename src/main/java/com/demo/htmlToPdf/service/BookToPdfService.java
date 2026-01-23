package com.demo.htmlToPdf.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BookToPdfService {

    private static final Logger log = LoggerFactory.getLogger(BookToPdfService.class);

    public void generate(String bookUrl) throws Exception {

	/*
	 * log.info("Starting PDF generation"); log.info("Source URL: {}", bookUrl);
	 * log.info("Personal archival use only");
	 * 
	 * List<Chapter> chapters = BookPageParser.extractChapters(bookUrl);
	 * 
	 * Path outputDir = Path.of("output"); Files.createDirectories(outputDir);
	 * 
	 * List<Path> generated = new ArrayList<>();
	 * 
	 * ChromeDriver driver = ChromeDriverFactory.create();
	 * 
	 * try {
	 * 
	 * int i = 1;
	 * 
	 * for (Chapter c : chapters) {
	 * 
	 * Path out = outputDir .resolve(String.format("%02d-%s.pdf", i,
	 * c.title().replaceAll("[^a-zA-Z0-9]", "_")));
	 * 
	 * try {
	 * 
	 * PdfPrinter.print(driver, c.url(), out); generated.add(out);
	 * 
	 * } catch (Exception e) {
	 * 
	 * log.error("Failed {}", c.title(), e); }
	 * 
	 * i++; }
	 * 
	 * } finally {
	 * 
	 * driver.quit(); }
	 * 
	 * if (!generated.isEmpty()) {
	 * 
	 * Path merged = outputDir.resolve("BOOK-FULL.pdf");
	 * PdfMergeUtil.merge(generated, merged); log.info("Merged PDF created: {}",
	 * merged); }
	 */
    }

}
