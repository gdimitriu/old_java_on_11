package component_tests.components;

import component_tests.TestComponent;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class TestChoice extends TestComponent {
    private Label messgeDisplay;
    private Button b;
    private String add="Add Items";
    private String remove = "Remove Items";
    private Choice choice;
    private String list[] = {"Item1","Item2","Item3","Item4"};

    private void addItems(String list[]) {
        for(String str : list) {
            choice.add(str);
        }
    }

    private void removeItems(String list[]) {
        for(String str : list) {
            choice.remove(str);
        }
    }

    @Override
    public void addTestComponents() {
        b = new Button(add);
        //create the action listener
        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cmd = e.getActionCommand();
                if(add.equals(cmd)) {
                    addItems(list);
                    choice.select(2);
                    b.setLabel(remove);
                } else if (remove.equals(cmd)) {
                    removeItems(list);
                    b.setLabel(add);
                }
            }
        };
        b.addActionListener(actionListener);

        choice = new Choice();
        //create the item listener
        ItemListener itemListener = new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                messgeDisplay.setText("Select " + choice.getSelectedItem() + " at index " + choice.getSelectedIndex());
            }
        };
        choice.addItemListener(itemListener);

        messgeDisplay = new Label("Message Display", Label.CENTER);

        setLayout(new GridLayout(3,1));
        add(b);
        add(choice);
        add(messgeDisplay);
    }
}
