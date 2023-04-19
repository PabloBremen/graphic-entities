/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package MesasGraficas;


import ESEventsMesas.ESMesaEvent;
import ESEventsMesas.ESMesa_listener;
import java.util.Optional;
import javafx.application.Application;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
//import org.controlsfx.dialog.Dialogs;

public class JavaFXApplication6 extends Application {
    public int i=0;
    private MesaGraph mOrigen;
    private MesaGraph mDestino;
    public Conexiones conecciones;
    public    Group root = new Group();
    public    Group root_mesas= new Group();
    private Mesas listadeMesas;
    
    public boolean edicionActivada=true;
    @Override
    public void start(final Stage primaryStage) {
        conecciones=new Conexiones();
        listadeMesas=new Mesas();
        
        primaryStage.setTitle("Quarzo @2014");
        //Group root = new Group();
        //Group root_mesas= new Group();
        Scene scene = new Scene(root, 700, 700, Color.web("0x00AAFF"));
        MenuBar menuBar = new MenuBar();
        // --- Menu File
        Menu menuFile = new Menu("File");
        // --- Menu Edit
        Menu menuEdit = new Menu("Edit");
        // --- Menu View
        Menu menuView = new Menu("View");
        menuBar.getMenus().addAll(menuFile, menuEdit, menuView);
        
        MenuItem borrar = new MenuItem("Borrar todo");
        borrar.setOnAction(new ListenerBorrar());
                   
        
        MenuItem modo_edicion = new MenuItem("des modo edicion");
        modo_edicion.setOnAction(new ListenerModoEdicion());
        MenuItem editarFb_db = new MenuItem("Edit FB/DB");
        editarFb_db.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                
            }
        });       
        
        menuFile.getItems().addAll(borrar);
        menuFile.getItems().addAll(modo_edicion);
        
        MenuItem edit_mesas = new MenuItem("Edit mesas");
        edit_mesas.setOnAction(new ListenerEditarMesas(primaryStage));
        
        menuEdit.getItems().addAll(edit_mesas);
        

        root.getChildren().addAll(menuBar);
        root.getChildren().add(root_mesas);

        /**
         * Handlers
        */
        scene.setOnKeyPressed(new ListenerKeyEvent(primaryStage));
        
        scene.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                if (edicionActivada) {
                    if (e.getButton() == MouseButton.PRIMARY) {
                        int m=0;
                        //Optional<String> response = Dialogs.create()
                        //        .owner(primaryStage)
                        //       .title("numero de Mesa")
                        //        .message("Introduce el numero de Mesa")
                        //        .showTextInput("");
                        
                        TextInputDialog dialog = new TextInputDialog("");
                        dialog.setTitle("Numero de Mesa");
                        dialog.setHeaderText(null);
                        dialog.setContentText("Introduce el numero de Mesa:");
                        Optional<String> response = dialog.showAndWait();

                        
                        if (response.isPresent()) {
                            System.out.println("responde:" + response.get().trim());
                            m = Integer.parseInt(response.get());
                            System.out.println("NNNN: " + m);

                        //MissingTextPrompt promp = new MissingTextPrompt(root_mesas.getScene().getWindow());
                            //String numero= promp.getResult();
                            //i = Integer.parseInt(numero);
                            if (listadeMesas.isMesaInLista(m) == false) {
                                final MesaGraph mesa = new MesaGraph(m);
                                listadeMesas.add(mesa);
                                //intento poner el espacio igual entre todas las mesas
                                double x = mesa.getCoordenada(e.getX(), 100);
                                double y = mesa.getCoordenada(e.getY(), 100);
                                mesa.setLayoutX(x);
                                mesa.setLayoutY(y);

                                root_mesas.getChildren().add(mesa);
                                //mesa.setOnMouseClicked(new ListenerElegirOriDest(mesa));
                                listenerMesaES_events mesa_events = new listenerMesaES_events();
                                mesa.addESMesaListener(mesa_events);
                                
                                //mesa.hboxDown

                                final ContextMenu cm = new ContextMenu();
                                MenuItem removeRec = new MenuItem("Quitar Mesa");
                                MenuItem CambiarNumero = new MenuItem("Cambiar Numero");
                                MenuItem rotarMesa = new MenuItem("rotar mesa");
                                MenuItem NumeroDB = new MenuItem("Numero DB");
                                MenuItem NumeroFB = new MenuItem("Numero FB");
                                MenuItem transportador = new MenuItem("transportador");

                                removeRec.setOnAction(new ListenerRemoveRec(mesa));
                                CambiarNumero.setOnAction(new ListenerCambiarNumero(primaryStage, mesa));

                                rotarMesa.setOnAction(new ListenerRotarMesa(mesa));

                                NumeroDB.setOnAction(new ListenerCambiarDB(primaryStage, mesa));
                                NumeroFB.setOnAction(new ListenerCambiarFB(primaryStage, mesa));

                                cm.getItems().add(removeRec);
                                cm.getItems().add(CambiarNumero);
                                cm.getItems().add(rotarMesa);
                                cm.getItems().add(NumeroDB);
                                cm.getItems().add(NumeroFB);
                                mesa.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                                    @Override
                                    public void handle(MouseEvent t) {
                                        if (t.getButton() == MouseButton.SECONDARY) {
                                            cm.show(mesa, t.getScreenX(), t.getScreenY());
                                        }
                                    }
                                });

                            } else {
                                //Dialogs.create()
                            	//        .owner(primaryStage)
                            	//        .title("Warning")
                            	//        .masthead("Introduzca otro numero de mesa")
                            	//        .message("esa mesa ya existe")
                            	//        .showWarning();
                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setTitle("Warning");
                                alert.setHeaderText("Encabezado");
                                alert.setContentText("Introduzca otro numero de mesa");
                                alert.showAndWait();
                            }
                        }
                    }
                }
            }
        });
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    public Group getRoot_Mesas(){
            return root_mesas;
    }
    class ListenerBorrar implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent t) {
            listadeMesas.BorrarMesas();
            conecciones.borrarTodas();
            root_mesas.getChildren().clear();
        }
    }
    class ListenerModoEdicion implements EventHandler<ActionEvent> {
        @Override
            public void handle(ActionEvent t) {
                if (edicionActivada) {
                    edicionActivada = false;
                    System.out.println("edicion desactivada");
                } else {
                    edicionActivada = true;
                    System.out.println("edicion activada");
                }
            }
    }
    class ListenerKeyEvent implements EventHandler<KeyEvent> {
        Stage miStage;
        private ListenerKeyEvent(Stage primaryStage) {
            this.miStage=primaryStage;
        }
        @Override
        public void handle(KeyEvent event) {
            //listar conexiones
            if (event.getText().equals("l")) {
                //Dialogs.create()
            	//        .owner(miStage)
            	//       .title("informacion de la instalacion")
            	//        .masthead(null)
            	//        .message(conecciones.getConexionesString() + listadeMesas.getMesasString())
            	//        .showInformation();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("informacion de la instalacion");
                alert.setHeaderText("Encabezado");
                alert.setContentText(conecciones.getConexionesString() + listadeMesas.getMesasString());
                alert.showAndWait();
            }
        }
    }
    
    class ListenerCambiarNumero implements EventHandler<ActionEvent>{
        Stage localStage;
        MesaGraph localMesa;
        private ListenerCambiarNumero(Stage primaryStage, MesaGraph mesa) {
            localStage=primaryStage;
            localMesa=mesa;
        }
        @Override
        public void handle(ActionEvent t) {
            //esto lo que regresa es el propio objeto menu item, 
            //pero yo lo que quiero es ver el 
            System.out.println(t.getSource().toString());
            localMesa.displayNumero();
            //Optional<String> response = Dialogs.create()
            //        .owner(localStage)
            //        .title("numero de Mesa")
            //        .message("Introduce el nuevo numero de Mesa")
            //        .showTextInput("");
            
            TextInputDialog dialog = new TextInputDialog("");
            dialog.setTitle("numero de Mesa");
            dialog.setHeaderText(null);
            dialog.setContentText("Introduce el nuevo numero de Mesa:");
            Optional<String> response = dialog.showAndWait();

            
            
            
            if (response.isPresent()) {
                i = Integer.parseInt(response.get());
            }
            if (listadeMesas.isMesaInLista(i) == false) {
                localMesa.setNumeroMesa(i);
                localMesa.setHelpText(i);
            } else {
                //Dialogs.create()
            	//        .owner(localStage)
            	//        .title("Warning")
            	//        .masthead("")
            	//        .message("esa mesa ya existe")
            	//        .showWarning();
                
                Alert alert = new Alert(AlertType.WARNING);
                alert.initOwner(localStage);
                alert.setTitle("Warning");
                alert.setHeaderText("");
                alert.setContentText("esa mesa ya existe");
                alert.showAndWait();
                
            }

        }

    }
    class ListenerRemoveRec     implements EventHandler<ActionEvent>{
        MesaGraph mesa;
        private ListenerRemoveRec(MesaGraph mesa) {
            this.mesa = mesa;
        }
        
        @Override
        public void handle(ActionEvent t) {
            //quitar conexiones donde se encuenra esta mesa
            for (int i = 0; i < conecciones.getConexiones().size(); i++) {
                if (conecciones.getConexiones().get(i).getmOrigen() == mesa) {
                    root_mesas.getChildren().remove(conecciones.getConexiones().get(i).getLinea_Conexion());
                    conecciones.remove(conecciones.getConexiones().get(i));

                }
                if (conecciones.getConexiones().get(i).getmDestino() == mesa) {
                    root_mesas.getChildren().remove(conecciones.getConexiones().get(i).getLinea_Conexion());
                    conecciones.remove(conecciones.getConexiones().get(i));
                }
            }
            listadeMesas.remove(mesa);
            root_mesas.getChildren().remove(mesa);

        }
    }
    class ListenerRotarMesa     implements EventHandler<ActionEvent>{
        MesaGraph mesa;

        private ListenerRotarMesa(MesaGraph mesa) {
            this.mesa=mesa;
        }
        @Override
        public void handle(ActionEvent t) {
            String temp = mesa.hboxTop.getEntradaSalida();
            mesa.getTextUp().setText(mesa.vboxLeft.getEntradaSalida());
            mesa.getTextLeft().setText(mesa.hboxDown.getEntradaSalida());
            mesa.getTextDown().setText(mesa.vboxRight.getEntradaSalida());
            mesa.getTextRight().setText(temp);
        }
    }
    
    // Este es la clase que implmentara a la interface ESMesa_listener 
    //estoy intentando hacer facil la conexion de las mesas
    class listenerMesaES_events implements ESMesa_listener {
        @Override
        public void e_s_Received(ESMesaEvent event) {
            event.get_Mesa_y_numES();
            if (edicionActivada == false) {
                if (MesaGraph.eligiendoSalidadeMesa) {
                    mOrigen = (MesaGraph)event.getSource();
                    mOrigen.numeroSalidaElegida=event.get_Mesa_y_numES().num_entrada_salida;
                    System.out.println("mesa origen lista para conectar" + mOrigen.getNumero());
                } else if (MesaGraph.eligiendoSalidadeMesa == false) {
                    mDestino = (MesaGraph)event.getSource();
                    mDestino.numeroEntradaElegida=event.get_Mesa_y_numES().num_entrada_salida;
                    
                    System.out.println("mesa destino lista para conectar M" + mDestino.getNumero() + " con " + "mOrigen: " + mOrigen);
                    System.out.println("--- el numero de la entrada es: " + mDestino.numeroEntradaElegida);
                    Conexion conn = new Conexion(mOrigen, mOrigen.numeroSalidaElegida,
                            mDestino, mDestino.numeroEntradaElegida, conecciones,
                            mOrigen.getX1() + mOrigen.getLayoutX(),
                            mOrigen.getY1() + mOrigen.getLayoutY(),
                            mDestino.getX2() + mDestino.getLayoutX(),
                            mDestino.getY2() + mDestino.getLayoutY());

                    //root_mesas.getChildren().add(conn.getLinea_Conexion());
                }
            }
        }
    }
    
    class ListenerCambiarDB implements EventHandler<ActionEvent>{
        Stage primaryStage;
        MesaGraph mesa;
        private ListenerCambiarDB(Stage primaryStage, MesaGraph mesa) {
            this.primaryStage=primaryStage;
            this.mesa=mesa;
        }
        @Override
        public void handle(ActionEvent t) {
            //Optional<String> response = Dialogs.create()
        	//        .owner(primaryStage)
        	//        .title("numero de db")
        	//        .message("Introduce el numero de db")
        	//        .showTextInput("a");
            
            TextInputDialog dialog = new TextInputDialog("");
            dialog.setTitle("numero de db");
            dialog.setHeaderText(null);
            dialog.setContentText("Introduce el numero de db:");
            Optional<String> response = dialog.showAndWait();

            if (response.isPresent()) {
                int db = Integer.parseInt(response.get());
                mesa.setDB(db);
                mesa.setEtiquetaNdb();
            }
        }

    }
    class ListenerCambiarFB implements EventHandler<ActionEvent>{
        Stage primaryStage;
        MesaGraph mesa;
        private ListenerCambiarFB(Stage primaryStage, MesaGraph mesa) {
            this.primaryStage=primaryStage;
            this.mesa=mesa;
        }
        @Override
        public void handle(ActionEvent t) {
            //Optional<String> response = Dialogs.create()
        	//        .owner(primaryStage)
        	//        .title("numero de fb")
        	//        .message("Introduce el numero de fb")
        	//        .showTextInput("");
            
            TextInputDialog dialog = new TextInputDialog("");
            dialog.setTitle("numero de fb");
            dialog.setHeaderText(null);
            dialog.setContentText("Introduce el numero de fb:");
            Optional<String> response = dialog.showAndWait();

            
            if (response.isPresent()) {
                int fb = Integer.parseInt(response.get());
                mesa.setFb(fb);
                mesa.setEtiquetaNfb();
            }
        }
    }
    class ListenerEditarMesas implements EventHandler<ActionEvent>{
        Stage primaryStage;
        private ListenerEditarMesas(Stage primaryStage){
            this.primaryStage=primaryStage;
        }
        @Override
        public void handle(ActionEvent t) {
            generarVentana();
        }
        
        private void generarVentana(){
            windowTabla windowtabla = new windowTabla(primaryStage,listadeMesas);
        }
    }

}
