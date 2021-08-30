package application.model;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;


public class BikeShopModel {
    private ArrayList<Bike> stock;
    private ArrayList<Reservation> reservations;
    private ArrayList<User> users;

    public BikeShopModel() {
        this.stock = new ArrayList<>();
        this.reservations = new ArrayList<>();
        this.users = new ArrayList<>();
    }

    public ArrayList<Bike> getStock() {
        return stock;
    }

    public ArrayList<Reservation> getReservations() {
        return reservations;
    }

    public ArrayList<User> getUsers() {
        return users;
    }




    /////////////////////////////////////////////BIKES//////////////////////////////////////////////////

    public void addBike(String size, String style, String gender, double price, String condition, String comment) {
        int bikeID = 100 + stock.size();

        Bike b = new Bike(bikeID, size, style, gender, price, condition, comment);

        stock.add(b);
        try {
            saveAll();
        }
        catch(Exception e){
            System.out.println("error  " + e);
        }
    }


    public boolean deleteBike(int bikeID) {
        int i = 0;
        for (Bike item : stock) {
            if (item.getBikeID() == bikeID) {
                stock.remove(i);
                try {
                    saveAll();
                } catch (Exception e) {
                }
                return true;
            }
            i++;
        }
        return false;
    }


    public boolean updateBike(int bikeID, String size, String style, String gender,
                              double price, String condition, String comment) {

        Bike b2 = new Bike(bikeID, size, style, gender, price, condition, comment);

        int i = 0;
        for (Bike item : stock) {
            if (item.getBikeID() == bikeID) {
                stock.set(i, b2);
                return true;
            }
            i++;
        }
        return false;
    }

    public String listBikes() {

        int i = 0;

        String displayBikes = "All Bikes";

        for (Bike item : stock) {

            displayBikes += "\n" + (i++) + ": " + item;

        }
        return displayBikes;

    }

    public Bike getBike(int index) {

        //check if index it valid if greater or equal to 0 and less than stock.size
        if (index >= 0 && index < stock.size()) {
            return stock.get(index);
        }
        return null;
    }


    public Bike findBike(int id){

        for (Bike item : stock) {
            if (item.getBikeID() == id)
                return item;

        }
        return null;
    }



///////////////////////////////RESERVATIONS///////////////////////////////////////

    public void addReservation(Reservation r) {

        reservations.add(r);
        try {
            saveAll();
        }
        catch(Exception e){
            System.out.println("error  " + e);
        }
    }

    public boolean deleteReservation(String contactNum) {
        int i = 0;
        for (Reservation item : reservations) {
            if (item.getContactNum() == contactNum) {
                reservations.remove(i);
                try {
                    saveAll();
                } catch (Exception e) {
                }
                return true;
            }

                i++;
            }
            return false;
    }

    public boolean updateReservation(LocalDate date, String custName, String contactNum, String bikeID, String duration, String pickUp, String dropOff, int index) {

        Bike bikeFound = findBike(Integer.parseInt(bikeID));
        Reservation r2 = new Reservation(date, custName, contactNum, bikeFound, duration, pickUp, dropOff);

        int i = 0;
       /* for (Reservation item : reservations) {
            if (item.getContactNum() == contactNum) {
                reservations.set(i, r2);
                return true;
            }
            i++;
        }*/
        if (index >= 0 && index < reservations.size()){
            reservations.set(index, r2);
            return true;
        }

        return false;
    }


    public String listReservations() {

        int i = 0;

        String displayReservations = "All Reservations";

        for (Reservation item : reservations) {

            displayReservations += "\n" + (i++) + ": " + item;

        }
        return displayReservations;

    }

    public Reservation findReservation(String contactNum){

        for (Reservation item : reservations) {
            if (item.getContactNum() == contactNum)
                return item;

        }
        return null;
    }

    public Reservation getReservation(int index) {

        if (index >= 0 && index < reservations.size()) {
            return reservations.get(index);
        }
        return null;
    }



    ////////////////////////////////USERS/////////////////////////////////////////////////

    public void addUser(User u) {

        users.add(u);
        try {
            saveAll();
        }
        catch(Exception e){
            System.out.println("error  " + e);
        }
    }

    public boolean deleteUser(String userName) {
        int i = 0;
        for (User item : users) {
            if (item.getUsername() == userName) {
                users.remove(i);
                return true;
            }
            i++;
        }
        return false;
    }

    public boolean updateUser(String username, String foreName, String surname, String role, String mobileNo, String email, String password) {

        User u2 = new User( username,  foreName,  surname,  role,  mobileNo,  email,  password);

        int i = 0;
        for (User item : users) {
            if (item.getUsername() == username) {
                users.set(i, u2);
                return true;
            }
            i++;
        }
        return false;
    }



    public String listUsers() {

        int i = 0;

        String displayUsers = "All Users";

        for (User item : users) {

            displayUsers += "\n" + (i++) + ": " + item;

        }
        return displayUsers;

    }

    public User findUser(String username, String password){

        for (User item : users) {
            if (item.getUsername().equals(username) && item.getPassword().equals(password))
                return item;

        }
        return null;
    }

    public User getUser(int index) {

        if (index < 0 || index > users.size()) {
            return users.get(index);
        }
        return null;
    }


    //////////////////////////////////////////////SAVE & LOAD/////////////////////////////////////////////



    public void saveAll() throws Exception {
        XStream xstream = new XStream(new DomDriver());

        ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("bikes.xml"));

        out.writeObject(stock);

        out.close();

        out = xstream.createObjectOutputStream(new FileWriter("reservations.xml"));

        out.writeObject(reservations);

        out.close();

        out = xstream.createObjectOutputStream(new FileWriter("users.xml"));

        out.writeObject(users);

        out.close();
    }


    public void loadAll() throws Exception {

        XStream xstream = new XStream(new DomDriver());

        ObjectInputStream is = xstream.createObjectInputStream(new FileReader("bikes.xml"));

        stock = (ArrayList<Bike>) is.readObject();

        is.close();

        ObjectInputStream is2 = xstream.createObjectInputStream(new FileReader("reservations.xml"));

        reservations = (ArrayList<Reservation>) is2.readObject();

        is2.close();

        ObjectInputStream is3 = xstream.createObjectInputStream(new FileReader("users.xml"));

        users = (ArrayList<User>) is3.readObject();

        is3.close();
    }




}
