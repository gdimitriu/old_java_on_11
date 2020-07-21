/**
 * Examples from Programarea distribuita in internet metode si aplicatii
 * by Florian Mircea Boian Editura Albastra 1999
 * Applet are no longer supported by modern browser.
 */
package rdir;

import java.applet.Applet;
import java.awt.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class RemoteDirApp extends Applet {
    public static final int PORT = 8899;
    Socket s;
    DataInputStream in;
    DataOutputStream out;
    TextField director;
    String masina;
    TextArea rezumat;
    DeAsteptare de_asteptare;
    public void init() {
        try {
            masina = this.getParameter("Masina");
            s = new Socket(masina, PORT);
            in = new DataInputStream(s.getInputStream());
            out = new DataOutputStream(s.getOutputStream());
            director = new TextField();
            rezumat = new TextArea();
            rezumat.setEditable(false);
            this.setLayout(new BorderLayout());
            this.add("North", director);
            this.add("Center", rezumat);
            de_asteptare = new DeAsteptare(in, rezumat);
            this.showStatus("RemoteDirApp connected to " + s.getInetAddress().getHostName() + ":" + s.getPort());
        } catch (IOException e) {
            this.showStatus(e.toString());
        }
    }
    //when a line is enter the line is send to the server
    public boolean action(Event e, Object cev) {
        if(e.target == director) {
            String dir = (String)e.arg;
            int nr_oct = dir.length();
            byte[] b = new byte[nr_oct];
            dir.getBytes(0,dir.length(), b, 0);
            try {
                out.writeInt(dir.length());
                out.write(b);
            } catch (IOException e1) {
                this.showStatus(e1.toString());
            }
            director.setText("");
            return true;
        }
        return false;
    }
}
class DeAsteptare extends Thread {
    DataInputStream in;
    TextArea rezumat;
    public DeAsteptare(DataInputStream in, TextArea rezumat) {
        this.in = in;
        this.rezumat = rezumat;
        this.start();
    }
    public void run() {
        try {
            int nr_oct = in.readInt();
            byte[] b = new byte[nr_oct];
            in.read(b);
            rezumat.setText(new String(b,0));
        }
        catch (IOException e) {
            rezumat.setText(e.toString());
        }
    }
}