package com.inmetrics.rpa.controller;

import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

import com.inmetrics.rpa.browser.WebDriverFactory;
import com.inmetrics.rpa.model.Pais;
import com.inmetrics.rpa.pages.PaisPage;
import com.inmetrics.rpa.pages.WikiPage;

public class WebController {
	private Logger log = Logger.getLogger(WebController.class);
	private boolean isHeadless = false;
	private WebDriver driver = null;

	public void init(){
		
		List<String> cidades = Arrays.asList("Bauru", "Cafelândia", "São José do Rio Preto", 
				"Barueri", "Tokio", "París", "Marília", "Osasco", "Ubatuba");
		
		int index = 0;
		int total = cidades.size();
		int retry = 0;
		
		try {
			driver = WebDriverFactory.getInstance(isHeadless);
			
			index = processaBusca(cidades, index, total);
								
			while(index < total) {
				retry++;
				if(retry == 3) {
					break;
				}
				log.info("Realizando nova tentativa, parou em: "+ index);
				
				Integer tmp = processaBusca(cidades, index, total);
				
				if(tmp > index) {
					index = tmp;
				}
			}
			
		} catch(Exception e) {
			e.printStackTrace();
			log.info(">>> "+ e.getMessage());
			if(e.getMessage().equals("no such window: target window already closed")) {
				driver = WebDriverFactory.getInstance(isHeadless);
			}
		} finally {
			if(driver != null) {
				driver.manage().deleteAllCookies();
				driver.close();
			}
		}
	}
	
	/**
	 * 
	 * @return
	 */
	private int processaBusca(List<String> cidades, int index, int total){
		// cidade = 10
		// cidaderetorno = 5
		int retorno = 0;
		
		try {
			
			for(int c = index; c <= total; c++) {
				
				String cidade = cidades.get(c);
				
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
				
				retorno++;
			}
			
		} catch(NoSuchElementException nsee) {
			log.warn("Cidade ou elemento não encontrado: ", nsee);
			log.info(">>> "+ nsee.getMessage());
		} catch(WebDriverException wde) {
			log.warn("WebDriverException>>> "+ wde.getMessage());
			driver = WebDriverFactory.getInstance(isHeadless);
		} catch(Exception e) {
			log.error(e);
		}
		
		return retorno;
	}
}
