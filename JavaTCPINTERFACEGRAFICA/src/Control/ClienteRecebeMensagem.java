package Control;

import java.io.InputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
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
/*        try {
            Scanner s = new Scanner(socket.getInputStream());
            while (s.hasNextLine()) {
                areaTexto.setText(areaTexto.getText() + "\n" + s.nextLine());
            }
            
        } catch (IOException ex) {
            Logger.getLogger(ClienteRecebeMensagem.class.getName()).log(Level.SEVERE, null, ex);
        }
  */
        try{
            InputStream is = socket.getInputStream();
            AES seguranca = new AES(null);
            
            while(true){
                int dado;
                byte[] recebimento = new byte[1024];
                is.read(recebimento);
                String resultado = seguranca.decrypt(recebimento);
                areaTexto.setText(areaTexto.getText() + "\n" + resultado);
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }  
}