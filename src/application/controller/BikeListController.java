package application.controller;

import application.model.Bike;
import application.model.BikeShopModel;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import java.util.ArrayList;

public class BikeListController {
    protected static BikeShopModel shop;
    @FXML
    private TableView<Bike> bikeTable;
    @FXML
    private TableColumn<Bike, String> txtBikeID;
    @FXML
    private TableColumn<Bike, String> txtSize;
    @FXML
    private TableColumn<Bike, String> txtStyle;
    @FXML
    private TableColumn<Bike, String> txtGender;
    @FXML
    private TableColumn<Bike, String> txtPrice;
    @FXML
    private TableColumn<Bike, String> txtCondition;
    @FXML
    private TextField txtComment;
    @FXML
    private TextArea txtFeedBack;
    @FXML
    private TextField txtAreaProductInfo;
    @FXML
    private TextField  txtSizeUpdate;
    @FXML
    private TextField  txtStyleUpdate;
    @FXML
    private TextField  txtGenderUpdate;
    @FXML
    private TextField  txtPriceUpdate;
    @FXML
    private TextField  txtConditionUpdate;

    ArrayList<Bike> allBikes = new ArrayList<>();


    Bike selectedBike;

    public void loadBikeTable() throws Exception
    {


        txtBikeID = new TableColumn<Bike,String>("BikeID");
        txtBikeID.setMinWidth(50);
        txtBikeID.setCellValueFactory(new PropertyValueFactory<>("bikeID"));

        txtSize = new TableColumn<Bike,String>("Size");
        txtSize.setMinWidth(80);
        txtSize.setCellValueFactory(new PropertyValueFactory<Bike,String>("Size"));

        txtStyle = new TableColumn<Bike,String>("Style");
        txtStyle.setMinWidth(240);
        txtStyle.setCellValueFactory(new PropertyValueFactory<Bike,String>("Style"));

        txtGender = new TableColumn<Bike,String>("Gender");
        txtGender.setMinWidth(100);
        txtGender.setCellValueFactory(new PropertyValueFactory<Bike,String>("Gender"));

        txtPrice = new TableColumn<Bike,String>("Price");
        txtPrice.setMinWidth(40);
        txtPrice.setCellValueFactory(new PropertyValueFactory<Bike,String>("Price"));

        txtCondition = new TableColumn<Bike,String>("Condition");
        txtCondition.setMinWidth(180);
        txtCondition.setCellValueFactory(new PropertyValueFactory<Bike,String>("Condition"));
        //txtCondition.setCellFactory(TextFieldTableCell.<Bike>forTableColumn());



        bikeTable.getColumns().addAll(txtBikeID, txtSize, txtStyle, txtGender, txtPrice, txtCondition);
        bikeTable.setEditable(true);
        bikeTable.getSelectionModel().cellSelectionEnabledProperty().set(true);
        ObservableList<Bike> bD = getBikes();
        ObservableList<Bike> bD2 = bikeTable.getSelectionModel().getSelectedItems();

        bD2.addListener(new ListChangeListener<Bike>() {
            @Override
            public void onChanged(Change<? extends Bike> change) {
                selectedBike = change.getList().get(0);
                txtSizeUpdate.setText(selectedBike.getSize());
                txtStyleUpdate.setText(selectedBike.getStyle());
                txtGenderUpdate.setText(selectedBike.getGender());
                txtPriceUpdate.setText(selectedBike.getStringPrice());
                txtConditionUpdate.setText(selectedBike.getCondition());
                System.out.println("Selection changed: " + change.getList());
            }
        });


        System.out.println(bD.toString());
        // usersTable.setItems(uD);
        bikeTable.getItems().clear();
        bikeTable.getItems().addAll(bD);

    }

    public  ObservableList<Bike> getBikes() throws Exception{

        //ObservableList<UserData> users = FXCollections.observableArrayList();
        //            users.addAll(group.values());
        ObservableList<Bike> bikes = FXCollections.observableArrayList();
        bikes.addAll(Main.shop.getStock());
        return bikes;
    }



    public void handleCancelBtn() throws Exception {
        Main.set_pane(2);
    }

    public void handleUpdateBtn() throws Exception
    {
        //int updateID = bikeTable.getSelectionModel().getSelectedIndex();
        //int actualBikeID = Main.shop.getBike(updateID).getBikeID();
       // int bikeID = Integer.parseInt(txtBikeID.getText());
        int bikeID = selectedBike.getBikeID();
        String size = txtSizeUpdate.getText();
        String style = txtStyleUpdate.getText();
        String gender = txtGenderUpdate.getText();
        double price = Double.parseDouble(txtPriceUpdate.getText());
        String condition = txtConditionUpdate.getText();
        //String comment = txtComment.getText();


        if(Main.shop.updateBike(bikeID, size ,style,gender ,price, condition,"none")){
            txtFeedBack.setText("Bike Updated");
            Main.shop.saveAll();
        }
        else{
            txtFeedBack.setText("Error: Bike ID not found");
        }
    }

    public void handleDeleteBtn() throws Exception
    {
       // int id = Integer.parseInt(txtBikeID.getText());
        int id = bikeTable.getSelectionModel().getSelectedIndex();
        System.out.println("ID"+ id);
        int actualBikeID = Main.shop.getBike(id).getBikeID();
        if(Main.shop.deleteBike(actualBikeID)){
            txtFeedBack.setText("Bike Deleted");
        }
        else{
            txtFeedBack.setText("Error: Bike ID not found");
        }
        System.out.println("ID"+ id);

        bikeTable.getItems().remove(id);
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
