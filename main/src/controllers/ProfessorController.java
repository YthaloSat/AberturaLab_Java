package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import connections.Database;
import gui.CadastroReservasProfessor;

public class ProfessorController {

    public static List<Object[]> getHistoricoReservas(String cpf) {
        List<Object[]> data = new ArrayList<>();

        try {
            Connection connection = Database.getConexao();
            String query = "SELECT t_laboratorio.nome_laboratorio, t_usuario.nome_usuario, t_reserva.hora_reserva, t_reserva.hora_entrega, t_status.status FROM t_reserva INNER JOIN t_usuario ON t_reserva.t_usuario_cpf = t_usuario.cpf AND t_reserva.t_usuario_t_tipoUsuario_id = t_usuario.t_tipoUsuario_id INNER JOIN t_laboratorio ON t_reserva.t_laboratorio_id_laboratorio = t_laboratorio.id_laboratorio INNER JOIN t_status ON t_reserva.t_status_id_status = t_status.id_status WHERE t_usuario.cpf = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, cpf);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String nomeLaboratorio = resultSet.getString("nome_laboratorio");
                String nomeUsuario = resultSet.getString("nome_usuario");
                String horaReserva = resultSet.getString("hora_reserva");
                String horaEntrega = resultSet.getString("hora_entrega");
                String status = resultSet.getString("status");

                Object[] row = new Object[]{nomeLaboratorio, nomeUsuario, horaReserva, horaEntrega, status};
                data.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return data;
    }

    public static void cadastrarReserva(String cpf, String laboratorio, String reservaDate, String entregaDate, CadastroReservasProfessor cadastroReservasProfessor) {
        try {
            Connection connection = Database.getConexao();
            String query = "SELECT * FROM t_laboratorio WHERE nome_laboratorio = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, laboratorio);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int laboratorioId = resultSet.getInt("id_laboratorio");

                query = "SELECT * FROM t_reserva WHERE (t_status_id_status = 3 OR t_status_id_status = 1) AND hora_reserva <= ? AND hora_entrega >= ? AND t_laboratorio_id_laboratorio = ?";
                statement = connection.prepareStatement(query);
                statement.setString(1, entregaDate);
                statement.setString(2, reservaDate);
                statement.setInt(3, laboratorioId);
                resultSet = statement.executeQuery();

                if (!resultSet.next()) {
                    query = "INSERT INTO t_reserva (hora_reserva, hora_entrega, t_usuario_cpf, t_usuario_t_tipoUsuario_id, t_laboratorio_id_laboratorio, t_status_id_status) VALUES (?, ?, ?, ?, ?, ?)";
                    statement = connection.prepareStatement(query);

                    statement.setString(1, reservaDate);
                    statement.setString(2, entregaDate);
                    statement.setString(3, cpf);
                    statement.setInt(4, 2);
                    statement.setInt(5, laboratorioId);
                    statement.setInt(6, 3);

                    int rowsInserted = statement.executeUpdate();

                    if (rowsInserted > 0) {
                        cadastroReservasProfessor.message("Reserva cadastrada com sucesso!");
                    } else {
                        cadastroReservasProfessor.message("Falha ao cadastrar reserva.");
                    }
                } else {
                    cadastroReservasProfessor.message("O laboratório não está disponível no intervalo de tempo da reserva até a entrega.");
                }
            } else {
                cadastroReservasProfessor.message("Laboratório não encontrado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
