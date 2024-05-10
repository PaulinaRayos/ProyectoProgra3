/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion;

import ObjetosNegocio.inputs.KeyBoardInputs;
import ObjetosNegocio.inputs.MouseInputs;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import static ObjetosNegocio.inputs.Constantes.PlayerConstants.*;
import static ObjetosNegocio.inputs.Constantes.Direcciones.*;
import static Presentacion.Juego.GAME_HEIGHT;
import static Presentacion.Juego.GAME_WIDTH;
/**
 *
 * @author lalo_
 */
public class JuegoPanel extends JPanel {
    private MouseInputs mouseInputs;
    private Juego juego;
    public JuegoPanel(Juego juego){
        mouseInputs= new MouseInputs(this);
        this.juego=juego;
        setPanelSize();
        addKeyListener(new KeyBoardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }
   
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        juego.render(g);
    }
    
    private void setPanelSize() {
        Dimension size=new Dimension(GAME_WIDTH,GAME_HEIGHT);
        
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }

    public void actualizaJuego(){

    }
    public Juego getJuego(){
        return juego;
    }
    
}
