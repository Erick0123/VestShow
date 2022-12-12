package model;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Usuario {
	private String nome;
    private String cpfCnpj;
    private String email;
    private String senha;
    private String perfil;
    //private Banco banco;
    
    private static Scanner sc = new Scanner(System.in);

    public Usuario() { }

    public Usuario(String nome, String cpfCnpj, String email, String senha, String perfil) {
        this.nome = nome;
        this.cpfCnpj = cpfCnpj;
        this.email = email;
        this.senha = senha;
        this.perfil = perfil;
    }

    public Usuario cadastrar() {
        System.out.print("\n\t\tDigite seu nome: " );
        nome = sc.nextLine();
        System.out.print("\t\tDigite seu email: ");
        email = sc.nextLine();
        System.out.print("\t\tDigite seu CPF ou CNPJ: ");
        cpfCnpj = sc.nextLine();
        System.out.print("\t\tDigite sua senha: ");
        senha = sc.nextLine();
        perfil = "cliente";
        return new Usuario(nome, cpfCnpj, email, senha, perfil);
    }

    public Usuario login(boolean isLoop, List<Usuario> userList) {
        String emailCpfCnpj, senha;
        Usuario user = new Usuario();
        while (isLoop) {
            System.out.println("\n***************************************************{ Login }***************************************************\n");
            System.out.print("Email ou CPF ou CNPJ: ");
            emailCpfCnpj = sc.nextLine();
            System.out.print("Senha: ");
            senha = sc.nextLine();
            System.out.println();
            for (Usuario u : userList) {
                if ((emailCpfCnpj.equals(u.getCpfCnpj()) || emailCpfCnpj.equals(u.getEmail())) && senha.equals(u.getSenha())) {
                    user = u;
                    isLoop = false;
                }
            }
            if ((user.getCpfCnpj() == null && user.getEmail() == null) && user.getSenha() == null) {
                System.err.println("\t\tUsuário não encontrado, tente novamente...");
            }
        }
        return user;
    }
    
    public void printUser() {
    	String dado ="\n************************************************{ Dados do Usuário }************************************************\n" +
	    					"Nome: "+ nome + "\nCPF/CNPJ: " + cpfCnpj + "\nE-mail: " + email + "\nPerfil: " + perfil + "\nSenha: **********" +
	    	"\n________________________________________________________________________________________________________________\n";
    	System.out.println(dado);    	
    }
    
    public Usuario alterUser(Usuario user) {
    	String[] campos;
    	int tentativa=0;
    	boolean loop =true;
    	System.out.println("\n*************************************************{ Editar Usuário }*************************************************\n");
		System.out.println(
				" ______________________________________________________________________________________________________________\n"
						+ "|\t\t O modelo de edição funciona com o nome do campo separado por '-' (traço) da seguinte forma:   |"
						+ "\n| \t\t\t\t  O que deseja editar: nome-cpf-cnpj\t\t\t\t\t       |"
						+ "\n|______________________________________________________________________________________________________________|\n\n");
		System.out.print("O que deseja editar: ");
		campos = sc.nextLine().trim().toLowerCase().split("-");
		while(loop) {			
			for (int i = 0; i < campos.length; i++) {
				if (campos[i].contains("nome")) {
					System.out.print("\nDigite seu nome: " );
			        user.setNome(sc.nextLine());
					loop=false;
				} else if(campos[i].contains("email")) {
					 System.out.print("\nDigite seu email: ");
				     user.setEmail(sc.nextLine());	
					loop=false;
				}else if (campos[i].contains("cpf")) {
			        System.out.print("\nDigite seu CPF: ");
			        user.setCpfCnpj( sc.nextLine());					
					loop=false;
				} else if( campos[i].contains("cnpj")) {
			        System.out.print("\nDigite seu CNPJ: ");
			        user.setCpfCnpj(sc.nextLine());				
					loop=false;
				} else if(campos[i].contains("senha")) {
					System.out.print("Digite sua antiga senha: ");
					if(sc.nextLine().equals(user.getSenha())) {
						System.out.print("Digite sua nova senha: ");
						user.setSenha(sc.nextLine());
						loop=false;
						System.out.println("Senha alterada com sucesso!");
						continue;
					}else {
						System.err.println("Senha incorreta!");
						tentativa++;
					}
					if(tentativa >= 3) {
						do {
							System.out.print("Você já errou "+ tentativa+" vezes, deseja tentar novamente: s/n ");
							switch (sc.nextLine().toLowerCase().charAt(0)) {
							case 'n': {
								loop = false;
								tentativa = 0;
								break;
							}
							case 's': {
								tentativa = 0;
								break;
							}
							default:
								System.err.println("opção inválida!");
								break;
							}
						}while(tentativa > 0);
						
					}
	
				} else if (user.getPerfil().equals("administrador") && campos[i].contains("perfil")) {
			        System.out.print("\nQual perfil o usuário vai ter: 1- Administrador 2- Cliente");
			        switch (sc.nextLine()) {
					case "1": {
						user.setPerfil("administrador");
						break;
					}
					case "2": {
						user.setPerfil("cliente");
						break;
					}
					default:
						System.err.println("Perfil não encontrado!");
					}					
				} else if (user.getPerfil().equals("cliente") && campos[i].contains("perfil")) {
			        System.err.print("Você não tem autorização para mudar o perfil!");
			        loop=false;
					
				} else {
					System.err.println("Campo " + campos[i] +" não encontrado!");
				}
			}
		}
    	return user;
    }
    
    private Usuario searchUser(String dado, List<Usuario> users) {
    	boolean isLoop = true;
    	for (Usuario user : users) {
    		if(user.cpfCnpj.equals(dado)) {
    			return user;
    		}
		}    	
    	System.err.println("Usuário não encontrado");
    	return null;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }
    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }
}
