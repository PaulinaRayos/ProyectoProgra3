/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ObjetosNegocio;

import ObjetosNegocio.nivel.AdministrarNivel;
import Presentacion.Juego;
import static Presentacion.Juego.SCALE;
import entidades.AdministradorEnemigos;
import entidades.Jugador;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 *
 * @author lalo_
 */
public class Jugando extends Estado implements  MetodosEstado{
    private Jugador jugador;
    private AdministrarNivel adminLevel;
    private AdministradorEnemigos adminEnemigos;
    
    private int xLvlOffset;
    private int leftBorder = (int) (0.2 * Juego.GAME_WIDTH);
    private int rightBorder = (int) (0.8 * Juego.GAME_WIDTH);
    private int lvlTilesWide = CargarGuardar.getDatosNivel()[0].length;
    private int maxTilesOffset = lvlTilesWide - Juego.TILES_IN_WIDTH;
    private int maxLvlOffsetX = maxTilesOffset * Juego.TILES_SIZE;
    
    public Jugando(Juego juego) {
        super(juego);
        initClases();
    }
    
    
    
    private void initClases() {
        adminLevel=new AdministrarNivel(juego);
        adminEnemigos=new AdministradorEnemigos(this);
        jugador=new Jugador(200,220,(int)(51*SCALE),(int)(47*SCALE));
        jugador.cargaNivel(adminLevel.getNivelActual().getDatosNivel());
        
    }
    public Jugador getJugador(){
        return jugador;
    }

    void windowFocusLost() {
        jugador.reiniciarDirBooleans();
    }

    @Override
    public void update() {
        adminLevel.actualiza();
        jugador.actializa();
        adminEnemigos.update();
        checkCloseToBorder();
    }
    private void checkCloseToBorder() {
        int playerX = (int) jugador.getHitBox().x;
        int diff = playerX - xLvlOffset;

        if (diff > rightBorder) {
            xLvlOffset += diff - rightBorder;
        } else if (diff < leftBorder) {
            xLvlOffset += diff - leftBorder;
        }

        if (xLvlOffset > maxLvlOffsetX) {
            xLvlOffset = maxLvlOffsetX;
        } else if (xLvlOffset < 0) {
            xLvlOffset = 0;
        }

    }
    @Override
    public void draw(Graphics g) {
        adminLevel.dibuja(g,xLvlOffset);
        jugador.render(g,xLvlOffset);
        adminEnemigos.draw(g,xLvlOffset);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getButton()==MouseEvent.BUTTON1){
           jugador.setAtaque(true);
       }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
         switch(e.getKeyCode()){
            case KeyEvent.VK_A:
                jugador.setIzquierda(true);
                break;
            case KeyEvent.VK_D:
               jugador.setDerecha(true);
                break;
             case KeyEvent.VK_SPACE:
                jugador.setSaltar(true);
                break;
            case KeyEvent.VK_BACK_SPACE:
		EstadoJuego.estado = EstadoJuego.MENU;
		break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch(e.getKeyCode()){
            case KeyEvent.VK_A:
               jugador.setIzquierda(false);
                break;
            case KeyEvent.VK_D:
               jugador.setDerecha(false);
                break;
            case KeyEvent.VK_SPACE:
                jugador.setSaltar(false);
                break;
        }
    }
}
