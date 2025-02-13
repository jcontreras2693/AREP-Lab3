package eci.edu.co;

import java.io.IOException;
import java.net.URISyntaxException;

import static eci.edu.co.http.PokemonServer.*;

public class WebApplication {
    public static void main(String[] args) throws IOException, URISyntaxException {
        // Configurar directorio de archivos estáticos
        staticFiles("src/main/resources/static");

        get("/hello", (req, res) -> "Hello World!");

        get("/pi", (req, res) -> String.valueOf(Math.PI));

        get("/e", (req, res) -> String.valueOf(Math.E));

        start(35000);
    }

    static void changeDirectory(String path){
        staticFiles(path);
    }
}
