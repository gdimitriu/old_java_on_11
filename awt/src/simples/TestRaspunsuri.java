package simples;

public class TestRaspunsuri extends  Raspunsuri {
    public TestRaspunsuri() {
        super("Raspunsuri posibile", "Rugam apasati unul din butoanele", "Da", null, "Anulare");
    }
    protected void yes() {
        System.out.println("pressed Yes");
        System.exit(0);
    }
    protected void no() {
        System.out.println("pressed No");
        System.exit(1);
    }

    protected void cancel() {
        System.out.println("pressed Cancel");
        System.exit(2);
    }
    public static void main(String...args) {
        new TestRaspunsuri();
    }
}
