/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package MesasGraficas;

import java.util.Optional;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Popup;
import javafx.stage.Stage;
import org.controlsfx.dialog.Dialogs;

/**
 *
 * @author pablo
 */
public class windowTabla {
    private TableView table = new TableView();
    final ObservableList<Mesa> data = FXCollections.observableArrayList();
    
    public windowTabla(Stage primaryStage, Mesas listadeMesas) {
        final Popup popup = new Popup();
        popup.setX(200);
        popup.setY(200);
        final Label label = new Label("Listado de mesas");
        label.setBackground(Background.EMPTY);
        label.setFont(new Font("Arial", 20));
 
        table.setEditable(true);
        
        TableColumn numeroCol = new TableColumn("Numero");
        numeroCol.setMinWidth(100);
        numeroCol.setCellValueFactory(new PropertyValueFactory<Mesa, Integer>("numero"));
        
        TableColumn dbCol = new TableColumn("DB");
        dbCol.setMinWidth(50);
        dbCol.setCellValueFactory(new PropertyValueFactory<Mesa, Integer>("db"));
        
        TableColumn fbCol = new TableColumn("FB");
        fbCol.setMinWidth(50);
        fbCol.setCellValueFactory(new PropertyValueFactory<Mesa, Integer>("fb"));

        TableColumn transportadorCol = new TableColumn("Transportador");
        transportadorCol.setMinWidth(200);
        transportadorCol.setCellValueFactory(new PropertyValueFactory<Mesa, String>("transportador"));
        
        table.getColumns().addAll(numeroCol,dbCol, fbCol, transportadorCol);
        
        for(int i=0;i<listadeMesas.getListaMesas().size();i++){
            Mesa m=listadeMesas.getListaMesas().get(i).getMesa();
            data.add(m);
        }
        
        table.setItems(data);
        
        
        Button b = new Button("Cerrar Tabla de Edicion");
        b.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                popup.hide();
            }
        });
        

        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, table,b);
        
        popup.getContent().addAll(vbox);
        
        //popup.getContent().addAll(new Circle(25, 25, 50, Color.AQUAMARINE));
        popup.show(primaryStage);
    }
}
