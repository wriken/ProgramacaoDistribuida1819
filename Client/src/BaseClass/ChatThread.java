package BaseClass;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ChatThread extends Thread {

    private Client model;
    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    public ChatThread(Client m,Socket s){
        model = m;
        socket = s;
    }

    public void chat(){

        try{

            in = new ObjectInputStream(socket.getInputStream());
            out = new ObjectOutputStream(socket.getOutputStream());

            while (true){
                

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
