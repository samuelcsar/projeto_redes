import java.io.*;
import java.net.*;

public class ServidorTCP {
    public static void main(String[] args) {
        try {
            // Definir o endereço e a porta para o servidor
            int porta = 12345;
            ServerSocket servidorSocket = new ServerSocket(porta);
            System.out.println("Servidor iniciado. Aguardando conexão...");

            // Aceitar conexão do cliente
            Socket clienteSocket = servidorSocket.accept();
            System.out.println("Cliente conectado: " + clienteSocket.getInetAddress());

            // Criação de streams para comunicação
            BufferedReader entradaCliente = new BufferedReader(new InputStreamReader(clienteSocket.getInputStream()));
            PrintWriter saidaCliente = new PrintWriter(clienteSocket.getOutputStream(), true);

            String mensagemCliente;
            while (true) {
                mensagemCliente = entradaCliente.readLine();  // Ler a mensagem do cliente
                if (mensagemCliente.equalsIgnoreCase("sair")) {
                    System.out.println("Conexão encerrada pelo cliente.");
                    break;
                }
                System.out.println("Mensagem recebida: " + mensagemCliente);
                saidaCliente.println("Mensagem recebida: " + mensagemCliente);  // Responder ao cliente
            }

            // Fechar a conexão
            entradaCliente.close();
            saidaCliente.close();
            clienteSocket.close();
            servidorSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
