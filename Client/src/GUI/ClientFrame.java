package GUI;
import BaseClass.Client;

import javax.swing.*;
import java.awt.*;

public class ClientFrame extends JFrame {

    private Client model;
    private ClientMainPanel mainPanel;


    public ClientFrame(Client model){

        super("Client");

        this.model = model;

        Container cp = getContentPane();
        mainPanel = new ClientMainPanel(model);

        cp.add(mainPanel,BorderLayout.CENTER);

        setLocation(35, 2);
        setSize(1280, 720);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        validate();

    }


}
