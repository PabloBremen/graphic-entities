/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package MesasGraficas;

import java.util.ArrayList;

/**
 *
 * @author pablo
 */
public class Mesas {
    private ArrayList<MesaGraph> listaMesas;
    public Mesas(){
        System.out.println("***************creando objeto de mesas************");
        listaMesas = new ArrayList<MesaGraph>();
        System.out.println("se ha creado el array de la lista de mesas");
    }
    public void add(MesaGraph m){
        listaMesas.add(m);
        System.out.println("mesa agregada" + m.toString());
    }
    public void remove(MesaGraph m){
        System.out.println("mesa siendo borrada" + m.toString());
        listaMesas.remove(m);
    }

    
    public void MostrarMesas(){
        System.out.println("**********mostrando mesas**********");
        for(int i=0;i<listaMesas.size();i++){
            System.out.println(listaMesas.get(i).toString());
        }
    }
    public String getMesasString(){
        String mesas;
        mesas="**********mostrando mesas**********\n";
        for(int i=0;i<listaMesas.size();i++){
            mesas=mesas+listaMesas.get(i).toString();
        }
        return mesas;
        
    }

    public boolean isMesaInLista(int numero) {
        boolean mesaExiste=false;
        for(int i=0;i<listaMesas.size();i++){
            if(listaMesas.get(i).getNumero()==numero){
                mesaExiste=true;
            }
        }
        return mesaExiste;
    }

    public ArrayList<MesaGraph> getListaMesas() {
        return listaMesas;
    }
    public void BorrarMesas(){
        listaMesas.clear();
    }
    public void removeMesa(int i){
        System.out.println("removiendo mesa: " + listaMesas.get(i).getNumero());
        listaMesas.remove(i);
    }
}
