package br.com.residencia.poo.contas;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.NumberFormat;
import java.time.LocalDate;

import br.com.residencia.poo.exceptions.CpfInvalidoException;
import br.com.residencia.poo.exceptions.OperacaoNaoAutorizadaException;
import br.com.residencia.poo.exceptions.SaldoInsuficienteException;
import br.com.residencia.poo.exceptions.ValorInvalidoException;

public class Conta {

	/* ATRIBUTOS */
	private Integer idConta;
	private String numeroAgencia;
	private String cpfTitular;
	private String tipoConta;
	private String numeroConta;
	private Double saldo;
	private String saldoFormatado;
	private LocalDate dataAbertura;
	private Boolean status;
	private String senha;
	
	NumberFormat nf = NumberFormat.getCurrencyInstance();

	static final double taxaSaque = 0.10;
	static final double taxaDeposito = 0.10;
	static final double taxaTransferencia = 0.20;

	/* CONSTRUTOR PARA DIFERENTES TIPOS DE NOVAS CONTAS */
	
	private static int totalDeContas = 0;
	
	public Conta() {
		 Conta.totalDeContas = Conta.totalDeContas + 1;
	}
	public void imprimirConta() {
		System.out.println(this.totalDeContas);
	}
	public int getNumeroContas() {
		return totalDeContas;
	}
	

	public Conta(Integer idConta, String numeroAgencia, String tipoConta, String numeroConta, Double saldo,
			Boolean status, String senha) {
		this.idConta = idConta;
		this.numeroAgencia = numeroAgencia;
		this.tipoConta = tipoConta;
		this.numeroConta = numeroConta;
		this.saldo = saldo;
		this.status = status;
		this.senha = senha;
		this.dataAbertura = LocalDate.now();
		Conta.totalDeContas = Conta.totalDeContas + 1;
	}

	/* GETTERS E SETTERS */
	public Integer getIdConta() {
		return idConta;
	}

	public String getNumeroAgencia() {
		return numeroAgencia;
	}

	public String getTipoConta() {
		return tipoConta;
	}

	public String getNumeroConta() {
		return numeroConta;
	}

	public double getSaldo() {
		return saldo;
	}

	public LocalDate getDataAbertura() {
		return dataAbertura;
	}

	public Boolean getStatus() {
		return status;
	}

	public String getSenha() {
		return senha;
	}

	public void setNumeroAgencia(String numeroAgencia) {
		this.numeroAgencia = numeroAgencia;
	}

	public void setTipoConta(String tipoConta) {
		this.tipoConta = tipoConta;
	}

	public void setNumeroConta(String numeroConta) {
		this.numeroConta = numeroConta;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	/* M�TODOS DA CONTA - TUDO QUE UMA CONTA CORRENTE E POUPANCA PODE REALIZAR */
	public void sacar(double valor)
			throws ValorInvalidoException, SaldoInsuficienteException, OperacaoNaoAutorizadaException {

		if (status) {
			if (valor <= 0) {
				throw new ValorInvalidoException();
			} else if (this.saldo < (valor + taxaSaque)) {
				throw new SaldoInsuficienteException();
			} else {
				this.saldo -= (valor + taxaSaque);
			}
		} else {
			throw new OperacaoNaoAutorizadaException("Imposs�vel sacar de uma conta fechada.");
		}
	}

	public void depositar(double valor) throws ValorInvalidoException {
		if (valor <= taxaDeposito) {
			throw new ValorInvalidoException("Imposs�vel depositar valores negativos.");
		} else {
			this.saldo += (valor - taxaDeposito);
		}

	}

	public void transferir(double valor, Conta destino) throws ValorInvalidoException, SaldoInsuficienteException, CpfInvalidoException, OperacaoNaoAutorizadaException {
		if (valor <= 0) {
			throw new ValorInvalidoException("Imposs�vel realizar transfer�ncia de valores negativos.");
		}
		else if (this.saldo < (valor + taxaTransferencia)) {
			throw new SaldoInsuficienteException();
		}
		else {
			sacar(valor + taxaTransferencia);
			destino.depositar(valor);
		}
	}
	
	public void exibirSaldo() {
		System.out.printf("Saldo atual e dispon�vel: R$ %.2f", this.saldo);
	}
	
	public void relatorioTransacao(String tipoConta, String tipoTransacao, String cpf, double valor) throws IOException {
        
		File diretorioRegistroTransacoes = new File ("C:\\RegistroTransacoesContas\\");
        File historicoConta = new File (diretorioRegistroTransacoes.getAbsolutePath() + "\\historicoTransacoes.txt");

        if (!diretorioRegistroTransacoes.exists()) {
        	diretorioRegistroTransacoes.mkdirs();  //cria um diret�rio
       }

        if(!historicoConta.exists()) {
        	historicoConta.createNewFile();  //cria um arquivo
        }

       try(FileWriter historicoContaWriter = new FileWriter(historicoConta, true);
            BufferedWriter historicoContaBuff = new BufferedWriter(historicoContaWriter)) {

    	   historicoContaBuff.append(tipoConta + "," + tipoTransacao + "," + this.cpfTitular + "," + valor + ".");
  	      	   
    	   historicoContaBuff.newLine();
    	   
       } catch (IOException e) {
           System.out.println(e.getMessage());
       }
   }
	
	/* OVERRIDES */
	@Override
	public String toString() {
		return "N�mero da ag�ncia: " + numeroAgencia + "\n" + "Tipo da conta: " + tipoConta + "\n" + "N�mero da conta: "
				+ numeroConta + "\n" + "Saldo da conta: " + saldo + "\n" + "Data de abertura: " + dataAbertura
				+ "\n" + "Status da conta: " + status + "\n";
	}
}
