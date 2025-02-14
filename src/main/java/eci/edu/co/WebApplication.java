package eci.edu.co;

import eci.edu.co.annotations.GetMapping;
import eci.edu.co.annotations.PostMapping;
import eci.edu.co.annotations.RestController;
import org.reflections.Reflections;


import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import static eci.edu.co.http.PokemonServer.*;

public class WebApplication {
    public static Map<String, Method> services = new HashMap();
    public static Map<String, Object> controllers = new HashMap<>();

    public static void main(String[] args) throws Exception {
        System.out.println("Iniciando WebApplication en forma de Mini SpringBoot...");
        loadComponents();
        staticFiles("src/main/resources/static");
        start(35000);
    }

    /**
     * Código tomado y adaptado de Cristian Javier Alvarez.
     */
    public static void loadComponents() {
        try {
            // Explorar el classpath en busca de clases anotadas con @RestController
            for (Class<?> clazz : findAllClasses()) {
                if (clazz.isAnnotationPresent(RestController.class)) {
                    System.out.println("RestController found: " + clazz.getName());
                    Object controllerInstance = clazz.getDeclaredConstructor().newInstance();

                    for (Method method : clazz.getDeclaredMethods()) {
                        if (method.isAnnotationPresent(GetMapping.class)) {
                            GetMapping mapping = method.getAnnotation(GetMapping.class);
                            System.out.println("Registering GET route: " + mapping.value());
                            services.put(mapping.value(), method);
                            controllers.put(mapping.value(), controllerInstance);
                        }
                        if (method.isAnnotationPresent(PostMapping.class)) {
                            PostMapping mapping = method.getAnnotation(PostMapping.class);
                            System.out.println("Registering POST route: " + mapping.value());
                            services.put(mapping.value(), method);
                            controllers.put(mapping.value(), controllerInstance);
                        }
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Error loading components", e);
        }
    }

    private static Iterable<Class<?>> findAllClasses() {
        Reflections reflections = new Reflections("eci.edu.co");
        return reflections.getTypesAnnotatedWith(RestController.class);
    }
}
