package BaseClass;

public class ClientSocketInformation {

    private String ip;
    private int port;
    private int fileSize;

    public ClientSocketInformation(String ipAddress,int portNumber,int size){

        ip = ipAddress;
        port = portNumber;
        fileSize = size;

    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getFileSize() {
        return fileSize;
    }

    public void setFileSize(int fileSize) {
        this.fileSize = fileSize;
    }


}
