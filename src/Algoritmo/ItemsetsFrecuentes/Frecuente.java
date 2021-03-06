
package Algoritmo.ItemsetsFrecuentes;

import java.util.ArrayList;


public class Frecuente {
        private Integer k; // Indice del frecuente
        private ArrayList<ItemSet> Itemsets; // Conjunto de itemsets del frecuente

    // Constructor de la clase
    public Frecuente(Integer k) {
        this.setK(k);
        this.setItemsets(new ArrayList<ItemSet>());
    }
    
    // Getters y Setters
    public Integer getK() {
        return k;
    }

    public void setK(Integer k) {
        this.k = k;
    }

    public ArrayList<ItemSet> getItemsets() {
        return Itemsets;
    }

    public void setItemsets(ArrayList<ItemSet> Itemsets) {
        this.Itemsets = Itemsets;
    }
    
    public void agregarItemSet (ItemSet item){
        this.getItemsets().add(item);
    }
    
    // Compara si dos itemsets difieren en el ultimo elemento
    public boolean Comparar (ItemSet uno, ItemSet dos,int barrera){
     int cont=0;
     while (cont<barrera){ // Itera segun la cantidad de elementos iguales que deberían tener los itemsets
     int item= uno.getElementos().get(cont); // Obtiene la valor que se encuentra en la poscición que indica el contador
     int item2= dos.getElementos().get(cont); 
     if (item != item2) {
         return false;
     }
     cont=cont + 1;
     }
    return true;
    }

      public void ordenar() {
        for (int i=0; i< this.getItemsets().size();i++){
            this.getItemsets().get(i).ordenar();
        }
    }
}
