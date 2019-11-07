package tests;

import java.awt.*;
import java.awt.event.*;

public class TestWindows {

    public static void main(String...args) {
        OurFrame frame = new OurFrame();
    }
}

class OurFrame extends Frame {
    private String stringButtons[] = {"Open Window", "Open Dialog", "Open Modal Dialog", "Open File Dialog"};
    private Button buttons[];
    private Frame frame;
    public OurFrame() {
        super("Main Window");
        frame = this;
        class OurFrameHandler implements WindowListener {
            @Override
            public void windowOpened(WindowEvent e) {
                System.out.println("Frame opened");
            }

            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("Frame Closing");
                e.getWindow().dispose();
            }

            @Override
            public void windowClosed(WindowEvent e) {
                System.out.println("Frame closed");
                System.exit(0);
            }

            @Override
            public void windowIconified(WindowEvent e) {
                System.out.println("Frame iconified");
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
                System.out.println("Frame deiconified");
            }

            @Override
            public void windowActivated(WindowEvent e) {
                System.out.println("Frame activated");
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
                System.out.println("Frame deactivated");
            }
        }
        addWindowListener(new OurFrameHandler());
        ActionListener buttonHandler = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cmd = e.getActionCommand();
                int cmdInt= Integer.parseInt(cmd);
                switch(cmdInt) {
                    case 0:
                        new TestWindow(frame);
                        break;
                    case 1:
                        new TestSimpleDialog(frame);
                        break;
                    case 2:
                        new TestModalDialog(frame);
                        break;
                    case 3:
                        FileDialog fdlg = new FileDialog(frame, "Test File Dialog");
                        fdlg.setVisible(true);
                        break;
                }
            }
        };

        setLayout(new FlowLayout());
        buttons = new Button[stringButtons.length];
        for (int i = 0; i< stringButtons.length; i++) {
            buttons[i] = new Button(stringButtons[i]);
            buttons[i].setActionCommand("" + i);
            buttons[i].addActionListener(buttonHandler);
            add(buttons[i]);
        }
        pack();
        setVisible(true);
    }
}

class TestWindow extends Window implements Runnable {
    private String message = "This can be a message window ... ";
    private Thread thisThread;
    private int width = 300;
    private int height = 200;

    public TestWindow(Frame frame) {
        super(frame);
        setSize(width, height);
        setOnMidleOfScreen();
        setVisible(true);
        thisThread = new Thread(this);
        thisThread.start();
    }

    public TestWindow(Frame frame, String message) {
        this(frame);
        this.message=message;
    }
    private void setOnMidleOfScreen() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screenSize.width-width)/2, (screenSize.height-height)/2);
    }

    @Override
    public void run() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {}
        dispose();
    }

    @Override
    public void paint(Graphics g) {
        g.drawString(message, 20, height/2);
    }
}

class TestSimpleDialog extends Dialog {
    public TestSimpleDialog(Frame frame) {
        super(frame, "Test Simple Dialog");
        addWindowListener(new WindowCloser());
        List tools = new List();
        for (int i = 1; i < 5; i++)
            tools.add("Tool " + i);
        add(tools, BorderLayout.CENTER);
        pack();
        setVisible(true);
    }
}

class WindowCloser extends WindowAdapter {
    @Override
    public void windowClosing(WindowEvent e) {
        e.getWindow().dispose();
    }
}

class TestModalDialog extends  Dialog implements ActionListener {

    private Frame parent;
    public TestModalDialog(Frame frame) {
        super(frame, "Test Modal Dialog", true);
        parent = frame;
        Button bYes = new Button("Yes");
        Button bNo = new Button("No");
        bYes.addActionListener(this);
        bNo.addActionListener(this);
        addWindowListener(new WindowCloser());
        setLayout(new GridLayout(3,0));
        add(new Label("Is Java cool?", Label.CENTER));
        add(bYes);
        add(bNo);
        pack();
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        dispose();
        String cmd = e.getActionCommand();
        if("Yes".equals(cmd)) {
            new TestWindow(parent,"You are my friend ... ");
        } if ("No".equals(cmd)) {
            new TestWindow(parent, "You are a bad guy ... ");
        }
    }
}
