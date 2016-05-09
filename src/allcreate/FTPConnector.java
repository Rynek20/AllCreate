
package allcreate;

import java.io.IOException;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;


public class FTPConnector {
   private String server; 
   private int port;
    private String login;
    private String password;
    private final FTPClient ftpClient;
    
    public FTPConnector(){
        this.ftpClient = new FTPClient();
    }
    
    public boolean connect(String login, String password){
        try{
            ftpClient.connect(server,port);
            showServerReply();
            int reply = ftpClient.getReply();
            if(FTPReply.isPositiveCompletion(reply)){
                return false;
            }
            boolean loginReply = ftpClient.login(login, password);
            showServerReply();
            return loginReply;
        }catch(IOException e){
            e.printStackTrace();
        }
        return true;
    }
    
    private void showServerReply() {
        String[] replies = ftpClient.getReplyStrings();
        if (replies != null && replies.length > 0) {
            for (String aReply : replies) {
                System.out.println("SERVER: " + aReply);
            }
        }
    }
}
