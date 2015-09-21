package Control;

import java.io.InputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import javax.swing.JTextArea;

/**
 * @author Iago Rodrigues
 */
public class EsperaNovaConexao extends Thread {

    ServerSocket socketServidor;
    ArrayList<PrintStream> saidas;
    JTextArea jTextAreaChat;
    ArrayList<InputStream> streamsEntrada;

    public EsperaNovaConexao(ServerSocket socketServidor,
            ArrayList<PrintStream> saidas, JTextArea jTextAreaChat,
            ArrayList<InputStream> streamsEntrada) {
        this.socketServidor = socketServidor;
        this.saidas = saidas;
        this.jTextAreaChat = jTextAreaChat;
        this.streamsEntrada = streamsEntrada;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Socket socketCliente = socketServidor.accept();
                PrintStream streamSaida = new PrintStream(socketCliente.getOutputStream());
                saidas.add(streamSaida);
                streamsEntrada.add(socketCliente.getInputStream());
                jTextAreaChat.setText(jTextAreaChat.getText() + "\nNova conexão com o cliente de IP "
                        + socketCliente.getInetAddress().getHostAddress() + "\n");

                ClienteRecebeMensagem recebimento = new ClienteRecebeMensagem(socketCliente, jTextAreaChat);
                recebimento.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}




