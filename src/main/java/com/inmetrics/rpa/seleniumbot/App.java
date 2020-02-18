package com.inmetrics.rpa.seleniumbot;

import java.util.List;

import org.apache.log4j.Logger;

import com.inmetrics.rpa.controller.ExcelController;
import com.inmetrics.rpa.model.Pais;

/**
 * Hello Selenium Bot! VM Arguments:
 * -Dlog4j.configuration=file:resources/log4j.properties
 *
 */
public class App {
	private static Logger log = Logger.getLogger(App.class);

	public static void main(String[] args) {
		ExcelController excelController = new ExcelController();
		System.setProperty("webdriver.chrome.driver", "resources/chromedriver");

		log.info("Hello Tech!");

//		WebController web = new WebController();
//
//		web.init();
		
		List<Pais> paises = excelController.lerExcel("resources/Capitais.xlsx");
		int c = 0;
		
		for(Pais p : paises) {
			p.setArea("1000");
			p.setDensidade("100020");
			p.setPopulacao("3000");
			
			paises.set(c, p);
			c++;
		}
		
		
		int retorno = excelController.gravarExcel(paises);

		log.info(retorno);
	}
}
