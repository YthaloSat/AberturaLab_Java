package gui;

import javax.swing.*;
import controllers.LoginRegisterController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CadastroWindow extends JFrame implements ActionListener {
    private JTextField cpfField;
    private JTextField nomeField;
    private JPasswordField senhaField;
    private JTextField chaveAcessoField;
    private JComboBox<String> tipoUsuarioComboBox;
    private LoginWindow loginWindow;
    private CadastroWindow cadastroWindow;

    public CadastroWindow() {}

    public CadastroWindow(LoginWindow loginWindow) {
        this.loginWindow = loginWindow;
    }


    public void getCadastroWindow(CadastroWindow cadastroWindow) {
        this.cadastroWindow = cadastroWindow;
        setTitle("Cadastro");
        setSize(290, 260);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        add(panel);
        panel.setLayout(null);

        JLabel nomeLabel = new JLabel("Usuário");
        nomeLabel.setBounds(10, 10, 80, 25);
        panel.add(nomeLabel);

        nomeField = new JTextField();
        nomeField.setBounds(100, 10, 160, 25);
        panel.add(nomeField);

        JLabel senhaLabel = new JLabel("Senha");
        senhaLabel.setBounds(10, 40, 80, 25);
        panel.add(senhaLabel);

        senhaField = new JPasswordField();
        senhaField.setBounds(100, 40, 160, 25);
        panel.add(senhaField);

        JLabel cpfLabel = new JLabel("CPF: xxx.xxx.xxx-xx");
        cpfLabel.setBounds(10, 70, 120, 25);
        panel.add(cpfLabel);

        cpfField = new JTextField();
        cpfField.setBounds(130, 70, 130, 25);
        panel.add(cpfField);

        JLabel chaveAcessoLabel = new JLabel("Chave de Acesso");
        chaveAcessoLabel.setBounds(10, 100, 120, 25);
        panel.add(chaveAcessoLabel);

        chaveAcessoField = new JTextField();
        chaveAcessoField.setBounds(130, 100, 130, 25);
        panel.add(chaveAcessoField);

        JLabel tipoUsuarioLabel = new JLabel("Tipo de Usuário");
        tipoUsuarioLabel.setBounds(10, 130, 120, 25);
        panel.add(tipoUsuarioLabel);

        String[] tiposUsuarios = {"Secretário", "Professor"};
        tipoUsuarioComboBox = new JComboBox<>(tiposUsuarios);
        tipoUsuarioComboBox.setBounds(130, 130, 130, 25);
        panel.add(tipoUsuarioComboBox);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(10, 180, 100, 25);
        loginButton.addActionListener(this);
        panel.add(loginButton);

        JButton cadastrarButton = new JButton("Cadastrar");
        cadastrarButton.setBounds(160, 180, 100, 25);
        cadastrarButton.addActionListener(this);
        panel.add(cadastrarButton);

        setLocationRelativeTo(null);

        setVisible(true);
    }

    private String verificaCpf(String cpf) {
        if (cpf.matches("[0-9]{3}.[0-9]{3}.[0-9]{3}-[0-9]{2}"))
            return cpf;
        else
            return null;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()) {
            case "Cadastrar":
                String cpf = cpfField.getText();
                String nome = nomeField.getText();
                String senha = new String(senhaField.getPassword());
                String chaveAcesso = chaveAcessoField.getText();
                String tipoUsuario = (String) tipoUsuarioComboBox.getSelectedItem();

                if (!(nome.isEmpty() || senha.isEmpty() || chaveAcesso.isEmpty() || tipoUsuario.isEmpty())) {

                    if (verificaCpf(cpf) == null) {
                        messageErro("CPF Inválido. Preencha-o Novamente.");
                    } else {
                        LoginRegisterController.acessCadastro(cpf, nome, senha, chaveAcesso, tipoUsuario, cadastroWindow);
                    }
                } else {
                    messageErro("Por Favor, Preencha Todos os Campos.");
                }
                break;
            case "Login": dispose();
                loginWindow.getLoginWindow(loginWindow);
                break;
        }
    }

    public void messageErro(String messageErro) {
        JOptionPane.showMessageDialog(this, messageErro);
    }

    public void sucessCadastro(String messageSuccess) {
        JOptionPane.showMessageDialog(this, messageSuccess);
        dispose();
        loginWindow.getLoginWindow(loginWindow);
    }
}
