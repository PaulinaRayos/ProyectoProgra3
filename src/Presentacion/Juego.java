/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion;

import ObjetosNegocio.nivel.AdministrarNivel;
import entidades.Jugador;
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
    private Jugador jugador;
    private AdministrarNivel adminLevel;
    
    public final static int TILES_DEFAULT_SIZE=32;
    public final static float SCALE=2f;
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
    public void actualiza(){
        jugador.actializa();
        adminLevel.actualiza();
    }
    public void render(Graphics g){
        adminLevel.dibuja(g);
        jugador.render(g);
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
        adminLevel=new AdministrarNivel(this);
        jugador=new Jugador(200,220,(int)(51*SCALE),(int)(47*SCALE));
        jugador.cargaNivel(adminLevel.getNivelActual().getDatosNivel());
        
    }
    public Jugador getJugador(){
        return jugador;
    }

    void windowFocusLost() {
        jugador.reiniciarDirBooleans();
    }
}
