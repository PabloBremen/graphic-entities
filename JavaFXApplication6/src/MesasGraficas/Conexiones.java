/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package MesasGraficas;

import MesasGraficas.Conexion;
import java.util.ArrayList;
import java.util.Iterator;

public class Conexiones {
    private ArrayList<Conexion> listaConexiones;
    public Conexiones(){
        System.out.println("***************creando objeto de conexiones************");
        listaConexiones = new ArrayList<Conexion>();
        System.out.println("se ha creado el array de la lista de conecciones");
    }
    public void add(Conexion c){
        listaConexiones.add(c);
        System.out.println("conexion agregada" + c.coneccionToString());
    }
    public void remove(Conexion c){
        System.out.println("conexion borrada" + c.coneccionToString());
        c.desconectar();
        listaConexiones.remove(c);
    }
    public ArrayList<Conexion> getConexiones(){
        return listaConexiones;
    }
    public void borrarTodas(){
        listaConexiones.clear();
    }

    
    public void MostrarConecciones(){
        // Declaramos el Iterador e imprimimos los Elementos del ArrayList
        System.out.println("**********mostrando conecciones**********");
        for(int i=0;i<listaConexiones.size();i++){
            listaConexiones.get(i).mostrar();
        }
    }
    public String getConexionesString(){
        String con;
        con="**********mostrando conecciones**********\n";
        for(int i=0;i<listaConexiones.size();i++){
            con=con+listaConexiones.get(i).coneccionToString();
        }
        return con;
        
    }
    
}
