/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion;

import ObjetosNegocio.EstadoJuego;
import ObjetosNegocio.Jugando;
import ObjetosNegocio.Menu;
import java.awt.Graphics;

/**
 *
 * @author lalo_
 */
public class Juego implements Runnable{
    private Ventana ventana;
    private JuegoPanel juegoPanel;
    private Thread juegoHilo;
    private final int FPS_SET=120;
    private final int UPS_SET=200;
    
    private Jugando jugando;
    private Menu menu;
    
    public final static int TILES_DEFAULT_SIZE=32;
    public final static float SCALE=1.5f;
    public final static int TILES_IN_WIDTH=26;
    public final static int TILES_IN_HEIGHT=14;
    public final static int TILES_SIZE=(int) (TILES_DEFAULT_SIZE*SCALE);
    public final static int GAME_WIDTH=TILES_SIZE*TILES_IN_WIDTH;
    public final static int GAME_HEIGHT=TILES_SIZE*TILES_IN_HEIGHT;
    
    public Juego(){
        initClases();
        juegoPanel=new JuegoPanel(this);
        ventana=new Ventana(juegoPanel);
        
        empiezaLoop();
        
    }

    private void empiezaLoop(){
        juegoHilo= new Thread(this);
        juegoHilo.start();
    }
    public void actualiza() {

        switch (EstadoJuego.estado) {
            case MENU:
                menu.update();
                break;
            case JUGANDO:
                jugando.update();
                break;
            case OPCIONES:
            case QUITAR:
            default:
                System.exit(0);
                break;

        }
    }

    public void render(Graphics g){
        switch(EstadoJuego.estado){
            case MENU:
               menu.draw(g);
                break;
            case JUGANDO:
               jugando.draw(g);
                break;
            default:
                break;
        }
        
    }
    @Override
    public void run() {
        double timePerFrame=1000000000.0/FPS_SET;
        double timePerUpdate=1000000000.0/UPS_SET;
        
        long previousTime=System.nanoTime();
        
        int frames = 0;
        int updates=0;
        long lastCheck = System.currentTimeMillis();
        double deltaU=0;
        double deltaF=0;
       while(true){
           long currentTime=System.nanoTime();
           
           deltaU+=(currentTime - previousTime)/ timePerUpdate;
           deltaF+=(currentTime - previousTime)/ timePerFrame;
           previousTime=currentTime;
           if(deltaU>=1){
               actualiza();
               updates++;
               deltaU--;
           }
           if(deltaF>=1){
               juegoPanel.repaint();
               deltaF--;
               frames++;
           }
           
           if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS: " + frames + " | " + updates );
                frames = 0;
                updates=0;
            }
       }
    }

    private void initClases() {
        menu=new Menu(this);
        jugando=new Jugando(this);
    }

    public void windowFocusLost() {
       if(EstadoJuego.estado==EstadoJuego.JUGANDO) {
            jugando.getJugador().reiniciarDirBooleans();
        }
    }
    public Menu getMenu(){
        return menu;
    }
    public Jugando getJugando(){
        return jugando;
    }
}
