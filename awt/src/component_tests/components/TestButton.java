package component_tests.components;

import component_tests.TestComponent;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TestButton extends TestComponent {

    private Label messageDisplay;
    private Button b1;
    private Button b2;
    @Override
    public void addTestComponents() {
        messageDisplay = new Label("Push buttons to see here messages", Label.CENTER);
        //create buttons
        b1 = new Button();
        b1.setLabel("Button1");
        b2 = new Button("Button2");
        b2.setActionCommand("Fire Button2");
        //define the action listener
        ActionListener handlerButtons = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                messageDisplay.setText(e.getActionCommand());
            }
        };
        //associate the handler to the components
        b1.addActionListener(handlerButtons);
        b2.addActionListener(handlerButtons);
        //add the components to the layout
        setLayout(new GridLayout(3,1));
        //add the components to the frame
        add(b1);
        add(b2);
        add(messageDisplay);
    }
}
