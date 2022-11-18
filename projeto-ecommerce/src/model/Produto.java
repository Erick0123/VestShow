package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Produto {
	private String nome;
    private String categoria;
    private int quantidade;
    private double valor;
    private String tamanho;
    private long codigo;

    public Produto() { }

    public Produto(String nome, String categoria, int quantidade, double valor, String tamanho, long codigo) {
        this.nome = nome;
        this.categoria = categoria;
        this.quantidade = quantidade;
        this.valor = valor;
        this.tamanho = tamanho;
        this.codigo = codigo;
    }

    public void menu(List<Produto> lista) {
        List<String> listaCategoria = categoryOption(lista);
        System.out.println(
                    "\n*******************************************{ Informa��es do Produto }*******************************************\n"+
                    "Nome " +  "\t\t\t\tCategoria"   + "\t\tValor"    +     "\t\tTamanho"   +      "\t\tEstoque" + "\t\tC�digo");
        if (listaCategoria.size() < 2) {
            lista.stream().distinct().forEach(f -> {
                if (f.categoria ==  listaCategoria.get(0)) {
                    System.out.println(f.imprimirTabela(f.nome.length()));
                }
            });
        } else {
            lista.stream().distinct().forEach(f -> {
                System.out.println(f.imprimirTabela(f.nome.length()));
            });
        }
        System.out.println("________________________________________________________________________________________________________________\n");
    }

    public void menuPerfilClient(List<Produto> lista) {
        menu(lista);
        Scanner sc = new Scanner(System.in);
        String menu =   "Gostou de algum produto?\n"+
        "Caso queira comprar, digite o n�mero do c�digo da compra ou 0 para sair da compra:  ";
        System.out.println(menu);
        int opcao = sc.nextInt();
        if (opcao == 0) {
            return;
        }
        //TODO rever esse metodo de procurar codigo do produto
        boolean isLoop = true;
        do{
//            lista.forEach(f -> f.codigo.equals("1"));
        }while(isLoop);
    }

    

    private List<String> categoryOption(List<Produto> lista) {
        Scanner sc = new Scanner(System.in);
        boolean isLoop = true;
        int opcao =0;
        List<String> categoria = new ArrayList<>();
        System.out.println("\n******************{ Categoria }******************\n");
        for (int i = 0; i < lista.size(); i++) {
            categoria.add(lista.get(i).categoria);
        }
        categoria = categoria.stream().distinct().collect(Collectors.toList());
        while(isLoop){
            int count = 1;
            for (int j = 0; j < categoria.stream().distinct().collect(Collectors.toList()).size(); j++) {
                System.out.println((count++) + " - " + categoria.get(j));
            }
            System.out.println((count) + " - " + "Todos");
            System.out.print("Digite qual deseja acessar: ");
            opcao = sc.nextInt();

            if (opcao == count) {
                return categoria;
            }else if (opcao - 1 >= categoria.size()) {
            	System.err.println("\n*******Op��o inv�lido******\n");
            }else  {
                List<String> categoriaReserva = new ArrayList<>();
                categoriaReserva.add(categoria.get(opcao-1));
                return categoriaReserva;
            }
        }
        return null;
    }

    private String imprimirTabela(int tamanhoPalavra) {
        String tabela = "";
        if (tamanhoPalavra < 8) {
            tabela =
            "________________________________________________________________________________________________________________\n" +
            nome  +  "\t\t\t\t"+categoria  +  "\t\t"+valor  +   "\t\t"+tamanho   +   "\t\t"+quantidade + "\t\t" + codigo;

        }else if (tamanhoPalavra > 8 && tamanhoPalavra <= 10 ) {
            tabela =
            "________________________________________________________________________________________________________________\n" +
            nome  +  "\t\t\t\t"+categoria  +  "\t\t"+valor  +   "\t\t"+tamanho   +   "\t\t"+quantidade + "\t\t" + codigo;
        } else if (tamanhoPalavra > 10 && tamanhoPalavra <= 15) {
            tabela =
            "________________________________________________________________________________________________________________\n" +
            nome  +  "\t\t\t"+categoria  +  "\t\t"+valor  +   "\t\t"+tamanho   +   "\t\t"+quantidade + "\t\t" + codigo;
        }else if (tamanhoPalavra > 15 && tamanhoPalavra <= 20) {
            tabela =
            "\t\t________________________________________________________________________________________________________________\n" +
            nome  +  "\t\t\t"+categoria  +  "\t\t\t"+valor  +   "\t\t"+tamanho   +   "\t\t"+quantidade + "\t\t" + codigo;
        }else if (tamanhoPalavra > 20 && tamanhoPalavra < 26) {
            tabela =
            "________________________________________________________________________________________________________________\n" +
            nome  +  "\t\t"+categoria  +  "\t\t\t"+valor  +   "\t\t"+tamanho   +   "\t\t"+quantidade + "\t\t" + codigo;
        }else if (tamanhoPalavra >= 26 && tamanhoPalavra <= 30 ) {
            tabela =
            "________________________________________________________________________________________________________________\n" +
            nome  +  "\t"+categoria  +  "\t\t\t"+valor  +   "\t\t"+tamanho   +   "\t\t"+quantidade + "\t\t" + codigo;
        }else {
            tabela =
            "________________________________________________________________________________________________________________\n" +
            nome  +  "\t\t\t"+categoria  +  "\t\t"+valor  +   "\t\t"+tamanho   +   "\t\t"+quantidade + "\t\t" + codigo;
        }
        return tabela;
    }

    public Produto createProducto(List<Produto> lista) {
        Scanner sc = new Scanner(System.in);
        System.out.println("******************************************{ Cria��o do Produto }******************************************\n\n");
        System.out.print("Nome: ");
        nome = sc.next();
        System.out.print("Categoria: ");
        categoria = sc.next();
        System.out.print("Pre�o: ");
        valor = sc.nextDouble();
        System.out.print("Tamanho: ");
        tamanho = sc.next();
        System.out.print("Quantidade: ");
        quantidade = sc.nextInt();
        codigo = lista.size() + 1;
        return new Produto(nome, categoria, quantidade, valor, tamanho, codigo);
    }

}
