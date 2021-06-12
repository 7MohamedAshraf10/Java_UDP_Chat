
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server_UDP_Chat_20190421 {

    public static void main(String[] args) {
        try {
            DatagramSocket serversocket = new DatagramSocket(4000);
            System.out.println("Server is up");

            byte[] requestBytes = new byte[4896];
            byte[] responseBytes;
            Scanner sc = new Scanner(System.in);

            while (true) {
                DatagramPacket clientPacket = new DatagramPacket(requestBytes, requestBytes.length);
                serversocket.receive(clientPacket);

                InetAddress clientIP = clientPacket.getAddress();
                int clientport = clientPacket.getPort();

                String req = new String(clientPacket.getData()).trim();
                System.out.println("Client " + clientPacket.getSocketAddress() + " : " + req);

                if ("weather".equalsIgnoreCase(req)) {
                    String input = "Today's weather : 16 Cْ ";
                    responseBytes = input.getBytes();
                    DatagramPacket myServerPacket = new DatagramPacket(responseBytes, responseBytes.length, clientIP, clientport);
                    serversocket.send(myServerPacket);
                } else if ("Time".equalsIgnoreCase(req)) {
                    String time = new Date().toString();
                    responseBytes = time.getBytes();
                    DatagramPacket myServerPacket = new DatagramPacket(responseBytes, responseBytes.length, clientIP, clientport);
                    serversocket.send(myServerPacket);
                } else if ("pc IP address".equalsIgnoreCase(req)) {
                    String IP = "The IP address is : " + clientIP;
                    responseBytes = IP.getBytes();
                    DatagramPacket myServerPacket = new DatagramPacket(responseBytes, responseBytes.length, clientIP, clientport);
                    serversocket.send(myServerPacket);
                } else {
                    String input = "Please select form the options ";
                    responseBytes = input.getBytes();
                    DatagramPacket myServerPacket = new DatagramPacket(responseBytes, responseBytes.length, clientIP, clientport);
                    serversocket.send(myServerPacket);
                    
                }

                requestBytes = new byte[4896];

            }

        } catch (IOException ex) {
            Logger.getLogger(Server_UDP_Chat_20190421.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
