/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import ObjetosNegocio.CargarGuardar;
import static ObjetosNegocio.inputs.Constantes.Direcciones.ABAJO;
import static ObjetosNegocio.inputs.Constantes.Direcciones.ARRIBA;
import static ObjetosNegocio.inputs.Constantes.Direcciones.DERECHA;
import static ObjetosNegocio.inputs.Constantes.Direcciones.IZQUIERDA;
import static ObjetosNegocio.inputs.Constantes.PlayerConstants.CORRER;
import static ObjetosNegocio.inputs.Constantes.PlayerConstants.PARADO;
import static ObjetosNegocio.inputs.Constantes.PlayerConstants.PEGAR;
import static ObjetosNegocio.inputs.Constantes.PlayerConstants.getAccion;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import static ObjetosNegocio.MetodosAyuda.*;
import static ObjetosNegocio.inputs.Constantes.PlayerConstants.BRINCAR;
import static ObjetosNegocio.inputs.Constantes.PlayerConstants.CAER;
import Presentacion.Juego;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author lalo_
 */
public class Jugador extends Entidad{

    private BufferedImage[][] subImages;
    private int aniTick,aniIndex,aniSpeed=25;
    private int accionJugador=CORRER;
    private boolean izquierda,derecha,arriba,abajo,saltar;
    private boolean movimiento=false, ataque=false;
    private float velocidadJugador=1.5F;
    private int[][] lvlDatos;
    private float xDrawOffset = 21 * Juego.SCALE;
    private float yDrawOffset = 4 * Juego.SCALE;
    
    //saltar y gravedad
    private float velocidadAire=0f;
    private float gravedad = 0.04f * Juego.SCALE;
    private float velocidadSalto = -2.41f * Juego.SCALE;
    private float fallSpeedAfterCollision = 0.5f * Juego.SCALE;
    private boolean enAire = false;
    
    
    
    public Jugador(float x, float y,int width, int height) {
        super(x, y,width,height);
        cargarImagenes();
        initHitbox(x,y,20*Juego.SCALE,28*Juego.SCALE);
        
    }
    public void actializa(){
        actualizarPosicion();
        actualizaAnimacion();
        setAnimacion();
        
    }
    public void render(Graphics g){
        g.drawImage(subImages[accionJugador][aniIndex],(int)(hitbox.x-xDrawOffset),(int)(hitbox.y-yDrawOffset-10),width,height,null);
        drawHitbox(g);
    }
   
   
    private void actualizaAnimacion(){
        aniTick++;
        if(aniTick>=aniSpeed){
            aniTick=0;
            aniIndex++;
            if(aniIndex>=getAccion(accionJugador)){
                aniIndex=0;
                ataque=false;
            }
                
        }
    }
    private void setAnimacion() {
        int empiezaAni=accionJugador;
        if(movimiento){
            accionJugador=CORRER;       
        }else{
            accionJugador=PARADO;
        }
        if(enAire){
            if(velocidadAire<0){
                accionJugador=BRINCAR;
            }else{
                
                  //  accionJugador=CAER;
            }
        }
        if(ataque){
            accionJugador=PEGAR;
        }
        if(empiezaAni != accionJugador){
            reiniciarTick();
        }
    }
    private void actualizarPosicion() {
        movimiento=false;
        if(saltar){
            saltar();
        }
        if(!izquierda && !derecha && !enAire){
            return;
        }
        float xVelocidad=0;
        
        if(izquierda )     {
            xVelocidad-=velocidadJugador;  
        }
        if(!enAire){
            if (!entidadEnElPiso(hitbox, lvlDatos)){
                enAire=true;
            }
        }
        if(derecha){
            xVelocidad+=velocidadJugador;
        }
        if(enAire){
            if (CanMoveHere(hitbox.x, hitbox.y + velocidadAire, hitbox.width, hitbox.height, lvlDatos)){
                hitbox.y += velocidadAire;
                velocidadAire += gravedad;
                acutualizaXPos(xVelocidad);
            }else {
                hitbox.y = GetEntityYPosUnderRoofOrAboveFloor(hitbox, velocidadAire);
                if (velocidadAire > 0){
                      resetInAir();
                }   
                else{
                    velocidadAire = fallSpeedAfterCollision;
                }
                 acutualizaXPos(xVelocidad);
            }
        }else{
            acutualizaXPos(xVelocidad);
        }
        movimiento=true;
    }
    private void acutualizaXPos(float xVelocidad){
        if(CanMoveHere(hitbox.x+xVelocidad,hitbox.y,hitbox.width,hitbox.height,lvlDatos)){
            hitbox.x+=xVelocidad;
        }else{
            hitbox.x=GetEntidadXPosSiguientePared(hitbox,xVelocidad);
        }
    }
    private void cargarImagenes(){
            BufferedImage img=CargarGuardar.GetJugadorAtlas(CargarGuardar.RUTAJUGADOR);
            subImages=new BufferedImage[5][5];
            //subImages[0]=img.getSubimage(118, 21, 40, 56);
            subImages[PARADO][0]=img.getSubimage(221, 247, 44, 56);
            subImages[PARADO][1]=img.getSubimage(202, 19, 40, 56);
            subImages[CORRER][0]=img.getSubimage(40, 514, 45, 49);
            subImages[CORRER][1]=img.getSubimage(134, 512, 57, 46);
            subImages[CORRER][2]=img.getSubimage(234, 507, 44, 47);
            subImages[PEGAR][0]=img.getSubimage(322, 252, 49, 51);
            subImages[PEGAR][1]=img.getSubimage(635, 250, 55, 49);
            subImages[BRINCAR][0]=img.getSubimage(373, 20, 42, 53);
            subImages[BRINCAR][1]=img.getSubimage(465, 22, 46, 46);
            subImages[BRINCAR][2]=img.getSubimage(564, 24, 44, 43);
            subImages[BRINCAR][3]=img.getSubimage(214, 134, 48, 47);
            subImages[BRINCAR][4]=img.getSubimage(409, 129, 45, 59);
            subImages[CAER][0]=img.getSubimage(509, 134, 48, 47);
            
    }
    public void cargaNivel(int[][] lvlDatos){
        this.lvlDatos=lvlDatos;
        if(!entidadEnElPiso(hitbox,lvlDatos)){
            enAire=true;
        }
    }
    private void resetInAir() {
        enAire = false;
        velocidadAire = 0;
    }
    public void reiniciarDirBooleans(){
        izquierda=false;
        derecha=false;
        arriba=false;
        abajo=false;
    }
    public void setAtaque(boolean ataque){
        this.ataque=ataque;
    }
    public boolean isIzquierda() {
        return izquierda;
    }

    public void setIzquierda(boolean izquierda) {
        this.izquierda = izquierda;
    }

    public boolean isDerecha() {
        return derecha;
    }

    public void setDerecha(boolean derecha) {
        this.derecha = derecha;
    }

    public boolean isArriba() {
        return arriba;
    }

    public void setArriba(boolean arriba) {
        this.arriba = arriba;
    }

    public boolean isAbajo() {
        return abajo;
    }

    public void setAbajo(boolean abajo) {
        this.abajo = abajo;
    }

    public boolean isSaltar() {
        return saltar;
    }

    public void setSaltar(boolean saltar) {
        this.saltar = saltar;
    }
    

    private void reiniciarTick() {
        aniTick=0;
        aniIndex=0;
    }

    private void saltar() {
        if(enAire){
            return;
        }
        enAire=true;
        velocidadAire=velocidadSalto;  
    }

    

    
    
}
