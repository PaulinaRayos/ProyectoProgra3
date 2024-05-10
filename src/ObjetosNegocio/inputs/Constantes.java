/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ObjetosNegocio.inputs;

/**
 *
 * @author lalo_
 */
public class Constantes {
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
        public static int getAccion(int accion){
            switch(accion){
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
