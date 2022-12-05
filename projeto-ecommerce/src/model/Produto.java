package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import javax.swing.tree.RowMapper;

public class Produto {
	private String nome;
	private String categoria;
	private int quantidade;
	private double preco;
	private String tamanho;
	private long codigo;

	private static Scanner sc = new Scanner(System.in);
	
	public Produto() {
	}

	public Produto(String nome, String categoria, int quantidade, double preco, String tamanho, long codigo) {
		this.nome = nome;
		this.categoria = categoria;
		this.quantidade = quantidade;
		this.preco = preco;
		this.tamanho = tamanho;
		this.codigo = codigo;
	}

	public boolean viewEstoque(List<Produto> lista) {
		List<String> listaCategoria = categoryOption(lista);
		if (listaCategoria == null) {
			System.out.println(
					" ______________________________________________________________________________________________________________");
			return false;
		}
		System.out.println(
				"\n*******************************************{ Informações do Produto }*******************************************\n"
						+ "Nome " + "\t\t\t\tCategoria" + "\t\tPreço" + "\t\tTamanho" + "\t\tQuantidade"
						+ "\t\tCódigo");
		if (listaCategoria.size() < 2) {
			lista.stream().distinct().forEach(f -> {
				if (f.categoria.equals(listaCategoria.get(0))) {
					System.out.println(f.imprimirTabela(f.nome.length()));
				}
			});
		} else {
			lista.stream().distinct().forEach(f -> {
				System.out.println(f.imprimirTabela(f.nome.length()));
			});
		}
		return true;
	}

//	public void menuPerfilClient(List<Produto> lista) {
//		viewEstoque(lista);
//		String menu = "Gostou de algum produto?\n"
//				+ "Caso queira comprar, digite o número do código da compra ou 0 para sair da compra:  ";
//		System.out.println(menu);
//		int opcao = sc.nextLine();
//		if (opcao == 0) {
//			return;
//		}
//		// TODO rever esse metodo de procurar codigo do produto
//		boolean isLoop = true;
//		do {
////            lista.forEach(f -> f.codigo.equals("1"));
//		} while (isLoop);
//	}

	private List<String> categoryOption(List<Produto> lista) {
		boolean isLoop = true;
		int opcao = 0;
		List<String> categoria = new ArrayList<>();
		System.out.println(
				"\n**************************************************{ Categoria }*************************************************");
		for (int i = 0; i < lista.size(); i++) {
			categoria.add(lista.get(i).categoria);
		}
		categoria = categoria.stream().distinct().collect(Collectors.toList());
		while (isLoop) {
			int count = 1;
			for (int j = -1; j < categoria.stream().distinct().collect(Collectors.toList()).size(); j++) {
				if (j == -1) {
					System.out.println(0 + "- " + "Ir ao Menu");
				} else {
					System.out.println((count++) + "- " + categoria.get(j));
				}
			}
			System.out.println((count) + "- " + "Todos");
			System.out.print("Digite qual deseja acessar: ");
			opcao = Integer.parseInt(sc.nextLine());

			if (opcao == 0) {
				isLoop = false;
			} else if (opcao == count) {
				return categoria;
			} else if (opcao - 1 >= categoria.size() || opcao - 1 < 0) {
				System.err.println("\n*******Opção inválido******\n");
			} else {
				List<String> categoriaReserva = new ArrayList<>();
				categoriaReserva.add(categoria.get(opcao - 1));
				return categoriaReserva;
			}
		}
		return null;
	}

