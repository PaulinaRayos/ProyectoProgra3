/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ObjetosNegocio;

import Presentacion.BotonMenu;
import Presentacion.Juego;
import java.awt.event.MouseEvent;

/**
 *
 * @author lalo_
 */
public class Estado {
    protected Juego juego;

    public Estado(Juego juego) {
            this.juego = juego;
    }
    public boolean esEn(MouseEvent e, BotonMenu bm){
        return bm.getBounds().contains(e.getX(),e.getY());
    }
    public Juego getGame() {
            return juego;
    }
}
