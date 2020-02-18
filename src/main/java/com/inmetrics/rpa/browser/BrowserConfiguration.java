package com.inmetrics.rpa.browser;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.chrome.ChromeOptions;

public class BrowserConfiguration {

	public static final ChromeOptions getChromeOptions(boolean isHeadless) {
		ChromeOptions  options = new ChromeOptions ();
		options.addArguments("enable-automation");
		options.addArguments("--window-size=1920,1080");
		options.addArguments("--no-sandbox");
		options.addArguments("--disable-extensions");
		options.addArguments("--dns-prefetch-disable");
		options.addArguments("--disable-gpu");
		options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
		
		if(isHeadless) {
			options.addArguments("--headless");
		}
		
		return options;
	}
}
