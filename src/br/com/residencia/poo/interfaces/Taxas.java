package br.com.residencia.poo.interfaces;

public class Taxas {
	
		double SAQUE = 0.1d, DEPOSITO = 0.1d, TRANSFERENCIA = 0.2d;

		double tributarSaque(double valor);
		double tributarDeposito(double valor);
		double tributarTransferencia(double valor);
}
