/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author lalo_
 */
public abstract class Entidad {
    protected float x,y;
    protected int width, height;
    protected Rectangle2D.Float hitbox;
    public Entidad(float x, float y,int width, int height){
        this.x=x;
        this.y=y;
        this.width = width;
	this.height = height;
    }
    protected void drawHitbox(Graphics g) {
		// For debugging the hitbox
        g.setColor(Color.PINK);
        g.drawRect((int) hitbox.x, (int) hitbox.y, (int) hitbox.width, (int) hitbox.height);
    }
    protected void initHitbox(float x, float y, float width, float height) {
	hitbox =new Rectangle2D.Float(x, y, width, height);
    }
 
    public Rectangle2D.Float getHitBox(){
        return hitbox;
    }
}
