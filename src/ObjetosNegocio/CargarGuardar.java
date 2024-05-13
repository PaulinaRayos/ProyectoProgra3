/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ObjetosNegocio;

import static ObjetosNegocio.inputs.Constantes.EnemyConstants.CRABBY;
import Presentacion.Juego;
import entidades.Cangrejo;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/**
 *
 * @author lalo_
 */
public class CargarGuardar {
    public static final String RUTAJUGADOR="../res/players.png";
    public static final String RUTANIVEL="../res/outside_sprites.png";
    //public static final String RUTANIVELDATOS="../res/level_one_data.png";
    public static final String RUTANIVELDATOS="../res/level_one_data_long.png";
    public static final String MENU_BOTONES = "../res/button_atlas.png";
    public static final String MENU_BACKGROUND = "../res/menu_background.png";
    public static final String MENU_BACKGROUND_IMG = "../res/sonicBackgournd2.jpg";
    public static final String CRABBY_SPRITE = "../res/crabby_sprite.png";
    
    
    public static BufferedImage GetJugadorAtlas(String filename){
        BufferedImage img=null;
        InputStream is=CargarGuardar.class.getResourceAsStream(filename);
        try{
            img=ImageIO.read(is);
            
        }catch(IOException ex ){
            ex.printStackTrace();
        }
        return img;
    }
    public static int [][] getDatosNivel(){
        
        BufferedImage img=GetJugadorAtlas(RUTANIVELDATOS);
        int [][] lvlDatos=new int [img.getHeight()][img.getWidth()];
        for (int j = 0; j < img.getHeight(); j++) {
            for (int i = 0; i < img.getWidth(); i++) {
                Color color=new Color(img.getRGB(i, j));
                int value=color.getRed();
                if(value>=48){
                    value=0;
                }
                lvlDatos[j][i]=color.getRed();
            }
        }
        return lvlDatos;
    }
    public static ArrayList<Cangrejo> GetCrabs() {
        BufferedImage img = GetJugadorAtlas(RUTANIVELDATOS);
        ArrayList<Cangrejo> list = new ArrayList<>();
        for (int j = 0; j < img.getHeight(); j++) {
            for (int i = 0; i < img.getWidth(); i++) {
                Color color = new Color(img.getRGB(i, j));
                int value = color.getGreen();
                if (value == CRABBY) {
                    list.add(new Cangrejo(i * Juego.TILES_SIZE, j * Juego.TILES_SIZE));
                }
            }
        }
        return list;

    }
}
