package PD;

import com.mysql.jdbc.authentication.MysqlClearPasswordPlugin;

public class Main {

    public static void main(String[] args) {
	// write your code here
        MySQL mySQL = new MySQL();

        mySQL.getUsersList();
        mySQL.deleteUserByUsername("pau");
        //mySQL.newFile(1,"wriken","IMG_20181023_134018","jpeg","2696","D:\\Transferências");
        mySQL.getFiles();
        System.out.println(mySQL.getFilePath("IMG_20181023_134018"));
    }
}
