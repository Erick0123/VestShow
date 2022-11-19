package model;

import java.util.List;
import java.util.Scanner;

public class Usuario {
	private String nome;
    private String cpfCnpj;
    private String email;
    private String senha;
    private String tipo;
    //private Banco banco;

    public Usuario() { }

    public Usuario(String nome, String cpfCnpj, String email, String senha, String tipo) {
        this.nome = nome;
        this.cpfCnpj = cpfCnpj;
        this.email = email;
        this.senha = senha;
        this.tipo = tipo;
    }

    public Usuario cadastrar() {
        Scanner sc = new Scanner(System.in);
        System.out.print("\n\t\tDigite seu nome: " );
        nome = sc.nextLine();
        System.out.print("\t\tDigite seu email: ");
        email = sc.nextLine();
        System.out.print("\t\tDigite seu CPF: ");
        cpfCnpj = sc.nextLine();
        System.out.print("\t\tDigite sua senha: ");
        senha = sc.nextLine();
        tipo = "cliente";
        return new Usuario(nome, cpfCnpj, email, senha, tipo);
    }

    public Usuario login(boolean isLoop, List<Usuario> userList) {
        Scanner sc = new Scanner(System.in);
        String emailCpfCnpj, senha;
        Usuario user = new Usuario();
        while (isLoop) {
            System.out.println("\n***************************************************{ Login }***************************************************\n");
            System.out.print("Email ou CPF ou CNPJ: ");
            emailCpfCnpj = sc.next();
            System.out.print("Senha: ");
            senha = sc.next();
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }
}
