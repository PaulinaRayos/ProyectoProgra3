/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ObjetosNegocio.nivel;

import ObjetosNegocio.CargarGuardar;
import Presentacion.Juego;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author lalo_
 */
public class AdministrarNivel {
    private Juego juego;
    private BufferedImage[] levelSprite;
    private Nivel nivel1;
    public AdministrarNivel(Juego juego){
        this.juego=juego;
        //levelSprite=CargarGuardar.GetJugadorAtlas(CargarGuardar.RUTANIVEL);
        importarSprites();
        nivel1= new Nivel(CargarGuardar.getDatosNivel());
    }
    public void dibuja(Graphics g){
        for (int j = 0; j < Juego.TILES_IN_HEIGHT; j++) {
            for (int i = 0; i < Juego.TILES_IN_WIDTH; i++) {
                int index=nivel1.getSpriteIndex(i, j);
                g.drawImage(levelSprite[index],Juego.TILES_SIZE*i , Juego.TILES_SIZE*j,Juego.TILES_SIZE,Juego.TILES_SIZE, null);
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
}
