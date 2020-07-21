/**
 * Examples from Programarea distribuita in internet metode si aplicatii
 * by Florian Mircea Boian Editura Albastra 1999
 */
package udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class DatagramRecv {
    static final int PORT = 9898;
    public static void main(String...args) throws Exception {
        byte[] b = new byte[1024];
        String mesaj;
        //create a packet empty where to get the data
        DatagramPacket pachet = new DatagramPacket(b,b.length);
        //create a socket
        DatagramSocket s = new DatagramSocket(PORT);
        for(;;) {
            //wait to receive a packet
            s.receive(pachet);
            //convert to string
            mesaj = new String(b,0,0,pachet.getLength());
            //print the message
            System.out.println("Received from: " + pachet.getAddress().getHostName() + ":" + pachet.getPort() + ":\n" + mesaj);
        }
    }
}
