/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ESEventsMesas;

import collisiondetection.Coordenadas;
import java.util.EventObject;

/**
 *
 * @author pablo
 */
public class ESMesaEvent extends EventObject{
    private Mesa_ES mesa_es;

    public ESMesaEvent(Object source, Mesa_ES mesa_es) {
        super(source);
        this.mesa_es = mesa_es;
    }
    
    public Mesa_ES get_Mesa_y_numES() {
        System.out.println("---------Mesa event ------------");
        System.out.println("Mesa: " + mesa_es.getNum_mesa()+ "\n");
        System.out.println("E/S: " + mesa_es.getNum_entrada_salida() + "\n");
        return mesa_es;
    }
}
    