package controllers;

import java.sql.*;
import Entities.Usuario;
import connections.*;
import gui.*;

public class LoginRegisterController {

    public static void acessLogin(String usuario, String senha, LoginWindow loginWindow) {
        try {
            Connection connection = Database.getConexao();
            String query = "SELECT t_usuario.* FROM t_usuario INNER JOIN t_tipousuario ON t_usuario.t_tipoUsuario_id = t_tipousuario.id WHERE t_usuario.nome_usuario = ? AND t_usuario.senha_usuario = ?";
            PreparedStatement statement = connection.prepareStatement(query);
    
            statement.setString(1, usuario);
            statement.setString(2, senha);
            ResultSet resultSet = statement.executeQuery();
   
            if (resultSet.next()) {
                String cpf = resultSet.getString("cpf");
                int tipoUsuario_id = resultSet.getInt("t_tipoUsuario_id");
                loginWindow.sucessLogin(new Usuario(cpf, usuario, senha, tipoUsuario_id));
            } else {
                loginWindow.messageErro("Credencias inválidas.");
            }
        } catch (Exception e) {
            loginWindow.messageErro("Erro Inesperado na Conexão: " + e.getMessage());
        }
    }

    public static void acessCadastro(String cpf, String nome, String senha, String chaveAcesso, String tipoUsuario, CadastroWindow cadastroWindow) {
        try {
            Connection connection = Database.getConexao();
            String query = "SELECT t_tipousuario.* FROM t_acesskey INNER JOIN t_tipousuario ON t_tipousuario.id = t_acesskey.t_tipoUsuario_id WHERE t_acesskey.id_acessKey = ? AND t_tipousuario.tipo = ?";
            PreparedStatement statement1 = connection.prepareStatement(query);

            statement1.setString(1, chaveAcesso);
            statement1.setString(2, tipoUsuario);
            ResultSet resultSet1 = statement1.executeQuery();

            if (resultSet1.next()) {
                query = "SELECT cpf FROM t_usuario WHERE t_usuario.cpf = ?";
                PreparedStatement statement = connection.prepareStatement(query);
    
                statement.setString(1, cpf);
                ResultSet resultSet = statement.executeQuery();
    
                if (!resultSet.next()) {
                    query = "SELECT nome_usuario FROM t_usuario WHERE t_usuario.nome_usuario = ?";
                    statement = connection.prepareStatement(query);
                    statement.setString(1, nome);
                    resultSet = statement.executeQuery();
    
                    if (!resultSet.next()) {
                        query = "SELECT id FROM t_tipousuario WHERE tipo = ?";
                        statement = connection.prepareStatement(query);
                        statement.setString(1, tipoUsuario);
                        resultSet = statement.executeQuery();
    
                        if (resultSet.next()) {
                            int tipoUsuarioId = resultSet.getInt("id");
                            query = "INSERT INTO t_usuario(cpf, nome_usuario, senha_usuario, t_tipoUsuario_id) VALUES (?, ?, ?, ?)";
                            statement = connection.prepareStatement(query);
                            statement.setString(1, cpf);
                            statement.setString(2, nome);
                            statement.setString(3, senha);
                            statement.setInt(4, tipoUsuarioId);
                            statement.executeUpdate();
    
                            cadastroWindow.sucessCadastro("Cadastro Realizado Com Sucesso.");
                        } else {
                            cadastroWindow.messageErro("Tipo de Usuário Inválido");
                        }
                    } else {
                        cadastroWindow.messageErro("Nome de Usuário Já Cadastrado.");
                    }
                } else {
                    cadastroWindow.messageErro("CPF Já Cadastrado.");
                }
            } else {
                cadastroWindow.messageErro("Chave de Acesso Inválida");
            }
        } catch (Exception e) {
            cadastroWindow.messageErro("Erro Inesperado na Conexão: " + e.getMessage());
        }
    }
}
