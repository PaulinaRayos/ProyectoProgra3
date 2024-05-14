/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import java.util.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author pauli
 *
 * Clase para administrar los puntajes de un juego. Permite guardar los puntajes
 * más altos en un archivo de texto y realizar validaciones sobre los nombres de
 * los jugadores.
 */
public class AdmPuntaje {

    // Nombre del archivo donde se guardarán los puntajes
    private static final String FILENAME = "highscores.txt";
    // Número máximo de puntajes altos que se guardarán
    private static final int MAX_HIGH_SCORES = 5;
    // Longitud máxima permitida para el nombre de un jugador
    private static final int MAX_NAME_LENGTH = 7;

    /**
     * Guarda un puntaje alto en la lista de puntajes.
     *
     * @param name Nombre del jugador.
     * @param score Puntaje obtenido por el jugador.
     */
    public void saveHighScore(String name, int score) {
        // Verifica si el nombre del jugador cumple con la longitud máxima permitida
        if (!validaCadena(MAX_NAME_LENGTH, name)) {
            System.out.println("El nombre no puede tener más de " + MAX_NAME_LENGTH + " letras.");
            return;
        }
        // Carga los puntajes existentes desde el archivo
        List<ScoreEntry> highScores = loadHighScores();
        // Agrega el nuevo puntaje a la lista
        highScores.add(new ScoreEntry(name, score));
        // Ordena la lista de puntajes de mayor a menor
        highScores.sort(Comparator.comparingInt(ScoreEntry::getScore).reversed());
        // Si hay más puntajes de los permitidos, conserva solo los primeros MAX_HIGH_SCORES puntajes
        if (highScores.size() > MAX_HIGH_SCORES) {
            highScores = highScores.subList(0, MAX_HIGH_SCORES);
        }
        // Guarda la lista de puntajes actualizada en el archivo
        saveHighScores(highScores);
    }

    /**
     * Valida que una cadena tenga una longitud máxima determinada.
     *
     * @param longMax Longitud máxima permitida.
     * @param s Cadena a validar.
     * @return true si la cadena cumple con la longitud máxima, false en caso
     * contrario.
     */
    public boolean validaCadena(int longMax, String s) {
        // Define una expresión regular para una cadena de longitud máxima dada por el parámetro longMax
        String reCadena = "^\\w{1," + longMax + "}$";
        // Compila la expresión regular a un patrón.
        Pattern pattern = Pattern.compile(reCadena);
        // Crea un comparador para comparar la cadena contra el patrón.
        Matcher matcher = pattern.matcher(s);
        // Si la cadena se ajusta al patrón devuelve true
        return matcher.matches();
    }

    /**
     * Obtiene los puntajes más altos y los devuelve en una lista de cadenas
     * formateadas.
     *
     * @return Lista de cadenas con los puntajes más altos en formato "nombre:
     * puntaje".
     * @throws IOException Si ocurre un error al cargar los puntajes desde el
     * archivo.
     */
    public List<String> getHighScores() throws IOException {
        // Carga los puntajes desde el archivo
        List<ScoreEntry> highScores = loadHighScores();
        // Crea una lista para almacenar los puntajes formateados
        List<String> formattedHighScores = new ArrayList<>();
        // Itera sobre los puntajes cargados y los formatea como cadenas
        for (ScoreEntry entry : highScores) {
            formattedHighScores.add(entry.getName() + ": " + entry.getScore());
        }
        // Devuelve la lista de puntajes formateados
        return formattedHighScores;
    }

    /**
     * Carga los puntajes almacenados en el archivo de puntajes y los devuelve
     * como una lista de ScoreEntry. Si el archivo no existe o no se puede leer,
     * retorna una lista vacía.
     *
     * @return Lista de puntajes cargados desde el archivo.
     */
    private static List<ScoreEntry> loadHighScores() {
        // Crea una lista para almacenar los puntajes cargados
        List<ScoreEntry> highScores = new ArrayList<>();
        try ( BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
            String line;
            // Lee cada línea del archivo y la convierte en un ScoreEntry
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(":");
                highScores.add(new ScoreEntry(parts[0], Integer.parseInt(parts[1])));
            }
        } catch (IOException e) {
            // El archivo no existe todavía o no se pudo leer, esto es normal si es la primera vez que se ejecuta el programa
        }
        // Devuelve la lista de puntajes cargados
        return highScores;
    }

    /**
     * Guarda la lista de puntajes en el archivo de puntajes.
     *
     * @param highScores Lista de puntajes a guardar en el archivo.
     */
    private static void saveHighScores(List<ScoreEntry> highScores) {
        try ( BufferedWriter bw = new BufferedWriter(new FileWriter(FILENAME))) {
            // Escribe cada puntaje en una línea del archivo
            for (ScoreEntry entry : highScores) {
                bw.write(entry.getName() + ":" + entry.getScore());
                bw.newLine();
            }
        } catch (IOException e) {
            // Se produjo un error al escribir en el archivo
            e.printStackTrace();
        }
    }

    /**
     * Clase interna que representa un puntaje, con un nombre y un valor.
     */
    private static class ScoreEntry {

        private String name;
        private int score;

        /**
         * Constructor de la clase ScoreEntry.
         *
         * @param name Nombre del jugador.
         * @param score Puntaje obtenido por el jugador.
         */
        public ScoreEntry(String name, int score) {
            this.name = name;
            this.score = score;
        }

        /**
         * Obtiene el nombre del jugador.
         *
         * @return El nombre del jugador.
         */
        public String getName() {
            return name;
        }

        /**
         * Obtiene el puntaje del jugador.
         *
         * @return El puntaje del jugador.
         */
        public int getScore() {
            return score;
        }
    }
}
