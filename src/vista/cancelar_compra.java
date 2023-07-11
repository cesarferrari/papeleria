/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

package vista;

import java.util.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Conexion;

/**
 *
 * @author julio
 */
public class cancelar_compra extends javax.swing.JFrame {

    DefaultTableModel modelo;
   // List<String>llenado= new ArrayList<>();
    //List<String>cantidad= new ArrayList<>();
    String[]llenado;
    String []cantidad;
    public cancelar_compra() {
        initComponents();
        modelo= new DefaultTableModel();
        
      
       
    }
 
    public void fill(){
          String []llenado=new String[codigos(codigo_compra()).size()];
             String []cantidad=new String[codigosConteo(codigo_compra()).size()];
      for (int i = 0; i < llenado.length; i++) {
         llenado[i]= codigos(codigo_compra()).get(i);
          cantidad[i]= codigosConteo(codigo_compra()).get(i);
      }
        for (int i = 0; i < llenado.length; i++) {
            System.out.println(llenado[i]);
               System.out.println(cantidad[i]);
        }
    }

  public void llenado(){
      List<String>arrProducto= new ArrayList<>();
      List<String>arrDescripcion= new ArrayList<>();
      List<String>arrPrecio= new ArrayList<>();
      List<String>arrCode= new ArrayList<>();
     //  llenado.addAll(codigos(codigo_compra()));
      // cantidad.addAll(codigosConteo(codigo_compra()));
      //  System.out.println(llenado);
       String []llenado=new String[codigos(codigo_compra()).size()];
             String []cantidad=new String[codigosConteo(codigo_compra()).size()];
      for (int i = 0; i < llenado.length; i++) {
         llenado[i]= codigos(codigo_compra()).get(i);
          cantidad[i]= codigosConteo(codigo_compra()).get(i);
      }
        String []titulos={"codigo","descripcion","precio unitario","producto","cantidad"};
        modelo.setColumnIdentifiers(titulos);
        Conexion conex= new Conexion();
        Connection cnx=conex.conectar();
        for (int i = 0; i < llenado.length; i++) {
          try{
              Statement st=cnx.createStatement();
              ResultSet rs=st.executeQuery("select nombre,descripcion,precio,codigo from productos1 where codigo="+cantidad[i]);
              while (rs.next()) {
                  arrProducto.add(rs.getString("nombre"));
                    arrDescripcion.add(rs.getString("descripcion"));
                      arrPrecio.add(rs.getString("precio"));
                      arrCode.add(rs.getString("codigo"));
              }
          
              
          }catch(Exception e){
              
          } 
      }
  
            String []numeros= new String[5];
             
                  for (int i = 0; i <llenado.length; i++) {
                   numeros[3]=arrProducto.get(i);
                   numeros[1]=arrDescripcion.get(i);
                    numeros[2]=arrPrecio.get(i);
                    numeros[0]=arrCode.get(i);
                   numeros[4]=llenado[i];
                           modelo.addRow(numeros);
      }
                  this.Tabla.setModel(modelo);
              
        
        
        
  }
   public void limpia_tabla(){
        
        modelo.getDataVector().removeAllElements();
        this.Tabla.updateUI();
    }
   public String codigo_compra(){
       String code="";
       Conexion c= new Conexion();
       Connection cnx=c.conectar();
       try{
           Statement st=cnx.createStatement();
           ResultSet rs=st.executeQuery("select codigo,productos from compra where codigo="+this.txt_codigo.getText());
           while (rs.next()) {
               code=rs.getString("productos");
           }
       }catch(Exception e){
           
       }
               
       
       return code; 
   }
   public List<String>codigos(String codigo){
       List<String>numerados= new ArrayList();
        List<String>nones= new ArrayList();
       String []arreglo_split=codigo.split("/");
       for (int i = 0; i < arreglo_split.length; i++) {
           if (i%4==2) {
                 numerados.add(arreglo_split[i]);
           }
              }
         return numerados;
       }
           
            public List<String>codigosConteo(String codigo){
       List<String>Conteo= new ArrayList();
        List<String>nones= new ArrayList();
       String []arreglo_split=codigo.split("/");
       for (int i = 0; i < arreglo_split.length; i++) {
           if (i%4==1) {
                 Conteo.add(arreglo_split[i]);
           }
              }
         return Conteo;
       }
           public void updateSingle(){
                Conexion cn= new Conexion();
      Connection cnx=cn.conectar();
      try{
         
          PreparedStatement pst=cnx.prepareStatement("update productos1 set stock=stock+"+this.txt_cantidad.getText()+" where codigo="+this.txt_code.getText());
              pst.execute();
      }catch(Exception e){
          JOptionPane.showMessageDialog(null, e);
      }
           }
              public void updateVenta(){
                Conexion cn= new Conexion();
      Connection cnx=cn.conectar();
      //int totales=this.Tabla.getRowCount();
                 
                       try{
         
          PreparedStatement pst=cnx.prepareStatement("update compra set productos='"+productoCancelado()+"' where codigo="+this.txt_codigo.getText());
          
              pst.execute();
      }catch(Exception e){
          JOptionPane.showMessageDialog(null, e);
      }
                  
           }
                public void updateSingleCiclo(){
                Conexion cn= new Conexion();
      Connection cnx=cn.conectar();
      int totales=this.Tabla.getRowCount();
                    for (int i = 0; i < totales; i++) {
                         try{
         
          PreparedStatement pst=cnx.prepareStatement("update productos1 set stock=stock+"+this.Tabla.getValueAt(i,4)+" where codigo="+this.Tabla.getValueAt(i,0));
              pst.execute();
      }catch(Exception e){
          JOptionPane.showMessageDialog(null, e);
      }
                    }
           }
              public void updateVentaCiclo(){
                Conexion cn= new Conexion();
      Connection cnx=cn.conectar();
      int totales=this.Tabla.getRowCount();
                
                        try{
         
          PreparedStatement pst=cnx.prepareStatement("update compra set productos='"+productosCanceladoCiclo()+"' where codigo="+this.txt_codigo.getText());
          
              pst.execute();
      }catch(Exception e){
          JOptionPane.showMessageDialog(null, e);
      }
                  }
           