	private String imprimirTabela(int tamanhoPalavra) {
		String tabela = "";
		if (tamanhoPalavra < 8) {
			tabela = "________________________________________________________________________________________________________________\n"
					+ nome + "\t\t\t\t" + categoria + "\t\t" + preco + "\t\t" + tamanho + "\t\t" + quantidade + "\t\t"
					+ codigo;

		} else if (tamanhoPalavra > 8 && tamanhoPalavra < 10) {
			tabela = "________________________________________________________________________________________________________________\n"
					+ nome + "\t\t\t\t" + categoria + "\t\t" + preco + "\t\t" + tamanho + "\t\t" + quantidade + "\t\t"
					+ codigo;
		} else if (tamanhoPalavra >= 10 && tamanhoPalavra <= 15) {
			tabela = "________________________________________________________________________________________________________________\n"
					+ nome + "\t\t\t" + categoria + "\t\t" + preco + "\t\t" + tamanho + "\t\t" + quantidade + "\t\t"
					+ codigo;
		} else if (tamanhoPalavra > 15 && tamanhoPalavra <= 20) {
			tabela = "________________________________________________________________________________________________________________\n"
					+ nome + "\t\t" + categoria + "\t\t\t" + preco + "\t\t" + tamanho + "\t\t" + quantidade + "\t\t"
					+ codigo;
		} else if (tamanhoPalavra > 20 && tamanhoPalavra < 26) {
			tabela = "________________________________________________________________________________________________________________\n"
					+ nome + "\t\t" + categoria + "\t\t\t" + preco + "\t\t" + tamanho + "\t\t" + quantidade + "\t\t"
					+ codigo;
		} else if (tamanhoPalavra >= 26 && tamanhoPalavra <= 30) {
			tabela = "________________________________________________________________________________________________________________\n"
					+ nome + "\t" + categoria + "\t\t\t" + preco + "\t\t" + tamanho + "\t\t" + quantidade + "\t\t"
					+ codigo;
		} else {
			tabela = "________________________________________________________________________________________________________________\n"
					+ nome + "\t\t\t" + categoria + "\t\t" + preco + "\t\t" + tamanho + "\t\t" + quantidade + "\t\t"
					+ codigo;
		}
		tabela += "\n________________________________________________________________________________________________________________\n";
		return tabela;
	}

	public void createProducto(List<Produto> lista) {
		long maiorNumero = 0;
		System.out.println(
				"\n*********************************************{ Criação do Produto }********************************************");
		System.out.print("Nome: ");
		nome = sc.nextLine();
		System.out.print("Categoria: ");
		categoria = sc.nextLine();
		System.out.print("Tamanho: ");
		tamanho = sc.nextLine();
		System.out.print("Preço: ");
		preco = Double.parseDouble(sc.nextLine());
		System.out.print("Quantidade: ");
		quantidade = Integer.parseInt(sc.nextLine());
		for (int i = 0; i < lista.size(); i++) {
			if(maiorNumero < lista.get(i).codigo)
				maiorNumero = lista.get(i).codigo;
		}
		codigo = maiorNumero + 1;
		lista.add(new Produto(nome, categoria, quantidade, preco, tamanho, codigo));
		System.out.println(
				"________________________________________________________________________________________________________________\n");
	}

	public void editProducto(List<Produto> lista, boolean loop, int posicao) {
		Produto produtoEncontrado = new Produto();
		Produto produtoAntigo = new Produto();
		String campos;
		String[] splitCampos;
		System.out.println(
				"\n*********************************************{ Edição de Produtos }********************************************");
		System.out.println(
				" ______________________________________________________________________________________________________________\n"
						+ "|\t\t O modelo de edição funciona com o nome do campo separado por '-' (traço) da seguinte forma:   |"
						+ "\n| \t\t\t\t  O que deseja editar: nome-tamanho-preço\t\t\t\t       |"
						+ "\n|______________________________________________________________________________________________________________|\n\n");

		produtoEncontrado = searchProduct(lista, true, posicao);
		produtoAntigo.setNome(produtoEncontrado.getNome());
		produtoAntigo.setCategoria(produtoEncontrado.getCategoria());
		produtoAntigo.setTamanho(produtoEncontrado.getTamanho());
		produtoAntigo.setPreco(produtoEncontrado.getPreco());
		produtoAntigo.setQuantidade(produtoEncontrado.getQuantidade());
		produtoAntigo.setCodigo(produtoEncontrado.getCodigo());
		loop = true;
		System.out.println(
				"\n*******************************************{ Informações do Produto }*******************************************\n"
						+ "Nome " + "\t\t\t\tCategoria" + "\t\tPreço" + "\t\tTamanho" + "\t\tQuantidade"
						+ "\t\tCódigo");
		System.out.println(produtoEncontrado.imprimirTabela(produtoEncontrado.nome.length()));
		System.out.print("\nO que deseja editar: ");
		sc.reset();
		campos = sc.nextLine();
		campos.trim().toLowerCase();
		splitCampos = campos.split("-");
		for (int i = 0; i < splitCampos.length; i++) {
			if (splitCampos[i] != null) {
				if (splitCampos[i].contains("nome")) {
					System.out.print("Digite o nome: ");
					produtoEncontrado.setNome(sc.nextLine());
				} else if (splitCampos[i].contains("categoria")) {
					System.out.print("Digite a categoria: ");
					produtoEncontrado.setCategoria(sc.nextLine());
				} else if (splitCampos[i].contains("preco") || splitCampos[i].contains("preço")) {
					System.out.print("Digite o preço: ");
					produtoEncontrado.setPreco(Double.parseDouble(sc.nextLine()));
				} else if (splitCampos[i].contains("tamanho")) {
					System.out.print("Digite o tamanho: ");
					produtoEncontrado.setTamanho(sc.nextLine());
				} else if (splitCampos[i].contains("quantidade")) {
					System.out.print("Digite a quantidade: ");
					produtoEncontrado.setQuantidade(Integer.parseInt(sc.nextLine()));
				} else {
					System.err.println("O campo '" + splitCampos[i] + "' não foi encontrado!");
				}
			}

		}
		while (loop) {
			System.out.println(
					"\n*******************************************{ Informações do Produto }*******************************************\n"
							+ "Nome " + "\t\t\t\tCategoria" + "\t\tPreço" + "\t\tTamanho" + "\t\tQuantidade"
							+ "\t\tCódigo");
			System.out.println(produtoEncontrado.imprimirTabela(produtoEncontrado.nome.length()));
			System.out.print("Deseja realmente alterar esse produto s/n: ");
			switch (sc.nextLine().toLowerCase().charAt(0)) {
			case 's': {
				lista.add(produtoEncontrado);
				System.out.println("Alterado com sucesso!");
				loop = false;
				break;
			}
			case 'n': {
				lista.remove(produtoEncontrado);
				lista.add(produtoAntigo);
				return;
			}
			default:
				System.err.println("\n*******Opção inválido******\n");
			}
		}

	}

