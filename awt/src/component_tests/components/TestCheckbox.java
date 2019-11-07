package component_tests.components;

import component_tests.TestComponent;

import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class TestCheckbox extends TestComponent {
    /* could be also fields if they are use in logic
    private Label disp1;
    private Label disp2;
    private Checkbox c11;
    private Checkbox c12;
    private Checkbox c13;
    private Checkbox c21;
    private Checkbox c22;
    private CheckboxGroup cg;
*/
    private class OurListener implements ItemListener {
        private Label display;
        public OurListener(Label disp) {
            display = disp;
        }
        @Override
        public void itemStateChanged(ItemEvent event) {
            Checkbox cb = (Checkbox) event.getItemSelectable();
            display.setText(cb.getLabel() + " is  " + cb.getState());
        }
    }
    @Override
    public void addTestComponents() {
        Label disp1;
        Label disp2;
        Checkbox c11;
        Checkbox c12;
        Checkbox c13;
        Checkbox c21;
        Checkbox c22;
        CheckboxGroup cg;
        disp1 = new Label("Age", Label.CENTER);
        disp2 = new Label("Others", Label.CENTER);

        cg = new CheckboxGroup();
        c11 = new Checkbox("Below 10", cg, false);
        c12 = new Checkbox("Between 10-20", cg, false);
        c13 = new Checkbox("Above 20", cg, false);

        c21 = new Checkbox("Married");
        c22 = new Checkbox("With Children");

        OurListener l1 = new OurListener(disp1);
        OurListener l2 = new OurListener(disp2);
        c11.addItemListener(l1);
        c12.addItemListener(l1);
        c13.addItemListener(l1);
        c21.addItemListener(l2);
        c22.addItemListener(l2);
        setLayout(new GridLayout(4,2));
        add(disp1);
        add(disp2);
        add(c11);
        add(c12);
        add(c13);
        add(c21);
        add(c22);
    }
}
