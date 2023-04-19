/*
 * esta clase lo unico que intenta es englobar en una clase para despues ser
 * utiliyados como parametros el numero de mesa y un numero e e/S de la mesa
 */

package ESEventsMesas;

/**
 *
 * @author pablo
 */
public class Mesa_ES {
    public int num_mesa;
    public int num_entrada_salida;
    public Mesa_ES(int num_mesa, int num_entrada_salida){
        this.num_mesa = num_mesa;
        this.num_entrada_salida = num_entrada_salida;
    }

    public int getNum_mesa() {
        return num_mesa;
    }

    public void setNum_mesa(int num_mesa) {
        this.num_mesa = num_mesa;
    }

    public int getNum_entrada_salida() {
        return num_entrada_salida;
    }

    public void setNum_entrada_salida(int num_entrada_salida) {
        this.num_entrada_salida = num_entrada_salida;
    }
    
    
}
