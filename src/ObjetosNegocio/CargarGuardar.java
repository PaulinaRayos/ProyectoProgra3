/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ObjetosNegocio;

import Presentacion.Juego;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

/**
 *
 * @author lalo_
 */
public class CargarGuardar {
    public static final String RUTAJUGADOR="../res/players.png";
    public static final String RUTANIVEL="../res/outside_sprites.png";
    public static final String RUTANIVELDATOS="../res/level_one_data.png";
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
        int [][] lvlDatos=new int [Juego.TILES_IN_HEIGHT][Juego.TILES_IN_WIDTH];
        BufferedImage img=GetJugadorAtlas(RUTANIVELDATOS);
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
    
}
