import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket serverSocket; //listen to incoming connections

    public Server(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }
     public void startServer(){     // keeps server running
        try {
            while (!serverSocket.isClosed()){
               Socket socket = serverSocket.accept();
                System.out.println("A new client has connected");
                //each obj of this class will be responsible for comm with client & implements interface runnable
                ClientHandler clientHandler = new ClientHandler(socket);

                Thread thread = new Thread(clientHandler);
                thread.start();
            }
        }catch (IOException e){

        }
     }
     public void closeServerSocket(){       // in case of error CSS
        try {
            if (serverSocket != null){
                serverSocket.close();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
     }

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(1234);
        Server server = new Server(serverSocket);
        server.startServer();
    }
}
