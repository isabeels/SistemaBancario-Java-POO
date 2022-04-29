package br.com.residencia.poo.contas;

import java.io.IOException;
import java.time.LocalDate;
import br.com.residencia.poo.enums.TipoConta;
import br.com.residencia.poo.exceptions.CpfInvalidoException;
import br.com.residencia.poo.exceptions.OperacaoNaoAutorizadaException;
import br.com.residencia.poo.exceptions.SaldoInsuficienteException;
import br.com.residencia.poo.exceptions.ValorInvalidoException;
import br.com.residencia.poo.pessoas.Pessoa;

public class ContaPoupanca extends Conta {

	/* ATRIBUTOS */
	protected static Integer idContaPoupanca = 0;
	protected LocalDate dataAbertura;
	protected Double rendimento;
	protected Integer dias;
	private static final TipoConta tipoCP = TipoConta.POUPANCA;
	private static final double JUROS = 0.001;

	/* CONSTRUTOR PARA INSTANCIAR NOVAS CONTAS POUPANCAS */
	
	public ContaPoupanca(Pessoa cpfTitular, String numeroAgencia, String numeroConta) {
		super(cpfTitular, numeroAgencia, numeroConta);
		incrementarID();
		getTipocp();
		dataAbertura = LocalDate.now();
	}


	/* GETTERS E SETTERS */
	public Integer getIdContaPoupanca() {
		return idContaPoupanca;
	}

	public void setIdContaPoupanca(Integer idContaPoupanca) {
		this.idContaPoupanca = idContaPoupanca;
	}

	public Double getRendimento() {
		return rendimento;
	}

	public void setRendimento(Double rendimento) {
		this.rendimento = rendimento;
	}
	
	public static TipoConta getTipocp() {
		return tipoCP;
	}


	/* M�TODO PARA A CONTA POUPANCA */

	public Double calcularRendimentos(Double dinheiro, Integer dias) throws ValorInvalidoException {
		if (dinheiro <= 0) {
            throw new ValorInvalidoException("N�o � poss�vel simular com valores negativos.");
        } else if (dias <= 0) {
        	 throw new ValorInvalidoException("N�o � poss�vel simular com dias iguais ou menores que 0.");
        }
		rendimento = dinheiro * (JUROS * dias);
		this.dias = dias;
		return rendimento;
	}
	void imprimir() {
		System.out.printf("Rendeu %.02f R$ em %d dia(s)",rendimento, dias);
}	

	public Integer incrementarID() {
		return idContaPoupanca++;
	}
	
	@Override
	public void sacar(Pessoa cpfTitular, double valor)
			throws ValorInvalidoException, SaldoInsuficienteException, OperacaoNaoAutorizadaException, IOException {
		super.sacar(cpfTitular, valor);
	}

	@Override
	public void depositar(double valor) throws ValorInvalidoException {
		super.depositar(valor);
	}

	@Override
	public void transferir(double valor, Conta destino) throws ValorInvalidoException, SaldoInsuficienteException,
			CpfInvalidoException, OperacaoNaoAutorizadaException, IOException {
		super.transferir(valor, destino);
	}

	@Override
	public String toString() {
		return "[ Conta Poupanca ID: " + idContaPoupanca + " | CPF do titular: " + this.cpfTitular.getCpf() + " | N�mero da ag�ncia: "
				+ numeroAgencia + " | Tipo de conta: " + getTipocp() + " | N�mero da Conta: " + numeroConta + " | Data de abertura: "
				+ dataAbertura + " | Saldo atual: " + saldo + " R$ ]";
	}
}
