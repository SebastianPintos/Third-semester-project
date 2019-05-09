package com.guerraRectangulos.com;

import java.util.*;

public class GR {

	private int turno;
	private String ganador = "";
	private int largoTablero;
	private int anchoTablero;
	private char[][] tablero;
	private int puntosJ1;
	private int puntosJ2;
	private int[] cursor;
	private int turnosPerdidos = 0;

	public GR(int a, int l) {
		this.anchoTablero = a;
		this.largoTablero = l;
		tablero = new char[anchoTablero][largoTablero];
		for (int i = 0; i < anchoTablero; i++) {
			for (int j = 0; j < largoTablero; j++)
				tablero[i][j] = ' ';
		}
		turno = 0;
		puntosJ1 = 0;
		puntosJ2 = 0;
		cursor = new int[2];
	}

	@Override
	public String toString() {
		StringBuilder tab = new StringBuilder();
		for (int i = 0; i < anchoTablero; i++) {
			for (int j = 0; j < largoTablero; j++) {
				tab.append('[');
				tab.append(tablero[i][j]);
				tab.append(']');
			}
			tab.append("\n");
		}
		tab.append("\nPuntos jugador 1: ");
		tab.append(puntosJ1);
		tab.append("     Puntos jugador 2: ");
		tab.append(puntosJ2);
		tab.append("\nturno: ");
		tab.append(turno);
		return tab.toString();
	}

	public String jugar() {

		if (turnosPerdidos < 2) {

			turno++;
			Random a = new Random();
			Random l = new Random();
			int ancho = a.nextInt(anchoTablero / 3) + 2;
			int largo = l.nextInt(largoTablero / 3) + 2;
			Rectangulo rec = new Rectangulo(ancho, largo);
			System.out.print("Turno: "+String.valueOf((char) (turno + 64)) + " tp: "+turnosPerdidos);
			System.out.print(" ancho rectangulo: "+ancho);
			System.out.println(" largo rectangulo: "+largo);
			
			if (turno % 2 == 1) {
				if (verificacion1(ancho, largo)) {

					int xLibre = cursor[0];
					int yLibre = cursor[1];

					for (int i = xLibre; i < xLibre + rec.getAncho(); i++) {
						for (int j = yLibre; j < yLibre + rec.getLargo(); j++) {
							tablero[i][j] = 'X';
							if (xLibre == i && yLibre == j)
								tablero[i][j] = (char) (turno + 64);
						}
					}

					puntosJ1 += (rec.getAncho() * rec.getLargo()) / 2;
					turnosPerdidos = 0;

				} else
					turnosPerdidos++;

			} else if (turno % 2 == 0) {
				if (verificacion2(ancho, largo)) {
					int xLibre = cursor[0];
					int yLibre = cursor[1];
					for (int i = xLibre; i > xLibre - rec.getAncho(); i--) {
						for (int j = yLibre; j > yLibre - rec.getLargo(); j--) {
							tablero[i][j] = 'O';
							if (xLibre - rec.getAncho() == i - 1 && yLibre - rec.getLargo() == j - 1)
								tablero[i][j] = (char) (turno + 64);

						}
					}
					puntosJ2 += (rec.getAncho() * rec.getLargo()) / 2;
					turnosPerdidos = 0;
				} else
					turnosPerdidos++;
			}
		}
		if (turnosPerdidos == 2) {
			turnosPerdidos = 3;
			if (puntosJ1 > puntosJ2)
				ganador = "Jugador1";
			else if (puntosJ1 < puntosJ2)
				ganador = "Jugador2";
			else
				ganador = "Empate";
			System.out.println("JUEGO TERMINADO.\nGanador: " + ganador + " con "
					+ String.valueOf(Math.max(puntosJ1, puntosJ2)) + " puntos");

		}
		return ganador;
	}

	private boolean verificacion1(int a, int l) {
		boolean acu1 = true;
		for (int i = 0; i < anchoTablero; i++) {
			for (int j = 0; j < largoTablero; j++) {
				if (tablero[i][j] == ' ') {
					if (j + l <= largoTablero) {
						
						for (int x = i; x < i + a; x++) {
							for (int y = j; y < j + l; y++) {
								if(i+a <anchoTablero && j+l < largoTablero) {
								acu1 = acu1 && tablero[x][y] == ' ';
								}
								else
									acu1=false;
								if (!acu1)
									break;
							}
							if (!acu1)
								break;
						}
						
						if (acu1) {
							cursor[0] = i;
							cursor[1] = j;
							return true;
						}
						acu1=true;
					}
				}
			}
		}
		return false;
	}

	private boolean verificacion2(int a, int l) {
		boolean acu1 = true;
		for (int i = anchoTablero - 1; i > 0; i--) {
			for (int j = largoTablero - 1; j > 0; j--) {
				if (tablero[i][j] == ' ') {
					if (j - l >= 0) {
						for (int x = i; x > i - a; x--) {
							for (int y = j; y > j - l; y--) {
								if(i-a >0 && j-l >0) {
									acu1 = acu1 && tablero[x][y] == ' ';
								}
								else
									acu1=false;
								if (!acu1)
									break;
							}
							if (!acu1)
								break;
						}
						
						if (acu1) {
							cursor[0] = i;
							cursor[1] = j;
							return true;							
						}
						acu1=true;

					}
				}
			}
		}
		return false;
	}
}
