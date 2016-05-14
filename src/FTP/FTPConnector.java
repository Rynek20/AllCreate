package FTP;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

public class FTPConnector {

    //For windows 7 firewall can bloock ftp, run in cmd: netsh advfirewall set global StatefulFtp disable
    public enum Status {

        CONNECTED, DISCONNECTED
    }

    private String server;
    private int port;
    private String login;
    private String password;
    private final FTPClient ftpClient;
    private Status status;


    public FTPConnector() {
        this.ftpClient = new FTPClient();
    }

    FTPConnector(Settings actualSettings) {
        this.ftpClient = new FTPClient();
        this.server = actualSettings.getServerName();
        this.port = actualSettings.getPortNr();
        this.status = Status.DISCONNECTED;
    }

    public boolean connect(String login, String password) throws IOException {
        try {

            ftpClient.connect(server, port);
            showServerReply();
            boolean Reply = ftpClient.login(login, password);
            showServerReply();
            if (Reply) {
                ftpClient.enterLocalPassiveMode();
                status = Status.CONNECTED;
                return true;
            } else {
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean disconnect() {
        try {
            ftpClient.logout();
            ftpClient.disconnect();
            this.status = Status.DISCONNECTED;
            showServerReply();
        } catch (IOException ex) {
            Logger.getLogger(FTPConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    private DefaultMutableTreeNode getAllNodes(String path, String current){
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(current);
        try {
            FTPFile[] files = ftpClient.listFiles(path);
            for (int i = 0; i < files.length; i++) {
                if(files[i].isDirectory()){
                    node.add(getAllNodes(path+"/"+files[i].getName(), files[i].getName()));
                }else{
                    node.add(new DefaultMutableTreeNode(files[i].getName()));
                }
            }
            return node;
            
        } catch (IOException ex) {
            Logger.getLogger(FTPConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public DefaultMutableTreeNode getNode(String path){
        String[] points = path.split("/");
        if(points.length==0){
            points = new String[1];
            points[0]="/";
        }
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(points[points.length-1]);
        try {
            FTPFile[] files = ftpClient.listFiles(path);
            if(files[0].getName().equals(path)){
                return null;
            }
            for (int i = 0; i < files.length; i++) {
                node.add(new DefaultMutableTreeNode(files[i].getName()));
            }
            return node;
            
        } catch (IOException ex) {
            Logger.getLogger(FTPConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public File getFile(String path){
        String[] points = path.split("/");
        if(points.length==0){
            points = new String[1];
            points[0]="/";
        }
        try {
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            File f = new File("tmpFiles/"+points[points.length-1]);
            boolean success;
            try (OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(f))) {
                success = ftpClient.retrieveFile(path, outputStream);
            }
            if (success) {
                System.out.println("File #1 has been downloaded successfully.");
                return null;
            }
            return f;
        } catch (IOException ex) {
            Logger.getLogger(FTPConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public DefaultTreeModel getStructure() {
        if (ftpClient.isConnected()) { 
        return new DefaultTreeModel(getAllNodes("", "/"));    
        }
        return null;
    }

    private void showServerReply() {
        String[] replies = ftpClient.getReplyStrings();
        if (replies != null && replies.length > 0) {
            for (String aReply : replies) {
                System.out.println("SERVER: " + aReply);
            }
        }
    }

    public Status getStatus() {

        return status;
    }

}
