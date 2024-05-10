/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ObjetosNegocio;

import Presentacion.Juego;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author lalo_
 */
public class MetodosAyuda {
    public static boolean CanMoveHere(float x, float y, float width, float height, int[][] lvlData) {
        if (!IsSolid(x, y, lvlData))
                if (!IsSolid(x + width, y + height, lvlData))
                        if (!IsSolid(x + width, y, lvlData))
                                if (!IsSolid(x, y + height, lvlData))
                                        return true;
        return false;
    }
    private static boolean IsSolid(float x, float y, int[][] lvlData) {
		if (x < 0 || x >= Juego.GAME_WIDTH)
			return true;
		if (y < 0 || y >= Juego.GAME_HEIGHT)
			return true;

		float xIndex = x / Juego.TILES_SIZE;
		float yIndex = y / Juego.TILES_SIZE;

		int value = lvlData[(int) yIndex][(int) xIndex];

		if (value >= 48 || value < 0 || value != 11)
			return true;
		return false;
    }
    public static float GetEntidadXPosSiguientePared(Rectangle2D.Float hitbox, float xSpeed){
        int currentTile = (int) (hitbox.x / Juego.TILES_SIZE);
        if (xSpeed > 0) {
            // derecha
            int tileXPos = currentTile * Juego.TILES_SIZE;
            int xOffset = (int) (Juego.TILES_SIZE - hitbox.width);
            return tileXPos + xOffset - 1;
        } else{
            // izquierda
            return currentTile * Juego.TILES_SIZE;
        }
    }
    public static float GetEntityYPosUnderRoofOrAboveFloor(Rectangle2D.Float hitbox, float airSpeed) {
        int currentTile = (int) (hitbox.y / Juego.TILES_SIZE);
        if (airSpeed > 0) {
            // caer- tocar el piso
            int tileYPos = currentTile * Juego.TILES_SIZE;
            int yOffset = (int) (Juego.TILES_SIZE - hitbox.height);
            return tileYPos + yOffset - 2;
        } else{
            // saltar
            return currentTile * Juego.TILES_SIZE;
        }
    }
    public static boolean entidadEnElPiso(Rectangle2D.Float hitbox, int[][] lvlData) {
        if (!IsSolid(hitbox.x, hitbox.y + hitbox.height + 1, lvlData)){
            if (!IsSolid(hitbox.x + hitbox.width, hitbox.y + hitbox.height + 1, lvlData)){
                return false;
            }
        }
        return true;
    }
    
    
}
