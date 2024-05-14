/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import static ObjetosNegocio.MetodosAyuda.CanMoveHere;
import static ObjetosNegocio.MetodosAyuda.GetEntityYPosUnderRoofOrAboveFloor;
import static ObjetosNegocio.MetodosAyuda.IsFloor;
import static ObjetosNegocio.MetodosAyuda.IsSightClear;
import static ObjetosNegocio.MetodosAyuda.entidadEnElPiso;
import static ObjetosNegocio.inputs.Constantes.Direcciones.DERECHA;
import static ObjetosNegocio.inputs.Constantes.Direcciones.IZQUIERDA;
import static ObjetosNegocio.inputs.Constantes.EnemyConstants.ATTACK;
import static ObjetosNegocio.inputs.Constantes.EnemyConstants.DEAD;
import static ObjetosNegocio.inputs.Constantes.EnemyConstants.GetEnemyDmg;
import static ObjetosNegocio.inputs.Constantes.EnemyConstants.GetMaxHealth;

import static ObjetosNegocio.inputs.Constantes.EnemyConstants.GetSpriteAmount;
import static ObjetosNegocio.inputs.Constantes.EnemyConstants.HIT;
import static ObjetosNegocio.inputs.Constantes.EnemyConstants.IDLE;
import static ObjetosNegocio.inputs.Constantes.EnemyConstants.RUNNING;
import Presentacion.Juego;
import java.awt.geom.Rectangle2D;


public abstract class Enemigo extends Entidad {

    protected int aniIndex, enemyState, enemyType;
    protected int aniTick, aniSpeed = 25;
    protected boolean firstUpdate = true;
    protected boolean inAir;
    protected float fallSpeed;
    protected float gravity = 0.04f * Juego.SCALE;
    protected float walkSpeed = 0.35f * Juego.SCALE;
    protected int walkDir = IZQUIERDA;
    protected int tileY;
    protected float attackDistance = Juego.TILES_SIZE;
    protected int maxHealth;
    protected int currentHealth;
    protected boolean active = true;
    protected boolean attackChecked;
    public static  int enemigosMuertos=0;

    public Enemigo(float x, float y, int width, int height,int enemyType) {
        super(x, y, width, height);
        this.enemyType = enemyType;
	initHitbox(x, y, width, height);
        maxHealth = GetMaxHealth(enemyType);
	currentHealth = maxHealth;
    }

    protected void updateAnimationTick() {
        aniTick++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= GetSpriteAmount(enemyType, enemyState)) {
                aniIndex = 0;
                switch (enemyState) {
                    case ATTACK:
                    case HIT:
                        enemyState = IDLE;
                        break;
                    case DEAD:
                        active = false;
                        enemigosMuertos+=1;
                        break;
                }
            }
        }
    }
    protected void firstUpdateCheck(int[][] lvlData) {
        if (!entidadEnElPiso(hitbox, lvlData)) {
            inAir = true;
        }
        firstUpdate = false;
    }
    protected void updateInAir(int[][] lvlData) {
        if (CanMoveHere(hitbox.x, hitbox.y + fallSpeed, hitbox.width, hitbox.height, lvlData)) {
            hitbox.y += fallSpeed;
            fallSpeed += gravity;
        } else {
            inAir = false;
            hitbox.y = GetEntityYPosUnderRoofOrAboveFloor(hitbox, fallSpeed);
            tileY = (int) (hitbox.y / Juego.TILES_SIZE);
        }
    }
    protected void move(int[][] lvlData) {
        float xSpeed = 0;

        if (walkDir == IZQUIERDA) {
            xSpeed = -walkSpeed;
        } else {
            xSpeed = walkSpeed;
        }

        if (CanMoveHere(hitbox.x + xSpeed, hitbox.y, hitbox.width, hitbox.height, lvlData)) {
            if (IsFloor(hitbox, xSpeed, lvlData)) {
                hitbox.x += xSpeed;
                return;
            }
        }

        changeWalkDir();
    }
    protected void turnTowardsPlayer(Jugador player) {
        if (player.hitbox.x > hitbox.x) {
            walkDir = DERECHA;
        } else {
            walkDir = IZQUIERDA;
        }
    }
    protected void changeWalkDir() {
        if (walkDir == IZQUIERDA) {
            walkDir = DERECHA;
        } else {
            walkDir = IZQUIERDA;
        }

    }
    protected void newState(int enemyState) {
        this.enemyState = enemyState;
        aniTick = 0;
        aniIndex = 0;
    }
    protected boolean canSeePlayer(int[][] lvlData, Jugador player) {
        int playerTileY = (int) (player.getHitBox().y / Juego.TILES_SIZE);
        if (playerTileY == tileY) {
            if (isPlayerInRange(player)) {
                if (IsSightClear(lvlData, hitbox, player.hitbox, tileY)) {
                    return true;
                }
            }
        }

        return false;
    }
    protected boolean isPlayerInRange(Jugador player) {
        int absValue = (int) Math.abs(player.hitbox.x - hitbox.x);
        return absValue <= attackDistance * 5;
    }
    public int getAniIndex() {
        return aniIndex;
    }

    public int getEnemyState() {
        return enemyState;
    }
    protected boolean isPlayerCloseForAttack(Jugador player) {
        int absValue = (int) Math.abs(player.hitbox.x - hitbox.x);
        return absValue <= attackDistance;
    }
    public void resetEnemy() {
        hitbox.x = x;
        hitbox.y = y;
        firstUpdate = true;
        currentHealth = maxHealth;
        newState(IDLE);
        active = true;
        fallSpeed = 0;
    }
    public boolean isActive() {
        return active;
    }
    public void hurt(int amount) {
        currentHealth -= amount;
        if (currentHealth <= 0) {
            newState(DEAD);
        } else {
            newState(HIT);
        }
    }
    protected void checkPlayerHit(Rectangle2D.Float attackBox, Jugador player) {
        if (attackBox.intersects(player.hitbox)) {
            player.changeHealth(-GetEnemyDmg(enemyType));
        }
        attackChecked = true;
    }
    public static int getEnemigosMuertos() {
        return enemigosMuertos;
    }
    public static void resetEnemigosMuertos() {
        enemigosMuertos = 0;
    }

}
