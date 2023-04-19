package MesasGraficas;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;


public class Conexion extends VBox{
    MesaGraph mOrigen;
    MesaGraph mDestino;
    private Line linea;
    private HBox bp;
    boolean conexionIsFailed;
    
    int entrada; //numero de entrada de la mesa destino
    int salida; //numero de entrada de la mesa destino
    
    public Conexion(MesaGraph Morig, int sal, MesaGraph Mdest, int ent,Conexiones conn,
                    double x1,double y1,double x2,double y2) {
        bp=new HBox();
        this.getChildren().add(bp);
        this.entrada=ent;
        this.salida=sal;
        mOrigen = Morig;
        mDestino = Mdest;
        conexionIsFailed=true;
        conectar(mOrigen, sal, mDestino, ent, conn,x1,y1,x2,y2);
        final ContextMenu cm = new ContextMenu();
        MenuItem removeLinea = new MenuItem("Remove Linea");
        
        removeLinea.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                borrarLinea();
            }

            private void borrarLinea() {
                //linea.setStrokeWidth(50.0);
                bp.getChildren().remove(linea);
                bp.getChildren().clear();
                System.out.println("borrando linea -------------");
            }

        });
        cm.getItems().add(removeLinea);
        linea.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                        @Override
            public void handle(MouseEvent t) {
                if (t.getButton() == MouseButton.SECONDARY) {
                    cm.show(linea, t.getScreenX(), t.getScreenY());
                }
            }
        });
    }
    private void conectar(MesaGraph mOrig, int salida, MesaGraph mDest, int entrada, Conexiones conn,
            double x1,double y1,double x2,double y2) {
        if(mDest.iseConectada(entrada)){
            System.out.println("Entrada mesa destino " + entrada + " ya conectada");
        
        }else if(mOrig.sConectada(salida)){
                System.out.println("Entrada mesa destino " + entrada + " ya conectada");
        }else if(mDest.iseConectada(entrada)==false && mOrig.sConectada(salida)==false){
            //entrada en mesa destino libre 
            linea=new Line(x1, y1, x2, y2);
            linea.setStrokeWidth(5.0);
            bp.getChildren().add(linea);
                    
            mDest.conectarE(mOrigen,entrada);
            mOrig.conectarS(mDest,salida);
            conexionIsFailed=false;
            conn.add(this);
        }
    }
    
    public void desconectar() {
            mDestino.desconectarE(mOrigen,entrada);
            mOrigen.desconectarS(mDestino, salida);
            bp.getChildren().remove(linea);
    }
    
    
    public void mostrar(){
        System.out.println("MesaGraph Origen:M"+ mOrigen.getNumero() + " S:" + salida + " MesaGraph destino:M" + mDestino.getNumero() + " E:" + entrada);
    }
    public String coneccionToString(){
        String s;
        s="Mesa Origen:M"+ mOrigen.getNumero() + " S:" + salida + " Mesa destino:M" + mDestino.getNumero() + " E:" + entrada +"\n";
        return s;
    }
    public Line getLinea_Conexion(){
        return linea;
    }

    public MesaGraph getmOrigen() {
        return mOrigen;
    }

    public MesaGraph getmDestino() {
        return mDestino;
    }
}
