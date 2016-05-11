package allcreate;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.net.ftp.FTPClient;

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
        } catch (IOException ex) {
            Logger.getLogger(FTPConnector.class.getName()).log(Level.SEVERE, null, ex);
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

    public Status getStatus() {

        return status;
    }

}