         public String productoCancelado(){
               String cancelado="";
               int seleccion=this.Tabla.getSelectedRow();
             
               int sel=this.Tabla.getRowCount();
              for (int i = 0; i <sel ; i++) {
                  if(i!=seleccion){
                      cancelado=cancelado+"/"+this.Tabla.getValueAt(i,0).toString()+"/"+this.Tabla.getValueAt(i,4).toString()+"/"
                              +this.Tabla.getValueAt(i,3).toString()+"/"+this.Tabla.getValueAt(i,2).toString()+"!";
                  }
                      if(i==seleccion){
                          cancelado=cancelado+"/"+this.Tabla.getValueAt(seleccion, 0).toString()+"/ 0/ cancelado/ 0.00!";
                      }
                  
             }
               return cancelado;
           }
         public String productosCanceladoCiclo(){
             String cancelado="";
             int totales=this.Tabla.getRowCount();
             for (int i = 0; i < totales; i++) {
                  cancelado=cancelado+"/"+this.Tabla.getValueAt(i,0).toString()+"/ 0/ cancelado/ 0.00!";
             }
             return  cancelado;
         }
           
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txt_codigo = new javax.swing.JTextField();
        btn_buscar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        Tabla = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        btn_single = new javax.swing.JButton();
        btn_all = new javax.swing.JButton();
        txt_code = new javax.swing.JTextField();
        txt_cantidad = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(153, 255, 204));

        jLabel1.setFont(new java.awt.Font("Rockwell", 1, 18)); // NOI18N
        jLabel1.setText("Cancelar Compra");

        btn_buscar.setText("buscar");
        btn_buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_buscarActionPerformed(evt);
            }
        });

        Tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(Tabla);

        jLabel2.setFont(new java.awt.Font("Rockwell Extra Bold", 1, 14)); // NOI18N
        jLabel2.setText("Codigo Compra");

        btn_single.setText("cancelar articulo");
        btn_single.setEnabled(false);
        btn_single.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_singleActionPerformed(evt);
            }
        });

        btn_all.setText("cancelar toda la compra");
        btn_all.setEnabled(false);
        btn_all.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_allActionPerformed(evt);
            }
        });

        txt_code.setEditable(false);

        txt_cantidad.setEditable(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(402, 402, 402)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(140, 140, 140)
                        .addComponent(jLabel2)
                        .addGap(42, 42, 42)
                        .addComponent(txt_codigo, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(85, 85, 85)
                        .addComponent(btn_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 712, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btn_single, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                            .addComponent(btn_all, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                            .addComponent(txt_cantidad)
                            .addComponent(txt_code))))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_codigo, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 417, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txt_code, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(txt_cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(81, 81, 81)
                        .addComponent(btn_single, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)
                        .addComponent(btn_all, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_buscarActionPerformed
    //    System.out.println(codigosConteo(codigo_compra()));
      //  System.out.println(codigos(codigo_compra()));
      limpia_tabla();
     
      btn_all.setEnabled(true);
      btn_single.setEnabled(true);
         llenado();
       
    }//GEN-LAST:event_btn_buscarActionPerformed

    private void TablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablaMouseClicked
      int seleccion=this.Tabla.getSelectedRow();
      
      
       this.txt_code.setText(this.Tabla.getValueAt(seleccion,0).toString());
       this.txt_cantidad.setText(this.Tabla.getValueAt(seleccion,4).toString());
    }//GEN-LAST:event_TablaMouseClicked

    private void btn_singleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_singleActionPerformed
     int jp=JOptionPane.showConfirmDialog(this,"desea cancelar la venta del producto seleccionado?","confirmar",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
     if(jp==JOptionPane.YES_OPTION){
        updateSingle();
         updateVenta();
       // JOptionPane.showMessageDialog(null,productoCancelado());
     }else if(jp==JOptionPane.NO_OPTION){
         JOptionPane.showMessageDialog(null,"ok continuar");
     }
    }//GEN-LAST:event_btn_singleActionPerformed

    private void btn_allActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_allActionPerformed
      int jp=JOptionPane.showConfirmDialog(this,"desea cancelar la venta de todos los productos?","confirmar",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
     if(jp==JOptionPane.YES_OPTION){
         updateSingleCiclo();
        updateVentaCiclo();
     }else if(jp==JOptionPane.NO_OPTION){
         JOptionPane.showMessageDialog(null,"ok continuar");
     }
    }//GEN-LAST:event_btn_allActionPerformed

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(cancelar_compra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(cancelar_compra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(cancelar_compra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(cancelar_compra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new cancelar_compra().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Tabla;
    private javax.swing.JButton btn_all;
    private javax.swing.JButton btn_buscar;
    private javax.swing.JButton btn_single;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txt_cantidad;
    private javax.swing.JTextField txt_code;
    private javax.swing.JTextField txt_codigo;
    // End of variables declaration//GEN-END:variables

}
