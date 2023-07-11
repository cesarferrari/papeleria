/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.awt.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.*;
public class Categoria extends javax.swing.JFrame {

  Conexion con= new Conexion();
  Statement st;
  ResultSet rs;
  Connection cnx;
  DefaultTableModel model;
  String sql="";
    
    public Categoria() {
        initComponents();
    }

    public  void radioClientesId(String consulta){
     
      //  sql="select id_cliente,nombre,identificacion,direccion,telefono from clientes where id_cliente=1";
        model= new DefaultTableModel();
        String titulos[]={"idcategoria","categoria"};
        model.setColumnIdentifiers(titulos);
        cnx=con.conectar();
        try{
       // st=cnx.createStatement();
       PreparedStatement pst= cnx.prepareStatement(consulta);
        rs=pst.executeQuery();
        String num[]=new String[5];
            while (rs.next()) {
               num[0]= rs.getString("idcategoria");
                num[1]=rs.getString("categoria");
             
                model.addRow(num);
            }
            this.jTable2.setModel(model);
            cnx.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
           
        }
    }
     public  void radioClientes(String consulta){
     
      //  sql="select id_cliente,nombre,identificacion,direccion,telefono from clientes where id_cliente=1";
        model= new DefaultTableModel();
        String titulos[]={"idcategoria","categoria"};
        model.setColumnIdentifiers(titulos);
        cnx=con.conectar();
        try{
       // st=cnx.createStatement();
       PreparedStatement pst= cnx.prepareStatement(consulta);
        rs=pst.executeQuery();
        String num[]=new String[5];
            while (rs.next()) {
               num[0]= rs.getString("idcategoria");
                num[1]=rs.getString("categoria");
             
                model.addRow(num);
            }
            this.jTable2.setModel(model);
            cnx.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
           
        }
    }
    public void limpiarcliente(){
        this.txt_id.setText(null);
      this.txt_categoria.setText("");
        
        
    }
    public void modificar(){
        int seleccion=this.jTable2.getSelectedRow();
        String select=this.jTable2.getValueAt(seleccion,0).toString();
          String select1=this.jTable2.getValueAt(seleccion,1).toString();
          sql="update categoria set categoria='"+this.txt_seleccion.getText()+"' where idcategoria="+select ;
                cnx=con.conectar();
               try{
               PreparedStatement pst=cnx.prepareStatement(sql);
             
              
               
               pst.executeUpdate();
                   JOptionPane.showMessageDialog(null,"datos actualizados correctamente");
               
               }catch(Exception e){
                   JOptionPane.showMessageDialog(null, e);
                   System.out.println(e.toString());
               }
    }
           public void insertarCategoria(){
             sql="insert into categoria(idcategoria,categoria)values(?,?)";
                cnx=con.conectar();
               try{
               PreparedStatement pst=cnx.prepareStatement(sql);
               pst.setString(1, this.txt_id.getText());
               pst.setString(2,this.txt_categoria.getText());
               
              if(this.txt_categoria.getText().isEmpty()){
                  JOptionPane.showMessageDialog(null,"indica una categoria");
              }else{
                     int in=pst.executeUpdate();
                       JOptionPane.showMessageDialog(null,"categoria ingresada ");
           ArrayList<Integer> numeros= new ArrayList<>();
            try {
            Connection conexion =con.conectar();
            PreparedStatement pst1 =conexion.prepareStatement("select idproveedor from proveedores");
            ResultSet rs1 =pst1.executeQuery();
           
               while (rs1.next()) {
                   numeros.add(Integer.parseInt(rs1.getString("idproveedor")));
                   
                }
                
                 in =numeros.size()+1;
                
           
        } catch (Exception ex) {
           
        }
      
               
              
                String identificador=String.valueOf(in);
                 this.txt_id.setText(identificador);
                this.txt_id.setEditable(false);
              }
               
               }catch(Exception e){
                   JOptionPane.showMessageDialog(null, e);
                   System.out.println(e.toString());
               }
           }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grupo1 = new javax.swing.ButtonGroup();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        btn_nuevo = new javax.swing.JButton();
        btn_guardar = new javax.swing.JButton();
        btn_modificar = new javax.swing.JButton();
        btn_cancelar = new javax.swing.JButton();
        btn_salir = new javax.swing.JButton();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel8 = new javax.swing.JPanel();
        radio_id = new javax.swing.JRadioButton();
        radio_categoria = new javax.swing.JRadioButton();
        txt_seleccion = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        btn_buscar = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txt_id = new javax.swing.JTextField();
        txt_categoria = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Clientes");
        setResizable(false);

        jPanel5.setBackground(new java.awt.Color(153, 255, 153));

