package com.inmetrics.rpa.browser;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverFactory {
	protected static WebDriver driver = null;
	
	public static WebDriver getInstance(boolean isHeasless) {
		driver = new ChromeDriver(BrowserConfiguration.getChromeOptions(isHeasless));
		driver.manage().timeouts().setScriptTimeout(20, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		return driver;
	}
}
