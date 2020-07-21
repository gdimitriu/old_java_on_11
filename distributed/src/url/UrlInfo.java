/**
 * Examples from Programarea distribuita in internet metode si aplicatii
 * by Florian Mircea Boian Editura Albastra 1999
 */
package url;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

public class UrlInfo {
    public static void printInfo(URLConnection u) throws IOException {
        System.out.println(u.getURL().toExternalForm() + ":");
        System.out.println("Content type: " + u.getContentType());
        System.out.println("Length content: " + u.getContentLength());
        System.out.println("Last modification: " + new Date(u.getLastModified()));
        System.out.println("Expiration:" + u.getExpiration());
        System.out.println("First 10 lines :");
        DataInputStream in = new DataInputStream(u.getInputStream());
        for (int i = 0; i < 10; i++) {
            String line = in.readLine();
            if (line == null) break;
            System.out.println(line);
        }
    }
    public static void main(String...args) throws MalformedURLException, IOException {
        URL url = new URL(args[0]);
        URLConnection connection = url.openConnection();
        printInfo(connection);
    }
}
