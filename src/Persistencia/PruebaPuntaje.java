/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import java.io.IOException;
import java.util.List;

/**
 *
 * @author pauli
 */
public class PruebaPuntaje {
    public static void main(String[] args) throws IOException {
        // Crear una instancia de AdmPuntaje
        AdmPuntaje admPuntaje = new AdmPuntaje();

        // Guardar algunos puntajes altos
        admPuntaje.saveHighScore("Jugad62", 20000);
        
        
        // Obtener los puntajes más altos y mostrarlos
        List<String> highScores = admPuntaje.getHighScores();
        System.out.println("Puntajes más altos:");
        for (String score : highScores) {
            System.out.println(score);
        }
    }
}