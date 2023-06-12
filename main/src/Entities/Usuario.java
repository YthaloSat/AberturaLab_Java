package Entities;

public class Usuario {
    private String cpf;
    private String nome;
    private String senha;
    private int tipoUsuario_id;

    public Usuario(String cpf, String nome, String senha, int tipoUsuario_id) {
        this.cpf = cpf;
        this.nome = nome;
        this.senha = senha;
        this.tipoUsuario_id = tipoUsuario_id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getTipoUsuario_id() {
        return tipoUsuario_id;
    }

    public void setTipoUsuario_id(int tipoUsuario_id) {
        this.tipoUsuario_id = tipoUsuario_id;
    }
    
}
