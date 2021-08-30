package application.model;

import java.time.LocalDate;

public class Reservation {
    private LocalDate date;
    private String custName;
    private String contactNum;
    private Bike bike;
    private String duration;
    private String pickUp;
    private String dropOff;
    private User currentUser;

    public Reservation(LocalDate date, String custName, String contactNum, Bike bike, String duration, String pickUp, String dropOff) {
        this.date = date;
        this.custName = custName;
        this.contactNum = contactNum;
        this.bike = bike;
        this.duration = duration;
        this.pickUp = pickUp;
        this.dropOff = dropOff;
    }

    public Reservation(LocalDate date, String custName, String contactNum, Bike bike, String duration, String pickUp, String dropOff, User currentUser) {
        this.date = date;
        this.custName = custName;
        this.contactNum = contactNum;
        this.bike = bike;
        this.duration = duration;
        this.pickUp = pickUp;
        this.dropOff = dropOff;
        this.currentUser = currentUser;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getContactNum() {
        return contactNum;
    }

    public void setContactNum(String contactNum) {
        this.contactNum = contactNum;
    }

    public int getBike() {
        return bike.getBikeID();
    }

    public void setBike(Bike bike) {
        this.bike = bike;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getPickUp() {
        return pickUp;
    }

    public void setPickUp(String pickUp) {
        this.pickUp = pickUp;
    }

    public String getDropOff() {
        return dropOff;
    }

    public void setDropOff(String dropOff) {
        this.dropOff = dropOff;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "date=" + date +
                ", name='" + custName + '\'' +
                ", contactNum='" + contactNum + '\'' +
                ", BikeID='" + bike + '\'' +
                ", duration='" + duration + '\'' +
                ", pickUp='" + pickUp + '\'' +
                ", dropOff='" + dropOff + '\'' +
                '}';
    }
}
