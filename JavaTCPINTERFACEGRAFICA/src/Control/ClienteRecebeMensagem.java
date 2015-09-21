/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;

/**
 *
 * @author Iago Rodrigues
 */
public class ClienteRecebeMensagem extends Thread {

    Socket socket;
    JTextArea areaTexto;
    ArrayList<PrintStream> streamsSaida;
    
    public ClienteRecebeMensagem (Socket socket, JTextArea areaTexto){
        this.socket = socket;
        this.areaTexto = areaTexto;
    }
    
    @Override
    public synchronized void run(){
        try {
            Scanner s = new Scanner(socket.getInputStream());
            while (s.hasNextLine()) {
                areaTexto.setText(areaTexto.getText() + "\n" + s.nextLine());
            }
            
        } catch (IOException ex) {
            Logger.getLogger(ClienteRecebeMensagem.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }  
}
