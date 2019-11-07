package component_tests;

import component_tests.components.*;

import java.util.HashMap;
import java.util.Map;

public class ComponentFactory {

    private static Map<String, Object> components = new HashMap<>();
    //register all components
    static {
        components.put("Label", new TestLabel());
        components.put("Button", new TestButton());
        components.put("Choice", new TestChoice());
        components.put("List", new TestList());
        components.put("Checkbox", new TestCheckbox());
        components.put("Scrollbar", new TestScrollbar());
        components.put("TextField", new TestTextField());
        components.put("TextArea", new TestTextArea());
    }

    public static void printComponents() {
        System.out.println("Registered Components");
        components.forEach((key,value) -> System.out.println(key));
    }

    public static TestComponent createComponent(String type) {
        if (components.containsKey(type)) {
            return (TestComponent) components.get(type);
        }
        return null;
    }
}
