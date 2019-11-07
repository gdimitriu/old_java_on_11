package component_tests.components;

import component_tests.TestComponent;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class TestList extends TestComponent {
    private Label messageDisplay;
    private List l1;
    private List l2;
    private String articles[]={"Computer","Display", "Printer"};
    private String prices[] = {"800$", "500$", "100$"};
    @Override
    public void addTestComponents() {
        l1 = new List();
        for(String article :articles) {
            l1.add(article);
        }
        l2 = new List();
        for (String price : prices) {
            l2.add(price);
        }
        //create the item listener and add to the list (click)
        ItemListener itemListener = new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                l2.select(l1.getSelectedIndex());
            }
        };
        l1.addItemListener(itemListener);
        //create the action listener double click
        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                messageDisplay.setText("Double Click on " + e.getActionCommand());
            }
        };
        l1.addActionListener(actionListener);
        messageDisplay = new Label("Message Display", Label.CENTER);
        setLayout(new GridLayout(0,2));
        add(l1);
        add(l2);
        add(messageDisplay);
    }
}
