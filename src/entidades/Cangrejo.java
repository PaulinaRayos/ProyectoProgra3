/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import static ObjetosNegocio.inputs.Constantes.EnemyConstants.*;

/**
 *
 * @author lalo_
 */
public class Cangrejo extends Enemigo {
    
    public Cangrejo(float x, float y) {
        super(x, y, CRABBY_WIDTH, CRABBY_HEIGHT, CRABBY);
    }
    
}
