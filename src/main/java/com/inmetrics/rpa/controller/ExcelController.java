package com.inmetrics.rpa.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.inmetrics.rpa.model.Pais;

public class ExcelController {
	private static Logger log = Logger.getLogger(ExcelController.class);

	/**
	 * 
	 * @param filename
	 * @return
	 */
	public List<Pais> lerExcel(String filename) {

		List<Pais> paises = new ArrayList<>();
		Workbook workbook = null;
		try (FileInputStream file = new FileInputStream(filename)) {

			try {
				// Recuperando o arquivo
				workbook = WorkbookFactory.create(file);

				// Setando a aba
				Sheet sheet = workbook.getSheetAt(0);

				for (Row row : sheet) {
					int cellnum = 0;
					Pais pais = new Pais();

					Cell cellPais = row.getCell(cellnum++);
					pais.setPais(cellPais.getStringCellValue());

					Cell cellCapital = row.getCell(cellnum++);
					pais.setCapital(cellCapital.getStringCellValue());

					Cell cellContinente = row.getCell(cellnum++);
					pais.setContinente(cellContinente.getStringCellValue());

					paises.add(pais);
					System.out.println(pais.toString());
				}

				workbook.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {

		}
		return paises;
	}

	public int gravarExcel(List<Pais> paises) {
		File arquivoDestino = new File("resources/Capitais.xlsx");

		try (FileInputStream fis = new FileInputStream(arquivoDestino)) {
			try (XSSFWorkbook workbook = new XSSFWorkbook(arquivoDestino)) {
				XSSFSheet sheetGravarExcel = workbook.getSheetAt(0);

				int rownum = 1;
				for (Pais pais : paises) {

					int cellnum = 0;

					XSSFRow row = sheetGravarExcel.createRow(rownum++);

					Cell cellPais = row.createCell(cellnum++);
					cellPais.setCellValue(pais.getPais());

					Cell cellCapital = row.createCell(cellnum++);
					cellCapital.setCellValue(pais.getCapital());

					Cell cellContinente = row.createCell(cellnum++);
					cellContinente.setCellValue(pais.getContinente());

					Cell cellArea = row.createCell(cellnum++);
					cellArea.setCellValue(pais.getArea());

					Cell cellPopulacao = row.createCell(cellnum++);
					cellPopulacao.setCellValue(pais.getPopulacao());

					Cell cellDensidade = row.createCell(cellnum++);
					cellDensidade.setCellValue(pais.getDensidade());

				}

				try (FileOutputStream arquivo = new FileOutputStream(fis.toString())) {
					workbook.write(arquivo);
					workbook.close();

				} finally {
					try {
						if (null != workbook) {
							workbook.close();
						}
					} catch (IOException e) {
						log.error(e);
					}
				}

			} catch (Exception e) {
				// TODO: handle exception
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return 0;

	}

}
