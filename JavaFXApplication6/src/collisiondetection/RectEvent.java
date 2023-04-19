/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package collisiondetection;

import java.util.EventObject;
import javafx.event.Event;
import javafx.event.EventType;

/**
 *
 * @author pablo
 */
class RectEvent extends EventObject{
    private Coordenadas coordenadas;

    public RectEvent(Object source, Coordenadas coor) {
        super(source);
        this.coordenadas = coor;
        
    }
    
    public Coordenadas coor() {
        System.out.println("---------Rect event ------------");
        System.out.println("Coordenadas: x" + coordenadas.getX() + "\n");
        System.out.println("Coordenadas: y" + coordenadas.getY() + "\n");
        return coordenadas;
    }
    
}