	public void deleteProducto(List<Produto> lista, boolean loop, int posicao) {
		
		System.out.println(
				"\n********************************************{ Exclusão de Produtos }*******************************************");
		Produto produtoEncontrado = null;
		produtoEncontrado = searchProduct(lista, true, posicao);
		loop = true;
		while (loop) {
			System.out.println(
					"\n*******************************************{ Informações do Produto }*******************************************\n"
							+ "Nome " + "\t\t\t\tCategoria" + "\t\tPreço" + "\t\tTamanho" + "\t\tQuantidade"
							+ "\t\tCódigo");
			System.out.println(produtoEncontrado.imprimirTabela(produtoEncontrado.nome.length()));
			System.out.print("Deseja realmente Excluir esse produto s/n: ");
			switch (sc.nextLine().toLowerCase().charAt(0)) {
			case 's': {
				for (int i = 0; i < 2; i++) {
					lista.remove(produtoEncontrado);					
				}
				System.out.println("Excluído com sucesso!");
				loop = false;
				break;
			}
			case 'n': {
				return;
			}
			default:
				System.err.println("\n*******Opção inválido******\n");
			}
		}

	}

	private Produto searchProduct(List<Produto> lista, boolean loop, int posicao) {
		long codigoEscolhido;
		System.out.print("\nDigite o código do " + (posicao) + "º produto: ");
		codigoEscolhido = Long.parseLong(sc.nextLine());
		for (Produto produtoEncontrado : lista) {
			if (produtoEncontrado.codigo == codigoEscolhido) {
				return produtoEncontrado;
			}
		}
		System.err.println("\n*******Produto não encontrado******\n");
		backEstoque(lista, loop, posicao);
		return null;
	}

	/*
	 * Foi necessário criar esse metodo pois a mensagem "Produto não encontrado"
	 * estava sobreescrevendo o "Deseja acessar o menu estoque ...." trazendo
	 * confusão no console.
	 */
	private void backEstoque(List<Produto> lista, boolean loop, int posicao) {
		while (loop) {
			System.out.print("Deseja acessar o menu estoque para rever o produto s/n: ");
			switch (sc.nextLine().toLowerCase().charAt(0)) {
			case 's': {
				viewEstoque(lista);
				searchProduct(lista, loop, posicao);
				loop = false;
				break;
			}
			case 'n': {
				searchProduct(lista, loop, posicao);
				loop = false;
				break;
			}
			default:
				System.err.println("\n*******Opção inválido******\n");
			}
		}
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public String getTamanho() {
		return tamanho;
	}

	public void setTamanho(String tamanho) {
		this.tamanho = tamanho;
	}

	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

}