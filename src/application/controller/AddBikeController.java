package application.controller;


import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.awt.*;
import java.awt.event.ActionEvent;

public class AddBikeController {

    @FXML
    private TextField txtbikeID;
    @FXML
    private TextField txtSize;
    @FXML
    private TextField txtStyle;
    @FXML
    private TextField txtGender;
    @FXML
    private TextField txtPrice;
    @FXML
    private TextField txtCondition;
    @FXML
    private TextField txtComment;
    @FXML
    private TextField txtFeedBack;
    @FXML
    private TextField txtAreaProductInfo;
    @FXML
    private TextField txtUser;
    @FXML
    private Button logBtn;



    public void handleCancelBtn() throws Exception {
        Main.set_pane(2);
    }

    public void handleAddBike() {
        String size = txtSize.getText();
        String style = txtStyle.getText();
        String gender = txtGender.getText();
        double price;
        try {
            price = Double.valueOf(txtPrice.getText());
        } catch (NumberFormatException e1) {
            txtFeedBack.setText("Bike NOT Added -- Price MUST be numbers only");
            return;
        } catch (Exception e1) {
            txtFeedBack.setText("Bike NOT Added -- Price MUST be numbers only...");
            return;
        }
        String condition = txtCondition.getText();
        String comment = txtComment.getText();

        Main.shop.addBike(size, style, gender, price, condition, comment);
        txtFeedBack.setText("Bike Added");
        System.out.println(Main.shop.listBikes());
    }
}

