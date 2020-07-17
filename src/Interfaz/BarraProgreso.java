
package Interfaz;

import Algoritmo.Apriori;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;


public class BarraProgreso extends javax.swing.JFrame {

    private int x=0;
    private Apriori ObApriori; // Objeto Apriori
    private String MinConf; // Umbral de confianza
    private String MinSup; // Umbral de soporte
    private String RutaFinal; // Ruta del archivo
    private double tiempo; // Tiempo de procesamiento

    // Getters y Setters de los atributos
    public String getMinConf() {
        return MinConf;
    }

    public double getTiempo() {
        return tiempo;
    }

    public void setTiempo(double tiempo) {
        this.tiempo = tiempo;
    }

        public void setMinConf(String MinConf) {
        this.MinConf = MinConf;
    }

    public Apriori getObApriori() {
        return ObApriori;
    }

    public void setObApriori(Apriori ObApriori) {
        this.ObApriori = ObApriori;
    }

    public String getMinSup() {
        return MinSup;
    }

    public void setMinSup(String MinSup) {
        this.MinSup = MinSup;
    }

    public String getRutaFinal() {
        return RutaFinal;
    }

    public void setRutaFinal(String RutaFinal) {
        this.RutaFinal = RutaFinal;
    }
    
    // Constructor de clase
    public BarraProgreso() throws FileNotFoundException, IOException {
        initComponents();
        setIconImage(new ImageIcon(getClass().getResource("/Imagenes/icons-180x180.png")).getImage());
        this.setLocationRelativeTo(null);
        this.setSize(jPanel1.getSize());
        this.setResizable(false);
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel5 = new javax.swing.JLabel();
        jDialog1 = new javax.swing.JDialog();
        jDialog2 = new javax.swing.JDialog();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jProgressBar1 = new javax.swing.JProgressBar();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        jLabel5.setText("jLabel5");

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jDialog2Layout = new javax.swing.GroupLayout(jDialog2.getContentPane());
        jDialog2.getContentPane().setLayout(jDialog2Layout);
        jDialog2Layout.setHorizontalGroup(
            jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog2Layout.setVerticalGroup(
            jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(153, 153, 153));

        jPanel1.setBackground(new java.awt.Color(0, 53, 100));
        jPanel1.setLayout(null);

        jLabel2.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(jLabel2);
        jLabel2.setBounds(92, 113, 398, 39);

        jLabel7.setFont(new java.awt.Font("Calisto MT", 0, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(jLabel7);
        jLabel7.setBounds(180, 170, 92, 31);

        jLabel8.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(jLabel8);
        jLabel8.setBounds(410, 170, 84, 31);

        jLabel3.setFont(new java.awt.Font("Century Gothic", 1, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Algoritmo A priori");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(104, 4, 320, 45);

        jLabel4.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Frecuente: ");
        jLabel4.setToolTipText("Ultimo frecuente generado");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(50, 170, 129, 31);

        jProgressBar1.setBackground(new java.awt.Color(0, 53, 100));
        jProgressBar1.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jProgressBar1.setForeground(new java.awt.Color(0, 53, 100));
        jProgressBar1.setToolTipText("");
        jProgressBar1.setStringPainted(true);
        jPanel1.add(jProgressBar1);
        jProgressBar1.setBounds(20, 60, 491, 46);

        jLabel6.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("ItemSets:");
        jLabel6.setToolTipText("Numero de itemsets del ultimo frecuente generado");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(290, 170, 104, 31);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/hdblue.jpg"))); // NOI18N
        jLabel1.setText("jLabel1");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(0, 0, 580, 270);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 541, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 8, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Unico metodo de la clase , que lleva a cabo la ejecución del algoritmo a priori
    public void GeneracionItemsCandidatos ()  throws IOException { 
       Integer CantidadProd;       
       Integer MiniCon= Integer.parseInt(this.getMinConf());
       Integer MiniSup= Integer.parseInt(this.getMinSup());
        double tiempoInicio;
        double tiempototal;
        tiempoInicio= System.currentTimeMillis(); // Tiempo en que inicia el procesamiento
        ObApriori= new Apriori(this.getRutaFinal(),MiniCon,MiniSup); // Se crea el objeto a priori que contiene los metodos principales del algoritmo (GenerarCandidato, GenerarFrecuente, Generar Reglas)
        CantidadProd = ObApriori.ObtenerCantProduc(); // Se obtiene la cantidad de productos del dataset junto con el candidato numero 1
        ObApriori.GenerarFrecuenteUno(); // Se obtiene el Frecuente numero 1 , contabilizando las frecuencias de cada itemset del candidato numero 1 y calculando su soporte
        jLabel2.setText("  Generando Frecuentes...");
        jLabel7.setText("1");
        jLabel8.setText(Integer.toString(ObApriori.getConjunFrecuentes().get(0).getItemsets().size()));
        int x=1;
        jProgressBar1.setMaximum(CantidadProd);
        for (int k=2; k<= CantidadProd;k++){ // Inicia el proceso iterativo para calcular los candidatos y frecuente con itemsets de tamaño K
            jProgressBar1.setValue(x);
                   if (ObApriori.getConjunFrecuentes().get(0).getItemsets().size()>1){
                       if (ObApriori.getConjunFrecuentes().get(ObApriori.getConjunFrecuentes().size()-1).getItemsets().size()>1){ // Control de si el ultimo frecuente tiene mas de un itemset
                           ObApriori.GenerarCandidatoK(k); // Se genera el candidato con itemsets de tamaño K
                           try {
                               ObApriori.GenerarFrecuenteK(k); // Se genera el frecuente con itemsets de tamaño K                      
                           } catch (IOException ex) {
                               Logger.getLogger(BarraProgreso.class.getName()).log(Level.SEVERE, null, ex);
                           } 
                jLabel7.setText(Integer.toString(k));
                jLabel8.setText(Integer.toString(ObApriori.getConjunFrecuentes().get(ObApriori.getConjunFrecuentes().size()-1).getItemsets().size()));
                       }
                   }
               x=x+1; 
                
        }  
                //Generamos las Reglas 
                jLabel2.setText("Generando Reglas...");
                if (ObApriori.getConjunFrecuentes().size()>1){
                    ObApriori.GenerarReglas(); // Se generan las reglas utilizando todos los frecuentes
                } 
                jProgressBar1.setValue(CantidadProd); 
                tiempototal= System.currentTimeMillis() - tiempoInicio; // Se obtiene el tiempo de procesamiento
                this.setTiempo(tiempototal/1000);          
   }

 public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(BarraProgreso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BarraProgreso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BarraProgreso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BarraProgreso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new BarraProgreso().setVisible(true);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(BarraProgreso.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(BarraProgreso.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog jDialog1;
    private javax.swing.JDialog jDialog2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JProgressBar jProgressBar1;
    // End of variables declaration//GEN-END:variables
}
