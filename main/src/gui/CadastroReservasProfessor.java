package gui;

import javax.swing.*;

import Entities.Usuario;
import controllers.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class CadastroReservasProfessor extends JFrame implements ActionListener {
    private ProfessorWindow professorWindow;
    private Usuario usuarioLogado;
    private JComboBox<String> laboratorioComboBox;
    private JTextField reservaDateTextField;
    private JTextField entregaDateTextField;
    private JButton cadastrarButton;
    private JButton voltarButton;

    public CadastroReservasProfessor(ProfessorWindow professorWindow, Usuario usuarioLogado) {
        this.professorWindow = professorWindow;
        this.usuarioLogado = usuarioLogado;
        ReservaController.verificarAtualizarStatusReservas();
        setTitle("Cadastro de Reservas");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        add(panel);
        panel.setLayout(null);

        JLabel laboratorioLabel = new JLabel("Laboratório:");
        laboratorioLabel.setBounds(30, 20, 100, 25);
        panel.add(laboratorioLabel);

        laboratorioComboBox = new JComboBox<>();
        laboratorioComboBox.addItem("Lab 01");
        laboratorioComboBox.addItem("Lab 02");
        laboratorioComboBox.setBounds(140, 20, 200, 25);
        panel.add(laboratorioComboBox);

        JLabel reservaLabel = new JLabel("Reserva (yyyy-MM-dd HH:mm:ss):");
        reservaLabel.setBounds(30, 60, 200, 25);
        panel.add(reservaLabel);

        reservaDateTextField = new JTextField();
        reservaDateTextField.setBounds(240, 60, 100, 25);
        panel.add(reservaDateTextField);

        JLabel entregaLabel = new JLabel("Entrega (yyyy-MM-dd HH:mm:ss):");
        entregaLabel.setBounds(30, 100, 200, 25);
        panel.add(entregaLabel);

        entregaDateTextField = new JTextField();
        entregaDateTextField.setBounds(240, 100, 100, 25);
        panel.add(entregaDateTextField);

        cadastrarButton = new JButton("Cadastrar");
        cadastrarButton.setBounds(90, 150, 100, 25);
        cadastrarButton.addActionListener(this);
        panel.add(cadastrarButton);

        voltarButton = new JButton("Voltar");
        voltarButton.setBounds(210, 150, 100, 25);
        voltarButton.addActionListener(this);
        panel.add(voltarButton);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Cadastrar")) {
            String laboratorio = laboratorioComboBox.getSelectedItem().toString();
            String reservaDate = reservaDateTextField.getText();
            String entregaDate = entregaDateTextField.getText();

            try {
                LocalDateTime reservaDateTime = LocalDateTime.parse(reservaDate, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                LocalDateTime entregaDateTime = LocalDateTime.parse(entregaDate, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

                if (entregaDateTime.isBefore(reservaDateTime)) {
                    message("A data de entrega deve ser posterior à data de reserva.");
                } else if (entregaDateTime.isEqual(reservaDateTime)) {
                    message("As datas não podem ser iguais.");
                } else {
                    ReservaController.verificarAtualizarStatusReservas();
                    ProfessorController.cadastrarReserva(usuarioLogado.getCpf(), laboratorio, reservaDate, entregaDate, this);
                }
            } catch (DateTimeParseException ex) {
                message("Formato de data inválido. Utilize o formato yyyy-MM-dd HH:mm:ss.");
            }
        } else if (e.getActionCommand().equals("Voltar")) {
            dispose();
            professorWindow.getProfessorWindow(professorWindow);
        }
    }

    public void message(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}
