/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion;

import java.awt.BorderLayout;
import java.awt.Font;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class HighScoresWindow extends JFrame{
    public HighScoresWindow(List<String> highScores) {
        setTitle("Puntajes más altos");
        setSize(400, 300);
        setLocationRelativeTo(null); // Centra la ventana
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JTextArea textArea = new JTextArea();
        textArea.setFont(new Font("Arial", Font.BOLD, 16));
        textArea.setEditable(false);

        StringBuilder sb = new StringBuilder();
        sb.append("Puntajes más altos:\n");
        for (String score : highScores) {
            sb.append(score).append("\n");
        }
        textArea.setText(sb.toString());

        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);
    }
}
