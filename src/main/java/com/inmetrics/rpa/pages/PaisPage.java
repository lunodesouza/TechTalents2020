package com.inmetrics.rpa.pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.inmetrics.rpa.model.Pais;

public class PaisPage extends PageObject {

	@FindBy(xpath="//a[text()='Área total']/parent::td/following-sibling::td")
	private WebElement areaTotal;
	
	@FindBy(xpath="//a[text()='População total']/parent::td/following-sibling::td")
	private WebElement populacaoTotal;
	
	@FindBy(xpath="//a[text()='Densidade']/parent::td/following-sibling::td")
	private WebElement densidade;
	
	public PaisPage(WebDriver driver) {
		super(driver);
	}
	
	public Pais coletarInformacoes() {
		Pais pais = new Pais();
		
		try {
			pais.setArea(areaTotal.getText());
			pais.setPopulacao(populacaoTotal.getText());
			pais.setDensidade(densidade.getText());
			
			return pais;
		} catch(NoSuchElementException nsee) {
			pais.setArea("N/A");
			pais.setPopulacao("N/A");
			pais.setDensidade("N/A");
		}
		return pais;
	}
}
