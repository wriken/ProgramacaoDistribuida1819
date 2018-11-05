import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class FilesInformation implements Serializable {

    private String DirectoryPath;
    private ArrayList<String> FileNameList;

    public FilesInformation(){
        DirectoryPath = "";
        FileNameList = new ArrayList<>();
    }

    public void addFile(String filename){
        FileNameList.add(filename);
    }

    public String getDirectoryPath(){
        return DirectoryPath;
    }

    public ArrayList<String> getFileNameList(){
        return FileNameList;
    }

    public void setDirectoryPath(String directoryPath) {
        DirectoryPath = directoryPath;
    }
}
