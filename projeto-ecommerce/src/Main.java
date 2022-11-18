import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.Produto;
import model.Usuario;

public class Main {
	
	// Lista
	static List<Usuario> userList = new ArrayList<>();
	static List<Produto> produtos = new ArrayList<>();
	// Objeto instanciados
	static Scanner sc = new Scanner(System.in);
	static Produto produto = new Produto();
	static Usuario user = new Usuario();

	public static void main(String[] args) {
		// Criando usuario adm
		userList.add(new Usuario("Usu�rio Adm", "70400654000198", "usuarioadm@xgames.com", "123456", "administrador"));

		// adicionando produtos
		// Camisetas
        produtos.add(new Produto("God of war 4", "Camiseta", 20, 49.99, "G", 1));
        produtos.add(new Produto("Sonic", "Camiseta", 15, 49.99, "M", 2));
        produtos.add(new Produto("M�rio", "Camiseta", 10, 49.99, "P", 3));
        //cal�as
        produtos.add(new Produto("Cal�a moletom - Preta", "Cal�a", 20, 99.99, "42", 4));
        produtos.add(new Produto("Cal�a jeans - Cinza escura", "Cal�a", 20, 95.99, "44", 5));
        produtos.add(new Produto("Cal�a moleto - Cinza chumbo", "Cal�a", 20, 80.90, "46", 6));
        //cal�ados
        produtos.add(new Produto("Air max - Nike", "Cal�ados", 20, 320.99, "42", 7));
        produtos.add(new Produto("Jordan 4 - Nike", "Cal�ados", 20, 122.90, "41", 8));
        produtos.add(new Produto("All Star", "Cal�ados", 20, 60.00, "36", 9));

		// variaveis
		String menu = 	"**************************************{ Seja Bem vindo � VestShow }**************************************\n" +
						"\t\t\t\t\tUma plataforma de ecommerce\n\n ";
		boolean isLoop = true;
		int opcao = 0;
		// Inicio----------------------------------------------------------------------------------------
		System.out.println(menu);
		System.out.println("1-Logar\n2-Cadastrar\n3-Entrar sem logar");
		user = escolherEntrada(isLoop, opcao);
		if (user == null) {
			produto.menu(produtos);	
			user = escolherEntrada(isLoop, opcao);
		}
		if (user != null) {
			System.out.println("\n******************{ Menu do " + user.getTipo() + " }******************\n");
			System.out.println("Ol� " + user.getNome() + "");
			if (user.getTipo().equals("administrador")) {
				menuPerfilAdm(produtos);
			} else if (user.getTipo().equals("cliente")) {
				produto.menuPerfilClient(produtos);
			}
		}
//		for (int i = 0; i < userList.size(); i++) {
//			System.out.println(userList.get(i));
//		}
		
	}

	public static Usuario escolherEntrada(boolean isLoop, int opcao) {
		while (isLoop) {
			System.out.print("Acesse a plataforma: ");
			opcao = sc.nextInt();
	        switch (opcao) {
	            case 1:
	            	return user.login(isLoop, userList);
	            case 2:
	                userList.add(user.cadastrar());
	                return user = user.login(isLoop, userList);
	            case 3:
	                isLoop = false;
	                break;
	            default:
	                System.err.println("\n*******Op��o inv�lido******\n");
	                break;
	        }
		}
		return null;
	}
	
	public static void menuPerfilAdm(List<Produto> lista) {
        Scanner sc = new Scanner(System.in);
        String menu =	"\n1- Ver menu \t\t\t5- Meus dados do usu�rio \n" +
						"2- Criar um novo produto \t6- Editar perfil do usu�rio \n" +
						"3- Editar produto \t\t7-Sair\n" +
						"4- Excluir produto\n" +
                "Qual op��o deseja acessar: ";
        System.out.print(menu);
        int opcao = sc.nextInt();
        boolean isLoop = true;
        while(isLoop){
            switch (opcao) {
                case 1:
                    produto.menu(lista);
                    menuPerfilAdm(lista);
                    isLoop = false;
                    break;
                case 2:
                    //create Producto
                    lista.add(produto.createProducto(lista));
                    isLoop = false;
                    break;
                case 3:
                    //editProducto(lista);
                    isLoop = false;
                    break;
                case 4:
                    //deleteProducto(lista)
                    isLoop = false;
                    break;
                case 5:
                    //myUser(lista)
                    isLoop = false;
                    break;
                case 6:
                    //editRoleUser(lista)
                    isLoop = false;
                    break;
                case 7:
                	System.out.println("Encerrando sess�o");
                    isLoop = false;
                    break;
                default:
                	System.err.println("\n*******Op��o inv�lido******\n");
                    break;
            }
        }

    }
}

