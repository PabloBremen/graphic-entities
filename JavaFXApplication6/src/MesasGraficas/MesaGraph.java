package MesasGraficas;


import ESEventsMesas.ESMesaEvent;
import ESEventsMesas.ESMesa_listener;
import ESEventsMesas.Mesa_ES;
import collisiondetection.RectConEventos;
import collisiondetection.RectListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ContextMenu;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class MesaGraph extends VBox{
    static boolean eligiendoSalidadeMesa=true;
    
    private final Mesa mesa;
    private final Text helpText;
    private final Text numeroDB;
    private final Text numeroFB;
    private final RectConEventos rectangle;
    private List _listeners = new ArrayList();
    
    private final BorderPane bp;
    HboxCustom hboxTop,hboxDown;
    VBoxCustom vboxLeft,vboxRight;
    StackPane vboxCenter;
    
    private double x1;
    private double y1;
    private double x2;
    private double y2;
        
    boolean[] eConectada=new boolean[5];
    boolean[] sConectada=new boolean[5];
    
    MesaGraph[] E=new MesaGraph[5];
    MesaGraph[] S=new MesaGraph[5];
    
    int numeroEntradaElegida;
    int numeroSalidaElegida;
    
    public MesaGraph(int num){
        mesa=new Mesa(num);
        
        numeroEntradaElegida=0;
        numeroSalidaElegida=0;
        rectangle=new RectConEventos(50, 50);
        this.rectangle.setFill(Color.web("0xEEE8AA"));

        bp = new BorderPane();
        hboxTop = new HboxCustom("1");
        hboxDown = new HboxCustom("2");
        vboxLeft = new VBoxCustom("3");
        vboxRight = new VBoxCustom("4");
        vboxCenter = new StackPane();
        
        helpText = new Text("M" + num);
        helpText.setFont(Font.font("Amble Cn", FontWeight.BOLD, 18));
        helpText.setFill(Color.BLACK);
        helpText.setStroke(Color.web("#7080A0"));
        numeroDB=new Text("DB");
        numeroFB=new Text("FB");
        
        hboxTop.setAlignment(Pos.CENTER);
        vboxRight.setAlignment(Pos.CENTER);
        hboxDown.setAlignment(Pos.CENTER);
        vboxLeft.setAlignment(Pos.CENTER);
        
        vboxCenter.getChildren().addAll(rectangle, helpText,numeroDB,numeroFB);
        vboxCenter.setAlignment(numeroDB,Pos.TOP_CENTER);
        vboxCenter.setAlignment(numeroFB,Pos.BOTTOM_CENTER);
        
        bp.setTop(hboxTop);
        bp.setLeft(vboxLeft);
        bp.setRight(vboxRight);
        bp.setBottom(hboxDown);
        bp.setCenter(vboxCenter);


        this.getChildren().add(bp);
        //vboxRight.setOnMouseClicked(new ListenerVBOX(this,vboxRight, vboxRight.getCircle()));
        //vboxLeft.setOnMouseClicked(new ListenerVBOX(this,vboxLeft,vboxRight.getCircle()));
        //hboxTop.setOnMouseClicked(new ListenerHBOX(this, hboxTop, hboxTop.getCircle()));
        //hboxDown.setOnMouseClicked(new ListenerHBOX(this,hboxDown, hboxDown.getCircle()));
        
        //agregar listeners para capturar los numeros de entrada salidas
        //listenerES_events listenerES_top = new listenerES_events();
        //listenerES_events listenerES_down = new listenerES_events();
        //listenerES_events listenerES_right = new listenerES_events();
        //listenerES_events listenerES_left = new listenerES_events();
        
        //vboxRight.addE_S_listener(listenerES_right);
        //vboxLeft.addE_S_listener(listenerES_left);
        //hboxTop.addE_S_listener(listenerES_top);
        //hboxDown.addE_S_listener(listenerES_down);
        
        
         //agregar los listeners para mover la mesa
        this.setOnMouseDragged(new listenerDragged(this));
        this.setOnMouseReleased(new listenerRelease());
        
        System.out.println("mesa creada:" + num);
    }//aqui se acaba el contructor!!
    
    private void createContextMenuEvent(final ContextMenu cm, final Rectangle rec) {
        addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                if (t.getButton() == MouseButton.SECONDARY) {
                    cm.show(rec, t.getScreenX(), t.getScreenY());
                }
            }
        });
    }
            
    public void displayNumero(){
        System.out.println("Numero de mesa es:" + mesa.getNumero());
    }
        
    public void conectarE(MesaGraph Mentrada,int ent) {
        if (eConectada[ent] == false) {
            E[ent] = Mentrada;
            setEconectada(ent);
            
        } else {
            System.out.println("Entrada " + ent + " ya conectada de mesa "+ getNumero());
        }
    }
    public void conectarS(MesaGraph Msalida,int sal) {
        if (eConectada[sal] == false) {
            E[sal] = Msalida;
            setEconectada(sal);
            
        } else {
            System.out.println("Salida " + sal + " ya conectada de mesa "+ getNumero());
        }
    }
    public void desconectarE(MesaGraph Mentrada,int ent) {
        E[ent] = Mentrada;
        setEdesconectada(ent);
    }
    public void desconectarS(MesaGraph Msalida,int sal) {
        E[sal] = Msalida;
        setEdesconectada(sal);
    }
    
    public void showEntrada(int entrada){
        if (eConectada[entrada] == false) {
            System.out.println("objeto vacio");
        } else {
            E[entrada].toString();
        }
    }
    public void showSalida(int salida){
        if (eConectada[salida] == false) {
            System.out.println("objeto vacio");
        } else {
            E[salida].toString();
        }
    }
    
    public String EtoString(int entrada) {
        if (eConectada[entrada]) {
            return "E" + entrada + E[entrada].getNumero();
        } else {
            return "Entrada no conectada";
        }
    }
    public String StoString(int salida) {
        if (sConectada[salida]) {
            return "S" + salida + S[salida].getNumero();
        } else {
            return "Salida no conectada";
        }
    }
    public String toString(){
        return "Mesa numero " + mesa.getNumero()+"\n";
    }

    public boolean iseConectada(int e) {
        return eConectada[e];
    }

    public boolean sConectada(int s) {
         return sConectada[s];
    }
    public int getNumero(){
        return mesa.getNumero();
    }
    public void setEconectada(int e){
        eConectada[e]=true;
    }
    public void setSconectada(int s){
        sConectada[s]=true;
    }
    public void setEdesconectada(int e){
            eConectada[e]=false;
    }
    public void setSdesconectada(int s){
        sConectada[s]=false;
    }
    
    public void draw(GraphicsContext gc){
        
    }
    public void setNumeroMesa(int nuevoNumero){
        SimpleIntegerProperty n= new SimpleIntegerProperty(nuevoNumero);
        mesa.setNumero(n);
        
    }
    public void cambiarNumero(){
        int nuevoNumero=0;
        String numero;
        
        //despliega un dialog
        MissingTextPrompt promp = new MissingTextPrompt(bp.getScene().getWindow());
        numero= promp.getResult();
        System.out.println("el numero del promp es: " + numero);
        nuevoNumero = Integer.parseInt(numero);

        setNumeroMesa(nuevoNumero); 
        System.out.println("y el nuevo numero de mesa es: " + nuevoNumero);
    }

    private void configurarTexto(Text var) {
        var.setFont(Font.font("Amble Cn", FontWeight.BOLD, 18));
        var.setFill(Color.BLACK);
        var.setStroke(Color.web("#7080A0"));
    }

    public boolean isEligiendoSalidadeMesa() {
        return eligiendoSalidadeMesa;
    }

    public void seteligiendoSalidadeMesa(boolean eligiendoSalidadeMesa) {
        this.eligiendoSalidadeMesa = eligiendoSalidadeMesa;
        System.out.println("cambio de la variable estatica 'eligiendoSalidadeMesa'" + isEligiendoSalidadeMesa());
    }

    public double getCoordenada(double x, double i) {
        return Math.round((x/i)) * i;
    }

    public double getX1() {
        System.out.println(x1);
        return x1;
    }

    public double getY1() {
        System.out.println(y1);
        return y1;
    }

    public double getX2() {
        System.out.println(x2);
        return x2;
    }

    public double getY2() {
        System.out.println(y2);
        return y2;
    }
    public void setHelpText(int num){
        helpText.setText("M" + num);
    }

    public Text getTextUp() {
        return hboxTop.getText();
        
    }
    public void setTextUp(Text textUp) {
        hboxTop.setText(textUp);
    }

    public Text getTextRight() {
        return vboxRight.getText();
    }

    public void setTextRight(Text textRight) {
        vboxRight.setText(textRight);
    }

    public Text getTextDown() {
        return hboxDown.getText();
    }

    public void setTextDown(Text textDown) {
        hboxDown.setText(textDown);
    }

    public Text getTextLeft() {
        return vboxLeft.getText();
    }

    public void setTextLeft(Text textLeft) {
        vboxLeft.setText(textLeft);
    }
    public int getDB() {
        return mesa.getDb();
    }

    public void setDB(int DB) {
        SimpleIntegerProperty n= new SimpleIntegerProperty(DB);
        mesa.setDb(n);
    }

    public int getFb() {
        return mesa.getFb();
    }

    public void setFb(int fb) {
        SimpleIntegerProperty n= new SimpleIntegerProperty(fb);
        mesa.setFb(n);
    }
    
    public void setEtiquetaNdb(){
        numeroDB.setText("DB"+getDB());
    }
    public void setEtiquetaNfb(){
        numeroFB.setText("FB"+getFb());
    }
    
    /*
    listeners
    */
    
    class listenerDragged implements EventHandler<MouseEvent>{
        MesaGraph r;
        public listenerDragged(MesaGraph r){
            this.r = r;
        }
        @Override
        public void handle(MouseEvent event) {
             //r.getBoundsInParent().getMinX() + event.getX() +
             //r.getBoundsInParent().getMinY() +event.getY() +
            
            r.setLayoutX( r.getBoundsInParent().getMinX() + getCoordenada(event.getX(), 100));
            r.setLayoutY( r.getBoundsInParent().getMinY() + getCoordenada(event.getY(), 100));
        }
    }
    class listenerRelease implements EventHandler<MouseEvent>{
        @Override
        public void handle(MouseEvent event) {
            System.out.println("x: " + event.getX());
            System.out.println("y: " + event.getY());
        }
    }
    class ListenerHBOX implements EventHandler<MouseEvent>{
        VBox v; //esta es la vbox madre de la clase
        HboxCustom h;
        Circle c;
        public ListenerHBOX(VBox v, HboxCustom h, Circle c){
            this.v=v;
            this.h=h;
            this.c=c;
        }
        public void handle(MouseEvent event) {
            if (event.getButton() == MouseButton.PRIMARY) {
                if (eligiendoSalidadeMesa) {
                    System.out.println("--- salida en mesa elegida --- ");
                    System.out.println("S:" + h.getEntradaSalida() + "Mesa: " + mesa.getNumero());
                    
                    x1 = c.getBoundsInParent().getMinX();
                    y1 = v.getBoundsInParent().getMinY() + c.getBoundsInLocal().getMinY() + 500;
                    //como la salida de la mesa ha sido elegida, en el siguiente click se tendra
                    //que elegir la entrada
                    seteligiendoSalidadeMesa(false);
                } else {
                    System.out.println("--- entrada en mesa elegida --- ");
                    System.out.println("E:" + h.getEntradaSalida() + "Mesa: " + mesa.getNumero());

                    x2 = c.getCenterX();
                    y2 = c.getCenterY();
                    seteligiendoSalidadeMesa(true);
                }
            }
        }
    }
    class ListenerVBOX implements EventHandler<MouseEvent>{
        VBox v; //esta es la vbox madre de la clase
        VBoxCustom vBoxCustom;
        Circle c;
        public ListenerVBOX(VBox v, VBoxCustom vboxparam, Circle c){
            this.v=v;
            this.vBoxCustom=vboxparam;
            this.c=c;
        }
        public void handle(MouseEvent event) {
            if (event.getButton() == MouseButton.PRIMARY) {
                if (eligiendoSalidadeMesa) {
                    System.out.println("--- salida en mesa elegida ---- ");
                    System.out.println("S::" + vBoxCustom.getEntradaSalida()+ "Mesa: " + mesa.getNumero());

                    x1 = c.getBoundsInParent().getMinX();
                    y1 = v.getBoundsInParent().getMinY() + c.getBoundsInLocal().getMinY() + 500;
                    //como la salida de la mesa ha sido elegida, en el siguiente click se tendra
                    //que elegir la entrada
                    seteligiendoSalidadeMesa(false);
                } else {
                    System.out.println("--- entrada en mesa elegida --- ");
                    System.out.println("E:" + vBoxCustom.getEntradaSalida() + "Mesa: " + mesa.getNumero());

                    x2 = c.getCenterX();
                    y2 = c.getCenterY();
                    seteligiendoSalidadeMesa(true);
                }
            }
        }
    }
    public Mesa getMesa(){
        return this.mesa;
    }
    
    class listenerES_events implements ESListener {
        @Override
        public void e_s_Received(ESEvent event) {
            System.out.println("evento recibido" + event.numeroES());
            eligiendoES(event.numeroES());
        }
    }
    
    ////////// -- fire events del tipo ES, esto para hacer las conexiones mas facilmente
    public synchronized void eligiendoES(int es_recibida) {
            _fireMesaESEvent(es_recibida);
    }
    public synchronized void addESMesaListener( ESMesa_listener l ) {
        _listeners.add( l );
    }
    
    public synchronized void removeESMesaListener( ESMesa_listener l ) {
        _listeners.remove( l );
    }
     
    private synchronized void _fireMesaESEvent(int es_recibida) {
        ESMesaEvent mesaEvent = new ESMesaEvent( this, new Mesa_ES(getNumero(),es_recibida )  );
        Iterator listeners = _listeners.iterator();
        while( listeners.hasNext() ) {
            ( (ESMesa_listener) listeners.next() ).e_s_Received(mesaEvent );
        }
    }
    
}
