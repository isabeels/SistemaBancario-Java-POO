package br.com.residencia.poo.principal;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.residencia.poo.agencias.Agencia;
import br.com.residencia.poo.contas.Conta;
import br.com.residencia.poo.pessoas.Cliente;
import br.com.residencia.poo.pessoas.Diretor;
import br.com.residencia.poo.pessoas.Funcionario;
import br.com.residencia.poo.pessoas.Gerente;
import br.com.residencia.poo.pessoas.Presidente;

public class Principal {

	public static void main(String[] args) {
		/* Lista contas */
		List<Conta> contas;
		contas = new ArrayList<Conta>();
		
		/* Lista diretor */
		List<Diretor> diretores;
		diretores = new ArrayList<Diretor>();
		
		/* Lista gerencia*/
		List<Gerente> gerentes;
		gerentes = new ArrayList<Gerente>();
		
		/* Lista Clientes */
		List<Cliente> clientes;
		clientes = new ArrayList<Cliente>();
		
		/* Instancia��o de contas */
		Conta conta1 = new Conta(1, "3761", "Conta Corrente", "89121-5", 0.15, true, "123");
		System.out.println(conta1.toString());
		contas.add(conta1);
		
		Conta conta2 = new Conta(2, "1673", "Conta Corrente", "51219-8", 1562.00, true, "321");
		System.out.println(conta2.toString());
		contas.add(conta2);
		
		Date data = new Date();
		System.out.println(data);
		
	
		/* Adicionar uma lista para guardar todas as ag�ncias instanciadas */
		/* Sintaxe -> List<TIPO> nomedoobjeto = new LISTA<TIPO>; */
		List<Agencia> agencias;
		agencias = new ArrayList<Agencia>();
		
		/* Instancia��o de ag�ncia */
		Agencia ag = new Agencia(1, "Avenida das Palmeiras", 1000, 1005, 1001);
		agencias.add(ag);
		
		
		//System.out.println(agencias.get(0).getIdAgencia());
		
		
		
		//INSTANCIA��O DOS FUNCION�RIOS
		
		/* Gerente Gabriel */
		Gerente Gabriel = new Gerente("Gabriel", "todos","Casado", "1112223334", "22-999093552", "Rua da paix�o",
				"gabrieltsf10@gmail.com", data , "gerente", 2, "gabriel1", "gabriellocao",
				127.000, "Gerente geral", 1005, 1000);
		gerentes.add(Gabriel);
		
		
		/* Gerente Rosana */
		Gerente Rosana = new Gerente("Rosana", "Sem g�nero definido","Casada", "1112220004", "22-23252718", "Estrada novo circuito",
				"dantasdoqa@gmail.com", data , "gerente", 2, "gabriel1", "gabriellocao",
				127.000, "Gerente geral", 1005, 1000);
		gerentes.add(Rosana);
		
		
		/* Diretor Vin�cius */
		Diretor diretor = new Diretor("Vinicin", "N�o supomos genero", "Casado", "123999666-6", "22-1313131313", "Rua do cruzeiro",
				"vinivspdasgata@gmail.com", data , "diretor", 1001, "palha�o1", "fazumapalha�adinha",
				17.99, "Diretor geral das palha�adinha", 1000, agencias);
		diretores.add(diretor);
		
		/* Presidenta Isabel */
		Presidente Isabel = new Presidente("Isabel", "Ela/Dela", "Casada", "Informa��o Oculta", "21-1313131313", "Rua da Lideran�a",
			"devsixlider@devsix.br", data, "Presidente", 13, "adminlider", "naoousehackearapresidenta",
			199_488_130.12, "Presidente", 1, diretores);
		

		/* Instancia��o de clientes */
		Cliente Harumi = new Cliente("Harumi Hiroshi Jun", "Elu/Delu", "Casade", "9997775544", "21-87674552", "Kenji Akira Abenyu",
				"youtube@gmail.com", data, "Cliente", 2, 1005);
		clientes.add(Harumi);
		Cliente Lesheshenko = new Cliente("Lesheshenko Plushchenko", "Elu/Delu", "Casade", "9997775544", "21-87674552", "Romaniuk Avenue",
				"ukraineliberationarmy@gmail.com", data, "Cliente", 2, 1005);
		clientes.add(Lesheshenko);
		Cliente HarumotoYakasaki = new Cliente("HarumotoYakasaki", "Elu/Delu", "Casade", "9997775544", "21-87674552", "Tsumago-juku",
				"tsumagojuku@gmail.com.jp", data, "Cliente", 3, 1005);
		clientes.add(HarumotoYakasaki);
		Cliente Wladimir = new Cliente("Wladimir BERELOWITCH", "Elu/Delu", "Solteire", "9997775544", "21-87674552", "Nikolai Dimitri Street",
				"russianvodkaforfree@gmail.com", data, "Cliente/Vendedor de Vodka", 4, 1005);
		clientes.add(Wladimir);
		Cliente Kwasi = new Cliente("Kwasi Oppong", "Elu/Delu", "Casade", "9997775544", "21-87674552", "Fumesua-Kokobra",
				"shumetsu@gmail.com", data, "Cliente", 5, 1005);
		clientes.add(Kwasi);
		Cliente Yousaf = new Cliente("Yousaf Manzil", "Elu/Delu", "Casade", "9997775544", "21-87674552", "Malik-Salam",
				"shumetsu@gmail.com", data, "Cliente", 5, 1005);
		clientes.add(Yousaf);
		Cliente Chaikinoy = new Cliente("Chaikinoy", "Elu/Delu", "Casade", "9997775544", "21-87674552", "Rua �rabe",
				"shumetsu@gmail.com", data, "Cliente", 5, 1005);
		clientes.add(Chaikinoy);
		
		
		System.out.print("\n");
		/* Output dos clientes */
		for(int i=0;i<clientes.size();i++) {
			System.out.println("Cliente "+(i+1)+": ");
			System.out.print(clientes.get(i).getNome()+"\n");
		}
		System.out.print("\n");
		/* Output dos Gerentes */
		for(int i=0;i<gerentes.size();i++) {
			System.out.println("Gerente "+(i+1)+": ");
			System.out.println(gerentes.get(i).getNome()+"\n");
		}
		System.out.print("\n");
		
		/* Output dos Diretores */
		for(int i=0;i<diretores.size();i++) {
			System.out.println("Diretor "+(i+1)+": ");
			System.out.print(diretores.get(i).getNome()+"\n");
		}
		System.out.print("\n");
		
		/* Output das Agencias */
		for(int i=0;i<agencias.size();i++) {
			System.out.println("Agencia "+(i+1)+": ");
			System.out.println(agencias.get(i).getIdAgencia()+"\n");
		}
		System.out.print("\n");
		
		

	}

}
