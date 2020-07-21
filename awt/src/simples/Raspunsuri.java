package simples;

import java.awt.*;

public class Raspunsuri extends Frame {
    public static final int NO = 0;
    public static final int YES = 1;
    public static final int CANCEL = -1;
    protected Button yes = null, no = null, cancel = null;
    protected Label eticheta;
    public Raspunsuri(String titlu, String intrebare, String dA, String nU, String aNulat) {
        //create the window
        super(titlu);
        //specify BorderLayout as manager
        this.setLayout(new BorderLayout(15,15));
        //write the question in middle of window
        eticheta = new Label(intrebare);
        this.add("Center", eticheta);
        //create a button panel and place it into the lower part of the window
        Panel p = new Panel();
        //specify FlowLayout as panel manager with right alignment
        p.setLayout(new FlowLayout(FlowLayout.RIGHT, 15, 15));
        //put the buttons on panel
        if (dA != null) p.add(yes = new Button(dA));
        if (nU != null) p.add(no = new Button(nU));
        if (aNulat != null) p.add(cancel = new Button(aNulat));

        //put the panel into the sud part of the frame
        this.add("South", p);
        //assemble
        this.pack();
        this.show();
    }
    //intercept the events from button and send result to the raspuns method using integer
    public boolean action(Event e, Object arg) {
        if (e.target instanceof Button) {
            this.hide();
            this.dispose();
            if (e.target == yes) raspuns(YES);
            else if (e.target == no) raspuns(NO);
            else raspuns(CANCEL);
            return true;
        }
        else return false;
    }

    protected void raspuns(int r) {
        switch (r) {
            case YES: yes(); break;
            case NO: no(); break;
            case CANCEL: cancel(); break;
        }
    }

    protected void yes() {
        System.out.println("pressed Yes");
    }
    protected void no() {
        System.out.println("pressed No");
    }
    protected void cancel() {
        System.out.println("pressed Cancel");
    }
}
