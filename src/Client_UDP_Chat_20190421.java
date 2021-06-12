
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client_UDP_Chat_20190421 {

    public static void main(String[] args) {
        try {
            DatagramSocket clientSocket = new DatagramSocket();
            System.out.println("Client active");

            InetAddress serverIP = InetAddress.getLocalHost();
            int serverPort = 4000;
            byte[] requestBytes;
            byte[] responseBytes = new byte[4896];
                

            Scanner sc = new Scanner(System.in);
            while (true) {
                System.out.println("1- Enter ' weather ' to know Today's weather \n 2- Enter ' Time ' to know the time  \n 3- Enter ' pc IP address ' to know the client IP \n 4- Enter 'close' or 'exit' to exit");
                String input = sc.nextLine();
                if (input.toLowerCase().equalsIgnoreCase("close")) {
                    System.out.println("Socket is closed");
                    clientSocket.close();
                    break;
                }
                else if (input.toLowerCase().equalsIgnoreCase("exit")) {
                    System.out.println("Socket is closed");
                    clientSocket.close();
                    break;
                }
                
                requestBytes = input.getBytes();
                DatagramPacket myClientPacket = new DatagramPacket(requestBytes, requestBytes.length, serverIP, serverPort);

                clientSocket.send(myClientPacket);

                DatagramPacket ServerPacket = new DatagramPacket(responseBytes, responseBytes.length);
                // recieve client's request
                clientSocket.receive(ServerPacket);

                String res = new String(ServerPacket.getData()).trim();
                System.out.println("Server: " + res);
                responseBytes = new byte[4896];

            }

        } catch (IOException ex) {
            Logger.getLogger(Client_UDP_Chat_20190421.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
