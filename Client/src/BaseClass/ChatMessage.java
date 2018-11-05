package BaseClass;

import java.io.Serializable;

public class ChatMessage implements Serializable {

    String username;
    String message;

    public ChatMessage(){


    }

    public ChatMessage(String user,String msg){

        username = user;
        message = msg;

    }

    public String getUsername() {
        return username;
    }

    public String getMessage() {
        return message;
    }

}