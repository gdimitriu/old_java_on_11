/**
 * Examples from Programarea distribuita in internet metode si aplicatii
 * by Florian Mircea Boian Editura Albastra 1999
 */
package rdir;

import java.io.*;
import java.net.ConnectException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerRemoteDir extends Thread {
    public final static int PORT = 8899;
    protected ServerSocket socket_intilnire;
    /* out in case of error */
    public static void fail(Exception e, String mesaj) {
        System.err.println("ServerRemoteDir " + mesaj + ":" + e);
        System.exit(1);
    }
    //create the socket
    public ServerRemoteDir() {
        try {
            socket_intilnire = new ServerSocket(PORT);
        } catch (IOException e) {
            fail(e, "ERR Creare");
        }
        System.out.println("ServerRemoteDir: wait at " + PORT);
        this.start();
    }
    public void run() {
        Socket socket_client;
        try {
            for(;;) {
                socket_client = socket_intilnire.accept();
                Conectare c = new Conectare(socket_client);
            }
        } catch (IOException e) {
            fail(e, "accept");
        }
    }
    //init server
    public static void main(String...args) {
        new ServerRemoteDir();
    }
}

class Conectare extends Thread {
    protected DataInputStream in;
    protected DataOutputStream out;
    Socket client;
    public Conectare(Socket socket_client) {
        client = socket_client;
        try {
            in = new DataInputStream(client.getInputStream());
            out = new DataOutputStream(client.getOutputStream());
        } catch (IOException e) {
            try {
                client.close();
            } catch (IOException e1) {

            }
            System.err.println("ServerRemoteDir citire socket " + e);
            return;
        }
        this.start();
    }
    public void run() {
        try {
            int nr_oct = in.readInt();
            byte[] b = new byte[nr_oct];
            in.read(b);
            String director = new String(b, 0);
            File f = new File(director);
            if (f.isDirectory()) {
                String[] rezumat = f.list();
                String s = new String();
                for (int i = 0; i< rezumat.length; i++)
                    s+= rezumat[i] + "\n";
                //convert to bytes
                b = new byte[s.length()];
                s.getBytes(0, s.length(), b, 0);
                //send length and contain
                out.writeInt(s.length());
                out.write(b);
            }
        } catch (IOException e) {

        }
        finally {
            try {
                client.close();
            } catch (IOException e1) {

            }
        }
    }
}