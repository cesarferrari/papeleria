/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import com.mxrck.autocompleter.TextAutoCompleter;
import static java.awt.event.KeyEvent.VK_ENTER;
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
public class Proveedores1 extends javax.swing.JFrame {

   private CompraX compra;
 Conexion con = new Conexion();
    private DefaultTableModel model;
     private  TextAutoCompleter ac;
    public Proveedores1() {
        initComponents();
    }

   public void mostrar(String consulta){
            model= new DefaultTableModel();
        String titulos[]={"idProveedor","nombre","direccion","telefono","email","identificacion"};
        model.setColumnIdentifiers(titulos);
        Conexion cnx= new Conexion();
        Connection con=cnx.conectar();
       
        try{
       // st=cnx.createStatement();
       PreparedStatement pst= con.prepareStatement(consulta);
        ResultSet rs=pst.executeQuery();
        String num[]=new String[6];
            while (rs.next()) {
               num[0]= rs.getString("idProveedor");
                num[1]=rs.getString("nombre");
                 num[2]=rs.getString("direccion");
                  num[3]=rs.getString("telefono");
                  num[4]=rs.getString("email");
                  num[5]=rs.getString("identificacion");
             
                model.addRow(num);
            }
            this.jTable1.setModel(model);
            con.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
           
        }
   }
 
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txt_proveedor = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        btn_salir = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 0));

        jPanel2.setBackground(new java.awt.Color(0, 204, 204));

        jLabel1.setText("criterios de busqueda");

        txt_proveedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txt_proveedorMouseEntered(evt);
            }
        });
        txt_proveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_proveedorActionPerformed(evt);
            }
        });
        txt_proveedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_proveedorKeyTyped(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        jLabel2.setText("Proveedor");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txt_proveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 433, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(204, 204, 204))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(10, 10, 10)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(txt_proveedor, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                .addContainerGap())
        );

        btn_salir.setText("salir");
        btn_salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_salirActionPerformed(evt);
            }
        });

        jTable1 =new javax.swing.JTable(){
            public boolean isCellEditable(int r,int c){
                return false;
            }
        };
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_salir, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_salir, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_proveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_proveedorActionPerformed
     
    }//GEN-LAST:event_txt_proveedorActionPerformed

    private void txt_proveedorMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_proveedorMouseEntered
              ac=new TextAutoCompleter(txt_proveedor);
            
       Connection cnx=con.conectar();
       String sql="select * from proveedores";
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
    }//GEN-LAST:event_txt_proveedorMouseEntered

    private void txt_proveedorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_proveedorKeyTyped
         String nombre="select idProveedor,nombre,direccion,telefono,email,identificacion from proveedores where nombre ='"+txt_proveedor.getText()+"'";
         
        if (evt.getKeyChar()==VK_ENTER) {
           
   mostrar(nombre);
     
     
            //JOptionPane.showMessageDialog(null, "tecla enter ");
        } 
    }//GEN-LAST:event_txt_proveedorKeyTyped

    private void btn_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_salirActionPerformed
        int seleccion=this.jTable1.getSelectedRow();
        try{
         CompraX.txt_proveedor.setText(tab());
          CompraX.cliente=Integer.parseInt(this.jTable1.getValueAt(seleccion, 0).toString());
         dispose();
         
     }catch(Exception e){
         JOptionPane.showMessageDialog(null,"selecciona una opcion");
     }
       
        
    }//GEN-LAST:event_btn_salirActionPerformed
  public String tab(){
        
            int seleccion=this.jTable1.getSelectedRow();
        String eleccion =this.jTable1.getValueAt(seleccion,1).toString();
        
       
        
        
        
        return eleccion;
    }
  
    public String[] tabla(){
        
            int seleccion=this.jTable1.getSelectedRow();
        String eleccion[] ={this.jTable1.getValueAt(seleccion,0).toString(),this.jTable1.getValueAt(seleccion,1).toString(),
        this.jTable1.getValueAt(seleccion,2).toString(),this.jTable1.getValueAt(seleccion,3).toString(),
        this.jTable1.getValueAt(seleccion,4).toString()};
       
       
        
        
        return eleccion;
    }
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
            java.util.logging.Logger.getLogger(Proveedores1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Proveedores1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Proveedores1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Proveedores1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Proveedores1().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_salir;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txt_proveedor;
    // End of variables declaration//GEN-END:variables
}
