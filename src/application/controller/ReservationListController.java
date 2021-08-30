package application.controller;

import application.model.Bike;
import application.model.BikeShopModel;
import application.model.Reservation;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.util.ArrayList;

public class ReservationListController {


    protected static BikeShopModel shop;
    @FXML
    private TableView<Reservation> reservationTable;
    @FXML
    private TableColumn<Reservation, LocalDate> txtDate;
    @FXML
    private TableColumn<Reservation, String> txtCustName;
    @FXML
    private TableColumn<Reservation, String> txtContactNum;
    @FXML
    private TableColumn<Reservation, String> txtBikeID;
    @FXML
    private TableColumn<Reservation, String> txtDuration;
    @FXML
    private TableColumn<Reservation, String> txtPickUp;
    @FXML
    private TableColumn<Reservation, String> txtDropOff;
    @FXML
    private TextField txtComment;
    @FXML
    private TextArea txtFeedBack;
    @FXML
    private TextField txtAreaProductInfo;
    @FXML
    private TextField txtUser;
    @FXML
    private TextField  txtDateUpdate;
    @FXML
    private TextField  txtCustNameUpdate;
    @FXML
    private TextField  txtContactNumUpdate;
    @FXML
    private TextField  txtBikeIDUpdate;
    @FXML
    private TextField  txtDurationUpdate;
    @FXML
    private TextField  txtPickUpUpdate;
    @FXML
    private TextField  txtDropOffUpdate;

    ArrayList<Reservation> allReservations = new ArrayList<>();

    Reservation selectedReservation;

    int selectedRow;

    public ReservationListController() {
    }


    public void loadReservationTable() throws Exception
    {



        txtDate = new TableColumn<Reservation,LocalDate>("Date");
        txtDate.setMinWidth(50);
        txtDate.setCellValueFactory(new PropertyValueFactory<Reservation,LocalDate>("Date"));

        txtCustName = new TableColumn<Reservation,String>("Customer Name");
        txtCustName.setMinWidth(150);
        txtCustName.setCellValueFactory(new PropertyValueFactory<Reservation,String>("CustName"));

        txtContactNum = new TableColumn<Reservation,String>("Contact No.");
        txtContactNum.setMinWidth(80);
        txtContactNum.setCellValueFactory(new PropertyValueFactory<Reservation,String>("ContactNum"));

        txtBikeID = new TableColumn<Reservation,String>("Bike ID");
        txtBikeID.setMinWidth(80);
        txtBikeID.setCellValueFactory(new PropertyValueFactory<>("bike"));

        txtDuration = new TableColumn<Reservation,String>("Duration");
        txtDuration.setMinWidth(80);
        txtDuration.setCellValueFactory(new PropertyValueFactory<Reservation,String>("Duration"));

        txtPickUp = new TableColumn<Reservation,String>("Pick Up");
        txtPickUp.setMinWidth(150);
        txtPickUp.setCellValueFactory(new PropertyValueFactory<Reservation,String>("PickUp"));

        txtDropOff = new TableColumn<Reservation,String>("Drop Off");
        txtDropOff.setMinWidth(150);
        txtDropOff.setCellValueFactory(new PropertyValueFactory<Reservation,String>("DropOff"));


       reservationTable.getColumns().addAll(txtDate, txtCustName, txtContactNum, txtBikeID, txtDuration, txtPickUp, txtDropOff);
        reservationTable.setEditable(true);
        reservationTable.getSelectionModel().cellSelectionEnabledProperty().set(true);
        ObservableList<Reservation> rD = getReservations();
        ObservableList<Reservation> rD2 = reservationTable.getSelectionModel().getSelectedItems();

        rD2.addListener(new ListChangeListener<Reservation>() {
            @Override
            public void onChanged(Change<? extends Reservation> change) {
                selectedReservation = change.getList().get(0);
                selectedRow = reservationTable.getSelectionModel().getSelectedIndex();
                txtDateUpdate.setText(selectedReservation.getDate().toString());
                txtCustNameUpdate.setText(selectedReservation.getCustName());
                txtContactNumUpdate.setText(selectedReservation.getContactNum());
                txtBikeIDUpdate.setText(String.valueOf(selectedReservation.getBike()));
                txtDurationUpdate.setText(selectedReservation.getDuration());
                txtPickUpUpdate.setText(selectedReservation.getPickUp());
                txtDropOffUpdate.setText(selectedReservation.getDuration());
                System.out.println("Selection changed: " + change.getList());
            }
        });


        System.out.println(rD.toString());
        reservationTable.getItems().clear();
        reservationTable.getItems().addAll(rD);

    }

    public  ObservableList<Reservation> getReservations() throws Exception{

        //ObservableList<UserData> users = FXCollections.observableArrayList();
        //            users.addAll(group.values());
        ObservableList<Reservation> reservations = FXCollections.observableArrayList();
        reservations.addAll(Main.shop.getReservations());
        return reservations;
    }



    public void handleCancelBtn() throws Exception {
        Main.set_pane(2);
    }

    public void handleUpdateBtn() throws Exception
    {

        LocalDate date = LocalDate.parse(txtDateUpdate.getText());
        String custName = txtCustNameUpdate.getText();
        String contactNum = txtContactNumUpdate.getText();
        String bikeID = txtBikeIDUpdate.getText();
        String duration = txtDurationUpdate.getText();
        String pickUp = txtPickUpUpdate.getText();
        String dropOff = txtDropOffUpdate.getText();


        if(Main.shop.updateReservation(date, custName, contactNum, bikeID, duration, pickUp, dropOff, selectedRow )) {

            txtFeedBack.setText("Reservation Updated");
            Main.shop.saveAll();
        }
        else{

            txtFeedBack.setText("Error: Reservation ID not found");
        }
    }

    public void handleDeleteBtn() throws Exception
    {
        int custNum = reservationTable.getSelectionModel().getSelectedIndex();
        String actualCustNum = Main.shop.getReservation(custNum).getContactNum();
        if(Main.shop.deleteReservation(actualCustNum)){
            txtFeedBack.setText("Reservation Deleted");
        }
        else{
            txtFeedBack.setText("Error: Customer Number not found");
        }
        System.out.println("ID"+ custNum);

        reservationTable.getItems().remove(custNum);
    }

    public void handleSaveBtn() throws Exception
    {
        shop.saveAll();
        txtFeedBack.setText("Bikes Saved");
    }

    public void handleLoadBtn() throws Exception
    {
        Main.shop.loadAll();
        txtFeedBack.setText("Bikes Loaded");
    }

}

