package component_tests.components;

import component_tests.TestComponent;

import java.awt.*;

public class TestLabel extends TestComponent {

    private Label label1;
    private Label label2;
    private Label label3;

    @Override
    public void addTestComponents() {
        //create the components
        label1 = new Label();
        label2 = new Label("Label2");
        label3 = new Label("Label3", Label.RIGHT);
        //create the layout
        setLayout(new GridLayout(3,1));
        //add the labels
        add(label1);
        add(label2);
        add(label3);
        //use the labels
        label1.setText("Label1");
        label1.setAlignment(Label.LEFT);
        label2.setAlignment(Label.CENTER);
        System.out.println(label1.getText());
    }
}
