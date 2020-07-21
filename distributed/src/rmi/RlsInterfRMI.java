/**
 * Examples from Programarea distribuita in internet metode si aplicatii
 * by Florian Mircea Boian Editura Albastra 1999
 */
package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RlsInterfRMI extends Remote {
    public String rlsRMI(String s) throws RemoteException;
}
