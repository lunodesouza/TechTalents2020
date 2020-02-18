package com.inmetrics.rpa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WikiPage extends PageObject {

	@FindBy(name="search")
	private WebElement campoBusca;
	
	@FindBy(id="searchButton")
	private WebElement buscar;
	

	public WikiPage(WebDriver driver) {
		super(driver);
	}
	
	public void setarCapital(String capital) {
		campoBusca.clear();
		campoBusca.sendKeys(capital);
	}
	
	public PaisPage buscar() {
		buscar.click();
		
		return new PaisPage(driver);
	}

						
}
