package application.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private TextField txtUsername;
    @FXML
    private TextField txtPassword;


    public void handleRegisterBtn() throws Exception {
        Main.set_pane(1);
    }

    public void handleLoginBtn() throws Exception {

        String username = txtUsername.getText();
        String password = txtPassword.getText();

        Main.setUser(Main.shop.findUser(username, password));

        if (Main.getUser() == null) {
            System.out.println("Login details incorrect");

        } else {
            Main.set_pane(2);
        }

    }
}
