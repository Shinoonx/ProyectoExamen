package Dominio;

import org.json.JSONObject;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ManejarJSON {
    private static final String RUTA_ARCHIVO = "src/main/java/Datos/dia.json";

    public static JSONObject leerJSON() {
        try {
            String contenido = new String(Files.readAllBytes(Paths.get(RUTA_ARCHIVO)));
            return new JSONObject(contenido);
        } catch (IOException e) {
            e.printStackTrace();
            return new JSONObject();
        }
    }

    public static void escribirJSON(JSONObject jsonObject) {
        try {
            Files.write(Paths.get(RUTA_ARCHIVO), jsonObject.toString(4).getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
