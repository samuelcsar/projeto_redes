import java.io.*;
import java.net.*;

public class ClienteTCP {
    public static void main(String[] args) {
        try {
            // Definir o endereço do servidor e a porta
            String host = "127.0.0.1";
            int porta = 12345;

            // Conectar ao servidor
            Socket socket = new Socket(host, porta);
            BufferedReader entradaTeclado = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader entradaServidor = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter saidaServidor = new PrintWriter(socket.getOutputStream(), true);

            String mensagem;
            while (true) {
                System.out.print("Digite uma mensagem para o servidor (ou 'sair' para encerrar): ");
                mensagem = entradaTeclado.readLine();  // Ler a mensagem do usuário
                saidaServidor.println(mensagem);  // Enviar mensagem para o servidor

                if (mensagem.equalsIgnoreCase("sair")) {
                    System.out.println("Conexão encerrada.");
                    break;
                }

                String respostaServidor = entradaServidor.readLine();  // Ler a resposta do servidor
                System.out.println("Resposta do servidor: " + respostaServidor);
            }

            // Fechar a conexão
            entradaTeclado.close();
            entradaServidor.close();
            saidaServidor.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
