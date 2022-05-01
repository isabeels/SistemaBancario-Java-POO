package br.com.residencia.poo.menu;

import java.io.IOException;
import java.util.InputMismatchException;

import br.com.residencia.poo.io.LeituraEscrita;
import br.com.residencia.poo.contas.Conta;
import br.com.residencia.poo.contas.ContaCorrente;
import br.com.residencia.poo.contas.ContaPoupanca;
import br.com.residencia.poo.enums.TipoConta;
import br.com.residencia.poo.enums.TipoPessoa;
import br.com.residencia.poo.exceptions.ContaException;
import br.com.residencia.poo.pessoas.Usuario;
import br.com.residencia.poo.principal.Principal;
import br.com.residencia.poo.interfaces.Taxas;

public class MenuRelatorio {
	
	Principal principal = new Principal();
	int teste;
	int operacao;
	MenuPrincipal menuPrincipal = new MenuPrincipal();

	public static void menuRelatorio(Integer idUsuario, Usuario usuario, Conta conta)
			throws IOException, InputMismatchException, NullPointerException, ContaException {

		Principal principal = new Principal();

		principal.pulaLinha();

		if (conta.getTipoConta().equalsIgnoreCase(TipoConta.CORRENTE.getTipoConta())) {
			System.out.println("[1]\tTributa��o conta corrente");
		} else {
			System.out.println("[1]\tSimulacao de rendimento");
		}
		if (idUsuario == 2) {
			System.out.println("[2]\tRelat�rio de contas gerenciadas");
		} else if (idUsuario == 3) {
			System.out.println("[2]\tRelat�rio de todas as contas e ag�ncias");
		} else if (idUsuario == 4){
			System.out.println("[2]\tRelat�rio com o total de capital do Banco");
		}
		
		System.out.println("[3]\tVoltar");
		imprimeLinhaHorizontal();

		System.out.print("Digite a op��o desejada: ");
		int operacao = Principal.sc.nextInt();

		principal.pulaLinha();

		switch (operacao) {

		case 1:

			if (conta.getTipoConta().equalsIgnoreCase(TipoConta.CORRENTE.getTipoConta())) {

				System.out.printf("O total gasto com opera��es foi de R$%.2f%n",
						((ContaCorrente) conta).getTotalTributado());
				System.out.printf("O valor cobrado para cada saque � de R$%.2f%n", Taxas.SAQUE);
				System.out.println("Total de saques realizados: " + ((ContaCorrente) conta).getTotalSaques());
				System.out.printf("O valor cobrado para cada deposito � de R$%.2f%n", Taxas.DEPOSITO);
				System.out.println("Total de dep�sitos realizados: " + ((ContaCorrente) conta).getTotalDepositos());
				System.out.printf("O valor cobrado para cada tranfer�ncia � de R$%.2f%n", Taxas.TRANSFERENCIA);
				System.out.println(
						"Total de transfer�ncias realizadas: " + ((ContaCorrente) conta).getTotalTransferencias());
				Integer totalDep = ((ContaCorrente) conta).getTotalSaques();
				Integer totalSaq =  ((ContaCorrente) conta).getTotalDepositos();
				LeituraEscrita.relatorioTributacaoContaCorrente(conta, totalDep, totalSaq);
				System.out.println("\nSeu arquivo foi gerado com sucesso!");
			} else {

				int inputDias;
				double inputValor;

				System.out.println("Digite o valor que deseja usar para a simula��o: ");
				inputValor = Double.parseDouble(Principal.sc.next());

				System.out.println("Digite o n�mero de dias para a simula��o: ");
				inputDias = Principal.sc.nextInt();

				((ContaPoupanca) conta).previsaoDeRendimento(inputValor, inputDias);

				LeituraEscrita.relatorioRendimentoPoupanca(conta, inputValor, inputDias);
				
				System.out.println("\nSeu arquivo foi gerado com sucesso!");
			}

			selecaoRelatorio(conta, usuario);

			break;

		case 2:
			if (idUsuario == 2) {
				LeituraEscrita.relatorioContasGerenciadas(conta);
				System.out.println("\nSeu arquivo foi gerado com sucesso!");
			}
		 else if (idUsuario == 3){
			 for (String cpf : Conta.mapaContas.keySet()) {
			System.out.println(Conta.mapaContas.get(cpf));
			 }
			 
			 LeituraEscrita.relatorioContasPorAgencia(conta);
			 System.out.println("\nSeu arquivo foi gerado com sucesso!");
		} else {
				LeituraEscrita.relatorioTotalCapital(conta, Conta.mapaContas);
				System.out.println("\nSeu arquivo foi gerado com sucesso!");
			}
			
			selecaoRelatorio(conta, usuario);

			break;
			
		

		case 3:

			principal.limpaTela();
			MenuPrincipal.menuPrincipal(usuario, conta);
			break;

		default:

			principal.pulaLinha();
			System.out.println("Op��o inv�lida!");
			selecaoRelatorio(conta, usuario);

		}

	}

	public static void selecaoRelatorio(Conta conta, Usuario usuario)
			throws InputMismatchException, NullPointerException, ContaException, IOException {

		if (usuario.getTipoUsuario().equalsIgnoreCase(TipoPessoa.CLIENTE.getTipoUsuario())) {
			MenuRelatorio.menuRelatorio(TipoPessoa.CLIENTE.getIdTipoUsuario(), usuario, conta);
		} else if (usuario.getTipoUsuario().equalsIgnoreCase(TipoPessoa.GERENTE.getTipoUsuario())) {
			MenuRelatorio.menuRelatorio(TipoPessoa.GERENTE.getIdTipoUsuario(), usuario, conta);
		} else if (usuario.getTipoUsuario().equalsIgnoreCase(TipoPessoa.DIRETOR.getTipoUsuario())) {
			MenuRelatorio.menuRelatorio(TipoPessoa.DIRETOR.getIdTipoUsuario(), usuario, conta);
		} else if (usuario.getTipoUsuario().equalsIgnoreCase(TipoPessoa.PRESIDENTE.getTipoUsuario())) {
			MenuRelatorio.menuRelatorio(TipoPessoa.PRESIDENTE.getIdTipoUsuario(), usuario, conta);
		}
	}

	public static void imprimeLinhaHorizontal() {
		System.out.println("--------------------------------------------------");
	}

}