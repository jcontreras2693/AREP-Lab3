package eci.edu.co.classwork.labTemplate;

import eci.edu.co.annotations.GetMapping;
import eci.edu.co.annotations.RestController;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EciBoot {
    public static Map<String, Method> services = new HashMap();

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        loadComponents(args);
        System.out.println(simulateRequest("/greeting"));
        System.out.println(simulateRequest("/pi"));
    }

    private static String simulateRequest(String route) throws InvocationTargetException, IllegalAccessException {
        Method s = services.get(route);
        String response = "HTTP/1.1 200 OK\r\n"
                + "Content-Type: application/json\r\n"
                + "\r\n"
                + "{\"resp\":\"" + (String) s.invoke(null, "AREP")
                + "\"}";
        return response;
    }

    public static void loadComponents(String[] args) {
        try {
            Class c = Class.forName(args[0]);
            if(!c.isAnnotationPresent(RestController.class)) {
                System.exit(0);
            }

            for(Method m: c.getDeclaredMethods()) {
                if(m.isAnnotationPresent(GetMapping.class)) {
                    GetMapping a = m.getAnnotation(GetMapping.class);
                    services.put(a.value(), m);
                }
            }

        } catch (Exception e) {
            Logger.getLogger(EciBoot.class.getName()).log(Level.SEVERE, null, e);
        }
    }

//    public static void loadComponents() {
//        try {
//            // Explorar el classpath en busca de clases anotadas con @RestController
//            for (Class<?> clazz : findAllClasses()) {
//                if (clazz.isAnnotationPresent(RestController.class)) {
//                    System.out.println("RestController found: " + clazz.getName());
//                    Object controllerInstance = clazz.getDeclaredConstructor().newInstance();
//
//                    for (Method method : clazz.getDeclaredMethods()) {
//                        if (method.isAnnotationPresent(GetMapping.class)) {
//                            GetMapping mapping = method.getAnnotation(GetMapping.class);
//                            System.out.println("Registering route: " + mapping.value()[0]);
//                            services.put(mapping.value()[0], method);
//                            controllers.put(mapping.value()[0], controllerInstance);
//                        }
//                    }
//                }
//            }
//        } catch (Exception e) {
//            throw new RuntimeException("Error loading components", e);
//        }
//    }
//
//    private static Iterable<Class<?>> findAllClasses() {
//        Reflections reflections = new Reflections("eci.edu.co");
//        return reflections.getTypesAnnotatedWith(RestController.class);
//    }
}
