/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ObjetosNegocio;

import ObjetosNegocio.nivel.AdministrarNivel;
import Persistencia.AdmPuntaje;
import Presentacion.GameOver;
import Presentacion.Juego;
import static Presentacion.Juego.SCALE;
import entidades.AdministradorEnemigos;
import entidades.Enemigo;
import entidades.Jugador;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.net.URL;
import javax.swing.JOptionPane;


public class Jugando extends Estado implements  MetodosEstado{
    private Jugador jugador;
    private AdministrarNivel adminLevel;
    private AdministradorEnemigos adminEnemigos;
    private GameOver gameOver;
    private  AdmPuntaje admPuntaje;
    private int xLvlOffset;
    private int leftBorder = (int) (0.2 * Juego.GAME_WIDTH);
    private int rightBorder = (int) (0.8 * Juego.GAME_WIDTH);
    private int lvlTilesWide = CargarGuardar.getDatosNivel()[0].length;
    private int maxTilesOffset = lvlTilesWide - Juego.TILES_IN_WIDTH;
    private int maxLvlOffsetX = maxTilesOffset * Juego.TILES_SIZE;
    private boolean solicitudRealizada = false;
    private boolean gameOverB;
    public int puntos=0;
    public int nivel=1;
    public int lugar=0;
    
    URL direccionSonidoSalto, direccionSonidoChoque;
    AudioClip sonidoChoque, sonidoSalto;
    public Jugando(Juego juego) {
        super(juego);
        initClases();
        direccionSonidoChoque = getClass().getResource("../res/choque.wav");
        sonidoChoque = Applet.newAudioClip(direccionSonidoChoque);
        direccionSonidoSalto = getClass().getResource("../res/salto.wav");
        sonidoSalto = Applet.newAudioClip(direccionSonidoSalto);
    }
    
    
    
    private void initClases() {
        adminLevel=new AdministrarNivel(juego,nivel);
        adminEnemigos=new AdministradorEnemigos(this);
        jugador=new Jugador(200,220,(int)(51*SCALE),(int)(47*SCALE),this);
        jugador.cargaNivel(adminLevel.getNivelActual().getDatosNivel());
        gameOver=new GameOver(this);
        admPuntaje= new AdmPuntaje();
    }
    public Jugador getJugador(){
        return jugador;
    }

    void windowFocusLost() {
        jugador.reiniciarDirBooleans();
    }
    public void setGameOver(boolean gameOver) {
        this.gameOverB = gameOver;
    }
    @Override
    public void update() {
        if (!gameOverB) {
            adminLevel.actualiza();
            jugador.actializa();
            adminEnemigos.update(adminLevel.getNivelActual().getDatosNivel(), jugador);
            checkCloseToBorder();
            if (nivel == 1) {
                if (adminEnemigos.getPuntos() == adminEnemigos.getNumeroCangrejos()) {
                    puntos = adminEnemigos.getPuntos();
                    nivel = 2;
                    pasarAlSiguienteNivel();

                }
            }else if(nivel==2){
                int cangrejosPorMorir=adminEnemigos.getNumeroCangrejos()+4;
                if (adminEnemigos.getPuntos() == cangrejosPorMorir) {
                    puntos = adminEnemigos.getPuntos();
                    nivel=1;
                    acabarJuego(puntos);

                }
            }
        }
    }
    private void acabarJuego(int puntos){
        lugar=1;
        setGameOver(true);
        
    }
    private void pasarAlSiguienteNivel() {
        initClases();
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
        if (gameOverB){
            gameOver.draw(g,lugar);
            if(!solicitudRealizada){
                solicitudRealizada = true; 
                guadarPuntuacion();
            }
            
        }		
    }
    public void guadarPuntuacion(){
        int puntos=adminEnemigos.getPuntos();
        String nombreJugador;
        if(puntos==9){
            nombreJugador = JOptionPane.showInputDialog("¡Puntuacion maxima! Ingrese su nombre:");
        }else{
            lugar=0;
            nombreJugador = JOptionPane.showInputDialog("¡Game Over! Ingrese su nombre:");
        }
        
        // Guardar el puntaje si se ingresó un nombre válido
        if (nombreJugador != null && !nombreJugador.isEmpty()) {
            admPuntaje.saveHighScore(nombreJugador, puntos);
        }
        puntos=0;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        if (!gameOverB) {
            if (e.getButton() == MouseEvent.BUTTON1) {
                sonidoChoque.play();
                jugador.setAtaque(true);
            }
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
        if (gameOverB){
            gameOver.keyPressed(e);
        }else{
            switch(e.getKeyCode()){
            case KeyEvent.VK_A:
                jugador.setIzquierda(true);
                break;
            case KeyEvent.VK_D:
               jugador.setDerecha(true);
                break;
             case KeyEvent.VK_SPACE:
                sonidoSalto.play();
                jugador.setSaltar(true);
                break;
            case KeyEvent.VK_BACK_SPACE:
		EstadoJuego.estado = EstadoJuego.MENU;
		break;
        }
        }
         
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (!gameOverB) {
            switch (e.getKeyCode()) {
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
    public void resetAll() {
        nivel=1;
        gameOverB = false;
        solicitudRealizada = false; 
        jugador.resetAll();
        Enemigo.resetEnemigosMuertos();
        adminEnemigos.setPuntos(0);
        adminEnemigos.resetAllEnemies();
        lugar=0;
        
        initClases();
    }
    public void checkEnemyHit(Rectangle2D.Float attackBox) {
        adminEnemigos.checkEnemyHit(attackBox);
    }
}
