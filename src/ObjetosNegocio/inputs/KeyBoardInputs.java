/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ObjetosNegocio.inputs;

import ObjetosNegocio.EstadoJuego;
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
        switch (EstadoJuego.estado) {
            case MENU:
                    juegoPanel.getJuego().getMenu().keyPressed(e);
                    break;
            case JUGANDO:
                    juegoPanel.getJuego().getJugando().keyPressed(e);
                    break;
            default:
                    break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (EstadoJuego.estado) {
            case MENU:
                    juegoPanel.getJuego().getMenu().keyReleased(e);
                    break;
            case JUGANDO:
                    juegoPanel.getJuego().getJugando().keyReleased(e);
                    break;
            default:
                    break;

            }
    }
    
}
