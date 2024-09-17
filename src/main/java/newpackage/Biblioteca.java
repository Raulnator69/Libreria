package newpackage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author raula
 */
public class Biblioteca extends JFrame implements ActionListener {

    private JTextField txtTitulo;
    private JTextField txtAutor;
    private JTextArea txtAreaResultado;
    private List<Libro> libros;
    private static final String ARCHIVO_LIBROS = "libros.json";

    public Biblioteca() throws JSONException {
        super("Biblioteca");

        libros = cargarLibros(); // Cargar libros al iniciar

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLayout(new BorderLayout());
        setResizable(false);
        setLocationRelativeTo(null);

        JPanel panelFormulario = new JPanel(new GridLayout(2, 2, 10, 10));
        panelFormulario.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));

        JLabel lblTitulo = new JLabel("Título");
        lblTitulo.setFont(new Font("Verdana", Font.BOLD, 16));
        txtTitulo = new JTextField();
        txtTitulo.setFont(new Font("Verdana", Font.PLAIN, 16));

        JLabel lblAutor = new JLabel("Autor");
        lblAutor.setFont(new Font("Verdana", Font.BOLD, 16));
        txtAutor = new JTextField();
        txtAutor.setFont(new Font("Verdana", Font.PLAIN, 16));

        panelFormulario.add(lblTitulo);
        panelFormulario.add(txtTitulo);
        panelFormulario.add(lblAutor);
        panelFormulario.add(txtAutor);

        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.addActionListener(this);
        btnAgregar.setFont(new Font("Verdana", Font.BOLD, 16));
        btnAgregar.setBackground(new Color(0, 153, 51));
        btnAgregar.setForeground(Color.white);
        btnAgregar.setPreferredSize(new Dimension(200, 40));

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.addActionListener(this);
        btnEliminar.setFont(new Font("Verdana", Font.BOLD, 16));
        btnEliminar.setBackground(new Color(204, 0, 0));
        btnEliminar.setForeground(Color.white);
        btnEliminar.setPreferredSize(new Dimension(200, 40));

        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.addActionListener(this);
        btnBuscar.setFont(new Font("Verdana", Font.BOLD, 16));
        btnBuscar.setBackground(new Color(0, 102, 204));
        btnBuscar.setForeground(Color.white);
        btnBuscar.setPreferredSize(new Dimension(200, 40));

        JButton btnInventario = new JButton("Inventario");
        btnInventario.addActionListener(this);
        btnInventario.setFont(new Font("Verdana", Font.BOLD, 16));
        btnInventario.setBackground(new Color(255, 53, 0));
        btnInventario.setForeground(Color.white);
        btnInventario.setPreferredSize(new Dimension(200, 40));

        panelFormulario.add(btnAgregar);
        panelFormulario.add(btnEliminar);
        panelFormulario.add(btnBuscar);
        panelFormulario.add(btnInventario);

        txtAreaResultado = new JTextArea();
        txtAreaResultado.setEditable(false);
        txtAreaResultado.setFont(new Font("Verdana", Font.PLAIN, 16));
        txtAreaResultado.setLineWrap(true);
        txtAreaResultado.setWrapStyleWord(true);
        txtAreaResultado.setBackground(new Color(240, 240, 240));
        txtAreaResultado.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        JScrollPane scrollPane = new JScrollPane(txtAreaResultado);
        scrollPane.setPreferredSize(new Dimension(560, 200));

        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.add(panelFormulario, BorderLayout.NORTH);
        panelPrincipal.add(scrollPane, BorderLayout.SOUTH);
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        add(panelPrincipal);

        setVisible(true);
    }

    private void guardarLibros() throws JSONException {
        JSONArray jsonArray = new JSONArray();
        for (Libro libro : libros) {
            jsonArray.put(libro.toJSON());
        }

        try (FileWriter file = new FileWriter(ARCHIVO_LIBROS)) {
            file.write(jsonArray.toString(4)); // Escribe con una indentación de 4 espacios
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Libro> cargarLibros() throws JSONException {
        List<Libro> librosCargados = new ArrayList<>();
        File archivo = new File(ARCHIVO_LIBROS);

        if (archivo.exists()) {
            try (FileReader reader = new FileReader(archivo)) {
                StringBuilder sb = new StringBuilder();
                int c;
                while ((c = reader.read()) != -1) {
                    sb.append((char) c);
                }

                JSONArray jsonArray = new JSONArray(sb.toString());
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    librosCargados.add(new Libro(jsonObject));
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return librosCargados;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Agregar")) {
            String titulo = txtTitulo.getText();
            String autor = txtAutor.getText();
            if (!titulo.isEmpty() && !autor.isEmpty()) {
                agregarLibro(titulo, autor);
                txtTitulo.setText("");
                txtAutor.setText("");
                txtAreaResultado.setText("El libro ha sido añadido correctamente a la Librería.");
                try {
                    guardarLibros(); // Guarda los cambios
                } catch (JSONException ex) {
                    Logger.getLogger(Biblioteca.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                txtAreaResultado.setText("Por favor, introduce el título y el autor del libro.");
            }
        } else if (e.getActionCommand().equals("Eliminar")) {
            String titulo = txtTitulo.getText();
            eliminarLibro(titulo);
            txtTitulo.setText("");
            txtAutor.setText("");
            txtAreaResultado.setText("El libro ha sido eliminado correctamente de la Librería.");
            try {
                guardarLibros(); // Guarda los cambios
            } catch (JSONException ex) {
                Logger.getLogger(Biblioteca.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (e.getActionCommand().equals("Buscar")) {
            String terminoBusqueda = txtTitulo.getText();
            if (!terminoBusqueda.isEmpty()) {
                List<String> resultados = buscarLibro(terminoBusqueda, "titulo");
                txtAreaResultado.setText("");
                if (resultados.isEmpty()) {
                    txtAreaResultado.setText("No se encuentran libros con ese título: " + terminoBusqueda + ".");
                } else {
                    for (String resultado : resultados) {
                        txtAreaResultado.append(resultado + "\n");
                    }
                }
            } else {
                txtAreaResultado.setText("Introduce un término de búsqueda.");
            }
        } else if (e.getActionCommand().equals("Inventario")) {
            txtAreaResultado.setText("");
            List<String> inventario = getInventario();
            if (inventario.isEmpty()) {
                txtAreaResultado.setText("La librería está vacía.");
            } else {
                for (String libro : inventario) {
                    txtAreaResultado.append(libro + "\n");
                }
            }
            txtTitulo.setText("");
            txtAutor.setText("");
        }
    }

    public void agregarLibro(String titulo, String autor) {
        Libro nuevoLibro = new Libro(titulo, autor);
        libros.add(nuevoLibro);
    }

    public void eliminarLibro(String titulo) {
        libros.removeIf(libro -> libro.GetTitulo().equalsIgnoreCase(titulo));
    }

    public List<String> buscarLibro(String terminoBusqueda, String tipoBusqueda) {
        List<String> resultados = new ArrayList<>();

        for (Libro libro : libros) {
            if (tipoBusqueda.equalsIgnoreCase("titulo") && libro.GetTitulo().equalsIgnoreCase(terminoBusqueda)) {
                resultados.add(libro.toString());
            } else if (tipoBusqueda.equalsIgnoreCase("autor") && libro.GetAutor().equalsIgnoreCase(terminoBusqueda)) {
                resultados.add(libro.toString());
            }
        }
        return resultados;
    }

    public List<String> getInventario() {
        List<String> inventario = new ArrayList<>();
        for (Libro libro : libros) {
            inventario.add(libro.toString());
        }
        return inventario;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                ex.printStackTrace();
            }

            try {
                new Biblioteca();
            } catch (JSONException ex) {
                Logger.getLogger(Biblioteca.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
}