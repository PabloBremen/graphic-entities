/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package collisiondetection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author pablo
 */
public class RectConEventos extends Rectangle{
    private Coordenadas coor;
    private List _listeners = new ArrayList();

    public RectConEventos(double width, double height) {
        super(width, height);
        this.coor = new Coordenadas(this.getBoundsInParent().getMaxX(), this.getBoundsInParent().getMaxY());
        
    }

    public RectConEventos(double width, double height, Paint fill) {
        super(width, height, fill);
        this.coor = new Coordenadas(this.getBoundsInParent().getMaxX(), this.getBoundsInParent().getMaxY());
    }
    
    public synchronized void moviendoRectangulo(boolean isMoving) {
        if(isMoving){
            coor.setX(this.getBoundsInParent().getMaxX()-getWidth()/2);
            coor.setY(this.getBoundsInParent().getMaxY()-getHeight()/2);
            _fireRectEvent();
        }
    }
    public synchronized void addRectListener( RectListener l ) {
        _listeners.add( l );
    }
    
    public synchronized void removeRectListener( RectListener l ) {
        _listeners.remove( l );
    }
     
    private synchronized void _fireRectEvent() {
        RectEvent rectEvent = new RectEvent( this, coor );
        Iterator listeners = _listeners.iterator();
        while( listeners.hasNext() ) {
            ( (RectListener) listeners.next() ).coorReceived( rectEvent );
        }
    }
    
}
