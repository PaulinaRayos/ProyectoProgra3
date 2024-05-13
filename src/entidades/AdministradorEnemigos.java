/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import ObjetosNegocio.CargarGuardar;
import ObjetosNegocio.Jugando;
import static ObjetosNegocio.inputs.Constantes.EnemyConstants.CRABBY_HEIGHT;
import static ObjetosNegocio.inputs.Constantes.EnemyConstants.CRABBY_HEIGHT_DEFAULT;
import static ObjetosNegocio.inputs.Constantes.EnemyConstants.CRABBY_WIDTH;
import static ObjetosNegocio.inputs.Constantes.EnemyConstants.CRABBY_WIDTH_DEFAULT;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 *
 * @author lalo_
 */
public class AdministradorEnemigos {
    private Jugando jugando;
    private BufferedImage[][] crabbyArr;
    private ArrayList<Cangrejo> crabbies = new ArrayList<>();
    public AdministradorEnemigos(Jugando jugando) {
        this.jugando = jugando;
        loadEnemyImgs();
        addEnemies();
    }
    private void addEnemies() {
        crabbies = CargarGuardar.GetCrabs();
        System.out.println("size of crabs: " + crabbies.size());
    }
    public void update() {
        for (Cangrejo c : crabbies) {
            c.update();
        }
    }

    public void draw(Graphics g, int xLvlOffset) {
        drawCrabs(g, xLvlOffset);
    }

    private void drawCrabs(Graphics g, int xLvlOffset) {
        for (Cangrejo c : crabbies) {
            g.drawImage(crabbyArr[c.getEnemyState()][c.getAniIndex()], (int) c.getHitBox().x - xLvlOffset, (int) c.getHitBox().y, CRABBY_WIDTH, CRABBY_HEIGHT, null);
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
}
