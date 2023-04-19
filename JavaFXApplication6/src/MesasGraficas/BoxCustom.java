/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package MesasGraficas;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

/**
 *
 * @author pablo
 */
public interface BoxCustom {
    public String getEntradaSalida();
    
    public StackPane getStackPane();
    public Circle getCircle();
    public Text getText();
    public void setText(Text text);
   
            
}
