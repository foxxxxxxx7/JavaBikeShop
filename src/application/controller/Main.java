package application.controller;


import application.model.BikeShopModel;
import application.model.Reservation;
import application.model.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Main extends Application {
    static AnchorPane root;
    static List<AnchorPane> anchor = new ArrayList<>();
    private static int sceneIndex = 0;
    private static User user = null;
    private static Reservation reservation = null;
    static BikeShopModel shop = new BikeShopModel();

    @Override
    public void start(Stage primaryStage) throws Exception {
        root = (AnchorPane)FXMLLoader.load(getClass().getResource("../view/LoginAndRegister.fxml"));

        anchor.add(FXMLLoader.load(getClass().getResource("../view/LoginAndRegister.fxml")));//index 0
        anchor.add(FXMLLoader.load(getClass().getResource("../view/Registration.fxml")));//index 1
        anchor.add(FXMLLoader.load(getClass().getResource("../view/Welcome.fxml")));//index 2
        anchor.add(FXMLLoader.load(getClass().getResource("../view/Reservation.fxml")));//index 3
       anchor.add(FXMLLoader.load(getClass().getResource("../view/ReservationList.fxml")));//index 4
         anchor.add(FXMLLoader.load(getClass().getResource("../view/AddBike.fxml")));//index 5
         anchor.add(FXMLLoader.load(getClass().getResource("../view/BikeList.fxml")));//index 6
        anchor.add(FXMLLoader.load(getClass().getResource("../view/UserList.fxml")));//index 7

        root.getChildren().add(anchor.get(2));// set initial screen to screen with index 0


        try{
            shop.loadAll();

        }
        catch(Exception e){
            System.out.println("Loading Error" + e);
        }
        primaryStage.setTitle("Bike Shop");
        primaryStage.setScene(new Scene(root, 800, 700));
        primaryStage.show();
    }

    private void init_app() {
        for (int i = 0; i < anchor.size(); i++) {
            //can be used to initiate some details about each pane
        }
    }

    public static AnchorPane get_pane(int index) {
        return anchor.get(index);
    }
    //Call this method to call a screen by index

    public static void set_pane(int index) {
        //TODO check that the index is valid ie. >0 and <size of ArrayList
        if (index < 0 ) {
            return;
        } else {
            root.getChildren().remove(anchor.get(sceneIndex));  //remove the existing pane from the root
            root.getChildren().add(anchor.get(index));          //add the selected pane to the root
            sceneIndex = index;                                   //update the stored sceneIndex
        }
    }

    public static void main(String[] args) {
        launch(args);
    }


    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        Main.user = user;

        System.out.println("User: " + user);
    }


    public static void setReservation(Reservation reservation) {
        Main.reservation = reservation;
        Main.shop.addReservation(reservation);
        System.out.println("Reservation: " + reservation);
    }
}
