package com.gui.input;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class KeyboardInputListener implements KeyListener{
    private boolean q;
    private boolean w;
    private boolean e;
    private boolean r;
    private boolean t;
    private boolean y;
    private boolean u;
    private boolean i;
    private boolean o;
    private boolean p;
    private boolean a;
    private boolean s;
    private boolean d;
    private boolean f;
    private boolean g;
    private boolean h;
    private boolean j;
    private boolean k;
    private boolean l;
    private boolean z;
    private boolean x;
    private boolean c;
    private boolean v;
    private boolean b;
    private boolean n;
    private boolean m;
    private boolean left;
    private boolean right;
    private boolean up;
    private boolean down;
    private boolean space;

    @Override
    public void keyTyped(KeyEvent ke) {
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        if (ke.getKeyCode()==ke.VK_A){
            a=true;
        }
        if (ke.getKeyCode()==ke.VK_B){
            b=true;
        }
        if (ke.getKeyCode()==ke.VK_C){
            c=true;
        }
        if (ke.getKeyCode()==ke.VK_D){
            d=true;
        }
        if (ke.getKeyCode()==ke.VK_E){
            e=true;
        }
        if (ke.getKeyCode()==ke.VK_F){
            f=true;
        }
        if (ke.getKeyCode()==ke.VK_G){
            g=true;
        }
        if (ke.getKeyCode()==ke.VK_H){
            h=true;
        }
        if (ke.getKeyCode()==ke.VK_I){
            i=true;
        }
        if (ke.getKeyCode()==ke.VK_J){
            j=true;
        }
        if (ke.getKeyCode()==ke.VK_K){
            k=true;
        }
        if (ke.getKeyCode()==ke.VK_L){
            l=true;
        }
        if (ke.getKeyCode()==ke.VK_M){
            m=true;
        }
        if (ke.getKeyCode()==ke.VK_N){
            n=true;
        }
        if (ke.getKeyCode()==ke.VK_O){
            o=true;
        }
        if (ke.getKeyCode()==ke.VK_P){
            p=true;
        }
        if (ke.getKeyCode()==ke.VK_Q){
            q=true;
        }
        if (ke.getKeyCode()==ke.VK_R){
            r=true;
        }
        if (ke.getKeyCode()==ke.VK_S){
            s=true;
        }
        if (ke.getKeyCode()==ke.VK_T){
            t=true;
        }
        if (ke.getKeyCode()==ke.VK_U){
            u=true;
        }
        if (ke.getKeyCode()==ke.VK_V){
            v=true;
        }
        if (ke.getKeyCode()==ke.VK_W){
            w=true;
        }
        if (ke.getKeyCode()==ke.VK_X){
            x=true;
        }
        if (ke.getKeyCode()==ke.VK_Y){
            y=true;
        }
        if (ke.getKeyCode()==ke.VK_Z){
            z=true;
        }
        if (ke.getKeyCode()==ke.VK_UP){
            up=true;
        }
        if (ke.getKeyCode()==ke.VK_DOWN){
            down=true;
        }
        if (ke.getKeyCode()==ke.VK_LEFT){
            left=true;
        }
        if (ke.getKeyCode()==ke.VK_RIGHT){
            right=true;
        }
        if (ke.getKeyCode()==ke.VK_SPACE){
            space=true;
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        if (ke.getKeyCode()==ke.VK_A){
            a=false;
        }
        if (ke.getKeyCode()==ke.VK_B){
            b=false;
        }
        if (ke.getKeyCode()==ke.VK_C){
            c=false;
        }
        if (ke.getKeyCode()==ke.VK_D){
            d=false;
        }
        if (ke.getKeyCode()==ke.VK_E){
            e=false;
        }
        if (ke.getKeyCode()==ke.VK_F){
            f=false;
        }
        if (ke.getKeyCode()==ke.VK_G){
            g=false;
        }
        if (ke.getKeyCode()==ke.VK_H){
            h=false;
        }
        if (ke.getKeyCode()==ke.VK_I){
            i=false;
        }
        if (ke.getKeyCode()==ke.VK_J){
            j=false;
        }
        if (ke.getKeyCode()==ke.VK_K){
            k=false;
        }
        if (ke.getKeyCode()==ke.VK_L){
            l=false;
        }
        if (ke.getKeyCode()==ke.VK_M){
            m=false;
        }
        if (ke.getKeyCode()==ke.VK_N){
            n=false;
        }
        if (ke.getKeyCode()==ke.VK_O){
            o=false;
        }
        if (ke.getKeyCode()==ke.VK_P){
            p=false;
        }
        if (ke.getKeyCode()==ke.VK_Q){
            q=false;
        }
        if (ke.getKeyCode()==ke.VK_R){
            r=false;
        }
        if (ke.getKeyCode()==ke.VK_S){
            s=false;
        }
        if (ke.getKeyCode()==ke.VK_T){
            t=false;
        }
        if (ke.getKeyCode()==ke.VK_U){
            u=false;
        }
        if (ke.getKeyCode()==ke.VK_V){
            v=false;
        }
        if (ke.getKeyCode()==ke.VK_W){
            w=false;
        }
        if (ke.getKeyCode()==ke.VK_X){
            x=false;
        }
        if (ke.getKeyCode()==ke.VK_Y){
            y=false;
        }
        if (ke.getKeyCode()==ke.VK_Z){
            z=false;
        }
        if (ke.getKeyCode()==ke.VK_UP){
            up=false;
        }
        if (ke.getKeyCode()==ke.VK_DOWN){
            down=false;
        }
        if (ke.getKeyCode()==ke.VK_LEFT){
            left=false;
        }
        if (ke.getKeyCode()==ke.VK_RIGHT){
            right=false;
        }
        if (ke.getKeyCode()==ke.VK_SPACE){
            space=false;
        }
    }

}
