import javax.swing.SwingUtilities;

import controllers.ReservaController;
import gui.LoginWindow;

public class App {
    public static void main(String[] args) throws Exception {
        ReservaController.verificarAtualizarStatusReservas();
        SwingUtilities.invokeLater(() -> {
            LoginWindow loginWindow = new LoginWindow();
            loginWindow.getLoginWindow(loginWindow);
        });
    }
}
