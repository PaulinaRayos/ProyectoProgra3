/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ObjetosNegocio.inputs;

import ObjetosNegocio.EstadoJuego;
import Presentacion.JuegoPanel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 *
 * @author lalo_
 */
public class MouseInputs implements MouseListener, MouseMotionListener{
 private JuegoPanel juegoPanel;
 public MouseInputs(JuegoPanel juegoPanel){
     this.juegoPanel=juegoPanel;
 }
    @Override
    public void mouseClicked(MouseEvent e) {
        switch (EstadoJuego.estado) {
            case JUGANDO:
                juegoPanel.getJuego().getJugando().mouseClicked(e);
                break;
            default:
                break;

        }

    }

    @Override
    public void mousePressed(MouseEvent e) {
        switch (EstadoJuego.estado) {
            case MENU:
                juegoPanel.getJuego().getMenu().mousePressed(e);
                break;
            case JUGANDO:
                juegoPanel.getJuego().getJugando().mousePressed(e);
                break;
            default:
                break;

        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        switch (EstadoJuego.estado) {
            case MENU:
                juegoPanel.getJuego().getMenu().mouseReleased(e);
                break;
            case JUGANDO:
                juegoPanel.getJuego().getJugando().mouseReleased(e);
                break;
            default:
                break;

        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        switch (EstadoJuego.estado) {
            case MENU:
                juegoPanel.getJuego().getMenu().mouseMoved(e);
                break;
            case JUGANDO:
                juegoPanel.getJuego().getJugando().mouseMoved(e);
                break;
            default:
                break;

        }
    }
    
}
