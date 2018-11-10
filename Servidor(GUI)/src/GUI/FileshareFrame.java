package GUI;

import javax.swing.*;
import java.awt.*;

public class FileshareFrame extends JFrame {

    private LoginPanel jLogin;
    private FilesharePanel jMain;

    public FileshareFrame(){
        super("Servidor");
        this.setSize(1280,768);

        jLogin = new LoginPanel();
        jMain = new FilesharePanel();

        add(jLogin);
        //add(jMain);
        //jLogin.setVisible(false);
        //jMain.setVisible(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    public FileshareFrame(int x, int y, int length, int width){
        super("Servidor");
        this.setPreferredSize(new Dimension(x,y));
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void setPanel(String painel){
        if(painel.equals("main")){
            getContentPane().removeAll();
            getContentPane().invalidate();
            setContentPane(jMain);
            getContentPane().revalidate();
        }
        else{
            getContentPane().removeAll();
            getContentPane().invalidate();
            setContentPane(jMain);
            getContentPane().revalidate();
        }
    }

    public void setFicheiros(String[] listaficheiros){
        jMain.setFicheiros(listaficheiros);
    }

    public void setClientes(String[] listaclientes){
        jMain.setClientes(listaclientes);
    }
}
