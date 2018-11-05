import java.sql.*;

public class MySQL {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private String cs = "jdbc:my§§sql://35.189.228.42:3306/programacaodistribuida?useSSL=false";
    private String user = "wriken";
    private String password = "wriken123";

    private Connection con;

    public MySQL() {

    }

    public MySQL(String cs, String user, String password, Connection con) {
        this.cs = cs;
        this.user = user;
        this.password = password;
        this.con = con;
    }

    public String getCs() {
        return cs;
    }

    public void setCs(String cs) {
        this.cs = cs;
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

    public void getUsersList() {

        String sql = "SELECT * FROM users_list ORDER BY id";

        try {
            Class.forName(JDBC_DRIVER);
            con = DriverManager.getConnection(cs, user, password);
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
            con = DriverManager.getConnection(cs, user, password);
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

            con = DriverManager.getConnection(cs, user, password);
            PreparedStatement pst = con.prepareStatement(sql);

            ResultSet rs = pst.executeQuery(sql);

            while (rs.next()) {

                return rs.getString("directoria");
            }
            rs.close(); pst.close(); con.close();


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
            con = DriverManager.getConnection(cs,user,this.password);
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
            con = DriverManager.getConnection(cs,user,this.password);
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
            con = DriverManager.getConnection(cs,user,this.password);
            PreparedStatement pst = con.prepareStatement(sql);
            pst.executeUpdate();

            pst.close(); con.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
