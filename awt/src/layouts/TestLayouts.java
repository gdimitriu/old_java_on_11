package layouts;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TestLayouts {
    public static void main(String...args) {
        Frame frame = new Frame("Window to test AWT components");
        class FrameClose extends WindowAdapter {

            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        }
        frame.addWindowListener(new FrameClose());
        TestPanel panelTest = new TestPanel();
        frame.add(panelTest, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
    }
}

class TestPanel extends Panel {
    private String cardIds[] = {"Card1", "Card2", "Card3", "Card4"};
    private Panel panelSouth = new Panel();
    private Panel panelWest = new Panel();
    private Panel panelCenter = new Panel();
    private Label panelNorth = new Label("This is a combination of layout managers");

    public TestPanel() {
        super();
        panelSouth.setLayout(new FlowLayout(6,6,FlowLayout.LEFT));
        for (int i = 0; i < cardIds.length; i++) {
            Button b = new Button(cardIds[i]);
            b.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    CardLayout c = (CardLayout) panelCenter.getLayout();
                    c.show(panelCenter, e.getActionCommand());
                }
            });
            panelSouth.add(b);
        }
        panelWest.setLayout(new GridLayout(5,0));
        Button next = new Button("Next");
        Button prev = new Button("Previous");
        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((CardLayout) panelCenter.getLayout()).next(panelCenter);
            }
        });
        prev.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((CardLayout) panelCenter.getLayout()).previous(panelCenter);
            }
        });
        panelWest.add(next);
        panelWest.add(prev);
        panelCenter.setLayout(new CardLayout());
        for(int i =0; i < cardIds.length; i++) {
            Button b = new Button("This is the panel for " + cardIds[i]);
            panelCenter.add(b,cardIds[i]);
        }
        setLayout(new BorderLayout());
        add(panelSouth, BorderLayout.SOUTH);
        add(panelCenter, BorderLayout.CENTER);
        add(panelNorth, BorderLayout.NORTH);
        add(panelWest, BorderLayout.WEST);
    }
}
