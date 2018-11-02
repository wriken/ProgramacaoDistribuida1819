package GUI;

import BaseClass.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPanel extends JPanel {

    Client model;
    JButton registerButton,loginButton;
    JTextField usernameField;
    JPasswordField passwordField;


    public LoginPanel(Client m){
        this.model = m;

        setupComponents();
        setupLayout();
        registerListeners();
    }


    public void setupComponents(){

        registerButton = new JButton("Register");
        loginButton = new JButton("Login");
        usernameField = new JTextField(15);
        passwordField = new JPasswordField(10);

    }

    public void setupLayout(){

        setLayout(new GridLayout(10,1,0,10));
        add(new Label(""));
        add(new Label(""));
        add(new Label(""));
        add(new Label(""));
        add(new Label(""));
        add(new Label("Username"));
        add(usernameField);
        add(new Label("Password"));
        add(passwordField);
        JPanel jp = new JPanel();
        jp.setLayout(new GridLayout(1,2,10,0));
        jp.add(loginButton);
        jp.add(registerButton);
        add(jp);

        setBackground(Color.gray);

    }

    public void registerListeners(){

        loginButton.addActionListener(new loginButtonListener());
        registerButton.addActionListener(new loginButtonListener()); //Temporary

    }


    class loginButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            String user = usernameField.getText();
            String pass = passwordField.getPassword().toString();

            model.loginConnection(user,pass);
        }

    }


}
