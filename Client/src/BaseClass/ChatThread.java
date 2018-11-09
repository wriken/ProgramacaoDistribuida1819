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
    private ChatMessage msg;

    public ChatThread(Client m,Socket s){
        model = m;
        socket = s;
    }

    @Override
    public void run() {
        super.run();

        chat();

    }

    public void chat(){

        try{

            in = new ObjectInputStream(socket.getInputStream());
            //out = new ObjectOutputStream(socket.getOutputStream());

            while (true){
                
                if(in.readObject() instanceof ChatMessage){

                    msg = (ChatMessage) in.readObject();
                    model.setLastChatMessage(msg);
                    //notify listeners;

                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
