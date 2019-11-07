package component_tests.components;

import component_tests.TestComponent;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;

public class TestTextField extends TestComponent {

    /**Notice:
     * There is no clear of the TextField you could only put a space which clear it.
     */
    private Label l1;
    private Label l2;
    private TextField tf1;
    private TextField tf2;
    private TextField display1;
    private TextField display2;
    private int count = 0;

    @Override
    public void addTestComponents() {
        l1 = new Label("Login");
        l2 = new Label("Password");
        tf1 = new TextField("Write your login name");
        tf2 = new TextField("Password",30);
        tf2.setEchoChar('*');

        //only for display data
        display1 = new TextField();
        display2 = new TextField();
        display1.setEditable(false);
        display2.setEditable(false);

        //handle all text changes
        TextListener textListener = new TextListener() {
            @Override
            public void textValueChanged(TextEvent e) {
                display1.setText("TextEvent: " + ++count);
            }
        };
        //handle text event on press Enter
        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                String oldString = display2.getText();
                display2.setText(oldString + "...ActionEvent:" + event.getActionCommand());
            }
        };
        tf1.addActionListener(actionListener);
        tf1.addTextListener(textListener);
        tf2.addActionListener(actionListener);
        tf2.addTextListener(textListener);

        setLayout(new GridLayout(3,2));
        add(l1);
        add(l2);
        add(tf1);
        add(tf2);
        add(display1);
        add(display2);
    }
}
