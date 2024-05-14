/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import static ObjetosNegocio.MetodosAyuda.CanMoveHere;
import static ObjetosNegocio.MetodosAyuda.GetEntityYPosUnderRoofOrAboveFloor;
import static ObjetosNegocio.MetodosAyuda.IsFloor;
import static ObjetosNegocio.MetodosAyuda.entidadEnElPiso;
import static ObjetosNegocio.inputs.Constantes.Direcciones.DERECHA;
import static ObjetosNegocio.inputs.Constantes.Direcciones.IZQUIERDA;
import static ObjetosNegocio.inputs.Constantes.EnemyConstants.*;
import Presentacion.Juego;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;


public class Cangrejo extends Enemigo {
    // AttackBox
    private Rectangle2D.Float attackBox;
    private int attackBoxOffsetX;
    public Cangrejo(float x, float y) {
        super(x, y, CRABBY_WIDTH, CRABBY_HEIGHT, CRABBY);
        initHitbox(x, y, (int) (22 * Juego.SCALE), (int) (19 * Juego.SCALE));
        initAttackBox();
    }
    private void initAttackBox() {
        attackBox = new Rectangle2D.Float(x, y, (int) (82 * Juego.SCALE), (int) (19 * Juego.SCALE));
        attackBoxOffsetX = (int) (Juego.SCALE * 30);
    }
    private void updateBehavior(int[][] lvlData,Jugador player) {
        if (firstUpdate) {
            firstUpdateCheck(lvlData);
        }

        if (inAir) {
           updateInAir(lvlData);
        } else {
            switch (enemyState) {
                case IDLE:
                    newState(RUNNING);
                    break;
                case RUNNING:
                    if (canSeePlayer(lvlData, player)) {
                        turnTowardsPlayer(player);
                    }
                    if (isPlayerCloseForAttack(player)) {
                        newState(ATTACK);
                    }

                    move(lvlData);
                    break;
                case ATTACK:
                    if (aniIndex == 0) {
                        attackChecked = false;
                    }

                    // Changed the name for checkEnemyHit to checkPlayerHit
                    if (aniIndex == 3 && !attackChecked) {
                        checkPlayerHit(attackBox, player);
                    }

                    break;
                case HIT:
                    break;
            }
        }

    }
     public void update(int[][] lvlData,Jugador player) {
        updateBehavior(lvlData,player);
        updateAnimationTick();
        updateAttackBox();
    }
    private void updateAttackBox() {
        attackBox.x = hitbox.x - attackBoxOffsetX;
        attackBox.y = hitbox.y;

    }
    public int flipX() {
        if (walkDir == DERECHA) {
            return width;
        } else {
            return 0;
        }
    }

    public int flipW() {
        if (walkDir == DERECHA) {
            return -1;
        } else {
            return 1;
        }

    }
    public void drawAttackBox(Graphics g, int xLvlOffset) {
        g.setColor(Color.red);
        g.drawRect((int) (attackBox.x - xLvlOffset), (int) attackBox.y, (int) attackBox.width, (int) attackBox.height);
    }

}
