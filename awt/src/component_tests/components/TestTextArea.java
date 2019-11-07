package component_tests.components;

import component_tests.TestComponent;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;

public class TestTextArea extends TestComponent {

    private TextArea ta;
    private TextField tfNew;
    private TextField display;
    int count = 0;

    @Override
    public void addTestComponents() {
        ta = new TextArea("", 10, 15, TextArea.SCROLLBARS_VERTICAL_ONLY);
        Panel p = new Panel();
        tfNew = new TextField(10);
        Button appendButton = new Button("Append text");
        Button insertButton = new Button("Insert text");
        display = new TextField("Messages...");
        p.setLayout(new GridLayout(4,0));
        p.add(tfNew);
        p.add(appendButton);
        p.add(insertButton);
        p.add(display);
        ActionListener actionListenera = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ta.append(tfNew.getText());
            }
        };
        appendButton.addActionListener(actionListenera);
        ActionListener actionListeneri = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ta.insert(tfNew.getText(), 0);
            }
        };
        insertButton.addActionListener(actionListeneri);
        TextListener textListener = new TextListener() {
            @Override
            public void textValueChanged(TextEvent e) {
                display.setText("TextEvent " + ++count);
            }
        };
        ta.addTextListener(textListener);
        setLayout(new BorderLayout());
        add(ta,BorderLayout.CENTER);
        add(p,BorderLayout.WEST);
    }
}
