package eci.edu.co.classwork2;

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
