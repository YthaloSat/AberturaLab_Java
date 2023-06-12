package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import controllers.ReservaController;

import java.awt.*;
import java.util.List;

public class HistoricoReservasSecretarioWindow extends JFrame {
    private SecretarioWindow secretarioWindow;
    private JTable table;
    private JButton voltarButton;

    public HistoricoReservasSecretarioWindow (SecretarioWindow secretarioWindow) {
        this.secretarioWindow = secretarioWindow;
    }

    public void getHistoricoReservasSecretarioWindow(List<Object[]> data) {
        ReservaController.verificarAtualizarStatusReservas();

        setTitle("Histórico do Secretário");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Laboratório");
        tableModel.addColumn("Usuário");
        tableModel.addColumn("Hora da Reserva");
        tableModel.addColumn("Hora da Entrega");
        tableModel.addColumn("Status");

        for (Object[] row : data) {
            tableModel.addRow(row);
        }

        table = new JTable(tableModel);
        table.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(table);

        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);

        setupVoltarButton();

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(voltarButton);

        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void setupVoltarButton() {
        voltarButton = new JButton("Voltar");
        voltarButton.addActionListener(e -> {
            dispose();
            secretarioWindow.getSecretarioWindow(secretarioWindow);
        });
    }
}

