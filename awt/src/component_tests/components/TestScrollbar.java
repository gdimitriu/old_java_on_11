package component_tests.components;

import component_tests.TestComponent;

import java.awt.*;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

public class TestScrollbar extends TestComponent {
    private Label display;
    private Scrollbar scrollbar;

    @Override
    public void addTestComponents() {
        scrollbar = new Scrollbar(Scrollbar.HORIZONTAL, 0, 25, 0 , 100);
        AdjustmentListener adjustmentListener = new AdjustmentListener() {
            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                display.setText("New Value = " + e.getValue());
            }
        };
        scrollbar.addAdjustmentListener(adjustmentListener);
        display = new Label("Messages..", Label.CENTER);
        setLayout(new GridLayout(2,0));
        add(scrollbar);
        add(display);
    }
}
