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
		
		
		//--------------------------------------- inicio da aplicação-------------------------------------------
		System.out.println(menu);
		viewEstoque(loop);
		user = startApplication(loop, opcao);
		if (user != null) {
			System.out.println(" ______________________________________________________________________________________________________________\n" +
			  "| \t\t\t\t  Seja bem vindo a central do " + user.getTipo() + ", " + user.getNome() + "\t\t\t       |" +
			"\n|______________________________________________________________________________________________________________|");
			
			if (user.getTipo().equals("administrador")) {
				menuPerfilAdm(produtos, loop);
			} else if (user.getTipo().equals("cliente")) {
//				produto.menuPerfilClient(produtos);
			}
		}
	}
	//--------------------------------------- final da aplicação-------------------------------------------
	
	//---------------------------------------     métodos   -------------------------------------------
	public static void viewEstoque(boolean loop) {
		do {
			if(!produto.viewEstoque(produtos)) {
				loop = false;
			};			
		} while(loop);		
	}

	public static Usuario startApplication(boolean isLoop, int opcao) {
		String entrada =
				  "| \t\t\t\t   Os passos a seguir é necessário fazer login!\t\t\t\t       |" +
				"\n|______________________________________________________________________________________________________________|\n\n" + 
				"0- Sair da plataforma\n1- Logar\n2- Cadastrar";
		
		String saida =
				  " ______________________________________________________________________________________________________________\n" +
				  "| \t\t\t\t\t   Obrigado e volte sempre :) \t\t\t\t\t       |" +
				"\n|______________________________________________________________________________________________________________|";
		System.out.println(entrada);
		while (isLoop) {
			System.out.print("Acesse a plataforma: ");
			opcao = Integer.parseInt(sc.nextLine());
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
	            default:
	                System.err.println("\n*******Opção inválido******\n");
	                break;
	        }
		}
		return null;
	}
	
	public static void menuPerfilAdm(List<Produto> lista, boolean isLoop) {
        Scanner sc = new Scanner(System.in);
        int quantidade = 0, opcao;
        String menu =	"\n1- Ver estoque \t\t\t5- Meus dados do usuário \n" +
						"2- Criar um novo produto \t6- Editar perfil do usuário \n" +
						"3- Editar produto \t\t7-Sair\n" +
						"4- Excluir produto\n" +
                "Qual opção deseja acessar: ";
        System.out.println("\n*******************************************{ Menu do " + user.getTipo() + " }*******************************************\n");
        System.out.print(menu);
        opcao = Integer.parseInt(sc.nextLine());
        isLoop = true;
        while(isLoop){
            switch (opcao) {
                case 1:
                	//view estoque
                    produto.viewEstoque(lista);
                	viewEstoque(isLoop);
                    menuPerfilAdm(lista, isLoop);
                    break;
                case 2:
                    //create Producto
                    produto.createProducto(lista);
                    menuPerfilAdm(lista, isLoop);
                    break;
                case 3:
                    //edition Producto
                	while (isLoop) {
            			System.out.print("Quantos produtos deseja editar: ");
            			quantidade = Integer.parseInt(sc.nextLine());
            			if (quantidade < 1) {
            				System.err.println("a quantidade deve ser maior que 0!");
            				isLoop = true;
            			} else {
            				isLoop = false;
            			}
            		}
                	for (int i = 0; i < quantidade; i++) {
                		produto.editProducto(lista, isLoop, (i + 1));						
					}
                	menuPerfilAdm(lista, isLoop);
                    break;
                case 4:
                    //delete Producto
                	System.out.print("Quantos produtos deseja Excluir: ");
        			quantidade = Integer.parseInt(sc.nextLine());
        			if (quantidade < 1) {
        				System.err.println("a quantidade deve ser maior que 0!");
        				isLoop = true;
        			} else {
        				isLoop = false;
        			}
	            	for (int i = 0; i < quantidade; i++) {
	            		produto.deleteProducto(lista, isLoop, (i + 1));						
					}
                	menuPerfilAdm(lista, isLoop);
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

