/**
 * Examples from Programarea distribuita in internet metode si aplicatii
 * by Florian Mircea Boian Editura Albastra 1999
 */
package rdir;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientRemoteDir {
    public static final int PORT = 8899;
    public static void main(String...args) {
        Socket s = null;
        String rezumat;
        try {
            s = new Socket(args[0], PORT);
            DataInputStream in = new DataInputStream(s.getInputStream());
            DataOutputStream out = new DataOutputStream(s.getOutputStream());
            System.out.println("Connect to " + s.getInetAddress() + ":" + s.getPort());
            //prepare the name of the directory as bytes and send it
            byte[] b = new byte[args[1].length()];
            args[1].getBytes(0, args[1].length(), b, 0);
            out.writeInt(args[1].length());
            out.write(b);
            //read from server first the length and then the content
            int nr_oct = in.readInt();
            b = new byte[nr_oct];
            in.read(b);
            rezumat = new String(b,0);
            System.out.println(rezumat);
        } catch(IOException e) {
            System.err.println(e);
        }
        finally {
            try {
                if (s != null)
                    s.close();
            } catch (IOException e1) {

            }
        }
    }
}
