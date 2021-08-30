package application.controller;

import application.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;



public class RegistrationController {
    @FXML
    private TextField username;
    @FXML
    private TextField forename;
    @FXML
    private TextField surname;
    @FXML
    private TextField mobileNo;
    @FXML
    private TextField role;
    @FXML
    private TextField email;
    @FXML
    private PasswordField password;
    @FXML
    private PasswordField repeat_password;
    @FXML
    private TextArea txtFeedBack;

    public void handleRegisterBtn() throws Exception {
        if(username.getText().length()<4 || password.getText().length()<4 ){
            txtFeedBack.setText("Username and Password need to be 4 characters or more");
        }
        else if(!password.getText().equals(repeat_password.getText())){
            txtFeedBack.setText("Password must match RepeatPassword");
        }
        else if(register(username.getText(),forename.getText(), surname.getText(),mobileNo.getText(),role.getText(),email
                .getText(), password.getText())){
            txtFeedBack.setText("Successful Registration"); //Never be seen because of next line
            Main.set_pane(0);
        }
    }

    private boolean register(String username, String foreName, String surname, String role, String mobileNo, String email, String password) {
        try {
            User user = new User(username, foreName, surname, role, mobileNo, email, password);
            Main.setUser(user);
            Main.shop.addUser(user);

        } catch (Exception e) {
            txtFeedBack.setText("Error writing to Password File");
            return false;
        }
        return true;
    }


    public void handleCancelBtn() throws Exception {
        Main.set_pane(0);
    }

}
