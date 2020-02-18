package com.inmetrics.rpa.model;

public class Pais {

	private String pais;
	private String capital;
	private String continente;
	private String area;
	private String populacao;
	private String densidade;

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getCapital() {
		return capital;
	}

	public void setCapital(String capital) {
		this.capital = capital;
	}

	public String getContinente() {
		return continente;
	}

	public void setContinente(String continente) {
		this.continente = continente;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getPopulacao() {
		return populacao;
	}

	public void setPopulacao(String populacao) {
		this.populacao = populacao;
	}

	public String getDensidade() {
		return densidade;
	}

	public void setDensidade(String densidade) {
		this.densidade = densidade;
	}

	@Override
	public String toString() {
		return "Pais [pais=" + pais + ", capital=" + capital + "]";
	}

}
