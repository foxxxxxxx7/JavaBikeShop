package application.controller;

import application.model.Bike;
import application.model.BikeShopModel;
import application.model.Reservation;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class ReservationController {
    @FXML
    private DatePicker date;
    @FXML
    private TextField custName;
    @FXML
    private TextField contactNum;
    @FXML
    private TextField bikeID;
    @FXML
    private TextField duration;
    @FXML
    private TextField pickUp;
    @FXML
    private TextField dropOff;
    @FXML
    private TextArea txtAreaFeedback;

    private boolean reserve(LocalDate date, String custName, String contactNum, String bikeID, String duration, String pickUp, String dropOff) {
        try {
            Bike bikeFound = Main.shop.findBike(Integer.parseInt(bikeID));
            Reservation reservation = new Reservation(date, custName, contactNum, bikeFound, duration, pickUp, dropOff);
            Main.setReservation(reservation);

        } catch (Exception e) {
            txtAreaFeedback.setText("Error writing to Password File");
            return false;
        }
        return true;
    }

    public void handleReserveBtn() {
        if (custName.getText().length() < 3) {
            txtAreaFeedback.setText("User's name must be at least 3 characters and/or productID must be at most 2 characters.");
        } else if (reserve(date.getValue(), custName.getText(), contactNum.getText(), bikeID.getText(), duration.getText(), pickUp.getText(), dropOff.getText())) {
            txtAreaFeedback.setText("SUCCESS");
            Main.set_pane(2);
        }
    }

    public void handleCancelBtn(javafx.event.ActionEvent e) throws Exception {
        Main.set_pane(2);
    }

    // public void save(){
    // BikeShopModel.shop.saveAll();
    // }

}

