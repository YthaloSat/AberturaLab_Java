package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import connections.Database;

public class SecretarioController {

    public static List<Object[]> getHistoricoReservas() {
        List<Object[]> data = new ArrayList<>();

        try {
            Connection connection = Database.getConexao();
            String query = "SELECT t_laboratorio.nome_laboratorio, t_usuario.nome_usuario, t_reserva.hora_reserva, t_reserva.hora_entrega, t_status.status FROM t_reserva INNER JOIN t_usuario ON t_reserva.t_usuario_cpf = t_usuario.cpf AND t_reserva.t_usuario_t_tipoUsuario_id = t_usuario.t_tipoUsuario_id INNER JOIN t_laboratorio ON t_reserva.t_laboratorio_id_laboratorio = t_laboratorio.id_laboratorio INNER JOIN t_status ON t_reserva.t_status_id_status = t_status.id_status WHERE t_status.id_status = 2";
            PreparedStatement statement = connection.prepareStatement(query);
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    public static List<Object[]> getRelatorioReservas() {
        List<Object[]> data = new ArrayList<>();

        try {
            Connection connection = Database.getConexao();
            String query = "SELECT t_laboratorio.nome_laboratorio, t_usuario.nome_usuario, t_reserva.hora_reserva, t_reserva.hora_entrega, t_status.status FROM t_reserva INNER JOIN t_usuario ON t_reserva.t_usuario_cpf = t_usuario.cpf AND t_reserva.t_usuario_t_tipoUsuario_id = t_usuario.t_tipoUsuario_id INNER JOIN t_laboratorio ON t_reserva.t_laboratorio_id_laboratorio = t_laboratorio.id_laboratorio INNER JOIN t_status ON t_reserva.t_status_id_status = t_status.id_status WHERE t_reserva.t_status_id_status IN (1, 3)";
            PreparedStatement statement = connection.prepareStatement(query);
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

}
