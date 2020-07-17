package Algoritmo;

import Algoritmo.GeneracionReglas.HConsecuente;
import Algoritmo.GeneracionReglas.Regla;
import Algoritmo.ItemsetsFrecuentes.Candidato;
import Algoritmo.ItemsetsFrecuentes.Frecuente;
import Algoritmo.ItemsetsFrecuentes.ItemSet;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Apriori {

    private String RutalFinal; //Ruta del archivo
    private Integer MinConf; // Confianza mínima
    private Integer MinSup; // Soporte mínimo
    private ArrayList<Candidato> ConjunCandidatos; // Conjunto de candidatos
    private ArrayList<Frecuente> ConjunFrecuentes; // Conjunto de frecuentes
    private ArrayList<Regla> ConjunReglas; // Conjunto de reglas
    private Integer NumTransc; // Numero de transacciones

    // Constructor de la clase
    public Apriori(String RutalFinal, Integer MinConf, Integer MinSup) {
        this.setRutalFinal(RutalFinal);
        this.setMinConf(MinConf);
        this.setMinSup(MinSup);
        this.setNumTransc(0);
        this.setConjunCandidatos(new ArrayList<Candidato>());
        this.setConjunFrecuentes(new ArrayList<Frecuente>());
        this.setConjunReglas(new ArrayList<Regla>());
    }

    // Getters y Setters de la clase
    public Integer getNumTransc() {
        return NumTransc;
    }

    public void setNumTransc(Integer NumTransc) {
        this.NumTransc = NumTransc;
    }

    public String getRutalFinal() {
        return RutalFinal;
    }

    public void setRutalFinal(String RutalFinal) {
        this.RutalFinal = RutalFinal;
    }

    public Integer getMinConf() {
        return MinConf;
    }

    public void setMinConf(Integer MinConf) {
        this.MinConf = MinConf;
    }

    public Integer getMinSup() {
        return MinSup;
    }

    public void setMinSup(Integer MinSup) {
        this.MinSup = MinSup;
    }

    public ArrayList<Candidato> getConjunCandidatos() {
        return ConjunCandidatos;
    }

    public void setConjunCandidatos(ArrayList<Candidato> ConjunCandidatos) {
        this.ConjunCandidatos = ConjunCandidatos;
    }

    public ArrayList<Frecuente> getConjunFrecuentes() {
        return ConjunFrecuentes;
    }

    public void setConjunFrecuentes(ArrayList<Frecuente> ConjunFrecuentes) {
        this.ConjunFrecuentes = ConjunFrecuentes;
    }

    public ArrayList<Regla> getConjunReglas() {
        return ConjunReglas;
    }

    public void setConjunReglas(ArrayList<Regla> COnjunReglas) {
        this.ConjunReglas = COnjunReglas;
    }

    public void GenerarFrecuenteUno() throws IOException { // Metodo que genera el frecuente numero 1
        Integer k = 1;
        Frecuente frecuente = new Frecuente(k); // Creación del nuevo frecuente
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        try {
            // Apertura del fichero y creación del BufferedReader para hacer una lectura comoda 
            archivo = new File(this.getRutalFinal());
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);
            // Lectura del Fichero
            String linea;
            linea = br.readLine(); // Lectura de la primera linea del archivo
            boolean bandera = false;
            ArrayList<String> cadenaaux = new ArrayList<String>();
            while (linea != null) {
                String[] cadena = linea.split(" "); // Carga en un arreglo de strings cada transacción que vaya leyendo
                for (int i = 0; i < cadena.length; i++) {
                    for (int x = 0; x < cadenaaux.size(); x++) {
                        if (cadenaaux.get(x).equals(cadena[i])) {
                            bandera = true;
                        }
                    }
                    if (bandera == false) {
                        cadenaaux.add(cadena[i]);
                        this.getConjunCandidatos().get(0).SumarFrecuenciaUno(cadena[i]); // Busca el itemset del canditato numero 1 y se aumenta la frecuencia en 1                    
                    }
                bandera=false;              
                }
                cadenaaux.clear();
                linea = br.readLine(); // Lee la siguiente linea del archivo 
            }
        } catch (FileNotFoundException ex) {
            System.out.print("Error");
        }
        for (int z = 0; z < this.getConjunCandidatos().get(0).getItemsets().size(); z++) { // Recorre los itemsets del candidato numero 1
            int frec = this.getConjunCandidatos().get(0).getItemsets().get(z).getFrecuencia(); // Obtiene la frecuencia
            float suport = (frec * 100) / this.getNumTransc(); // Calcula el soporte del itemset
            float minsup = (float) this.getMinSup();
            if (suport >= minsup) {
                ItemSet item = this.getConjunCandidatos().get(0).getItemsets().get(z);
                frecuente.agregarItemSet(item); // Agrega el itemset que pasa el soporte al frecuente numero 1
            }
        }
        this.getConjunFrecuentes().add(frecuente); // Carga el frecuente numero 1 a  la lista de frecuentes 
    }

    public void GenerarCandidatoK(int k) { // Generación del candidato con itemsets de tamaño K
        Candidato candidato = new Candidato(k); // Crea un nuevo candidato
        Frecuente frecuente = this.getConjunFrecuentes().get(k - 2); // Se recupera el ultimo frecuente generado
        int pos = 0;
        if (k == 2) { // Si se debe generar el candidato numero 2
            for (int i = 0; i < frecuente.getItemsets().size(); i++) { //Se recorren los itemsets del frecuente numero 1
                pos = i + 1;
                for (int y = pos; y < frecuente.getItemsets().size(); y++) {
                    ItemSet itemset = new ItemSet(); 
                    itemset.AgregarElemento(frecuente.getItemsets().get(i).getElementos().get(0));
                    itemset.AgregarElemento(frecuente.getItemsets().get(y).getElementos().get(0));
                    candidato.AgregarItemset(itemset); // Se carga un nuevo itemset al candidato numero 2
                }
            }
            this.getConjunCandidatos().add(candidato); // Carga el candidato numero 2 a lista de candidatos 
        } else { // Si se debe generar un candidato con itemsets de tamaño mayor a dos
            for (int i = 0; i < frecuente.getItemsets().size(); i++) { //Se recorren los itemsets del ultimo frecuente
                pos = i + 1;
                for (int y = pos; y < frecuente.getItemsets().size(); y++) {//Se recorren los itemsets del ultimo frecuente partiendo del segundo elemento
                    boolean bandera = frecuente.Comparar(frecuente.getItemsets().get(i), frecuente.getItemsets().get(y), k - 2); // Se comparan dos itemsets para ver si se pueden combinar
                    if (bandera == true) { // Si se pueden combinar 
                        ItemSet itemset = new ItemSet(); //Se crea un nuevo itemset
                        // Se cargan al nuevo itemset los elementos de los itemsets que se van a combinar
                        for (int x = 0; x < frecuente.getItemsets().get(i).getElementos().size(); x++) {
                            itemset.AgregarElemento(frecuente.getItemsets().get(i).getElementos().get(x));
                        }
                        itemset.AgregarElemento(frecuente.getItemsets().get(y).getElementos().get(k - 2));
                        candidato.AgregarItemset(itemset); // Se agrega al candidato el nuevo itemset de tamaño K
                    }
                }
            }
            Candidato candidato2 = new Candidato(k); // Se crea un nuevo candidato para guardar aquellos itemsets cuyo subconjuntos se encuentren en el frecuente K-1
            for (int z = 0; z < candidato.getItemsets().size(); z++) { //Se recorren los itemsets del candidato generado
                int cont = 0;
                for (int v = 0; v < frecuente.getItemsets().size(); v++) { // Se recorren los itemsets del ultimo frecuente
                    ItemSet itemset1 = candidato.getItemsets().get(z); //Se obtiene un itemset del candidato
                    ItemSet itemset2 = frecuente.getItemsets().get(v); // Se obtiene un itemset del frecuente
                    int tam = frecuente.getK();
                    int cont2 = 0;
                    // Se toma cada elemento del itemset del frecuente y se verifica que se encuentre en el itemset del candidato
                    for (int m = 0; m < itemset2.getElementos().size(); m++) {
                        int num = itemset2.getElementos().get(m);
                        for (int l = 0; l < itemset1.getElementos().size(); l++) {
                            int numero = num;
                            int numero2 = itemset1.getElementos().get(l);
                            if (numero == numero2) {
                                cont2 = cont2 + 1;
                                break;
                            }
                        }
                    }
                    if (cont2 == tam) { // Si conto tanta veces como tamaño tenga el itemset del frecuente
                        cont = cont + 1; // Valida que un subconjunto del itemset analizado se encuentra en el frecuente K-1
                    }
                }
                if (cont == k) { // Si la cantidad de subonjuntos es igual al tamaño del itemset del candidato entonces es valido dicho itemset
                    ItemSet itemset = new ItemSet(); 
                    itemset.Agregartemset(candidato.getItemsets().get(z)); // Se copia el itemset analizado a un nuevo itemset
                    candidato2.AgregarItemset(itemset); // Se agrega al candidato2 el itemset
                }
            }
            this.getConjunCandidatos().add(candidato2); // Finalmente se agrega a la lista de candidatos , el candidato con itemsets de tamaño K
        }
    }

    public void GenerarReglas() { // Modulo de generación de reglas
        float minconf = (float) this.getMinConf(); // Se obtiene la confianza ingresada como umbral
        for (int k = 1; k < this.getConjunFrecuentes().size(); k++) { // Recorre los frecuentes 
            if (k == 1) { // Si K=1 se deben generar las reglas utilizando el frecuente numero 2
                Frecuente frecuente = this.getConjunFrecuentes().get(1); // Se obtiene le frecuente numero 2
                for (int m = 0; m < frecuente.getItemsets().size(); m++) { // Se recorren los itemsets del frecuente
                    int frecuencia1 = 0; // Se utiliza para guardar la frecuencia del antecedente 1 si la regla es 1 -> 2
                    int frecuencia2 = 0; // Se utiliza para guardar la frecuencia del antecedente 2 si la regla es 2 -> 1
                    int frecuencia = frecuente.getItemsets().get(m).getFrecuencia(); //Se obtiene la frecuencia del itemset

                    // Se buscan las frecuencias de ambos antecedentes recorriendo el frecuente numero 1
                    for (int x = 0; x < this.getConjunFrecuentes().get(0).getItemsets().size(); x++) {
                        if (frecuente.getItemsets().get(m).getElementos().get(0) == this.getConjunFrecuentes().get(0).getItemsets().get(x).getElementos().get(0)) {
                            frecuencia1 = this.getConjunFrecuentes().get(0).getItemsets().get(x).getFrecuencia();

                        } else if (frecuente.getItemsets().get(m).getElementos().get(1) == this.getConjunFrecuentes().get(0).getItemsets().get(x).getElementos().get(0)) {
                            frecuencia2 = this.getConjunFrecuentes().get(0).getItemsets().get(x).getFrecuencia();

                        }
                    }
                    float confianza1 = (((float) frecuencia / (float) frecuencia1) * 100); // Calculo de confianza
                    float confianza2 = (((float) frecuencia / (float) frecuencia2) * 100); // Calculo de confianza
                    if (confianza1 >= minconf) { // Si la confianza es mayor o igual al umbral
                        Regla regla = new Regla(); // Se crea una regla
                        regla.AgregarPremisa(this.getConjunFrecuentes().get(1).getItemsets().get(m).getElementos().get(0)); // Se guarda la premisa
                        regla.AgregarConclusion(this.getConjunFrecuentes().get(1).getItemsets().get(m).getElementos().get(1)); // Se guarda la conclusión
                        regla.setConfianza((int) confianza1); // Se guarda la confianza
                        regla.setSoporte((int) ((frecuencia * 100) / this.getNumTransc())); // Se guarda el soporte
                        this.getConjunReglas().add(regla); // Se agrega la nueva regla al conjunto de reglas de ObApriori
                    }
                    if (confianza2 >= minconf) { // Si la confianza es mayor o igual al umbral
                        Regla regla2 = new Regla(); // Se crea una regla
                        regla2.AgregarPremisa(this.getConjunFrecuentes().get(1).getItemsets().get(m).getElementos().get(1));  // Se guarda la premisa
                        regla2.AgregarConclusion(this.getConjunFrecuentes().get(1).getItemsets().get(m).getElementos().get(0));// Se guarda la conclusión
                        regla2.setConfianza((int) confianza2); // Se guarda la confianza
                        regla2.setSoporte((int) ((frecuencia * 100) / this.getNumTransc())); // Se guarda el soporte
                        this.getConjunReglas().add(regla2);  // Se agrega la nueva regla al conjunto de reglas de ObApriori
                    }
                }
            } else { // Si se deben generar reglas con itemsets de tamaño mayor a dos
                Frecuente frecuente = this.getConjunFrecuentes().get(k); // Se obtiene el frecuente K.
                Frecuente frecuente2 = this.getConjunFrecuentes().get(k - 1); // Se obtiene el frecuente K-1.
                for (int i = 0; i < frecuente.getItemsets().size(); i++) { // Se recorren los itemset del frecuente
                    HConsecuente H = new HConsecuente(1); // Se crea un objeto H1 para guardar los consecuentes
                    ItemSet itemset = frecuente.getItemsets().get(i);
                    int frecuencia = frecuente.getItemsets().get(i).getFrecuencia();

                    for (int x = 0; x < itemset.getElementos().size(); x++) { // Se recorren los elementos del itemset que se va a utilizar para generar sus respectivas reglas
                        int frecuencia1 = 0;
                        ArrayList<Integer> Arreglo = new ArrayList<Integer>(); // Se utiliza para guardar el antecedente de la regla
                        for (int y = 0; y < itemset.getElementos().size(); y++) {
                            if (itemset.getElementos().get(y) != itemset.getElementos().get(x)) {
                                Arreglo.add(itemset.getElementos().get(y));
                                ;
                            }
                        }
                        // Se busca la frecuencia del antecedente utilizando la variable arreglo creada anteriormente
                        for (int l = 0; l < frecuente2.getItemsets().size(); l++) {
                            int cont = 0;
                            for (int m = 0; m < frecuente2.getItemsets().get(l).getElementos().size(); m++) {
                                for (int z = 0; z < Arreglo.size(); z++) {
                                    if (Arreglo.get(z) == frecuente2.getItemsets().get(l).getElementos().get(m)) {
                                        cont = cont + 1;
                                        break;
                                    }
                                }
                            }
                            if (cont == frecuente2.getItemsets().get(l).getElementos().size()) {
                                frecuencia1 = frecuente2.getItemsets().get(l).getFrecuencia(); // Se obtiene la frecuencia del antecedente
                            }
                        }
                        float confianza = (((float) frecuencia / (float) frecuencia1) * 100); // Se calcula la confianza
                        if (confianza >= minconf) { // Si supera el mínimo de umbral establecido
                            Regla regla = new Regla(); //Se crea una nueva regla
                            regla.AgregarConclusion(frecuente.getItemsets().get(i).getElementos().get(x)); // Se agrega la conclusión
                            regla.AgregarPremisas(Arreglo); // Se agregan las premisas
                            regla.setConfianza((int) confianza); //Se carga su soporte
                            regla.setSoporte((int) ((frecuencia * 100) / this.getNumTransc())); //Se carga su confianza
                            this.getConjunReglas().add(regla); // Se agrega la nueva regla al conjunto de reglas de ObApriori
                            ItemSet itemset1 = new ItemSet();
                            itemset1.AgregarElemento(frecuente.getItemsets().get(i).getElementos().get(x));
                            H.AgregarItemSet(itemset1); // Se agrega un itemset a la lista de itemsets de H1 con el consecuente de la regla creada
                        }
                    } // Se sige con la generación de otra regla utilizando el mismo itemset
                    apgenRules(itemset, H); // Se llama al metodo apgenRules con el itemset y H1
                }
            }
        }
    }

    // Este metodo genera las reglas con consecuentes mayores a un elemento
    public void apgenRules(ItemSet itemset, HConsecuente Hm) {
        float minconf = (float) this.getMinConf();
        if ((itemset.getElementos().size() > Hm.getIndice() + 1) && (Hm.getItemsets().size() > 0)) {
            Hm = GenerarHm(Hm); // Se genera el H que contiene los consecuentes de tamaño K
            HConsecuente Hmm = new HConsecuente(Hm.getIndice()); // Se utiliza un H auxiliar para guardar los consecuentes cuyas reglas superen el umbral de confianza 
            int frecuencia = itemset.getFrecuencia(); // Se obtiene la frecuencia del itemset
            for (int i = 0; i < Hm.getItemsets().size(); i++) { // Se recoren los consecuentes
                ItemSet itemset2 = new ItemSet(); // Se utiliza para guardar los antencedentes
                for (int m = 0; m < itemset.getElementos().size(); m++) {
                    int cont = 0;
                    for (int l = 0; l < Hm.getItemsets().get(i).getElementos().size(); l++) {
                        if (Hm.getItemsets().get(i).getElementos().get(l) == itemset.getElementos().get(m)) {
                            cont = cont + 1;
                        }
                    }
                    if (cont == 0) { // Si el elemento del itemset no se encuentra en el consecuente entonces se lo guarda como un antecedente
                        itemset2.AgregarElemento(itemset.getElementos().get(m)); // Se obtiene el antecedente de la regla
                    }
                }
                //Buscamos la frecuencia de la premisa o antecedente
                int tamanio = itemset2.getElementos().size();
                int frecuencia1 = 0;
                for (int z = 0; z < this.getConjunFrecuentes().size(); z++) {
                    if (this.getConjunFrecuentes().get(z).getK() == tamanio) {
                        for (int k = 0; k < this.getConjunFrecuentes().get(z).getItemsets().size(); k++) {
                            int cont = 0;
                            ItemSet itemset3 = this.getConjunFrecuentes().get(z).getItemsets().get(k);
                            for (int n = 0; n < itemset3.getElementos().size(); n++) {
                                for (int t = 0; t < itemset2.getElementos().size(); t++) {
                                    if (itemset3.getElementos().get(n) == itemset2.getElementos().get(t)) {
                                        cont = cont + 1;
                                        break;
                                    }
                                }
                            }
                            if (cont == itemset2.getElementos().size()) {
                                frecuencia1 = this.getConjunFrecuentes().get(z).getItemsets().get(k).getFrecuencia(); // Se guarda la frecuencia del antecedente
                            }
                        }
                    }
                }
                //Calculamos Confianza
                float confi = ((float) frecuencia / (float) frecuencia1) * 100;
                if (confi >= minconf) {
                    Regla regla = new Regla(); // Si pasa el umbral establecido se crea una nueva regla
                    regla.AgregarPremisas(itemset2.getElementos()); // Se cargan premisas
                    regla.AgregarConclusiones(Hm.getItemsets().get(i).getElementos()); // Se cargan conclusiones
                    regla.setConfianza((int) confi); // Se agrega la confianza obtenida
                    regla.setSoporte((int) ((frecuencia * 100) / this.getNumTransc())); //Se agrega el soporte obtenido
                    this.getConjunReglas().add(regla); //La nueva regla es cargada al conjunto de reglas 
                    Hmm.AgregarItemSet(Hm.getItemsets().get(i)); //Se agrega al H auxiliar el consecuente utilizado para generar la regla
                }
            } // Se prosige con otro consecuente
            this.apgenRules(itemset, Hmm); // De manera recursiva una vez recorrido todos los consecuente se llama a la función apgenRules con el itemset y el H m+1.
        }
    }

    public HConsecuente GenerarHm(HConsecuente Hm) { // Dicho metodo genera la combinación de los itemsets de la misma manera que GenerarCandidatoK
        HConsecuente Hmm = new HConsecuente(Hm.getIndice() + 1);
        if (Hm.getIndice() == 1) {
            for (int i = 0; i < Hm.getItemsets().size(); i++) {
                int pos = i + 1;
                for (int y = pos; y < Hm.getItemsets().size(); y++) {
                    ItemSet itemset = new ItemSet();
                    itemset.AgregarElemento(Hm.getItemsets().get(i).getElementos().get(0));
                    itemset.AgregarElemento(Hm.getItemsets().get(y).getElementos().get(0));
                    Hmm.AgregarItemSet(itemset);
                }
            }
        } else {
            for (int i = 0; i < Hm.getItemsets().size(); i++) {
                int pos = i + 1;
                for (int y = pos; y < Hm.getItemsets().size(); y++) {
                    boolean bandera = Hm.comparar(Hm.getItemsets().get(i), Hm.getItemsets().get(y), Hm.getIndice() - 1);
                    if (bandera == true) {
                        ItemSet itemset = new ItemSet();
                        for (int x = 0; x < Hm.getItemsets().get(i).getElementos().size(); x++) {
                            itemset.AgregarElemento(Hm.getItemsets().get(i).getElementos().get(x));
                        }
                        itemset.AgregarElemento(Hm.getItemsets().get(y).getElementos().get(Hm.getIndice() - 1));
                        Hmm.AgregarItemSet(itemset);
                    }
                }
            }
        }
        return Hmm; // Devuelve los itemsets combinados
    }

    // Metodo que obtiene la frecuencia de cada itemset del ultimo candidato y calcula el soporte para generar el Frecuente con itemsets de tamaño K
    public void GenerarFrecuenteK(int k) throws FileNotFoundException, IOException {
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        archivo = new File(this.getRutalFinal());
        fr = new FileReader(archivo);
        br = new BufferedReader(fr);
        Frecuente frecuente = new Frecuente(k); // Crea un nuevo frecuente 
        String linea;
        linea = br.readLine();//Lee la primera linea del archivo

        while (linea != null) {
            String[] cadena = linea.split(" ");
            for (int w = 0; w < this.getConjunCandidatos().get(k - 1).getItemsets().size(); w++) { //Recorre los itemsets del ultimo candidato
                ItemSet itemset = this.getConjunCandidatos().get(k - 1).getItemsets().get(w);
                int cont = 0;
                //Verifica que dicho itemset se encuentre en la transacción
                for (int x = 0; x < itemset.getElementos().size(); x++) {
                    int elem = itemset.getElementos().get(x);
                    int elemento = elem;
                    for (int t = 0; t < cadena.length; t++) {
                        int elemento2 = Integer.parseInt(cadena[t]);
                        if (elemento == elemento2) {
                            cont = cont + 1;
                            break;
                        }
                    }
                }
                if (cont == k) {
                    this.getConjunCandidatos().get(k - 1).getItemsets().get(w).SumarFrec(); // Si se encuentra en la transacción aumenta la frencuencia en un valor 1
                }
            }
            linea = br.readLine(); //Lee la siguiente transacción
        }
        
        // Recorre los itemsets del candidato y calcula el soporte
        for (int h = 0; h < this.getConjunCandidatos().get(k - 1).getItemsets().size(); h++) {
            int frec = this.getConjunCandidatos().get(k - 1).getItemsets().get(h).getFrecuencia(); // Obtiene la frecuencia del itemset
            float suport = (frec * 100) / (this.getNumTransc());// Calcula el soporte
            float minsup = (float) this.getMinSup(); // Obtiene el umbral indicado 
            if (suport >= minsup) {
                ItemSet item = this.getConjunCandidatos().get(k - 1).getItemsets().get(h);
                frecuente.agregarItemSet(item); // Si supera el soporte entonces el itemset es agregado al conjunto de itemsets del nuevo frecuente
            }
        }
        frecuente.ordenar();
        this.getConjunFrecuentes().add(frecuente); // Se agrega el nuevo frecuente a la lista de frecuentes de ObApriori
    }

    // Se genera el candidato numero 1
    public Integer ObtenerCantProduc() throws FileNotFoundException, IOException {
        Integer CantProductos = 0;
        Integer k = 1;
        Candidato Candidato = new Candidato(k); // Creación del nuevo candidato
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        try {
            // Apertura del fichero y creacion del BufferedReader para hacer una lectura comoda 
            archivo = new File(this.getRutalFinal());
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);
            String linea;
            linea = br.readLine(); // Lectura de la primera linea del archivo
            while ((linea) != null) {
                this.setNumTransc(this.getNumTransc() + 1); // Cuenta el numero de transacciones
                String[] cadena = linea.split(" ");

                for (int i = 0; i < cadena.length; i++) {
                    boolean existe = Candidato.Existe(cadena[i]); // Verifica si el candidato no tiene el producto ya cargado
                    if (existe == false) { //Si no lo tiene
                        CantProductos = CantProductos + 1; // Cuenta la candidad de productos
                        ItemSet itemset = new ItemSet(); // Se crea el itemset
                        itemset.AgregarElemento(Integer.parseInt(cadena[i]));  // Se carga el producto al nuevo itemset
                        Candidato.AgregarItemset(itemset); // Se agrega al candidato el itemset de tamaño 1
                    }
                }
                linea = br.readLine(); // Lectura de la siguiente linea del archivo
            }
        } catch (FileNotFoundException ex) {
            System.out.print("No encontro el archivo");
        }
        Candidato.ordenar();
        this.getConjunCandidatos().add(Candidato); // Se agrega el candidato a la lista de candidatos de ObApriori
        br.close();
        return CantProductos;
    }
}
