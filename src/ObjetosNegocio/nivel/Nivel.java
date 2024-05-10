/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ObjetosNegocio.nivel;

/**
 *
 * @author lalo_
 */
public class Nivel {
    private int [][]lvlDatos;
    public Nivel(int[][] lvlDatos){
        this.lvlDatos=lvlDatos;
    }
    
    public int getSpriteIndex(int x,int y){
        return lvlDatos[y][x];
    }
    public int [][] getDatosNivel(){
        return lvlDatos;
    }
}
