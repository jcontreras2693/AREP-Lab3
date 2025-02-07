package eci.edu.co.classwork;

import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.Arrays;

public class InvokePrintMembers {
    public static void main(String... args) {
        try {
            Class<?> c = Class.forName(args[0]);
            Class[] argTypes = new Class[]{Member[].class , "AREP".getClass()};
            Method main = c.getDeclaredMethod("printMembers", argTypes);

            Class otraClase = Integer.class;
            Member[] members = otraClase.getMethods();

            System.out.format("invoking %s.main()%n", c.getName());
            main.invoke(null, members, "Methods");
            // production code should handle these exceptions more gracefully
        } catch (Exception x) {
            x.printStackTrace();
        }
    }
}
