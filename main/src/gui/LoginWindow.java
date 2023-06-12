package gui;

import javax.swing.*;

import Entities.Usuario;
import controllers.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginWindow extends JFrame implements ActionListener {
    private JTextField usuarioField;
    private JPasswordField senhaField;
    private LoginWindow loginWindow;

    public void getLoginWindow(LoginWindow loginWindow) {
        this.loginWindow = loginWindow;
        setTitle("Login / Cadastro");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        add(panel);
        panel.setLayout(null);

        JLabel userLabel = new JLabel("Usuário");
        userLabel.setBounds(10, 10, 80, 25);
        panel.add(userLabel);

        usuarioField = new JTextField(20);
        usuarioField.setBounds(100, 10, 160, 25);
        panel.add(usuarioField);

        JLabel passwordLabel = new JLabel("Senha");
        passwordLabel.setBounds(10, 40, 80, 25);
        panel.add(passwordLabel);

        senhaField = new JPasswordField(20);
        senhaField.setBounds(100, 40, 160, 25);
        panel.add(senhaField);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(10, 80, 80, 25);
        loginButton.addActionListener(this);
        panel.add(loginButton);

        JButton registerButton = new JButton("Cadastro");
        registerButton.setBounds(170, 80, 90, 25);
        registerButton.addActionListener(this);
        panel.add(registerButton);

        setLocationRelativeTo(null);

        setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        switch(e.getActionCommand()) {
            case "Login":
                String usuario = usuarioField.getText();
                String senha = new String(senhaField.getPassword());

                if (!(usuario.isEmpty() || senha.isEmpty())) {
                    LoginRegisterController.acessLogin(usuario, senha, loginWindow);
                } else {
                    messageErro("Por Favor, Preencha Todos os Campos.");
                }
                break;
            case "Cadastro": dispose(); 
                CadastroWindow cadastroWindow = new CadastroWindow(new LoginWindow());
                cadastroWindow.getCadastroWindow(cadastroWindow);
                break;
        }
    }

    public void messageErro(String messageErro) {
        JOptionPane.showMessageDialog(this, messageErro);
    }

    public void sucessLogin(Usuario usuarioLogado) {
        switch (usuarioLogado.getTipoUsuario_id()) {
            case 1: dispose();
                SecretarioWindow secretarioWindow = new SecretarioWindow(new LoginWindow());
                secretarioWindow.getSecretarioWindow(secretarioWindow);
                break;
            case 2: dispose();
                ProfessorWindow professorWindow = new ProfessorWindow(new LoginWindow(), usuarioLogado);
                professorWindow.getProfessorWindow(professorWindow);
                break;
        }
    }
}
