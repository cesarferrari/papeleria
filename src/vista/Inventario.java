/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Conexion;
/*import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;*/

/**
 *
 * @author julio
 */
public class Inventario extends javax.swing.JFrame {
Conexion cn= new Conexion();
   DefaultTableModel modelo;
    DefaultTableModel modelo1;
     DefaultTableModel modelo2;
   Cancelar_venta cancel= new Cancelar_venta();
   
    public Inventario() {
        initComponents();
        modelo=new DefaultTableModel();
         modelo1=new DefaultTableModel();
          modelo2=new DefaultTableModel();
        String columnas[]={"codigo","producto","descripcion","Stock"};
        String column[]={"codigo","entradas"};
         String column1[]={"codigo","salidas"};
        modelo.setColumnIdentifiers(columnas);
          modelo1.setColumnIdentifiers(column);
            modelo2.setColumnIdentifiers(column1);
    }
    
    public String fechaInicio(){
      
            String fecha=""; 
        try{
         
     java.util.Date date= new java.util.Date();
     SimpleDateFormat fr= new SimpleDateFormat("yyyy-MM-dd");
     fecha=fr.format(jdc_1.getDate());
       
    }catch(Exception e){
        JOptionPane.showMessageDialog(null,e);
    }
              return fecha;
    }
    public String fechaFin(){
                String fecha=""; 
        try{
         
     java.util.Date date= new java.util.Date();
     SimpleDateFormat fr= new SimpleDateFormat("yyyy-MM-dd");
     fecha=fr.format(jdc_2.getDate());
       
    }catch(Exception e){
        JOptionPane.showMessageDialog(null,e);
    }
              return fecha;
    }
 
