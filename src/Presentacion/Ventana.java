/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion;

import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import javax.swing.JFrame;

/**
 *
 * @author lalo_
 */
public class Ventana {
    private JFrame jframe;
    public Ventana(JuegoPanel juegoPanel){
        jframe=new JFrame();
        
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.add(juegoPanel);
        jframe.setLocationRelativeTo(null);
        jframe.setResizable(false);
        jframe.pack();
        juegoPanel.setFocusable(true); // Habilitar el enfoque del teclado en el JuegoPanel
        juegoPanel.requestFocusInWindow(); 
        jframe.setVisible(true);
        jframe.addWindowFocusListener(new WindowFocusListener() {
            @Override
            public void windowGainedFocus(WindowEvent e) {
               
            }

            @Override
            public void windowLostFocus(WindowEvent e) {
               juegoPanel.getJuego().windowFocusLost();
            }
        });
        
    }
}
