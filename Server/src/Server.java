import javax.sound.midi.SoundbankResource;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;

public class Server {

    public static final int SERVICE_PORT = 6000;
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://35.189.228.42:3306/programacaodistribuida?useSSL=false";



    public static void runServidor() {

        try {
            ServerSocket server = new ServerSocket(SERVICE_PORT);
            System.out.println("Servidor a correr...");
            conectaBD();

            while(true){

                Socket novoCliente = server.accept();


                System.out.println("Novo cliente conectado: " + novoCliente.getInetAddress().getHostAddress() + " Porta: " + novoCliente.getPort());

                ObjectInputStream in = new ObjectInputStream(novoCliente.getInputStream());

                LoginInformation clientLogin = (LoginInformation)in.readObject();

                System.out.println(clientLogin.Username + " " + clientLogin.Password);

                ObjectOutputStream out = new ObjectOutputStream(novoCliente.getOutputStream());
                int responseToClient = 1;
                out.writeObject(responseToClient);

                FilesInformation infoFromClient;
                infoFromClient = (FilesInformation) in.readObject();
                System.out.println(infoFromClient.getDirectoryPath());
                for(String s : infoFromClient.getFileNameList())
                    System.out.println(s);


            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public static void conectaBD() throws ClassNotFoundException {


        Class.forName("com.mysql.jdbc.Driver");
        System.out.println("Connecting to database...");


        try {
            Connection conn = DriverManager.getConnection(DB_URL,"macgab","macgab123");
            Statement stmt = conn.createStatement();


            String sql;
            //sql = "SELECT * FROM users_list WHERE ip = 1";
            sql = "SELECT id,name FROM users";
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println("Conexção estabelecida com a Base de Dados!");


            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");

                System.out.println("ID:" + id);
                System.out.println("Nome: " + name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




    public static void main(String[] args) {


        runServidor();

    }
}



