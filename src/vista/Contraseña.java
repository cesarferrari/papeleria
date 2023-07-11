/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.Conexion;

/**
 *
 * @author julio
 */

public class Contraseña extends javax.swing.JFrame {
Conexion cn= new Conexion();
   private Connection con=null;
   private Statement st;
   private PreparedStatement pst;
   private ResultSet rs;
   private DefaultTableModel model;
   public static String user="";
  
    public Contraseña() {
        initComponents();
    }
public void tabla(){
     String sql="select*from vendedor where usuario=?";
    con=cn.conectar();
    model=new DefaultTableModel();
    String tit[]={"usuario","contraseña","telefono","celular","direccion"};
    model.setColumnIdentifiers(tit);
    try{
   pst=con.prepareStatement(sql);
   pst.setString(1, user);
    rs=pst.executeQuery();
    String[]number= new String[5];
        while (rs.next()) {
            
            number[0]=rs.getString("usuario");
            number[1]=rs.getString("contraseña");
            number[2]=rs.getString("telefono");
            number[3]=rs.getString("telefonoCelular");
            number[4]=rs.getString("direccion");
            model.addRow(number);
            this.txt_usuario.setText(rs.getString("usuario"));
            this.txt_contraseña.setText(rs.getString("contraseña"));
            this.txt_telefono.setText(rs.getString("telefono"));
            this.txt_celular.setText(rs.getString("telefonoCelular"));
            this.txt_direccion.append(rs.getString("direccion"));
        }
       
        con.close();
    }catch(Exception e){
        JOptionPane.showMessageDialog(null,e);
    }
}
public boolean check_user_pass(){
    boolean bandera=false;
    // String user=this.txt_usuario.getText();
       String pass= this.txt_contraseña.getText();
      
           String valida_user="";
           String valida_pass="";
           String valida_rol="";
       
       String sql="select id_vendedor,usuario,contraseña,rol  from vendedor where usuario= ? and contraseña= ? and rol=?";
       try{
           Connection con =cn.conectar();
            PreparedStatement pst =con.prepareStatement(sql);
            pst.setString(1, user);
            pst.setString(2, pass);
          //  pst.setString(3, this.jComboBox1.getSelectedItem().toString());
            ResultSet rs =pst.executeQuery();
           
           String numero[]= new String[3];
          
          
           while (rs.next()) {
                 numero[0]=rs.getString("id_vendedor");
                 valida_user=numero[1]=rs.getString("usuario");
                  valida_pass=numero[2]=rs.getString("contraseña");
                  valida_rol=rs.getString("rol");
            // JOptionPane.showMessageDialog(null,"el usuario y la contraseña  ya existe en la base de datos "+numero[1]+numero[2]);
              this.txt_usuario.setText("");
              this.txt_contraseña.setText("");
           }
           con.close();
       }catch(Exception e){
           JOptionPane.showMessageDialog(null, e);
       }
       if (valida_user.equalsIgnoreCase(user)&&valida_pass.equalsIgnoreCase(pass)&&valida_rol.equalsIgnoreCase("")) {
        bandera=true;
       
    }
      return bandera;
}
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btn_cambiar = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        txt_usuario = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txt_contraseña = new javax.swing.JPasswordField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txt_celular = new javax.swing.JTextField();
        txt_telefono = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        txt_direccion = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("contraseña");

        jPanel1.setBackground(new java.awt.Color(0, 255, 204));

        btn_cambiar.setBackground(new java.awt.Color(0, 0, 153));
        btn_cambiar.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btn_cambiar.setForeground(new java.awt.Color(255, 255, 255));
        btn_cambiar.setText("cambiar");
        btn_cambiar.setEnabled(false);
        btn_cambiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cambiarActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(0, 0, 153));
        jButton2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("mostrar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel1.setText("usuario");

        jLabel2.setText("contraseña");

        txt_contraseña.setText("jPasswordField1");

        jLabel5.setText("Direccion");

        jLabel6.setText("Telefono");

        jLabel7.setText(" Celular");

        txt_direccion.setColumns(20);
        txt_direccion.setRows(5);
        jScrollPane2.setViewportView(txt_direccion);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btn_cambiar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 69, Short.MAX_VALUE)
                                .addComponent(jButton2)
                                .addGap(89, 89, 89))
                            .addComponent(jScrollPane2)))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel7)
                            .addGap(28, 28, 28)
                            .addComponent(txt_celular))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel6)
                            .addGap(22, 22, 22)
                            .addComponent(txt_telefono))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2)
                                .addComponent(jLabel1))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txt_usuario)
                                .addComponent(txt_contraseña, javax.swing.GroupLayout.DEFAULT_SIZE, 328, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txt_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(txt_contraseña, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txt_telefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txt_celular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel5))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2)
                    .addComponent(btn_cambiar))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        tabla();
        this.btn_cambiar.setEnabled(true);
    }//GEN-LAST:event_jButton2ActionPerformed
public void actualizar(){
  
    String SQL="update vendedor set contraseña=?,telefono=?,telefonoCelular=?,direccion=? where usuario=?";
    Conexion cn= new Conexion();
    con=cn.conectar();
    try{
        pst=con.prepareStatement(SQL);
        pst.setString(1, this.txt_contraseña.getText());
       pst.setString(2, this.txt_telefono.getText());
     pst.setString(3, this.txt_celular.getText());
      pst.setString(4, this.txt_direccion.getText());
      pst.setString(5, user);
        
        pst.executeUpdate();
    JOptionPane.showMessageDialog(null, "los datos se actualizaron correctamente ");
    }catch(Exception e){
   
    
    }
   
    
    
}
    private void btn_cambiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cambiarActionPerformed
     
     if(this.txt_contraseña.getText().isEmpty()){
         JOptionPane.showMessageDialog(null,"debe ingresar una contraseña");
     }else{
            actualizar();
       tabla();
       this.txt_usuario.setText("");
       this.txt_contraseña.setText("");
       this.txt_telefono.setText("");
       this.txt_celular.setText("");
       this.txt_direccion.setText("");
     
    }//GEN-LAST:event_btn_cambiarActionPerformed
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
            java.util.logging.Logger.getLogger(Contraseña.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Contraseña.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Contraseña.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Contraseña.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Contraseña().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cambiar;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField txt_celular;
    public javax.swing.JPasswordField txt_contraseña;
    private javax.swing.JTextArea txt_direccion;
    private javax.swing.JTextField txt_telefono;
    private javax.swing.JTextField txt_usuario;
    // End of variables declaration//GEN-END:variables
}
