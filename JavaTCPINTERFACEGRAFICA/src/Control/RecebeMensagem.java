package Control;

import java.io.InputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JTextArea;

/**
 *
 * @author Iago Rodrigues
 */
public class RecebeMensagem extends Thread {

    Socket socket;
    JTextArea areaTexto;
    ArrayList<PrintStream> streamsSaida;

    public RecebeMensagem(Socket socket, JTextArea areaTexto) {
        this.socket = socket;
        this.areaTexto = areaTexto;
    }

    @Override
    public synchronized void run() {
        try {
            Scanner s = new Scanner(socket.getInputStream());
            while (s.hasNextLine()) {
                String encriptado = s.nextLine();
                System.out.println(encriptado);
                AES seguranca = new AES(null);
                String resultado = seguranca.decrypt(encriptado.getBytes());
                areaTexto.setText(areaTexto.getText() + "\n" + resultado);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        /*
         try{
         InputStream is = socket.getInputStream();
         AES seguranca = new AES(null);
            
         while(true){
         int dado;
         String encriptado = "";
                
         while((dado=is.read())!=-1){
         encriptado = encriptado + (char)dado;
         }
                
         String resultado = seguranca.decrypt(encriptado.getBytes());
                
         areaTexto.setText(areaTexto.getText() + "\n" + resultado);
         }
            
         }catch(Exception e){
         e.printStackTrace();
         }
         */
    }
}
