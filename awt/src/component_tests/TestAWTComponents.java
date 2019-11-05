package component_tests;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Scanner;

public class TestAWTComponents {

    private static int dimX=500;
    private static int dimY=400;

    public static void main(String...args) {
        Frame frame = new Frame("Window for AWT testing");

        ComponentFactory.printComponents();
        System.out.println("Choose a component:");
        Scanner scanner = new Scanner(System.in);
        String type = scanner.nextLine();
        //close the the windows
        class FrameClose extends WindowAdapter {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        }

        frame.addWindowListener(new FrameClose());

        TestComponent panelTest = ComponentFactory.createComponent(type);

        panelTest.addTestComponents();
        frame.add(panelTest, BorderLayout.CENTER);
        frame.setSize(dimX, dimY);
        frame.setVisible(true);
    }
}
