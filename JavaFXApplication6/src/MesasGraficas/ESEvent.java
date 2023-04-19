package MesasGraficas;

import java.util.EventObject;

public class ESEvent extends EventObject{
    private final int mesa_es; //numero de entrada salida 

    public ESEvent(Object source, int num_entrada_salida) {
        super(source);
        mesa_es= num_entrada_salida;
    }
    
    public int numeroES() {
        System.out.println("---------ES event ------------");
        System.out.println("E/S: " + mesa_es + "\n");
        return mesa_es;
    }
    
    
}