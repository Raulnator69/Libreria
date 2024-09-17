package newpackage;

import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author raula
 */
public class Libro {
    private String titulo;
    private String autor;

    public Libro(String titulo, String autor) {
        this.titulo = titulo;
        this.autor = autor;
    }

    public Libro(JSONObject jsonObject) throws JSONException {
        this.titulo = jsonObject.getString("titulo");
        this.autor = jsonObject.getString("autor");
    }

    public String GetTitulo() {
        return titulo;
    }

    public String GetAutor() {
        return autor;
    }

    public JSONObject toJSON() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("titulo", titulo);
        jsonObject.put("autor", autor);
        return jsonObject;
    }

    @Override
    public String toString() {
        return "Título: " + titulo + ", Autor: " + autor;
    }
}