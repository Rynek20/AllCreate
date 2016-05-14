
package HTTPGenerator;

import java.io.File;
import java.util.ArrayList;


public class Item {
    private String name;
    private ArrayList<File> imageList;
    private String FTPpath;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<File> getImageList() {
        return imageList;
    }

    public void setImageList(ArrayList<File> imageList) {
        this.imageList = imageList;
    }

    public String getFTPpath() {
        return FTPpath;
    }

    public void setFTPpath(String FTPpath) {
        this.FTPpath = FTPpath;
    }
    
    
    
}
