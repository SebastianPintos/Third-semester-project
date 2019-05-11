package com.guerraRectangulos.com;

public class Rectangulo {

	
	private int ancho;
	private int largo;
	private int numero;
	private int x1;
	private int x2;
	private int y1;
	private int y2;
	
	public Rectangulo(int a,int l) {
		this.ancho = a;
		this.largo = l;
	}
	
	public int getAncho() {
		return ancho;
	}
	
	public int getLargo() {
		return largo;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}
	
	public void setCoordenadas(int x1,int y1, int x2,int y2) {
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
	}

	public String alto() {
		return String.valueOf(ancho);
	}

	public String ancho() {
		return String.valueOf(largo);
	}
	
	
}
