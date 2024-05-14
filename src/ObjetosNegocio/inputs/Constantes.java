/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ObjetosNegocio.inputs;

import Presentacion.Juego;


public class Constantes {
    public static class EnemyConstants {

        public static final int CRABBY = 0;

        public static final int IDLE = 0;
        public static final int RUNNING = 1;
        public static final int ATTACK = 2;
        public static final int HIT = 3;
        public static final int DEAD = 4;

        public static final int CRABBY_WIDTH_DEFAULT = 72;
        public static final int CRABBY_HEIGHT_DEFAULT = 32;

        public static final int CRABBY_WIDTH = (int) (CRABBY_WIDTH_DEFAULT * Juego.SCALE);
        public static final int CRABBY_HEIGHT = (int) (CRABBY_HEIGHT_DEFAULT * Juego.SCALE);
        
        
        public static final int CRABBY_DRAWOFFSET_X = (int) (26 * Juego.SCALE);
	public static final int CRABBY_DRAWOFFSET_Y = (int) (9 * Juego.SCALE);
        
        public static int GetSpriteAmount(int enemy_type, int enemy_state) {

            switch (enemy_type) {
                case CRABBY:
                    switch (enemy_state) {
                        case IDLE:
                            return 9;
                        case RUNNING:
                            return 6;
                        case ATTACK:
                            return 7;
                        case HIT:
                            return 4;
                        case DEAD:
                            return 5;
                    }
            }

            return 0;

        }
        public static int GetMaxHealth(int enemy_type) {
            switch (enemy_type) {
                case CRABBY:
                    return 10;
                default:
                    return 1;
            }
        }

        public static int GetEnemyDmg(int enemy_type) {
            switch (enemy_type) {
                case CRABBY:
                    return 15;
                default:
                    return 0;
            }

        }

    }
    public static class UI{
        public static class Buttons {
            public static final int B_WIDTH_DEFAULT = 140;
            public static final int B_HEIGHT_DEFAULT = 56;
            public static final int B_WIDTH = (int) (B_WIDTH_DEFAULT * Juego.SCALE);
            public static final int B_HEIGHT = (int) (B_HEIGHT_DEFAULT * Juego.SCALE);
        }
    }
    public static class Direcciones{
       public static final int IZQUIERDA=0;
       public static final int ARRIBA=1;
       public static final int DERECHA=2;
       public static final int ABAJO=3;
       
    }
    public static class PlayerConstants{
        public static final int CORRER=1;
        public static final int PARADO=0;
        public static final int BRINCAR=2;
        public static final int CAER=3;
        public static final int PEGAR=4;
        public static final int MORIR=5;
        public static final int GOLPE=6;
        public static int getAccion(int accion){
            switch(accion){
                case MORIR:
                    return 1;
                case GOLPE:
                    return 1;
                case CORRER:
                    return 3;
                case PARADO:
                    return 2;
                case BRINCAR:
                    return 5;
                case PEGAR:
                    return 2;
                case CAER:
                    return 1;
                default:
                    return 1;
            }
        }
    }
}
