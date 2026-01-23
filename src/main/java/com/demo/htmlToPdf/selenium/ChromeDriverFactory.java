package com.demo.htmlToPdf.selenium;

import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public final class ChromeDriverFactory {

    private ChromeDriverFactory() {
    }

    public static ChromeDriver create() {

	/*
	 * AppConfig cfg = AppConfig.getInstance();
	 * 
	 * Path chrome = Path.of(cfg.get("chrome.binary.path")); Path driver =
	 * Path.of(cfg.get("chrome.driver.path"));
	 * 
	 * if (!Files.exists(chrome) || !Files.exists(driver)) {
	 * 
	 * throw new IllegalStateException("Chrome or ChromeDriver missing"); }
	 * 
	 * System.setProperty("webdriver.chrome.driver", driver.toString());
	 * 
	 * ChromeOptions options = new ChromeOptions();
	 * options.setBinary(chrome.toString()); options.addArguments("--headless=new",
	 * "--disable-gpu", "--no-sandbox", "--disable-dev-shm-usage",
	 * "--user-data-dir=chrome-profile");
	 * 
	 * return new ChromeDriver(options);
	 */

	WebDriverManager.chromedriver().setup();

	return new ChromeDriver();

    }
}
