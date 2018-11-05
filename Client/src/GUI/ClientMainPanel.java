package GUI;

import BaseClass.Client;

import javax.swing.*;
import java.awt.*;

public class ClientMainPanel extends JPanel {

    Client clientMethods;
    ConnectPanel connectPanel;
    LoginPanel loginPanel;


    public ClientMainPanel(Client client){

        clientMethods = client;

        setupComponents();
        setupLayout();


    }

    public void setupComponents(){

        connectPanel = new ConnectPanel(clientMethods);
        loginPanel = new LoginPanel(clientMethods);
    }

    public void setupLayout(){

       add(connectPanel);
       setBackground(Color.gray);
    }
}
