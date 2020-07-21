/**
 * Examples from Programarea distribuita in internet metode si aplicatii
 * by Florian Mircea Boian Editura Albastra 1999
 */

package udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class DatagramSend {
    static final int PORT = 9898;
    public static void main(String...args) throws Exception {
        if (args.length != 2) {
            System.out.println("Usage: java machine message");
            System.exit(1);
        }
        //get the internet address of the machine
        InetAddress adresa = InetAddress.getByName(args[0]);

        //convert the message into the byte array
        int lungime = args[1].length();
        byte[] mesaj = new byte[lungime];
        args[1].getBytes(0, lungime, mesaj, 0);
        //init the package with data and address
        DatagramPacket pachet = new DatagramPacket(mesaj, lungime, adresa, PORT);
        //create the socket and send the package
        DatagramSocket s = new DatagramSocket();
        s.send(pachet);
    }
}
