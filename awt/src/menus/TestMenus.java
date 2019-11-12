package menus;

import java.awt.*;
import java.awt.event.*;

public class TestMenus extends WindowAdapter {
    public static void main(String...args) {
        OurFrame frame = new OurFrame();
        frame.addWindowListener(new TestMenus());
    }

    @Override
    public void windowClosing(WindowEvent e) {
        System.exit(0);
    }
}

class OurFrame extends Frame {
        private int width = 400;
        private int height = 300;
        private Frame frame;
        private MenuBar menuBar = new MenuBar();
        private Menu m1 = new Menu("Menu1");
        private Menu m2 = new Menu("Menu2");
        private PopupMenu popup;

        class MenuHandler implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
                new MessageWindow(frame, e.getActionCommand());
            }
        }

        class ItemHandler implements ItemListener {

            @Override
            public void itemStateChanged(ItemEvent e) {
                CheckboxMenuItem cbi = (CheckboxMenuItem) e.getItemSelectable();
                StringBuffer buff = new StringBuffer(cbi.getName());
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    buff.append(" Selected");
                } else {
                    buff.append(" Deselected");
                }
                new MessageWindow(frame, buff.toString());
            }
        }

        class MouseHandler extends MouseAdapter {
            @Override
            public void mouseClicked(MouseEvent e) {
                popup.show(frame, e.getX(), e.getY());
            }
        }

        public OurFrame() {
            super("Test Menus Frame");
            frame = this;
            new MessageWindow(frame, "Start program");
            //create handlers
            MenuHandler mh = new MenuHandler();
            ItemHandler ih = new ItemHandler();
            addMouseListener(new MouseHandler());
            //create menus
            //prepare menu1
            MenuItem mi11 = new MenuItem("MenuItem11");
            MenuItem mi12 = new MenuItem("MenuItem12");
            MenuItem mi13 = new MenuItem("MenuItem13");
            mi11.setShortcut(new MenuShortcut(KeyEvent.VK_A));
            mi12.setShortcut(new MenuShortcut(KeyEvent.VK_B));
            mi12.setActionCommand("Menu12 selected");
            mi13.setEnabled(false);
            mi11.addActionListener(mh);
            mi12.addActionListener(mh);
            mi13.addActionListener(mh);
            m1.add(mi11);
            m1.add(mi12);
            m1.addSeparator();
            m1.add(mi13);
            //prepare menu2
            CheckboxMenuItem mi21 = new CheckboxMenuItem("CheckboxMenuItem21");
            CheckboxMenuItem mi22 = new CheckboxMenuItem("CheckboxMenuItem22", true);
            Menu mi23 = new Menu("More...");
            mi23.add("More 1");
            mi23.add("More 2");
            mi21.addItemListener(ih);
            mi22.addItemListener(ih);
            mi23.addActionListener(mh);
            m2.add(mi21);
            m2.add(mi22);
            m2.add(mi23);

            menuBar.add(m1);
            menuBar.add(m2);
            setMenuBar(menuBar);

            //prepare popup
            popup = new PopupMenu();
            popup.add("Option 1");
            popup.add("Option 2");
            popup.addSeparator();
            popup.add("Option 3");
            popup.addActionListener(mh);
            add(popup);

            setSize(width, height);
            setVisible(true);
        }
}

class MessageWindow extends Window implements Runnable {

    private String message = "No message ...";
    private Thread thisThread;
    private int width = 200;
    private int height = 200;
    private int border = 5;

    public MessageWindow(Frame frame) {
        super(frame);
        setSize(width, height);
        setOnMiddleOfScreen();
        setVisible(true);
        thisThread = new Thread(this);
        thisThread.start();
    }
    public MessageWindow(Frame frame, String message) {
        this(frame);
         if(message != null) {
             this.message = message;
         }
    }
    private void setOnMiddleOfScreen() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screenSize.width-width)/2, (screenSize.height = height)/2);
    }
    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {}
        dispose();
    }
    @Override
    public void paint(Graphics g) {
        g.setColor(Color.BLUE);
        g.fill3DRect(0,0,width, height, true);
        g.setColor(Color.RED);
        g.fill3DRect(border, border, width-2*border, height-2*border, false);
        g.setColor(Color.BLACK);
        g.drawString(message, 20, height/2);
    }
}
