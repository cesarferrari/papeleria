
package vista;

import com.mxrck.autocompleter.TextAutoCompleter;
import static java.awt.event.KeyEvent.VK_ENTER;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import modelo.Conexion;


public class Verificar_producto extends javax.swing.JFrame {

    
    TextAutoCompleter ac;
    public Verificar_producto() {
        initComponents();
        radio_codigo.setSelected(true);
    }

  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        txt_producto = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        radio_codigo = new javax.swing.JRadioButton();
        radio_producto = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("verificar producto");
        setBackground(new java.awt.Color(0, 255, 0));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 0));

        txt_producto.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        txt_producto.setForeground(new java.awt.Color(0, 0, 0));
        txt_producto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txt_productoMouseEntered(evt);
            }
        });
        txt_producto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_productoKeyTyped(evt);
            }
        });

        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jButton1.setText("producto");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        buttonGroup1.add(radio_codigo);
        radio_codigo.setText("codigo de barras");

        buttonGroup1.add(radio_producto);
        radio_producto.setText("producto");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(radio_codigo)
                                .addGap(52, 52, 52)
                                .addComponent(radio_producto))
                            .addComponent(txt_producto, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addGap(38, 38, 38))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radio_codigo)
                    .addComponent(radio_producto))
                .addGap(8, 8, 8)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_producto, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(47, 47, 47)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
public void limpiar(){
    this.jTextArea1.setText("");
}
public String radioEleccion(){
   String cons="";
   
    if (this.radio_codigo.isSelected()) {
             cons="select  nombre,descripcion,proveedor,stock,precioCompra,precio,categoria from productos1 where codigo="+this.txt_producto.getText();
            
            this.txt_producto.setText("");
             return cons;

        }else if(this.radio_producto.isSelected()){
             cons="select  nombre,descripcion,proveedor,stock,precioCompra,precio,categoria from productos1 where nombre='"+this.txt_producto.getText()+"'";
            
            this.txt_producto.setText("");
             return cons;
        }
   return cons;
}
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
      productos();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txt_productoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_productoMouseEntered
              ac=new TextAutoCompleter(txt_producto);
             Conexion con = new Conexion();
       Connection cnx=con.conectar();
       String sql="select id,nombre,descripcion,stock,precio,precioCompra,codigo  from productos1";
       try{
           Statement st =cnx.createStatement();
           ResultSet rs =st.executeQuery(sql);
           String numero[]= new String[1];
          
          
           while (rs.next()) {
             
              ac.addItem(rs.getString("nombre"));
         
              
           }
           cnx.close();
       }catch(Exception e){
           JOptionPane.showMessageDialog(null, e);
       }
        
        limpiar();
       
    }//GEN-LAST:event_txt_productoMouseEntered

    private void txt_productoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_productoKeyTyped
        if (evt.getKeyChar()==VK_ENTER) {
           
   
     productos();
     
            //JOptionPane.showMessageDialog(null, "tecla enter ");
        } 
    }//GEN-LAST:event_txt_productoKeyTyped

   
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
            java.util.logging.Logger.getLogger(Verificar_producto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Verificar_producto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Verificar_producto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Verificar_producto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Verificar_producto().setVisible(true);
            }
        });
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JRadioButton radio_codigo;
    private javax.swing.JRadioButton radio_producto;
    private javax.swing.JTextField txt_producto;
    // End of variables declaration//GEN-END:variables

    private void productos() {
         Conexion cn = new Conexion();
       Connection cnx =cn.conectar();
     //  int num =Integer.parseInt(this.txt_producto.getText());
       this.jTextArea1.setText("");
       String sql=radioEleccion();
       try{
           Statement st =cnx.createStatement();
           ResultSet rs =st.executeQuery(sql);
          String numeros[]=new String[7];
           while(rs.next()) {
               
           numeros[0] =  rs.getString("nombre");
                numeros[1]=rs.getString("descripcion");
                 numeros[2]=rs.getString("proveedor");
                 numeros[3]= rs.getString("stock");
                    numeros[4]=rs.getString("precioCompra");
                    numeros[5]=   rs.getString("categoria");
                       numeros[6]=rs.getString("precio");
               this.jTextArea1.append("producto : "+numeros[0]+"\n\ndescripcion : "+numeros[1]+"\n\nproveedor : "+numeros[2]+"\n\nstock : "
                       +numeros[3]+"\n\nprecio Venta : "+numeros[6]);
               this.txt_producto.setText("");
           }
           
          
           
           
           
       }catch(Exception e){
           JOptionPane.showMessageDialog(null,e);
           
       }
        
        
        this.jTextArea1.append(this.txt_producto.getText());
    }
}
