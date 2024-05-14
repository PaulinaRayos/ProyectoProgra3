/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import ObjetosNegocio.CargarGuardar;
import ObjetosNegocio.Jugando;
import static ObjetosNegocio.inputs.Constantes.EnemyConstants.CRABBY_DRAWOFFSET_X;
import static ObjetosNegocio.inputs.Constantes.EnemyConstants.CRABBY_DRAWOFFSET_Y;
import static ObjetosNegocio.inputs.Constantes.EnemyConstants.CRABBY_HEIGHT;
import static ObjetosNegocio.inputs.Constantes.EnemyConstants.CRABBY_HEIGHT_DEFAULT;
import static ObjetosNegocio.inputs.Constantes.EnemyConstants.CRABBY_WIDTH;
import static ObjetosNegocio.inputs.Constantes.EnemyConstants.CRABBY_WIDTH_DEFAULT;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


public class AdministradorEnemigos {
    private Jugando jugando;
    private BufferedImage[][] crabbyArr;
    private ArrayList<Cangrejo> crabbies = new ArrayList<>();
    public int numeroCangrejos=0;
    Font font = new Font("Arial", Font.BOLD, 24);
    public int puntos=0;
    public AdministradorEnemigos(Jugando jugando) {
        this.jugando = jugando;
        this.puntos=jugando.puntos;
        loadEnemyImgs();
        addEnemies();
    }
    private void addEnemies() {
        crabbies = CargarGuardar.GetCrabs(jugando.nivel);
        numeroCangrejos=crabbies.size();
    }
    public void update(int[][] lvlData,Jugador player) {
        for (Cangrejo c : crabbies) {
            if (c.isActive()){
                c.update(lvlData,player);
            }
        }
        
    }

    public void draw(Graphics g, int xLvlOffset) {
        drawCrabs(g, xLvlOffset);
        g.setFont(font);
        g.setColor(Color.WHITE);
        puntos = Enemigo.getEnemigosMuertos();
        g.drawString("Puntos: " + puntos, 0, 100);
    }

    private void drawCrabs(Graphics g, int xLvlOffset) {
        for (Cangrejo c : crabbies) {
            if (c.isActive()) {
                g.drawImage(crabbyArr[c.getEnemyState()][c.getAniIndex()], (int) c.getHitBox().x - xLvlOffset - CRABBY_DRAWOFFSET_X + c.flipX(), (int) c.getHitBox().y - CRABBY_DRAWOFFSET_Y, CRABBY_WIDTH * c.flipW(),CRABBY_HEIGHT, null);
                //c.drawAttackBox(g, xLvlOffset);
            }
        }

    }

    private void loadEnemyImgs() {
        crabbyArr = new BufferedImage[5][9];
        BufferedImage temp = CargarGuardar.GetJugadorAtlas(CargarGuardar.CRABBY_SPRITE);
        for (int j = 0; j < crabbyArr.length; j++) {
            for (int i = 0; i < crabbyArr[j].length; i++) {
                crabbyArr[j][i] = temp.getSubimage(i * CRABBY_WIDTH_DEFAULT, j * CRABBY_HEIGHT_DEFAULT, CRABBY_WIDTH_DEFAULT, CRABBY_HEIGHT_DEFAULT);
            }
        }
    }
    public void resetAllEnemies() {
        for (Cangrejo c : crabbies) {
            c.resetEnemy();
        }
    }
    public void checkEnemyHit(Rectangle2D.Float attackBox) {
        for (Cangrejo c : crabbies) {
            if (c.isActive()) {
                if (attackBox.intersects(c.getHitBox())) {
                    c.hurt(10);
                    return;
                }
            }
        }
    }
    public int getNumeroCangrejos(){
        return numeroCangrejos;
    }
    public int getPuntos(){
        return puntos;
    }
    public void setPuntos(int puntos){
        this.puntos=puntos;
    }
}
