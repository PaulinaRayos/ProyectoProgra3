/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ObjetosNegocio.inputs;

import Presentacion.JuegoPanel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import static ObjetosNegocio.inputs.Constantes.Direcciones.*;
/**
 *
 * @author lalo_
 */
public class KeyBoardInputs implements KeyListener{
    private JuegoPanel juegoPanel;
    
    public KeyBoardInputs(JuegoPanel juegoPanel){
        this.juegoPanel=juegoPanel;
    }
    @Override
    public void keyTyped(KeyEvent e) {
       
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
        
        switch(e.getKeyCode()){
            case KeyEvent.VK_A:
                juegoPanel.getJuego().getJugador().setIzquierda(true);
                break;
            case KeyEvent.VK_S:
                juegoPanel.getJuego().getJugador().setAbajo(true);
                break;
            case KeyEvent.VK_D:
               juegoPanel.getJuego().getJugador().setDerecha(true);
                break;
            case KeyEvent.VK_W:
                juegoPanel.getJuego().getJugador().setArriba(true);
                break;
             case KeyEvent.VK_SPACE:
                juegoPanel.getJuego().getJugador().setSaltar(true);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch(e.getKeyCode()){
            case KeyEvent.VK_A:
                juegoPanel.getJuego().getJugador().setIzquierda(false);
                break;
            case KeyEvent.VK_S:
                juegoPanel.getJuego().getJugador().setAbajo(false);
                break;
            case KeyEvent.VK_D:
               juegoPanel.getJuego().getJugador().setDerecha(false);
                break;
            case KeyEvent.VK_W:
                juegoPanel.getJuego().getJugador().setArriba(false);
                break;
            case KeyEvent.VK_SPACE:
                juegoPanel.getJuego().getJugador().setSaltar(false);
                break;
        }
    }
    
}
