/**
 * Examples from Programarea distribuita in internet metode si aplicatii
 * by Florian Mircea Boian Editura Albastra 1999
 */
package thread;
class Aduna extends Thread {
    int s,d;
    public Aduna(int s, int d) {
        super("a[" + s+"]+[a" + d + "] ->a[" + s+"]"); //give name of the thread
        this.s = s;
        this.d = d;
        this.start();
    }
    public void run() {
        Paralel.a[s] += Paralel.a[d];
        System.out.println(getName());
    }
}
public class Paralel {

    static final int DOILN = 32;
    static final int N = 5;
    static int[] a = new int[DOILN];
    public Paralel() {
        Aduna thr[] = new Aduna[DOILN];
        int k  = -1;
        for(int i =1; i<=DOILN; i++) a[i-1] = i;
        for(int i = 0, p =1; i < N; i++, p+=p ) {
            for(int j = 0; j < DOILN; j += 2*p) {
                k++;
                thr[k] = new Aduna(j, j+p);
            }
            for(int j = 0; j < DOILN; j++)
                if(thr[k].isAlive())
                    try {
                        thr[k].join();
                    } catch (InterruptedException e) {
                        System.err.println(e);
                    }
        }
        System.out.println("Total: "  + a[0]);
    }
    public static void main(String...args) {
        new Paralel();
    }
}
