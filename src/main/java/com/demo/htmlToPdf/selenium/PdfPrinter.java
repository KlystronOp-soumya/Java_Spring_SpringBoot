package com.demo.htmlToPdf.selenium;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;
import java.util.Optional;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.devtools.v120.page.Page;

public final class PdfPrinter {

    private PdfPrinter() {
    }

    public static void print(ChromeDriver driver, String url, Path output) throws Exception {

	DevTools devTools = ((HasDevTools) driver).getDevTools();
	devTools.createSession();

	driver.get(url);

	Page.PrintToPDFResponse pdf = devTools.send(Page.printToPDF(Optional.of(false), // portrait
		Optional.of(false), // no header/footer
		Optional.of(true), // print background (CRITICAL)
		Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(),
		Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(),
		Optional.of(false), Optional.empty(), Optional.empty()));

	Files.write(output, Base64.getDecoder().decode(pdf.getData()));
    }

    private static <T> Optional<T> o(T value) {
	return Optional.ofNullable(value);
    }
}
