package application.controller;

import java.awt.event.ActionEvent;

public class WelcomeController {

    public void handleMakeReservationBtn( ) throws Exception {
        Main.set_pane(3);
    }

    public void handleViewReservationsBtn() throws Exception {
        Main.set_pane(4);
    }

    public void handleCreateBikeBtn() throws Exception {
        Main.set_pane(5);
    }

    public void handleViewBikesBtn() throws Exception {
        Main.set_pane(6);
    }

    public void handleViewUsersBtn() throws Exception {
        Main.set_pane(7);
    }

    public void handleLogoutBtn() throws Exception {
        Main.set_pane(0);
    }
}
