package com.inmetrics.rpa.controller;

import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import com.inmetrics.rpa.browser.WebDriverFactory;
import com.inmetrics.rpa.model.Pais;
import com.inmetrics.rpa.pages.PaisPage;
import com.inmetrics.rpa.pages.WikiPage;

public class WebController3 {
	private Logger log = Logger.getLogger(WebController3.class);
	private boolean isHeadless = false;

	public void init(){
		
		WebDriver driver = null;
//		WebDriverWait waiter = null;
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.id<locator>));
		//wait.until(ExpectedConditions.elementToBeClickable(By.id<locator>));
		
		List<String> cidades = Arrays.asList("Bauru", "Cafelândia", "São José do Rio Preto", 
				"Barueri", "Tokio", "París", "Marília", "Osasco", "Ubatuba");
		
		try {
			driver = WebDriverFactory.getInstance(isHeadless);
			
			for(String cidade : cidades) {
				driver.get("https://pt.wikipedia.org/");
				
				WikiPage wiki = new WikiPage(driver);
				PaisPage paisPage = new PaisPage(driver);
				
				wiki.setarCapital(cidade);
				wiki.buscar();
				
				Pais pais = new Pais();
				pais = paisPage.coletarInformacoes();
				
				log.info("Cidade:"+ cidade);
				log.info("Area: "+ pais.getArea());
				log.info("Polulação: "+ pais.getPopulacao());
				log.info("Densidade: "+ pais.getDensidade());
			}
							
		} catch(NoSuchElementException nsee) {
			log.warn("Cidade ou elemento não encontrado: ", nsee);
		} catch(Exception e) {
			log.error(e);
		} finally {
			if(driver != null) {
				driver.manage().deleteAllCookies();
				driver.close();
			}
		}
		
	}
	
}
