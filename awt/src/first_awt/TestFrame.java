package first_awt;

import java.awt.*;
import java.awt.event.*;

public class TestFrame extends Frame implements ComponentListener, FocusListener, MouseListener, KeyListener {

    private int dimX = 500, dimY =400;
    MyButton b1, b2;
    Button b3,b4,b5;
    TextArea ta;

    //dimensions
    Rectangle r1 = new Rectangle(40,40,100,40);
    Rectangle r2 = new Rectangle(40, 100, 100, 40);
    Rectangle r3 = new Rectangle(40, 160, 100, 40);
    Rectangle r4 = new Rectangle(40,220, 100, 40);
    Rectangle r5 = new Rectangle(40, 280, 100, 40);
    Rectangle r6 = new Rectangle(180, 40, 280, 320);
    Dimension d1 = new Dimension(100, 40);
    Dimension d2 = new Dimension(50, 20);

    public TestFrame(String name) {
        super(name);
        class FrameClose extends WindowAdapter {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        }
        addWindowListener(new FrameClose());
        setSize(dimX, dimY);
        setResizable(false);
        setBackground(Color.BLACK);
        setLayout(null);

        add(b1 = new MyButton("One"));
        add(b2 = new MyButton("Two"));
        add(b3 = new Button("Hide button"));
        add(b4 = new Button("Switch positions"));
        add(b5  = new Button("Change size"));
        add(ta = new TextArea(20,20));

        b1.setBackground(Color.BLUE);
        b1.setBounds(r1);
        b2.setBackground(Color.RED);
        b2.setBounds(r2);
        b3.setBackground(Color.GRAY);
        b3.setBounds(r3);
        b4.setBackground(Color.GRAY);
        b4.setBounds(r4);
        b5.setBackground(Color.GRAY);
        b5.setBounds(r5);
        ta.setBackground(Color.WHITE);
        ta.setBounds(r6);
        ta.setEditable(false);

        setVisible(true);

        b1.addComponentListener(this);
        b1.addFocusListener(this);
        b1.addMouseListener(this);
        b1.addKeyListener(this);
        b2.addComponentListener(this);
        b2.addFocusListener(this);
        b2.addMouseListener(this);
        b2.addKeyListener(this);

        b3.addActionListener(new HideListener());
        b4.addActionListener(new SwitchListener());
        b5.addActionListener(new ResizeListener());
    }

    private void out (String line) {
        ta.append("\r\n" + line);
    }

    @Override
    public void componentResized(ComponentEvent e) {
        out("Component " + e.getComponent().getName() + " resized");
    }

    @Override
    public void componentMoved(ComponentEvent e) {
        out("Component" + e.getComponent().getName() + " moved");
    }

    @Override
    public void componentShown(ComponentEvent e) {
        out("Component " + e.getComponent().getName() + " shown");
    }

    @Override
    public void componentHidden(ComponentEvent e) {
        out("Component " + e.getComponent().getName() + " hidden");
    }

    @Override
    public void focusGained(FocusEvent e) {
        out("Component " + e.getComponent().getName() + " gained focus");
    }

    @Override
    public void focusLost(FocusEvent e) {
        out("Component " + e.getComponent().getName() + " lost focus");
    }

    @Override
    public void keyTyped(KeyEvent e) {
        out("KeyTyped = \t" + e.getKeyChar());
    }

    @Override
    public void keyPressed(KeyEvent e) {
        out("KeyTyped = \t" + e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        out("Is action key = \t" + e.isActionKey());
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        out("Mouse clicked in " + e.getComponent().getName());
    }

    @Override
    public void mousePressed(MouseEvent e) {
        out("Mouse presed in " + e.getComponent().getName());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        out("Mouse released in " + e.getComponent().getName());
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    /**
     * handler to hide button b1
     */
    class HideListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(b1.isVisible()) {
                b3.setLabel("Show One");
            } else {
                b3.setLabel("Hide One");
            }
            b1.setVisible(!b1.isVisible());
            repaint();
        }
    }

    /**
     * handler for the switch button
     */
    class SwitchListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            //switch the position of the two buttons
            b1.setBounds(r2);
            b2.setBounds(r1);
            Rectangle temp = r2;
            r2=r1;
            r1= temp;
            repaint();
        }
    }

    class ResizeListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Dimension d = b1.getSize();
            if (d1.equals(d)) {
                b1.setSize(d2);
            } else {
                b1.setSize(d1);
            }
        }
    }
}
