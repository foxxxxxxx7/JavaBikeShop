package application.model;

public class User {

    private String username;
    private String foreName;
    private String surname;
    private String role;
    private String mobileNo;
    private String email;
    private String password;

    public User(String username, String foreName, String surname, String role, String mobileNo, String email, String password) {
        this.username = username;
        this.foreName = foreName;
        this.surname = surname;
        this.role = role;
        this.mobileNo = mobileNo;
        this.email = email;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String user_id) {
        this.username = user_id;
    }

    public String getForeName() {
        return foreName;
    }

    public void setForeName(String foreName) {
        this.foreName = foreName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id='" + username + '\'' +
                ", foreName='" + foreName + '\'' +
                ", surname='" + surname + '\'' +
                ", role='" + role + '\'' +
                ", mobileNo='" + mobileNo + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}



