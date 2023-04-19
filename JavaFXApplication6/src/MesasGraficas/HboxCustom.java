package MesasGraficas;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

/**
 *
 * @author pablo
 */
public class HboxCustom extends HBox implements BoxCustom{
    //voy a incluir un pequeño rectangolo para que sea la 
    //referencia grafica de las conexiones. Y desde su cento, se 
    //puedan colocar las conexiones
    private Text text;
    private Circle circle;
    private StackPane stackpane;
    private List _listeners = new ArrayList();
       
    public HboxCustom (String num){
        super();
        text= new Text(num);
        
        stackpane= new StackPane();
        circle= new Circle(15);
        circle.setOpacity(0.0);
        
        this.getChildren().add(stackpane);
        stackpane.getChildren().addAll(circle,text);
        stackpane.setAlignment(text,Pos.CENTER);
        stackpane.setAlignment(circle,Pos.CENTER);
        
        
        circle.setOnMouseEntered(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                circle.setOpacity(10.0);
                circle.setStroke(Color.RED);
            }
        });
        circle.setOnMouseExited(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                circle.setOpacity(0.0);
            }
        });
        text.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                System.out.println("Numero E/S" + text.getText());
                haciendoClickEnE_S();
            }
            
        });
        
    }
    public String getEntradaSalida(){
        System.out.println(text.getText());
        return text.getText();
    }
    public StackPane getStackPane(){
        return this.stackpane;
    }
    public Circle getCircle(){
        return this.circle;
    }
    public Text getText(){
        return text;
    }
    public void setText(Text text){
        this.text=text;
    }
    
    //////// disparo de eventos del tipo E_S_event ///
     public synchronized void haciendoClickEnE_S() {
         _fireESEvent();
    }

    public synchronized void addE_S_listener(ESListener l) {
        _listeners.add(l);
    }
    public synchronized void removeE_S_listener(ESListener l) {
        _listeners.remove(l);
    }
    public synchronized void _fireESEvent() {
        ESEvent rectEvent = new ESEvent( this, Integer.parseInt(text.getText()) );
        Iterator listeners = _listeners.iterator();
        while( listeners.hasNext() ) {
            ( (ESListener) listeners.next() ).e_s_Received( rectEvent );
        }
    }
    
}
