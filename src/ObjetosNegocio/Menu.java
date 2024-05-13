/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ObjetosNegocio;

import Presentacion.BotonMenu;
import Presentacion.Juego;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

/**
 *
 * @author lalo_
 */
public class Menu extends Estado  implements MetodosEstado{

    private BotonMenu[] buttons = new BotonMenu[3];
    private BufferedImage backgroundImg, backgroundImgSonic;
    private int menuX, menuY, menuWidth, menuHeight;

    public Menu(Juego juego) {
        super(juego);
        loadButtons();
	loadBackground();
        backgroundImgSonic=CargarGuardar.GetJugadorAtlas(CargarGuardar.MENU_BACKGROUND_IMG);
    }
    private void loadBackground() {
        backgroundImg = CargarGuardar.GetJugadorAtlas(CargarGuardar.MENU_BACKGROUND);
        menuWidth = (int) (backgroundImg.getWidth() * Juego.SCALE);
        menuHeight = (int) (backgroundImg.getHeight() * Juego.SCALE);
        menuX = Juego.GAME_WIDTH / 2 - menuWidth / 2;
        menuY = (int) (45 * Juego.SCALE);

    }
    private void loadButtons() {
        buttons[0] = new BotonMenu(Juego.GAME_WIDTH / 2, (int) (150 * Juego.SCALE), 0, EstadoJuego.JUGANDO);
        buttons[1] = new BotonMenu(Juego.GAME_WIDTH / 2, (int) (220 * Juego.SCALE), 1, EstadoJuego.OPCIONES);
        buttons[2] = new BotonMenu(Juego.GAME_WIDTH / 2, (int) (290 * Juego.SCALE), 2, EstadoJuego.QUITAR);
    }

    @Override
    public void update() {
        for (BotonMenu mb : buttons){
            mb.update();
        }		
    }

    @Override
    public void draw(Graphics g) {
       g.drawImage(backgroundImgSonic, 0, 0, Juego.GAME_WIDTH,Juego.GAME_HEIGHT,null);
       g.drawImage(backgroundImg, menuX, menuY, menuWidth, menuHeight, null);
        for (BotonMenu mb : buttons){
            mb.draw(g);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        for (BotonMenu mb : buttons) {
            if (esEn(e, mb)) {
                mb.setMousePressed(true);
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        for (BotonMenu mb : buttons) {
            if (esEn(e, mb)) {
                if (mb.isMousePressed()) {
                    mb.applyGamestate();
                }
                break;
            }
        }

        resetButtons();
    }
    private void resetButtons() {
        for (BotonMenu mb : buttons) {
            mb.resetBools();
        }

    }
    @Override
    public void mouseMoved(MouseEvent e) {
        for (BotonMenu mb : buttons) {
            mb.setMouseOver(false);
        }

        for (BotonMenu mb : buttons) {
            if (esEn(e, mb)) {
                mb.setMouseOver(true);
                break;
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER){
            EstadoJuego.estado = EstadoJuego.JUGANDO;
        }
	
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
    
    
}
