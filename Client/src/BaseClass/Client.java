package BaseClass;
import java.io.*;
import java.net.Socket;

public class Client implements Constants {

    private String addressServer = "";
    private int portServer;
    static int TIMEOUT = 10000;
    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private LoginInformation login;
    private FilesStorageInformation files;
    private ChatMessage lastChatMessage;
    private ChatMessage sentMessage;

    public Client(){
        socket = null;
        files = new FilesStorageInformation();
    }

    public void setLastChatMessage(ChatMessage msg){
        lastChatMessage = msg;
    }

    public ChatMessage getLastChatMessage(){

        return lastChatMessage;
    }

    public int getPortServer() {
        return portServer;
    }

    public String getAddressServer() {
        return addressServer;
    }

    public void setAddressServer(String address){
        addressServer = address;
    }

    public void setPortServer(int port){
        portServer = port;
    }

    public boolean serverConnection() {

        try {
            socket = new Socket(addressServer, portServer);
            socket.setSoTimeout(TIMEOUT);

            out = new ObjectOutputStream(socket.getOutputStream());

            int serverResponse;

            in = new ObjectInputStream(socket.getInputStream());
            serverResponse = (int)in.readObject();

            switch (serverResponse){
                case CONNECTED_TO_SERVER: System.out.println("Connected sucessfuly!");return true;
                case ERROR_CONNECT_SERVER: System.out.println("Error connecting to Server");break;

            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }


        return false;
    }

    //Works for register
    public int loginConnection(String username,String password){

        login = new LoginInformation(username,password);
        int serverResponse = -2;

        try {
            out.writeObject(login);

            serverResponse = (int)in.readObject();

            switch (serverResponse){
                case LOGIN_SUCCESSFULLY: System.out.println("Logged in!");break;
                case LOGIN_USER_ALREADY_LOGGED: System.out.println("User already authenticated");break;
                case LOGIN_ERROR: System.out.println("Error on login");
            }


        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        return serverResponse;
    }

    public void setFiletoDownload(String file){
        files.setLastFileToDownload(file);
    }

    public void setDirectoryUploadDFiles(String dir){
        files.setDirectoryPath(dir);
    }

    public void setDirectoryToDownloadFiles(String dir){
        files.setDirectoryToSaveFile(dir);
    }

    private int downloadFileFromUser() {

        Socket s;
        File localDirectory;
        FileOutputStream localFileOutputStream;
        String localFilePath,fileToDownload;
        byte [] fileChunk = new byte[MAX_FILE_SIZE];
        int nbytes;

        try{

            fileToDownload = files.getLastFileToDownload();
            localDirectory = new File(files.getDirectoryPath());
            localFilePath = localDirectory.getCanonicalPath()+File.separator+fileToDownload;

            boolean fileAlreadyExists = new File(localFilePath).exists();
            if(fileAlreadyExists)
                return ERROR_FILE_ALREADY_EXIST_IN_DIRECTORY;

            localFileOutputStream = new FileOutputStream(localFilePath);

            //Sends information to server -> filename
            out.writeObject(fileToDownload);

            if(in.readObject() instanceof Integer){

                Integer res = (Integer)in.readObject();

                switch(res){
                    case ERROR_NOSUCH_FILENAME_ON_SERVER: System.out.println("No such filename on server");break;
                }

                return res;
            }

            else if(in.readObject() instanceof ClientSocketInformation){

               ClientSocketInformation userInformation = (ClientSocketInformation) in.readObject();

               s = new Socket(userInformation.getIp(),userInformation.getPort());
               s.setSoTimeout(TIMEOUT);

                //Receive from socket
                InputStream input = socket.getInputStream();
                //Send to socket
                PrintWriter output = new PrintWriter(s.getOutputStream(),true);
                output.println(fileToDownload);
                output.flush();

                while((nbytes = input.read(fileChunk)) > 0){
                    localFileOutputStream.write(fileChunk, 0, nbytes);
                }

                //Check if file is valid...
                File file = new File(localFilePath);
                if(file.length() == userInformation.getFileSize()) {
                    s.close();
                    input.close();
                    output.close();
                    return FILE_DOWNLOADED_SUCCESSFULLY;
                }
                else{
                    System.out.println("Error on downloaded file");
                    if(file.delete())
                        System.out.println("File successfully deleted");
                    else
                        System.out.println("Error on deleting file from directory");
                }


            }

        } catch (IOException e) {
            return EXCEPTION_ERROR_CONSTANT;
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found received from server");
            return EXCEPTION_ERROR_CONSTANT;
        }

        return ERROR_FILE_DOWNLOAD;
    }


    public void terminateTasks(){
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public boolean sendMessageGlobal(String message){

        sentMessage = new ChatMessage(login.getUsername(),message);


        try {
            out.writeObject(sentMessage);
        } catch (IOException e) {
            return false;
        }

        return true;
    }

    public void sendMessageToUser(String message,String user){

    }

}
