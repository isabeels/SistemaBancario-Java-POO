package br.com.residencia.poo.contas;

import java.io.IOException;
import java.time.LocalDate;

import br.com.residencia.poo.enums.TipoConta;
import br.com.residencia.poo.exceptions.CpfInvalidoException;
import br.com.residencia.poo.exceptions.OperacaoNaoAutorizadaException;
import br.com.residencia.poo.exceptions.SaldoInsuficienteException;
import br.com.residencia.poo.exceptions.ValorInvalidoException;
import br.com.residencia.poo.pessoas.Pessoa;

public class ContaCorrente extends Conta {

	/* ATRIBUTOS */
	protected Integer idContaCorrente;
	protected LocalDate dataAbertura;
	private static final TipoConta tipoCC = TipoConta.CORRENTE;

	/* CONSTRUTOR PARA INSTANCIAR NOVAS CONTAS CORRENTES */
	

	


	/* GETTERS E SETTERS */
	public Integer getIdContaCorrente() {
		return idContaCorrente;
	}

	public ContaCorrente(String numeroAgencia, String numeroConta) {
		super(numeroAgencia, numeroConta);
		
	}

	public void setIdContaCorrente(Integer idContaCorrente) {
		this.idContaCorrente = idContaCorrente;
	}
	
	public static TipoConta getTipocc() {
		return tipoCC;
	}

	@Override
	public double getTaxaSaque() {
		return super.getTaxaSaque();
	}

	@Override
	public double getTaxaDeposito() {
		return super.getTaxaDeposito();
	}

	@Override
	public double getTaxaTransferencia() {
		return super.getTaxaTransferencia();
	}

	@Override
	public void sacar(Pessoa conta, double valor)
			throws ValorInvalidoException, SaldoInsuficienteException, OperacaoNaoAutorizadaException, IOException {
		super.sacar(cpfTitular, valor + this.getTaxaSaque());
	}

	@Override
	public void depositar(double valor) throws ValorInvalidoException {
		super.depositar(valor - this.getTaxaDeposito());
	}

	@Override
	public void transferir(double valor, Conta destino) throws ValorInvalidoException, SaldoInsuficienteException,
			CpfInvalidoException, OperacaoNaoAutorizadaException, IOException {
		super.transferir(valor + this.getTaxaTransferencia(), destino);
	}

	@Override
	public void exibirSaldo() {
		super.exibirSaldo();
	}

	@Override
	public String toString() {
		return "ContaCorrente [idContaCorrente=" + idContaCorrente + ", cpfTitular=" + cpfTitular + ", numeroAgencia="
				+ numeroAgencia + ", tipoConta=" + getTipocc() + ", numeroConta=" + numeroConta + ", saldo=" + saldo
				+ ", dataAbertura=" + dataAbertura + "]";
	}

}
