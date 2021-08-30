package application.controller;

import application.model.Bike;
import application.model.User;
import application.model.BikeShopModel;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class UserListController {
    protected static BikeShopModel shop;
    @FXML
    private TableView<User> userTable;
    @FXML
    private TableColumn<User, String> txtUsername;
    @FXML
    private TableColumn<User, String> txtForeName;
    @FXML
    private TableColumn<User, String> txtSurname;
    @FXML
    private TableColumn<User, String> txtRole;
    @FXML
    private TableColumn<User, String> txtMobileNum;
    @FXML
    private TableColumn<User, String> txtEmail;
    @FXML
    private TextField  txtUsernameUpdate;
    @FXML
    private TextField  txtForeNameUpdate;
    @FXML
    private TextField  txtSurnameUpdate;
    @FXML
    private TextField  txtRoleUpdate;
    @FXML
    private TextField  txtMobileNumUpdate;
    @FXML
    private TextField txtEmailUpdate;
    @FXML
    private TextField txtPasswordUpdate;
    @FXML
    private TextArea txtFeedBack;
    @FXML
    private TextField txtAreaProductInfo;
    @FXML
    private TextField txtUser;


    User selectedUser;


    public void loadUserTable() throws Exception
    {

        txtUsername = new TableColumn<User,String>("Username");
        txtUsername.setMinWidth(700/6);
        txtUsername.setCellValueFactory(new PropertyValueFactory<User,String>("username"));

        txtForeName = new TableColumn<User,String>("Forename");
        txtForeName.setMinWidth(700/6);
        txtForeName.setCellValueFactory(new PropertyValueFactory<User,String>("foreName"));

        txtSurname = new TableColumn<User,String>("Surname");
        txtSurname.setMinWidth(700/6);
        txtSurname.setCellValueFactory(new PropertyValueFactory<User,String>("surname"));

        txtRole = new TableColumn<User,String>("Role");
        txtRole.setMinWidth(700/6);
        txtRole.setCellValueFactory(new PropertyValueFactory<User,String>("role"));

        txtMobileNum = new TableColumn<User,String>("Mobile No.");
        txtMobileNum.setMinWidth(700/6);
        txtMobileNum.setCellValueFactory(new PropertyValueFactory<User,String>("mobileNo"));

        txtEmail = new TableColumn<User,String>("Email");
        txtEmail.setMinWidth(700/6);
        txtEmail.setCellValueFactory(new PropertyValueFactory<User,String>("email"));


        userTable.getColumns().addAll(txtUsername, txtForeName, txtSurname, txtRole, txtMobileNum, txtEmail);
        userTable.setEditable(true);
        userTable.getSelectionModel().cellSelectionEnabledProperty().set(true);
        ObservableList<User> uD = getUsers();
        ObservableList<User> uD2 = userTable.getSelectionModel().getSelectedItems();

        uD2.addListener(new ListChangeListener<User>() {
            @Override
            public void onChanged(Change<? extends User> change) {
                selectedUser = change.getList().get(0);
                txtUsernameUpdate.setText(selectedUser.getUsername());
                txtForeNameUpdate.setText(selectedUser.getForeName());
                txtSurnameUpdate.setText(selectedUser.getSurname());
                txtRoleUpdate.setText(selectedUser.getRole());
                txtMobileNumUpdate.setText(selectedUser.getMobileNo());
                txtEmailUpdate.setText(selectedUser.getEmail());
                txtPasswordUpdate.setText(selectedUser.getPassword());

                System.out.println("Selection changed: " + change.getList());
            }
        });


        System.out.println(uD.toString());
        // usersTable.setItems(uD);
        userTable.getItems().clear();
        userTable.getItems().addAll(uD);

    }

    public  ObservableList<User> getUsers() throws Exception{

        //ObservableList<UserData> users = FXCollections.observableArrayList();
        //            users.addAll(group.values());
        ObservableList<User> users = FXCollections.observableArrayList();
        users.addAll(Main.shop.getUsers());
        return users;
    }



    public void handleCancelBtn() throws Exception {
        Main.set_pane(2);
    }

    public void handleUpdateBtn() throws Exception
    {
        String username = selectedUser.getUsername();
        String foreName = selectedUser.getForeName();
        String surname = selectedUser.getSurname();
        String role = txtRoleUpdate.getText();
        String mobileNum = txtMobileNumUpdate.getText();
        String email = txtEmailUpdate.getText();
        String password = txtPasswordUpdate.getText();




        if(Main.shop.updateUser(username, foreName, surname, role,mobileNum,email, password )){
            txtFeedBack.setText("User Updated");
        }
        else{
            txtFeedBack.setText("Error: User not found");
        }
    }

    public void handleDeleteBtn() throws Exception
    {
        String userName = txtUsername.getText();
        if(shop.deleteUser(userName)){
            txtFeedBack.setText("User Deleted");
        }
        else{
            txtFeedBack.setText("Error: Username not found");
        }
    }

    public void handleSaveBtn(ActionEvent e) throws Exception
    {
        shop.saveAll();
        txtFeedBack.setText("User Saved");
    }

    public void handleLoadBtn(ActionEvent e) throws Exception
    {
        shop.loadAll();
        txtFeedBack.setText("Users Loaded");
    }

}
