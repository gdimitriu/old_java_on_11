/**
 * Examples from Programarea distribuita in internet metode si aplicatii
 * by Florian Mircea Boian Editura Albastra 1999
 */
package rmi;

import java.rmi.Naming;
import java.rmi.RMISecurityManager;

public class RlsClientRMI {
    public static void main(String...args) {
        System.setSecurityManager(new RMISecurityManager());
        try {
            RlsInterfRMI r = (RlsInterfRMI) Naming.lookup("RezumatRMI");
            String rezumat = r.rlsRMI(args[0]);
            System.out.println("RlsClientRMI\n" + rezumat);
        } catch (Exception e) {
            System.err.println("RlsClientRMI " + e);
        }
    }
}
