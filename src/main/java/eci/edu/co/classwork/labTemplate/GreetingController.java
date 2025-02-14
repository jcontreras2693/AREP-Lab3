package eci.edu.co.classwork.labTemplate;

import eci.edu.co.annotations.GetMapping;
import eci.edu.co.annotations.RequestParam;
import eci.edu.co.annotations.RestController;

@RestController
public class GreetingController {
    @GetMapping("/greeting")
    public static String greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return "Hola" + name;
    }

    @GetMapping("/pi")
    public static String pi (String value) {
        return Double.toString(Math.PI);
    }
}
