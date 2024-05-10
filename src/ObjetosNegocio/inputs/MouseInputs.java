/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ObjetosNegocio.inputs;

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
       if(e.getButton()==MouseEvent.BUTTON1){
           juegoPanel.getJuego().getJugador().setAtaque(true);
       }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
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
        
    }
    
}
