/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion;

import ObjetosNegocio.CargarGuardar;
import ObjetosNegocio.EstadoJuego;
import static ObjetosNegocio.inputs.Constantes.UI.Buttons.*;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 *
 * @author lalo_
 */
public class BotonMenu {
    private int xPos, yPos, rowIndex, index;
    private int xOffsetCenter = B_WIDTH / 2;
    private EstadoJuego estado;
    private BufferedImage[] imgs;
    private boolean mouseOver, mousePressed;
    private Rectangle bounds;
    public BotonMenu(int xPos, int yPos,int rowIndex, EstadoJuego estado){
        this.xPos = xPos;
        this.yPos = yPos;
        this.rowIndex = rowIndex;
        this.estado = estado;
        loadImgs();
        initBounds();
    }
    private void initBounds() {
	bounds = new Rectangle(xPos - xOffsetCenter, yPos, B_WIDTH, B_HEIGHT);
    }
    private void loadImgs() {
        imgs = new BufferedImage[3];
        BufferedImage temp = CargarGuardar.GetJugadorAtlas(CargarGuardar.MENU_BOTONES);
        for (int i = 0; i < imgs.length; i++)
                imgs[i] = temp.getSubimage(i * B_WIDTH_DEFAULT, rowIndex * B_HEIGHT_DEFAULT, B_WIDTH_DEFAULT, B_HEIGHT_DEFAULT);
    }
    public void draw(Graphics g) {
        g.drawImage(imgs[index], xPos - xOffsetCenter, yPos, B_WIDTH, B_HEIGHT, null);
    }

    public void update() {
        index = 0;
        if (mouseOver){
            index = 1;
        }
               
        if (mousePressed){
            index = 2;
        }
               
    }

    public boolean isMouseOver() {
        return mouseOver;
    }

    public void setMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
    }

    public boolean isMousePressed() {
        return mousePressed;
    }

    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void applyGamestate() {
        EstadoJuego.estado = estado;
    }

    public void resetBools() {
        mouseOver = false;
        mousePressed = false;
    }
}
