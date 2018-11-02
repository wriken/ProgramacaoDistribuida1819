package GUI;

import BaseClass.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class ConnectPanel extends JPanel {

    Client model;
    JTextField addressField,portField;
    JButton confirmButton,cancelButton;
    JPanel panel;


    public ConnectPanel(Client model){

        this.model = model;

        setupComponents();
        setupLayout();
        registerListeners();
    }

    public void setupComponents(){

        addressField = new JTextField(15);
        portField = new JTextField(4);
        confirmButton = new JButton("Connect");
        cancelButton = new JButton("cancel");

    }

    public void setupLayout(){


        setLayout(new GridLayout(10,1,0,10));
        add(new Label(""));
        add(new Label(""));
        add(new Label(""));
        add(new Label(""));
        add(new Label(""));
        add(new Label("Address of server"));
        add(addressField);
        add(new Label("Port of server"));
        add(portField);
        JPanel jp = new JPanel();
        jp.setLayout(new GridLayout(1,2,10,0));
        jp.add(confirmButton);
        jp.add(cancelButton);
        add(jp);

        setBackground(Color.gray);


    }

    public void registerListeners(){

        confirmButton.addActionListener(new ConfirmButtonListener());

    }

    class ConfirmButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            model.setAddressServer(addressField.getText());
            model.setPortServer(Integer.parseInt(portField.getText()));

        }
    }



}