       public String duplicado(){
          
        String code="";
        Conexion cn= new Conexion();
        Connection cnx=cn.conectar();
        try{
            Statement st=cnx.createStatement();
            ResultSet rs=st.executeQuery("select* from productos1");
            while (rs.next()) {
                if(this.txt_codigo.getText().equalsIgnoreCase(rs.getString("codigo"))){
                       code=rs.getString("codigo");
                }
             
            }
            
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }finally{
            try {
                cnx.close();
            } catch (SQLException ex) {
               JOptionPane.showMessageDialog(null,ex);
            }
        }
        
        return code;
    
       }
       public  List<String> codigo(){
        List<String>code= new ArrayList<>();
        Conexion cn= new Conexion();
        Connection cnx=cn.conectar();
        try{
            Statement st=cnx.createStatement();
            ResultSet rs=st.executeQuery("select* from productos1");
            while (rs.next()) {
                code.add(rs.getString("codigo"));
            }
            
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }finally{
            try {
                cnx.close();
            } catch (SQLException ex) {
               JOptionPane.showMessageDialog(null,ex);
            }
        }
        
        return code;
    } 
      public void mostrar_entradas(){
        Map<String,Integer>map1=new HashMap<>();
        map1.put(this.txt_codigo.getText(),0);
         
    try {
        Connection cnx=cn.conectar();
        PreparedStatement pst=cnx.prepareCall("select codigo_producto,sum(cantidad)as cantidad from detalle_compra where fecha between ? and ?   group by codigo_producto having codigo_producto=? ");
       pst.setString(1, fechaInicio());
        pst.setString(2, fechaFin());
        pst.setString(3, this.txt_codigo.getText());
        ResultSet rs=pst.executeQuery();
        Object numeros[]=new Object[2];
        while (rs.next()) {
          
          
           map1.put(rs.getString("codigo_producto"), rs.getInt("cantidad"));
          }
         for (Map.Entry<String,Integer> numero : map1.entrySet()) {
            numeros[0]=numero.getKey();
                    numeros[1]=numero.getValue();
                    modelo1.addRow(numeros);
        }
        this.tabla2.setModel(modelo1);
      
     
    } catch (SQLException ex) {
        Logger.getLogger(Inventario.class.getName()).log(Level.SEVERE, null, ex);
    }
      
  }
            public void mostrar_salidas(){
        Map<String,Integer>map2=new HashMap<>();
        map2.put(this.txt_codigo.getText(),0);
       
    try {
        Connection cnx=cn.conectar();
        PreparedStatement pst=cnx.prepareCall("select codigo_producto,sum(cantidad)as cantidad from detalle_venta where fecha between ? and ?   group by codigo_producto having codigo_producto=? ");
       pst.setString(1, fechaInicio());
       pst.setString(2, fechaFin());
       pst.setString(3,this.txt_codigo.getText());
        ResultSet rs=pst.executeQuery();
        Object numeros[]=new Object[2];
        while (rs.next()) {
          
          
            map2.put(rs.getString("codigo_producto"), rs.getInt("cantidad"));
         
           
         
          
        }
        for (Map.Entry<String,Integer> numero : map2.entrySet()) {
            numeros[0]=numero.getKey();
            numeros[1]=numero.getValue();
            modelo2.addRow(numeros);
        }
          
        this.tabla3.setModel(modelo2);
      
        //JOptionPane.showMessageDialog(null, fechaInicio()+"  "+fechaFin());
    } catch (SQLException ex) {
        Logger.getLogger(Inventario.class.getName()).log(Level.SEVERE, null, ex);
    }
      
  }
  public void mostrar_entradasAll(){
          List<String>arreglo= new ArrayList<>();
          Map<String,Integer>map1=new HashMap<>();
          for (int i = 0; i < codigo().size(); i++) {
              map1.put(codigo().get(i), 0);
          }
    try {
        Connection cnx=cn.conectar();
        PreparedStatement pst=cnx.prepareCall("select codigo_producto,sum(cantidad)as cantidad from detalle_compra where fecha between ? and ?  group by codigo_producto   ");
         pst.setString(1,fechaInicio());
      pst.setString(2, fechaFin());
        ResultSet rs=pst.executeQuery();
        Object numeros[]=new Object[2];
        while (rs.next()) {
           numeros[0]=rs.getString("codigo_producto");
            numeros[1]=rs.getString("cantidad");
          
           map1.put(rs.getString("codigo_producto"), rs.getInt("cantidad"));
         
           
         
         
        }
         for (Map.Entry<String,Integer> entrada : map1.entrySet()) {
             numeros[0]=entrada.getKey();
             numeros[1]=entrada.getValue();
             modelo1.addRow(numeros);
         }
         
        this.tabla2.setModel(modelo1);
      
   
    } catch (SQLException ex) {
        Logger.getLogger(Inventario.class.getName()).log(Level.SEVERE, null, ex);
    }
      
  }
            public void mostrar_salidasAll(){
          List<String>arreglo= new ArrayList<>();
          Map<String,Integer>map2=new HashMap<>();
          for (int i = 0; i < codigo().size(); i++) {
              map2.put(codigo().get(i), 0);
          }
    try {
        Connection cnx=cn.conectar();
        PreparedStatement pst=cnx.prepareCall("select codigo_producto,sum(cantidad)as cantidad from detalle_venta where fecha between ? and ?   group by codigo_producto");
     
      pst.setString(1,fechaInicio());
      pst.setString(2, fechaFin());
        ResultSet rs=pst.executeQuery();
        Object numeros[]=new Object[2];
        while (rs.next()) {
        
          
           map2.put(rs.getString("codigo_producto"), rs.getInt("cantidad"));
         
           
         
          
        }
          for (Map.Entry<String,Integer> entrada : map2.entrySet()) {
             numeros[0]=entrada.getKey();
             numeros[1]=entrada.getValue();
             modelo2.addRow(numeros);
         }
        this.tabla3.setModel(modelo2);
      
      
    } catch (SQLException ex) {
        Logger.getLogger(Inventario.class.getName()).log(Level.SEVERE, null, ex);
    }
      
  }
   public void limpia_tabla(){
        
        modelo.getDataVector().removeAllElements();
        this.tabla1.updateUI();
           modelo1.getDataVector().removeAllElements();
        this.tabla2.updateUI();
           modelo2.getDataVector().removeAllElements();
        this.tabla3.updateUI();
        this.txt_codigo.setText("");
    }
  public void mostrarUnico(){
    try {
        Connection cnx=cn.conectar();
        Statement st=cnx.createStatement();
        ResultSet rs=st.executeQuery("select*from productos1 where codigo="+this.txt_codigo.getText());
        Object numeros[]=new Object[4];
        while (rs.next()) {
            numeros[0]=rs.getString("codigo");
            numeros[1]=rs.getString("nombre");
            numeros[2]=rs.getString("descripcion");
          
            
            numeros[3]=rs.getString("stock");
            modelo.addRow(numeros);
        }
        this.tabla1.setModel(modelo);
      
    } catch (SQLException ex) {
        Logger.getLogger(Inventario.class.getName()).log(Level.SEVERE, null, ex);
    }
      
  }
    public void mostrarAll(){
        int seleccion=this.tabla2.getRowCount();
        for (int i = 0; i < seleccion; i++) {
               try {
        Connection cnx=cn.conectar();
        Statement st=cnx.createStatement();
        ResultSet rs=st.executeQuery("select*from productos1 where codigo='"+this.tabla2.getValueAt(i, 0).toString()+"'");
        Object numeros[]=new Object[4];
        while (rs.next()) {
            numeros[0]=rs.getString("codigo");
            numeros[1]=rs.getString("nombre");
            numeros[2]=rs.getString("descripcion");
          
            
            numeros[3]=rs.getString("stock");
            modelo.addRow(numeros);
        }
        this.tabla1.setModel(modelo);
       
    } catch (SQLException ex) {
        Logger.getLogger(Inventario.class.getName()).log(Level.SEVERE, null, ex);
    }
        }
      
  }
   
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txt_codigo = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla2 = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabla3 = new javax.swing.JTable();
        btn_clean = new javax.swing.JButton();
        jdc_2 = new com.toedter.calendar.JDateChooser();
        jdc_1 = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 204, 204));

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));
        jPanel2.setForeground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Verdana", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("INVENTARIO PRODUCTOS EXISTENTES");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(241, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel2.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel2.setText("Codigo Producto");

        txt_codigo.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N

        jButton1.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jButton1.setText("Buscar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jButton2.setText("Todos los Productos");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jButton3.setText("Generar Reporte");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        tabla1.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jScrollPane1.setViewportView(tabla1);

        tabla2.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jScrollPane2.setViewportView(tabla2);

        tabla3.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jScrollPane3.setViewportView(tabla3);

        btn_clean.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        btn_clean.setText("Nuevo");
        btn_clean.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cleanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_clean, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jdc_1, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jdc_2, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txt_codigo, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(28, 28, 28)
                        .addComponent(jScrollPane1)
                        .addGap(47, 47, 47)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(43, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jdc_2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jdc_1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(87, 87, 87)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_codigo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(85, 85, 85)
                        .addComponent(btn_clean, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(80, 80, 80)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 95, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(77, 77, 77)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2)))
                .addGap(154, 154, 154))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (this.txt_codigo.getText().equalsIgnoreCase("")) {
            JOptionPane.showMessageDialog(null, "seleccione un codigo de producto");
        }else if(fechaInicio().equalsIgnoreCase("")||fechaFin().equalsIgnoreCase("")){
             JOptionPane.showMessageDialog(null, "seleccione un rango de fechas");
        }else if(duplicado().equalsIgnoreCase("")){
            JOptionPane.showMessageDialog(null,"codigo inexistente");
        }else{
             
        mostrarUnico();
        mostrar_entradas();
        mostrar_salidas();
        }
       
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
          if(fechaInicio().equalsIgnoreCase("")||fechaFin().equalsIgnoreCase("")){
             JOptionPane.showMessageDialog(null, "seleccione un rango de fechas");
        }else{
            limpia_tabla();
      mostrar_entradasAll();
      mostrar_salidasAll();
       mostrarAll();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btn_cleanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cleanActionPerformed
          limpia_tabla();
    }//GEN-LAST:event_btn_cleanActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
     /*try {
        Connection cnx=cn.conectar();
      JasperReport report=null;
      String path="E:\\Documentos\\NetBeansProjects\\papel\\src\\jasper\\ticketVenta.jasper";
    
        report=(JasperReport) JRLoader.loadObjectFromFile(path);
        JasperPrint jprint=JasperFillManager.fillReport(report,null,cnx);
        JasperViewer view = new JasperViewer(jprint,false);
        view.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        view.setVisible(true);
    } catch (JRException ex) {
        Logger.getLogger(Inventario.class.getName()).log(Level.SEVERE, null, ex);
    }*/
    }//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(Inventario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Inventario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Inventario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Inventario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Inventario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_clean;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private com.toedter.calendar.JDateChooser jdc_1;
    private com.toedter.calendar.JDateChooser jdc_2;
    private javax.swing.JTable tabla1;
    private javax.swing.JTable tabla2;
    private javax.swing.JTable tabla3;
    private javax.swing.JTextField txt_codigo;
    // End of variables declaration//GEN-END:variables
}
