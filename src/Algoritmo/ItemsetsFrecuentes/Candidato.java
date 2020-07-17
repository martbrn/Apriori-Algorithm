
package Algoritmo.ItemsetsFrecuentes;

import java.util.ArrayList;
import java.util.Collections;


public class Candidato {
    private Integer k; // Indice del candidato
    private ArrayList<ItemSet> Itemsets; // Conjunto de itemsets
 
    // Constructor de la clase
    public Candidato(Integer k){ 
      this.setK(k);
      this.setItemsets(new ArrayList<ItemSet>());
   }
    
    // Getters y Setters 
    public ArrayList<ItemSet> getItemsets() {
        return Itemsets;
    }

    public void setItemsets(ArrayList<ItemSet> Itemsets) {
        this.Itemsets = Itemsets;
    }
        
    public Integer getK() {
        return k;
    }

    public void setK(Integer k) {
        this.k = k;
    }
    
    public void AgregarItemset (ItemSet ItemSet){
    this.getItemsets().add(ItemSet);
    }
    
    // Busca en el conjunto al itemset para aumentar la frecuencia en 1
    public void SumarFrecuenciaUno(String Elemento){ 
          int tam= this.getItemsets().size();
          for (int i=0; i < tam ; i++){
             boolean bandera= this.getItemsets().get(i).SumarFrecUno(Elemento);
             if (bandera == true) {
              break;
             }
         }
    }
    
    // Recorre su lista de itemsets y valida que no exista el nuevo itemset que se quiere agregar al conjunto
    public boolean Existe (String elemento){
       Boolean bandera;
       bandera = false;
      int tamanio= this.getItemsets().size();
       for (int i=0; i< tamanio ;i++){
           ItemSet itemset= this.getItemsets().get(i);
          if ((itemset.lotiene(elemento))== true){
           bandera= true;
           return bandera;
          }    
       }
       return bandera;
    }
    
    public void ordenar() {
        Collections.sort(this.getItemsets());
    }
}
