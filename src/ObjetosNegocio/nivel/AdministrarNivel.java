/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ObjetosNegocio.nivel;

import ObjetosNegocio.CargarGuardar;
import Presentacion.Juego;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;


public class AdministrarNivel {
    private Juego juego;
    private BufferedImage[] levelSprite;
    private Nivel nivel1;
    private int nivelIndex;
    public AdministrarNivel(Juego juego, int nivel){
        this.juego=juego;
        this.nivelIndex = nivel;
        //levelSprite=CargarGuardar.GetJugadorAtlas(CargarGuardar.RUTANIVEL);
        importarSprites();
        cargarNivel(this.nivelIndex);
    }
     public void cargarNivel(int nivel) {
        nivel1 = new Nivel(CargarGuardar.getDatosNivel(nivel));
    }
    public void dibuja(Graphics g, int lvlOffset){
        for (int j = 0; j < Juego.TILES_IN_HEIGHT; j++) {
            for (int i = 0; i < nivel1.getDatosNivel()[0].length; i++) {
                int index=nivel1.getSpriteIndex(i, j);
                g.drawImage(levelSprite[index],Juego.TILES_SIZE*i -lvlOffset , Juego.TILES_SIZE*j,Juego.TILES_SIZE,Juego.TILES_SIZE, null);
            }
        }
    }
    public void actualiza(){
        
    }

    private void importarSprites() {
        BufferedImage img=CargarGuardar.GetJugadorAtlas(CargarGuardar.RUTANIVEL);
        levelSprite=new BufferedImage [48];
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 12; i++) {
                int index=j*12+i;
                levelSprite[index]=img.getSubimage(i*32, j*32, 32, 32);
            }
        }
    }
    public Nivel getNivelActual(){
        return nivel1;
    }
    public void siguienteNivel() {
        nivelIndex++;
        cargarNivel(nivelIndex);
    }
}
