package BaseClass;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class FilesStorageInformation implements Serializable {

    private String DirectoryPath;
    private String lastFileToDownload;
    private String DirectoryToSaveFile;

    public FilesStorageInformation() {
        DirectoryPath = "";
        lastFileToDownload = "";
        DirectoryToSaveFile = "";
    }

    public String getDirectoryPath(){
        return DirectoryPath;
    }

    public void setDirectoryPath(String directoryPath) {
        DirectoryPath = directoryPath;
    }

    public void setLastFileToDownload(String lastFileToDownload) {
        this.lastFileToDownload = lastFileToDownload;
    }

    public String getLastFileToDownload() {
        return lastFileToDownload;
    }

    public String getDirectoryToSaveFile() {
        return DirectoryToSaveFile;
    }

    public void setDirectoryToSaveFile(String directoryToSaveFile) {
        DirectoryToSaveFile = directoryToSaveFile;
    }
}
