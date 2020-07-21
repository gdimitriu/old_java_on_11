/**
 * Examples from Programarea distribuita in internet metode si aplicatii
 * by Florian Mircea Boian Editura Albastra 1999
 */
package rmi;

import java.io.File;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RlsImplemRMI extends UnicastRemoteObject implements RlsInterfRMI {

    protected RlsImplemRMI() throws RemoteException {
        super();
    }

    @Override
    public String rlsRMI(String s) throws RemoteException {
        String result = new String();
        File f = new File(s);
        if (f.isDirectory()) {
            String rezumat[] = f.list();
            for(int i = 0; i < rezumat.length; i++)
                result += rezumat + "\n";
        } else {
            result = s + " nu este director!\n";
        }
        return result;
    }
    public static void main(String...args) {
        try {
            //create an implemention object
            RlsInterfRMI i = new RlsImplemRMI();
            //fix the exterior publish name
            Naming.rebind("RezumatRMI", i);
            System.err.println("Serverul RlsImplemRMI incarcat");
        } catch (RemoteException re) {
            System.err.println("RlsImplemRMI " + re);
        } catch (MalformedURLException me) {
            System.err.println("RlsImplemRMI " + me);
        }
    }
}
