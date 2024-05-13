/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import static ObjetosNegocio.inputs.Constantes.EnemyConstants.GetSpriteAmount;

/**
 *
 * @author lalo_
 */
public abstract class Enemigo extends Entidad {

    private int aniIndex, enemyState, enemyType;
    private int aniTick, aniSpeed = 25;

    public Enemigo(float x, float y, int width, int height,int enemyType) {
        super(x, y, width, height);
        this.enemyType = enemyType;
	initHitbox(x, y, width, height);
    }

    private void updateAnimationTick() {
        aniTick++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= GetSpriteAmount(enemyType, enemyState)) {
                aniIndex = 0;
            }
        }
    }

    public void update() {
        updateAnimationTick();
    }

    public int getAniIndex() {
        return aniIndex;
    }

    public int getEnemyState() {
        return enemyState;
    }

}
