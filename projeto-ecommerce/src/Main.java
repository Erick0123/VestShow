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
		userList.add(new Usuario("Erick Alan", "70400654000198", "usuario.adm@gmail.com", "123456", "administrador"));

		// adicionando produtos
		// Camisetas
        produtos.add(new Produto("God of war 4", "Camiseta", 20, 49.99, "G", 1));
        produtos.add(new Produto("Sonic", "Camiseta", 15, 49.99, "M", 2));
        produtos.add(new Produto("Mario Kart", "Camiseta", 10, 49.99, "P", 3));
        //calças
        produtos.add(new Produto("Calça moletom - Preta", "Calça", 20, 99.99, "42", 4));
        produtos.add(new Produto("Calça jeans - Cinza escura", "Calça", 20, 95.99, "44", 5));
        produtos.add(new Produto("Calça moleto - Cinza chumbo", "Calça", 20, 80.90, "46", 6));
        //calçados
        produtos.add(new Produto("Air max - Nike", "Calçados", 20, 320.99, "42", 7));
        produtos.add(new Produto("Jordan 4 - Nike", "Calçados", 20, 122.90, "41", 8));
        produtos.add(new Produto("All Star", "Calçados", 20, 60.00, "36", 9));

		// variaveis
		boolean loop = true;
		int opcao = 0;
		String menu = 	"******************************************{ Seja Bem vindo à VestShow }*****************************************\n\n" +
													"\t\t\t\t\t   Uma plataforma de ecommerce ";
		
		
		//---------------------------------------inicio da aplicação-------------------------------------------
		System.out.println(menu);
		do {
			if(!produto.viewEstoque(produtos)) {
				loop = false;
			};			
		} while(loop);		
		user = startApplication(loop, opcao);
		if (user != null) {
			System.out.println("\n*******************************************{ Menu do " + user.getTipo() + " }*******************************************\n");
			System.out.println(" ______________________________________________________________________________________________________________\n" +
			  "| \t\t\t\t  Seja bem vindo a central do " + user.getTipo() + ", " + user.getNome() + "\t\t\t       |" +
			"\n|______________________________________________________________________________________________________________|");
			
			if (user.getTipo().equals("administrador")) {
				menuPerfilAdm(produtos);
			} else if (user.getTipo().equals("cliente")) {
				produto.menuPerfilClient(produtos);
			}
		}
	}

	public static Usuario startApplication(boolean isLoop, int opcao) {
		isLoop = true;
		String entrada =
				  "| \t\t\t\t   Os passos a seguir é necessário fazer login!\t\t\t\t       |" +
				"\n|______________________________________________________________________________________________________________|\n\n" + 
				"0- Sair da plataforma\n1- Logar\n2- Cadastrar\n3- Entrar sem logar";
		
		String saida =
				  " ______________________________________________________________________________________________________________\n" +
				  "| \t\t\t\t\t   Obrigado e volte sempre :) \t\t\t\t\t       |" +
				"\n|______________________________________________________________________________________________________________|";
		System.out.println(entrada);
		while (isLoop) {
			System.out.print("Acesse a plataforma: ");
			opcao = sc.nextInt();
	        switch (opcao) {
            	case 0:
            		System.out.println(saida);
            		isLoop = false;
            		break;
	            case 1:
	            	return user.login(isLoop, userList);
	            case 2:
	                userList.add(user.cadastrar());
	                return user = user.login(isLoop, userList);
	            case 3:
	                isLoop = false;
	                break;
	            default:
	                System.err.println("\n*******Opção inválido******\n");
	                break;
	        }
		}
		return null;
	}
	
	public static void menuPerfilAdm(List<Produto> lista) {
        Scanner sc = new Scanner(System.in);
        String menu =	"\n1- Ver estoque \t\t\t5- Meus dados do usuário \n" +
						"2- Criar um novo produto \t6- Editar perfil do usu�rio \n" +
						"3- Editar produto \t\t7-Sair\n" +
						"4- Excluir produto\n" +
                "Qual opção deseja acessar: ";
        System.out.print(menu);
        int opcao = sc.nextInt();
        boolean isLoop = true;
        while(isLoop){
            switch (opcao) {
                case 1:
                	//view estoque
                    produto.viewEstoque(lista);
                    menuPerfilAdm(lista);
                    break;
                case 2:
                    //create Producto
                    produto.createProducto(lista);
                    menuPerfilAdm(lista);
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
                	System.out.println("Encerrando sessão");
                    isLoop = false;
                    break;
                default:
                	System.err.println("\n*******Opção inválido******\n");
                    break;
            }
        }

    }
}

