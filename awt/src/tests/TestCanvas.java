package tests;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TestCanvas {
    //window dimensions
    private static int dimX = 500;
    private static int dimY = 400;
    public static void main(String...args) {
        Frame frame = new Frame("Windows to test AWT components");
        class FrameClose extends WindowAdapter {

            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        }
        frame.addWindowListener(new FrameClose());
        TestPanel paneltest = new TestPanel();
        paneltest.addTestComponents();
        frame.add(paneltest, BorderLayout.CENTER);
        frame.setSize(dimX, dimY);
        frame.setVisible(true);
    }
}
class TestPanel extends Panel {
    //we want a table of nrX/nrY button x-0
    private int nrX = 10;
    private int nrY = 4;
    XOButton tableButtons[][] = new XOButton[nrX][nrY];

    public void addTestComponents() {
        setLayout(new GridLayout(nrX, nrY, 3,3));
        for(int i =0 ;i < nrX; i++) {
            for(int j = 0; j < nrY; j++) {
                tableButtons[i][j] = new XOButton();
                tableButtons[i][j].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        ((XOButton)e.getSource()).commute();
                    }
                });
                add(tableButtons[i][j]);
            }
        }
    }
}

class XOButton extends Canvas {
    //border width
    private int bw = 3;
    private Color borderColor = Color.BLUE;
    private Color fillColor = Color.RED;
    private Color drawColor = Color.BLACK;
    boolean onOrOff = true;

    @Override
    public void paint(Graphics g) {
        int width = getSize().width;
        int height = getSize().height;

        //draw the border in conformity with the state on/off
        g.setColor(borderColor);
        g.fill3DRect(bw, bw, width-2*bw, height-2*bw, onOrOff);
        //color the background
        g.setColor(fillColor);
        g.fillRect(bw, bw, width-2*bw, height-2*bw);
        //draw what I need
        g.setColor(drawColor);
        if(onOrOff) {
            g.drawLine(bw,bw,width-bw, height-bw);
            g.drawLine(bw, height-bw, width-bw, bw);
        } else {
            g.fillOval(2*bw, 2*bw, width-4*bw, height-4*bw);
        }
    }

    public void commute() {
        onOrOff = !onOrOff;
        repaint();
    }
}