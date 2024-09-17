# Biblioteca

Este proyecto es una aplicación gráfica simple de una Biblioteca, implementada en Java utilizando **Swing** para la interfaz de usuario. Permite realizar las siguientes acciones:

- Agregar libros a la biblioteca.
- Eliminar libros de la biblioteca.
- Buscar libros por título.
- Ver el inventario completo de la biblioteca.

Los datos de los libros se almacenan en un archivo `libros.json` y se cargan automáticamente al iniciar la aplicación.

## Características

- **Interfaz Gráfica (GUI):** Diseñada con **Java Swing** con un estilo moderno utilizando el `NimbusLookAndFeel`.
- **Gestión de Libros:** Los libros se pueden agregar, buscar, eliminar y mostrar en un inventario.
- **Persistencia de Datos:** Los libros se almacenan en un archivo JSON (`libros.json`) que se guarda y carga automáticamente.
- **Interacción con el Usuario:** La interfaz incluye campos para ingresar el título y autor de los libros, junto con botones para realizar las acciones principales (agregar, eliminar, buscar, ver inventario).

## Requisitos

- **Java 8** o superior.
- **Bibliotecas:**
  - **org.json** para manejar la serialización y deserialización de datos JSON.

## Instalación

1. Clona este repositorio en tu máquina local:
    ```bash
    git clone https://github.com/Raulnator69/Libreria.git
    ```

2. Asegúrate de tener **Java** instalado en tu sistema.

3. Instala la dependencia de **org.json**:
   Si estás usando un entorno de desarrollo como **NetBeans** o **Eclipse**, asegúrate de añadir la dependencia de `org.json`. Puedes descargar el archivo JAR desde [Maven Repository](https://mvnrepository.com/artifact/org.json/json).

4. Compila y ejecuta el programa desde tu IDE o mediante línea de comandos.

## Uso

Al ejecutar la aplicación, verás una ventana con los siguientes campos y botones:

- **Título:** Campo de texto para ingresar el título del libro.
- **Autor:** Campo de texto para ingresar el autor del libro.
- **Botones:**
  - `Agregar`: Añade el libro ingresado a la biblioteca.
  - `Eliminar`: Elimina un libro de la biblioteca utilizando el título como referencia.
  - `Buscar`: Busca libros en la biblioteca utilizando el título ingresado.
  - `Inventario`: Muestra el inventario completo de libros almacenados.

### Ejemplo de Interacción

1. Ingresa el título y autor de un libro y haz clic en **Agregar** para añadirlo.
2. Para eliminar un libro, ingresa el título y haz clic en **Eliminar**.
3. Usa el botón **Buscar** para encontrar un libro por su título.
4. Usa **Inventario** para ver todos los libros disponibles.

## Estructura del Proyecto

```bash
.
├── src
│   └── newpackage
│       ├── Biblioteca.java     # Clase principal que maneja la interfaz gráfica y la lógica de negocio.
│       └── Libro.java          # Clase que representa un libro con atributos como título y autor.
├── libros.json                  # Archivo donde se almacenan los libros en formato JSON.
└── README.md                    # Descripción del proyecto.
