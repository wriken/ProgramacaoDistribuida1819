import java.io.Serializable;

public class LoginInformation implements Serializable {

    String Username,Password;

    public LoginInformation(){


    }

    public LoginInformation(String user, String pass){
        Username = user;
        Password = pass;
    }

    public String getUsername(){
        return Username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public void setUsername(String username) {
        Username = username;
    }
}
