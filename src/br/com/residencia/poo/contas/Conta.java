package br.com.residencia.poo.contas;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

import br.com.residencia.poo.exceptions.CpfInvalidoException;
import br.com.residencia.poo.exceptions.OperacaoNaoAutorizadaException;
import br.com.residencia.poo.exceptions.SaldoInsuficienteException;
import br.com.residencia.poo.exceptions.ValorInvalidoException;

public class Conta {

	/* ATRIBUTOS */
	private Integer idConta;
	private String numeroAgencia;
	private Conta cpfTitular;
	private String tipoConta;
	private String numeroConta;
	private Double saldo;
	private LocalDate dataAbertura;
	private Boolean status;
	private String senha;
	double taxaSaque = 0.10;
	double taxaDeposito = 0.10;
	double taxaTransferencia = 0.20;

	/* CONSTRUTOR PARA DIFERENTES TIPOS DE NOVAS CONTAS */
	
//	private static int totalDeContas = 0;
//	
//	public Conta() {
//		 Conta.totalDeContas = Conta.totalDeContas + 1;
//	}
//	public void imprimirConta() {
//		System.out.println(this.totalDeContas);
//	}
//	public int getNumeroContas() {
//		return totalDeContas;
//	}
	

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
		//Conta.totalDeContas = Conta.totalDeContas + 1;
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
	
	public double getTaxaSaque() {
		return taxaSaque;
	}

	public double getTaxaDeposito() {
		return taxaDeposito;
	}

	public double getTaxaTransferencia() {
		return taxaTransferencia;
	}
	

	public Conta getCpfTitular() {
		return cpfTitular;
	}

	/* M�TODOS DA CONTA - TUDO QUE UMA CONTA CORRENTE E POUPANCA PODE REALIZAR */
	public void sacar(Conta cpfTitular, double valor)
			throws ValorInvalidoException, SaldoInsuficienteException, OperacaoNaoAutorizadaException, IOException {

		if (Boolean.TRUE.equals(status)) {
			if (valor <= 0) {
				throw new ValorInvalidoException();
			} else if (this.saldo < valor) {
				throw new SaldoInsuficienteException();
			} else {
				this.saldo -= valor;
				exibirSaldo();
				comprovanteSaque(cpfTitular,valor);
<<<<<<< HEAD
			
				this.totalTaxaSaque += taxaSaque;
				
				
=======
>>>>>>> branch 'main' of https://github.com/isabeels/poo.git
				}
		} else {
			throw new OperacaoNaoAutorizadaException("Imposs�vel sacar de uma conta fechada.");
		}
	}

	public void depositar(double valor) throws ValorInvalidoException {
		if (valor < 0) {
			throw new ValorInvalidoException("Imposs�vel depositar valores negativos.");
		} else {
			this.saldo += valor;
			exibirSaldo();
		}

	}

	public void transferir(double valor, Conta destino) throws ValorInvalidoException, SaldoInsuficienteException, CpfInvalidoException, OperacaoNaoAutorizadaException, IOException {
		if (valor <= 0) {
			throw new ValorInvalidoException("Imposs�vel realizar transfer�ncia de valores negativos.");
		}
		else if (this.saldo < (valor)) {
			throw new SaldoInsuficienteException();
		}
		else {
			sacar(cpfTitular, valor);
			destino.depositar(valor);
			exibirSaldo();
		}
	}
	
	public void exibirSaldo() {
		System.out.printf("Saldo atual e dispon�vel: R$ %.2f", this.saldo);
	}
	
	public void comprovanteSaque(Conta conta, double valor) throws IOException {
        
		File diretorioRegistroTransacoes = new File ("./temp/");
        File historicoConta = new File (diretorioRegistroTransacoes.getAbsolutePath() + "\\historicoSaques.txt");

        if (!diretorioRegistroTransacoes.exists()) {
        	diretorioRegistroTransacoes.mkdirs();  //cria um diret�rio
       }

        if(!historicoConta.exists()) {
        	historicoConta.createNewFile();  //cria um arquivo
        }

       try(FileWriter historicoContaWriter = new FileWriter(historicoConta, true);
            BufferedWriter historicoContaBuff = new BufferedWriter(historicoContaWriter)) {

    	   historicoContaBuff.append("��������COMPROVANTE DE MOVIMENTA��O���������");
    	   historicoContaBuff.newLine();
    	   historicoContaBuff.append(conta.getCpfTitular() + "," + valor + ".");
    	   
    	   
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