        btn_nuevo.setBackground(new java.awt.Color(255, 255, 204));
        btn_nuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Img/nuevo.png"))); // NOI18N
        btn_nuevo.setText("Nuevo");
        btn_nuevo.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_nuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_nuevoActionPerformed(evt);
            }
        });

        btn_guardar.setBackground(new java.awt.Color(255, 255, 204));
        btn_guardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Img/GuardarTodo.png"))); // NOI18N
        btn_guardar.setText("Guardar");
        btn_guardar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_guardar.setEnabled(false);
        btn_guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_guardarActionPerformed(evt);
            }
        });

        btn_modificar.setBackground(new java.awt.Color(255, 255, 204));
        btn_modificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Img/report.png"))); // NOI18N
        btn_modificar.setText("Modificar");
        btn_modificar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_modificar.setEnabled(false);
        btn_modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_modificarActionPerformed(evt);
            }
        });

        btn_cancelar.setBackground(new java.awt.Color(255, 255, 204));
        btn_cancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Img/eliminar.png"))); // NOI18N
        btn_cancelar.setText("Cancelar");
        btn_cancelar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelarActionPerformed(evt);
            }
        });

        btn_salir.setBackground(new java.awt.Color(255, 255, 204));
        btn_salir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Img/salir_1.png"))); // NOI18N
        btn_salir.setText("Salir");
        btn_salir.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_salirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btn_nuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btn_guardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btn_modificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btn_cancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btn_salir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(btn_nuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_guardar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_modificar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_salir, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel8.setBackground(new java.awt.Color(102, 204, 255));

        grupo1.add(radio_id);
        radio_id.setText("id");

        grupo1.add(radio_categoria);
        radio_categoria.setText("categoria");

        jTable2=new javax.swing.JTable(){
            public boolean isCellEditable(int r,int c){
                return false;
            }
        };
        jTable2.setFocusable(false);
        jTable2.getTableHeader().setReorderingAllowed(false);
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable2);

        btn_buscar.setText("buscar");
        btn_buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_buscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 582, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_seleccion, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(radio_id)
                        .addGap(102, 102, 102)
                        .addComponent(radio_categoria)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(radio_id)
                            .addComponent(radio_categoria))
                        .addGap(18, 18, 18)
                        .addComponent(txt_seleccion, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(btn_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(60, 60, 60)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 413, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("nuevo/modificar", jPanel8);

        jPanel7.setBackground(new java.awt.Color(102, 204, 255));

        jLabel3.setText("id");

        jLabel4.setText("categoria");

        txt_id.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txt_id.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txt_idMouseEntered(evt);
            }
        });

        txt_categoria.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addGap(43, 43, 43)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txt_categoria, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
                    .addComponent(txt_id))
                .addContainerGap(302, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txt_categoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17))
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(454, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("buscar", jPanel7);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 594, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guardarActionPerformed
       insertarCategoria();
       limpiarcliente();
    }//GEN-LAST:event_btn_guardarActionPerformed

    private void btn_nuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_nuevoActionPerformed
       this.btn_modificar.setEnabled(true);
       this.btn_guardar.setEnabled(true);
       this.jTabbedPane2.setSelectedIndex(1);
    }//GEN-LAST:event_btn_nuevoActionPerformed

    private void btn_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelarActionPerformed
      this.txt_categoria.setText("");
      this.txt_id.setText("");
      grupo1.clearSelection();
        model.getDataVector().removeAllElements();
        this.jTable2.updateUI();
    }//GEN-LAST:event_btn_cancelarActionPerformed

    private void btn_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_salirActionPerformed
        dispose();
    }//GEN-LAST:event_btn_salirActionPerformed

    private void btn_buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_buscarActionPerformed
       if (this.radio_id.isSelected()) {
           String cons="select *from categoria where idcategoria="+this.txt_seleccion.getText();
            radioClientesId(cons);
            this.txt_seleccion.setText("");
            
        }else if(this.radio_categoria.isSelected()){
             String cons="select * from categoria where categoria='"+this.txt_seleccion.getText()+"'";
            radioClientesId(cons);
            this.txt_seleccion.setText("");
        }else {
             String cons="select * from categoria ";
            radioClientes(cons);
            this.txt_seleccion.setText("");
        }
    }//GEN-LAST:event_btn_buscarActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
      int code =this.jTable2.getSelectedRow();
      String id=this.jTable2.getValueAt(code, 0).toString();
      String categoria=this.jTable2.getValueAt(code, 1).toString();
      
      this.txt_id.setText(id);
      this.txt_categoria.setText(categoria);
              
    }//GEN-LAST:event_jTable2MouseClicked

    private void txt_idMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_idMouseEntered
           int in=0;
           ArrayList<Integer> numeros= new ArrayList<>();
            try {
            Connection conexion =con.conectar();
            PreparedStatement pst1 =conexion.prepareStatement("select idcategoria from categoria");
            ResultSet rs1 =pst1.executeQuery();
           
               while (rs1.next()) {
                   numeros.add(Integer.parseInt(rs1.getString("idcategoria")));
                   
                }
                
                 in =numeros.get(numeros.size()-1)+1;
                
           
        } catch (Exception ex) {
           
        }
      
               
              
                String identificador=String.valueOf(in);
                 this.txt_id.setText(identificador);
                this.txt_id.setEditable(false);
    }//GEN-LAST:event_txt_idMouseEntered

    private void btn_modificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_modificarActionPerformed
       Connection cnx=con.conectar();
       
       try{
           PreparedStatement pst=cnx.prepareStatement("update categoria set categoria=? where idcategoria=?");
           pst.setString(1, this.txt_categoria.getText());
           pst.setInt(2, Integer.parseInt(this.txt_id.getText()));
           pst.execute();
       }catch(Exception e){
           JOptionPane.showMessageDialog(null, e);
       }
    }//GEN-LAST:event_btn_modificarActionPerformed

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
            java.util.logging.Logger.getLogger(Categoria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Categoria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Categoria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Categoria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Categoria().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_buscar;
    private javax.swing.JButton btn_cancelar;
    private javax.swing.JButton btn_guardar;
    private javax.swing.JButton btn_modificar;
    private javax.swing.JButton btn_nuevo;
    private javax.swing.JButton btn_salir;
    private javax.swing.ButtonGroup grupo1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTable jTable2;
    private javax.swing.JRadioButton radio_categoria;
    private javax.swing.JRadioButton radio_id;
    private javax.swing.JTextField txt_categoria;
    private javax.swing.JTextField txt_id;
    private javax.swing.JTextField txt_seleccion;
    // End of variables declaration//GEN-END:variables
}
