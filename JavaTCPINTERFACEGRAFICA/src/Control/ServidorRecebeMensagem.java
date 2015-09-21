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
public class ServidorRecebeMensagem extends Thread {

    Socket socket;
    JTextArea areaTexto;
    ArrayList<PrintStream> streamsSaida;
    
    public ServidorRecebeMensagem (Socket socket, JTextArea areaTexto, ArrayList<PrintStream> streamsSaida){
        this.socket = socket;
        this.areaTexto = areaTexto;
        this.streamsSaida = streamsSaida;
    }
    
    @Override
    public synchronized void run(){
        try {
            Scanner s = new Scanner(socket.getInputStream());
            while (s.hasNextLine()) {
                areaTexto.setText(areaTexto.getText() + "\n" + s.nextLine());
                for (int i = 0; i < streamsSaida.size(); i++) {
                    streamsSaida.get(i).println(s.nextLine());
                }
            }
            
        } catch (IOException ex) {
            Logger.getLogger(ClienteRecebeMensagem.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }  
}
