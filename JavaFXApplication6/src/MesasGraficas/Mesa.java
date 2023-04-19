package MesasGraficas;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author pablo
 */
public class Mesa {
    private SimpleIntegerProperty numero;
    private SimpleIntegerProperty db;
    private SimpleIntegerProperty fb;
    private SimpleStringProperty transportador;    

    Mesa(int num) {
        numero = new SimpleIntegerProperty(num);
        db = new SimpleIntegerProperty(0);
        fb = new SimpleIntegerProperty(0);
        transportador = new SimpleStringProperty("Tran");
    }
    Mesa(int num, String transportador) {
        numero = new SimpleIntegerProperty(num);
        db = new SimpleIntegerProperty(0);
        fb = new SimpleIntegerProperty(0);
        this.transportador = new SimpleStringProperty(transportador);
    }

    public int getNumero() {
        return numero.getValue();
    }

    public void setNumero(SimpleIntegerProperty numero) {
        this.numero = numero;
    }

    public int getDb() {
        return db.getValue();
    }

    public void setDb(SimpleIntegerProperty db) {
        this.db = db;
    }

    public int getFb() {
        return fb.getValue();
    }

    public void setFb(SimpleIntegerProperty fb) {
        this.fb = fb;
    }

    public String getTrasportador() {
        return transportador.getValue();
    }

    public void setTrasportador(SimpleStringProperty Trasportador) {
        this.transportador = Trasportador;
    }
    
    
}
