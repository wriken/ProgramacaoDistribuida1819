import javax.sound.midi.SoundbankResource;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.*;
import java.util.Scanner;

public class Server {

    public static final int SERVICE_PORT = 6000;
    MySQL ms;


    public static void runServidor() {

        try {
            ServerSocket server = new ServerSocket(SERVICE_PORT);
            System.out.println("Servidor a correr...");

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

    public void showFiles(){

        Scanner scanner = new Scanner(System.in);

        System.out.println("Introduza o nome do ficheiro: ");
        String filename = scanner.nextLine();
        ms.getFilePath(filename);
    }

    public  void addFile(){

        ms.newFile(, , , , , );
    }

    public void addUser(){

        Scanner scanner = new Scanner(System.in);

        System.out.println("Introduza o username: ");
        String user = scanner.nextLine();
        System.out.println("Introduza a password: ");
        String password = scanner.nextLine();

        try {
            ms.newUser(user, password, InetAddress.getLocalHost().getHostAddress(), 1);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(){

        Scanner scanner = new Scanner(System.in);

        System.out.println("Introduza o username a eliminar: ");
        String username = scanner.nextLine();
        ms.deleteUserByUsername(username);
    }

    public void serverOptions(){

        int option = 0;
        Scanner scanner = new Scanner(System.in);

        System.out.println("1 - Listar Utilizadores");
        System.out.println("2 - Listar Ficheiros");
        System.out.println("3 - Listar Ficheiros por Diretoria");
        System.out.println("4 - Adicionar Ficheiro");
        System.out.println("5 - Adicionar Utilizador");
        System.out.println("6 - Eliminar Utilizador");
        System.out.println("7 - Sair");
        option = scanner.nextInt();


        switch (option){


            case 1: ms.getUsersList(); break;

            case 2: ms.getFiles(); break;

            case 3: showFiles(); break;

            case 4: addFile(); break;

            case 5: addUser(); break;

            case 6: deleteUser(); break;

            case 7: System.exit(0);

            default: System.out.println("Opção Inválida!");

        }
    }


    public static void main(String[] args) {


        runServidor();
    }
}



