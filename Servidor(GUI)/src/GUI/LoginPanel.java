package GUI;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPanel extends JPanel {

    private JTextField ip;
    private JTextField username;
    private JTextField password;
    private JButton bLogin;

    public LoginPanel() {
        super();

        setLayout(new GridLayout(8,1,0,10));
        ip = new JTextField();
        username = new JTextField();
        password = new JTextField();
        bLogin = new JButton("Login");
        add(new JLabel("IP: "));
        add(ip);
        add(new JLabel("Username: "));
        add(username);
        add(new JLabel("Password: "));
        add(password);
        add(bLogin);

        setBorder(BorderFactory.createEmptyBorder(150,420,150,420));

        setVisible(true);

        bLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }


}
