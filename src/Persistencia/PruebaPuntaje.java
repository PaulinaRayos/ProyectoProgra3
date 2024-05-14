/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import java.io.IOException;
import java.util.List;


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
