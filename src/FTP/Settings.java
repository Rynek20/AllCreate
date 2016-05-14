/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FTP;


public class Settings {
    private String serverName;
    private int portNr;
    
    public Settings(){
        this.serverName = "kartalcarpets.home.pl";
        this.portNr = 21;
    }
    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public int getPortNr() {
        return portNr;
    }

    public void setPortNr(int portNr) {
        this.portNr = portNr;
    }
    
    
}
