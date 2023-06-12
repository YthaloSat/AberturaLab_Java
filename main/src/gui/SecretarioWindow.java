package gui;

import javax.swing.*;

import controllers.SecretarioController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class SecretarioWindow extends JFrame implements ActionListener{
    private LoginWindow loginWindow;
    private SecretarioWindow secretarioWindow;


    public SecretarioWindow(LoginWindow loginWindow) {
        this.loginWindow = loginWindow;
    }

    public void getSecretarioWindow(SecretarioWindow secretarioWindow) {
        this.secretarioWindow = secretarioWindow;
        setTitle("Menu do Secretário");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        add(panel);
        panel.setLayout(null);

        JButton relatorioReservasButton = new JButton("Relatório de Reservas");
        relatorioReservasButton.setBounds(60, 10, 160, 25);
        relatorioReservasButton.addActionListener(this);
        panel.add(relatorioReservasButton);

        JButton historicoReservasButton = new JButton("Histórico de Reservas");
        historicoReservasButton.setBounds(60, 40, 160, 25);
        historicoReservasButton.addActionListener(this);
        panel.add(historicoReservasButton);

        JButton sairButton = new JButton("Sair");
        sairButton.setBounds(90, 80, 90, 25);
        sairButton.addActionListener(this);
        panel.add(sairButton);

        setLocationRelativeTo(null);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Relatório de Reservas": dispose();
                List<Object[]> data = SecretarioController.getRelatorioReservas();
                RelatorioReservasSecretario relatorioReservasSecretario = new RelatorioReservasSecretario(secretarioWindow);
                relatorioReservasSecretario.getRelatorioReservasSecretario(data);
                break;
            case "Histórico de Reservas": dispose();
                List<Object[]> data2 = SecretarioController.getHistoricoReservas();
                HistoricoReservasSecretarioWindow historicoReservasSecretarioWindow = new HistoricoReservasSecretarioWindow(secretarioWindow);
                historicoReservasSecretarioWindow.getHistoricoReservasSecretarioWindow(data2);
                break;
            case "Sair": dispose();
                loginWindow.getLoginWindow(loginWindow);
                break;
        }
    }
}


