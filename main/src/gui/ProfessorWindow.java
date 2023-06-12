package gui;

import javax.swing.*;
import Entities.Usuario;
import controllers.ProfessorController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ProfessorWindow extends JFrame implements ActionListener {
    private LoginWindow loginWindow;
    private Usuario usuarioLogado;
    private ProfessorWindow professorWindow;

    public ProfessorWindow(LoginWindow loginWindow, Usuario usuarioLogado) {
        this.loginWindow = loginWindow;
        this.usuarioLogado = usuarioLogado;
    }

    public void getProfessorWindow(ProfessorWindow professorWindow) {
        this.professorWindow = professorWindow;
        setTitle("Menu do Professor");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        add(panel);
        panel.setLayout(null);

        JButton relatorioReservasButton = new JButton("Cadastro de Reservas");
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
            case "Cadastro de Reservas": dispose();
                new CadastroReservasProfessor(this, usuarioLogado);
                break;
            case "Histórico de Reservas": dispose();
                List<Object[]> data = ProfessorController.getHistoricoReservas(usuarioLogado.getCpf());
                new HistoricoReservasProfessorWindow(professorWindow, data);
                break;
            case "Sair": dispose();
                loginWindow.getLoginWindow(loginWindow);
                break;
        }
    }
}