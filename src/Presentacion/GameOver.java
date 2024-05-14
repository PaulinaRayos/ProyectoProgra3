/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion;

import ObjetosNegocio.CargarGuardar;
import ObjetosNegocio.EstadoJuego;
import ObjetosNegocio.Jugando;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class GameOver {
    private Jugando jugando;
     private BufferedImage gameOverImg,ganadorImg;
    public GameOver(Jugando jugando){
        this.jugando=jugando;
        gameOverImg=CargarGuardar.GetJugadorAtlas(CargarGuardar.GAME_OVER);
        ganadorImg=CargarGuardar.GetJugadorAtlas(CargarGuardar.GAME_GANADOR);
    }
    public void draw(Graphics g,int lugar) {
        g.setColor(new Color(0, 0, 0, 200));
        g.fillRect(0, 0, Juego.GAME_WIDTH, Juego.GAME_HEIGHT);
        if(lugar==0){
            g.drawImage(gameOverImg, (Juego.GAME_WIDTH - gameOverImg.getWidth(null)) / 2, 160,null);
        }else{
            g.drawImage(ganadorImg, (Juego.GAME_WIDTH - gameOverImg.getWidth(null)) / 2, 160,null);
        }
        
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            jugando.resetAll();
            EstadoJuego.estado = EstadoJuego.MENU;
        }
    }
}
