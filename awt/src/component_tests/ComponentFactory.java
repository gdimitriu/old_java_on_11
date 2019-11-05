package component_tests;

import component_tests.components.TestButton;
import component_tests.components.TestChoice;
import component_tests.components.TestLabel;

import java.util.HashMap;
import java.util.Map;

public class ComponentFactory {

    private static Map<String, Object> components = new HashMap<>();
    //register all components
    static {
        components.put("Label", new TestLabel());
        components.put("Button", new TestButton());
        components.put("Choice", new TestChoice());
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
