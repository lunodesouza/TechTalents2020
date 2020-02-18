package com.inmetrics.rpa.controller;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.inmetrics.rpa.browser.BrowserConfiguration;

public class WebController2 {
	Logger log = Logger.getLogger(WebController2.class);

	public void init(){
		
		WebDriver driver = null;
		WebDriverWait waiter = null;
		
		try {
			
			driver = new ChromeDriver(BrowserConfiguration.getChromeOptions(false));
			driver.manage().timeouts().setScriptTimeout(20, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(30L, TimeUnit.SECONDS);
			
			driver.get("https://pt.wikipedia.org/");
			
			WebElement busca = driver.findElement(By.name("search"));
			busca.sendKeys("Bauru");
			
			WebElement search = driver.findElement(By.id("searchButton"));
			search.click();
			
			WebElement area = driver.findElement(By.xpath("//a[text()='Área total']/parent::td/following-sibling::td"));
			
			log.info("Area: "+ area.getText());
			
			WebElement populacao = driver.findElement(By.xpath("//a[text()='População total']/parent::td/following-sibling::td"));
			
			log.info("População total: "+ populacao.getText());
			
			WebElement densidade = driver.findElement(By.xpath("//a[text()='Densidade']/parent::td/following-sibling::td"));
			
			log.info("Densidade: "+ densidade.getText());
			
			//Densidade
			
		} catch(Exception e) {
			log.error(e);
		} finally {
			if(driver != null) {
				driver.manage().deleteAllCookies();
//				driver.close();
			}
		}
		
	}
}
