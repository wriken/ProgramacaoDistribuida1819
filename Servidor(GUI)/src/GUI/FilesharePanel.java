package GUI;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class FilesharePanel extends JPanel {

    private JList<String> ficheiros;
    private JList<String> clientes;
    private JScrollPane spFicheiros;
    private JScrollPane spClientes;

    public FilesharePanel(){
        super();
        setLayout(new GridLayout(2,2,0,0));
        Font myFont = new Font("Areal", Font.BOLD, 20);
        ficheiros = new JList<>(new String[]{"motocross.mp4", "camioes_tiro.jpeg","O pedro e os caes.pdf"});
        clientes = new JList<>(new String[]{"Joao", "Jose","Gabriel"});

        ficheiros.setFont(myFont);
        ficheiros.setBorder(BorderFactory.createEmptyBorder(0,10,10,10));
        clientes.setFont(myFont);
        clientes.setBorder(BorderFactory.createEmptyBorder(0,10,10,10));
        spFicheiros = new JScrollPane(ficheiros);
        spClientes = new JScrollPane(clientes);
        add(spFicheiros);
        add(spClientes);
        add(new JLabel(""));
        add(new JLabel(""));

        spFicheiros.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        spFicheiros.setBackground(Color.GREEN);
        spClientes.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        spClientes.setBackground(Color.RED);
        //setBorder(BorderFactory.createEmptyBorder(150,420,150,420));

        setVisible(true);
    }

    public void setFicheiros(String[] listaFicheiros){
        ficheiros.setListData(listaFicheiros);
        System.out.println("Mudei a lista de Clientes");
    }

    public void setClientes(String[] listaClientes){
        clientes.setListData(listaClientes);
        System.out.println("Mudei a lista de Ficheiros");
    }
}
