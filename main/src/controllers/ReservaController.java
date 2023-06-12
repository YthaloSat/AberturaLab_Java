package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import connections.Database;

public class ReservaController {
    public static void verificarAtualizarStatusReservas() {
        try {
            Connection connection = Database.getConexao();
            
            String query = "UPDATE t_reserva SET t_status_id_status = 2 WHERE hora_reserva < ? AND hora_entrega < ?";
            PreparedStatement statement = connection.prepareStatement(query);
            
            LocalDateTime now = LocalDateTime.now();
            Timestamp currentTime = Timestamp.valueOf(now);
            
            statement.setTimestamp(1, currentTime);
            statement.setTimestamp(2, currentTime);
            statement.executeUpdate(); 
            
            query = "UPDATE t_reserva SET t_status_id_status = 1 WHERE hora_reserva < ? AND hora_entrega > ?";
            statement = connection.prepareStatement(query);
            
            statement.setTimestamp(1, currentTime);
            statement.setTimestamp(2, currentTime);
            statement.executeUpdate();
            
            query = "UPDATE t_reserva SET t_status_id_status = 3 WHERE hora_reserva > ? AND hora_entrega > ?";
            statement = connection.prepareStatement(query);
            
            statement.setTimestamp(1, currentTime);
            statement.setTimestamp(2, currentTime);
            statement.executeUpdate();
            
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}


