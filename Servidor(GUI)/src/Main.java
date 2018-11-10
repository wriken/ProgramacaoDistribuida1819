import GUI.FileshareFrame;


public class Main {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://35.189.228.42/programacaodistribuida?useSSL=false";
    static FileshareFrame m;
    public static void main(String[] args){


        m = new FileshareFrame();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        mudaParaMain();
        m.setClientes(new String[]{"a","b","c","d","e","f","g","h"});

    }

    public static void mudaParaMain(){
        m.setPanel("main");
    }
}
    /*public static void main(String[] args) throws ClassNotFoundException {

        Class.forName("com.mysql.jdbc.Driver");

        System.out.println("Connecting to database...");


        try {
            Connection conn = DriverManager.getConnection(DB_URL,"wriken","wriken123");
            Statement stmt = conn.createStatement();
            String sql;
            //sql = "SELECT * FROM users_list WHERE ip = 1";
            //sql = "SELECT id,name FROM users";
            sql = "INSERT INTO users(id,name) VALUES (2,'Gagriel')";

            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");

                System.out.println("ID:" + id);
                System.out.println("Name: " + name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}*/
