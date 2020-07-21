/**
 * Examples from Programarea distribuita in internet metode si aplicatii
 * by Florian Mircea Boian Editura Albastra 1999
 */
package rdir;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class RemoteDirC {
    public static final int PORT = 12345;
    public static final int DIRSIZE = 8192;
    public static void main(String...args) {
        Socket s = null;
        String rezumat;
        int i;
        try {
            s = new Socket(args[0], PORT);
            DataInputStream in = new DataInputStream(s.getInputStream());
            DataOutputStream out = new DataOutputStream(s.getOutputStream());
            System.out.println("Connect to " + s.getInetAddress() + ":" + s.getPort());
            byte[] b = new byte[args[1].length()];
            args[1].getBytes(0, args[1].length(), b, 0);
            out.write(b);
            b = new byte[DIRSIZE];
            i = in.read(b);
            rezumat = new String(b,0,0,i);
            System.out.println(rezumat);
        } catch (IOException e) {
            System.err.println(e);
        } finally {
            try {
                if (s != null) s.close();
            } catch (IOException e1) {

            }
        }
    }
}
