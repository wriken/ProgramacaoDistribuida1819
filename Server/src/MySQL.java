import javax.swing.*;
import java.sql.*;

public class MySQL {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private String DB_URL = "jdbc:my§§sql://35.189.228.42:3306/programacaodistribuida?useSSL=false";
    private String user = "macgab";
    private String password = "macgab123";

    private Connection con;

    public MySQL() {

    }

    public MySQL(String DB_URL, String user, String password, Connection con) {
        this.DB_URL = DB_URL;
        this.user = user;
        this.password = password;
        this.con = con;
    }

    public String getDB_URL() {
        return DB_URL;
    }

    public void setDB_URL(String DB_URL) {
        this.DB_URL = DB_URL;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }

    public  void conectDB() throws ClassNotFoundException {


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


    public void getUsersList() {

        String sql = "SELECT * FROM users_list ORDER BY id";

        try {
            Class.forName(JDBC_DRIVER);
            con = DriverManager.getConnection(DB_URL, user, password);
            PreparedStatement pst = con.prepareStatement(sql);

            ResultSet rs = pst.executeQuery(sql);

            while (rs.next()) {
                //Retrieve by column name
                String username = rs.getString("username");
                String password = rs.getString("password");
                String ip = rs.getString("ip");
                int autenticado = rs.getInt("autenticado");
                //Display values
                System.out.print("username:  " + username);
                System.out.print(", password: " + password);
                System.out.print(", ip: " + ip);
                System.out.println(", autenticado: " + autenticado);
            }
            rs.close(); pst.close(); con.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void getFiles(){
        String sql = "SELECT * FROM files ORDER BY id";

        try {
            Class.forName(JDBC_DRIVER);
            con = DriverManager.getConnection(DB_URL, user, password);
            PreparedStatement pst = con.prepareStatement(sql);

            ResultSet rs = pst.executeQuery(sql);

            while (rs.next()) {
                //Retrieve by column name
                int id = rs.getInt("id");
                String user = rs.getString("user");
                String filename = rs.getString("filename");
                String filesize = rs.getString("filesize");
                String filetype = rs.getString("filetype");
                String directoria = rs.getString("directoria");
                //Display values
                System.out.print("id: " + id);
                System.out.print(", user: " + user);

                System.out.print(", filename: " + filename);
                System.out.print(", filesize: " + filesize);
                System.out.print(", filetype: " + filetype);
                System.out.println(", directoria: " + directoria);
            }
            rs.close(); pst.close(); con.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String getFilePath(String filename){

        String sql = "SELECT * FROM files WHERE filename='"+filename+"'";

        try {
            Class.forName(JDBC_DRIVER);

            con = DriverManager.getConnection(DB_URL, user, password);
            PreparedStatement pst = con.prepareStatement(sql);

            ResultSet rs = pst.executeQuery(sql);

            while (rs.next()) {

                return rs.getString("directoria");
            }
            rs.close();
            pst.close();
            con.close();


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "Nao existe esse ficheiro";
    }

    public void newFile(int id, String user, String filename, String filetype, String filesize, String directoria){

        String sql = "INSERT INTO files (id,user, filename, filetype, filesize, directoria) " +
                "VALUES ('"+id+"','"+user+"','"+filename+"','"+filetype+"','"+filesize+"','"+directoria+"')";
        try {
            Class.forName(JDBC_DRIVER);
            con = DriverManager.getConnection(DB_URL,user,this.password);
            PreparedStatement pst = con.prepareStatement(sql);
            pst.executeUpdate();

            pst.close(); con.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void newUser(String username, String password, String ip, int autenticado){

        String sql = "INSERT INTO users_list(username,password,ip,autenticado) " +
                "VALUES('" + username + "','" + password + "','" + ip + "','" + autenticado + "')";
        try {
            Class.forName(JDBC_DRIVER);
            con = DriverManager.getConnection(DB_URL,user,this.password);
            PreparedStatement pst = con.prepareStatement(sql);
            pst.executeUpdate();

            pst.close(); con.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void deleteUserByUsername(String username){
        String sql = "DELETE FROM users_list " +
                "WHERE username = '"+username+"'";
        try {
            Class.forName(JDBC_DRIVER);
            con = DriverManager.getConnection(DB_URL,user,this.password);
            PreparedStatement pst = con.prepareStatement(sql);
            pst.executeUpdate();

            pst.close(); con.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
