package com.demo.htmlToPdf.selenium.fetcher;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageFetcher {

    /**
     * 
     * @return String the page source from the given url
     * 
     * 
     */
    public static String fetchHtml(ChromeDriver driver, String url) {

	driver.get(url);

	new WebDriverWait(driver, Duration.ofSeconds(30))
		.until(d -> ((JavascriptExecutor) d).executeScript("return document.readyState").equals("complete"));

	return driver.getPageSource();
    }

}
