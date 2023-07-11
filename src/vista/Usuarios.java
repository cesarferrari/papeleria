package vista;


import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.Conexion;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author julio
 */
public class Usuarios extends javax.swing.JFrame {
  DefaultTableModel modelo = new DefaultTableModel();
   Conexion cn = new Conexion();
    public Usuarios() {
     
                
        initComponents();
        setTitle("USUARIOS");
        setResizable(false);
       
       setLocationRelativeTo(null);
      
       
    }
public String fechaInicio(){
      
            String fecha=""; 
        try{
         
     java.util.Date date= new java.util.Date();
     SimpleDateFormat fr= new SimpleDateFormat("yyyy-MM-dd");
     fecha=fr.format(jdc_nacimineto.getDate());
       
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
     fecha=fr.format(jdc_ingreso.getDate());
       
    }catch(Exception e){
        JOptionPane.showMessageDialog(null,e);
    }
              return fecha;
    }
    
public void eliminar(){
   
    Connection cnx=cn.conectar();
    int eleccion =this.TABLA1.getSelectedRow();
    String aux=this.TABLA1.getValueAt(eleccion, 0).toString();
    String SQL="delete from vendedor where id_vendedor="+aux;
    try{
        PreparedStatement pst=cnx.prepareStatement(SQL);
        pst.execute();
        JOptionPane.showMessageDialog(null,"los datos de eliminaron correctamente ");
        limpiar();
    }catch(Exception e){
        
    }
    
}
public void mostrar(){
       limpiar();
    Connection cnx=cn.conectar();
     String SQL="select*from vendedor";
   
     String titulos[]={"id_vendedor","nombre","usuario","contraseña","rol","apellido","sexo","fecha ingreso","email",
     "sueldo","fecha nacimiento","direccion","telefono","telefono celular"};
     modelo.setColumnIdentifiers(titulos);
   this.TABLA1.setBackground(Color.MAGENTA);
   this.TABLA1.getGridColor();
     try{
         Statement st=cnx.createStatement();
         ResultSet rs=st.executeQuery(SQL);
         String numeros[]=new String[14];
         while (rs.next()) {
             numeros[0]=rs.getString("id_vendedor");
             numeros[1]=rs.getString("nombre");
             numeros[2]=rs.getString("usuario");
             numeros[3]=rs.getString("contraseña");
             numeros[4]=rs.getString("rol");
           
             numeros[5]=rs.getString("apellido");
             numeros[6]=rs.getString("sexo");
             numeros[7]=rs.getString("fechaIngreso");
             numeros[8]=rs.getString("email");
               numeros[9]=rs.getString("sueldo");
             numeros[10]=rs.getString("fechaNacimiento");
             numeros[11]=rs.getString("direccion");
             numeros[12]=rs.getString("telefono");
              numeros[13]=rs.getString("telefonoCelular");
             modelo.addRow(numeros);
         }
         this.TABLA1.setModel(modelo);
         cnx.close();
         
         
     }catch(Exception e){
      JOptionPane.showMessageDialog(null, e);
     }
    
}
 public void atualizar(){
        
       
    Connection cnx=cn.conectar();
        String SQL="update vendedor set nombre=?,rol=?,apellido=?,sexo=?,fechaNacimiento=?,email=?,"
                + "sueldo=?,fechaIngreso=?,direccion=?,telefono=?,telefonoCelular=? where id_vendedor=?";
        try{
            PreparedStatement pst=cnx.prepareStatement(SQL);
           
            
            
             pst.setString(1, this.txt_nombre.getText());
            
            //pst.setString(2, this.txt_usuario.getText());
           // pst.setString(3, this.txt_contraseña.getText());
            
            pst.setString(2, String.valueOf(this.cbx_rol.getSelectedItem()));
            pst.setString(3,this.txt_apellido.getText());
            pst.setString(4,String.valueOf(this.cbx_sexo.getSelectedItem()));
           pst.setString(5,fechaInicio());
            pst.setString(6,this.txt_email.getText());
            pst.setDouble(7,Double.parseDouble(this.txt_sueldo.getText()));
            pst.setString(8, fechaFin());
            pst.setString(9, this.area_direccion.getText());
            pst.setString(10,this.txt_phoneCasa.getText());
            pst.setString(11,this.txt_phoneMovil.getText());
             pst.setInt(12,Integer.parseInt(txt_id.getText()));
            if (txt_id.getText().isEmpty()||txt_nombre.getText().isEmpty()||txt_usuario.getText().isEmpty()||
                    txt_contraseña.getText().isEmpty()||txt_apellido.getText().isEmpty()||fechaInicio().isEmpty()||txt_email.getText().isEmpty()||
                    txt_sueldo.getText().isEmpty()||fechaFin().isEmpty()) {
                JOptionPane.showMessageDialog(null," llena los campos obligatorios ");
            }else if(this.cbx_rol.getSelectedItem().toString().equalsIgnoreCase("seleccione un sexo")||this.cbx_rol.getSelectedItem().toString().equalsIgnoreCase("seleccione un rol")){
                JOptionPane.showMessageDialog(null,"elija rol y sexo  ");
            }else{
                 int eleccion=   pst.executeUpdate();
            if (eleccion>0) {
                JOptionPane.showMessageDialog(null,"datos ingresados correctamente");
            }else{
                JOptionPane.showMessageDialog(null,"error","mensaje error",JOptionPane.ERROR_MESSAGE);
            }
            }
            
          mostrar();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
public boolean check_user_pass(){
    boolean bandera=false;
     String user=this.txt_usuario.getText();
       String pass= this.txt_contraseña.getText();
           String valida_user="";
           String valida_pass="";
       
       String sql="select id_vendedor,usuario,contraseña  from vendedor where usuario= ? and contraseña= ?";
       try{
           Connection con =cn.conectar();
            PreparedStatement pst =con.prepareStatement(sql);
            pst.setString(1, user);
            pst.setString(2, pass);
            ResultSet rs =pst.executeQuery();
           
           String numero[]= new String[3];
          
          
           while (rs.next()) {
                 numero[0]=rs.getString("id_vendedor");
                 valida_user=numero[1]=rs.getString("usuario");
                  valida_pass=numero[2]=rs.getString("contraseña");
            // JOptionPane.showMessageDialog(null,"el usuario y la contraseña  ya existe en la base de datos "+numero[1]+numero[2]);
              this.txt_usuario.setText("");
              this.txt_contraseña.setText("");
           }
           con.close();
       }catch(Exception e){
           JOptionPane.showMessageDialog(null, e);
       }
       if (valida_user.equalsIgnoreCase(user)&&valida_pass.equalsIgnoreCase(pass)) {
        bandera=true;
       
    }
      return bandera;
}
public void limpiar(){
          this.txt_id.setText("");
      this.txt_nombre.setText("");
        this.txt_usuario.setText(""); 
        this.txt_contraseña.setText("");
        this.cbx_rol.setSelectedIndex(0);
         this.txt_apellido.setText("");
         this.jdc_nacimineto.setDate(null);
         this.jdc_ingreso.setDate(null);
           this.cbx_sexo.setSelectedIndex(0);
           //  this.txt_fechaIngreso.setText(fechaIngreso);
        this.txt_email.setText("");
        this.txt_sueldo.setText("");
        // this.txt_fechaNac.setText(fechaNacimiento);
       this.area_direccion.setText("");
        this.txt_phoneCasa.setText("");
       this.txt_phoneMovil.setText("");
        modelo.getDataVector().removeAllElements();
        this.TABLA1.updateUI();
} 
public String user(){
    String user="";
    Connection cnx=cn.conectar();
    PreparedStatement pst;
      try {
          pst = cnx.prepareStatement("select* from vendedor where usuario=?");
            pst.setString(1, this.txt_usuario.getText());
          ResultSet rs=pst.executeQuery();
          
           if(rs.next()){
               user=rs.getString("usuario");
             
           }
     
      } catch (SQLException ex) {
          Logger.getLogger(Usuarios.class.getName()).log(Level.SEVERE, null, ex);
          
      }
   return user;
}
    public void insertar(){
        
       
    Connection cnx=cn.conectar();
        String SQL="insert into vendedor(id_vendedor,nombre,usuario,contraseña,rol,apellido,sexo,fechaNacimiento,email,sueldo,fechaIngreso,direccion,"
                + "telefono,telefonoCelular)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try{
            PreparedStatement pst=cnx.prepareStatement(SQL);
            pst.setString(1, txt_id.getText());
            
            
             pst.setString(2, this.txt_nombre.getText());
            
            pst.setString(3, this.txt_usuario.getText());
            pst.setString(4, this.txt_contraseña.getText());
            
            pst.setString(5, String.valueOf(this.cbx_rol.getSelectedItem()));
            pst.setString(6,this.txt_apellido.getText());
            pst.setString(7,String.valueOf(this.cbx_sexo.getSelectedItem()));
           pst.setString(8,fechaInicio());
            pst.setString(9,this.txt_email.getText());
            pst.setString(10,this.txt_sueldo.getText());
            pst.setString(11, fechaFin());
            pst.setString(12, this.area_direccion.getText());
            pst.setString(13,this.txt_phoneCasa.getText());
            pst.setString(14,this.txt_phoneMovil.getText());
            
            if (txt_id.getText().isEmpty()||txt_nombre.getText().isEmpty()||txt_usuario.getText().isEmpty()||
                    txt_contraseña.getText().isEmpty()||txt_apellido.getText().isEmpty()||fechaInicio().isEmpty()||txt_email.getText().isEmpty()||
                    txt_sueldo.getText().isEmpty()||fechaFin().isEmpty()) {
                JOptionPane.showMessageDialog(null," llena los campos obligatorios ");
            }else if(check_user_pass()==true){
                JOptionPane.showMessageDialog(null, "el usuario y contraseña ya existen en la base de datos  ");
            }else if(this.cbx_rol.getSelectedItem().toString().equalsIgnoreCase("seleccione un sexo")||this.cbx_rol.getSelectedItem().toString().equalsIgnoreCase("seleccione un rol")){
                JOptionPane.showMessageDialog(null,"elija rol y sexo  ");
            }else if(user().equalsIgnoreCase(this.txt_usuario.getText())){
                JOptionPane.showMessageDialog(null,"usuario ya existe eliga otro");
            }else{
                 int eleccion=   pst.executeUpdate();
            if (eleccion>0) {
                JOptionPane.showMessageDialog(null,"datos ingresados correctamente");
            }else{
                JOptionPane.showMessageDialog(null,"error","mensaje error",JOptionPane.ERROR_MESSAGE);
            }
            }
            
          mostrar();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu1 = new javax.swing.JMenu();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TABLA1 = new javax.swing.JTable();
        txt_usuario = new javax.swing.JTextField();
        cbx_rol = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txt_id = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txt_nombre = new javax.swing.JTextField();
        txt_apellido = new javax.swing.JTextField();
        txt_email = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        cbx_sexo = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txt_sueldo = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txt_phoneCasa = new javax.swing.JTextField();
        txt_contraseña = new javax.swing.JPasswordField();
        jdc_nacimineto = new com.toedter.calendar.JDateChooser();
        jdc_ingreso = new com.toedter.calendar.JDateChooser();
        jButton3 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        txt_phoneMovil = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        area_direccion = new javax.swing.JTextArea();
        jButton4 = new javax.swing.JButton();

        jMenu1.setText("jMenu1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));

        TABLA1 = new javax.swing.JTable(){
            public boolean isCellEditable(int row,int column){
                return false;
            }
        };
        TABLA1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        TABLA1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        TABLA1.setFocusable(false);
        TABLA1.getTableHeader().setReorderingAllowed(false);
        TABLA1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TABLA1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TABLA1);

        txt_usuario.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_usuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_usuarioActionPerformed(evt);
            }
        });

        cbx_rol.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cbx_rol.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "seleccione un rol", "cajero", "administrador", " ", " " }));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("usuario");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("contraseña");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("rol");

        txt_id.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_id.setEnabled(false);
        txt_id.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txt_idMouseEntered(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("id");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("nombre");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("apellido");

        txt_nombre.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txt_apellido.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txt_email.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_email.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_emailFocusLost(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Email");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("fecha ingreso");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("sexo");

        cbx_sexo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cbx_sexo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "seleccione un sexo", "masculino", "femenino", " " }));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("fecha nacimiento");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("sueldo");

        txt_sueldo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setText("telefono casa");

        txt_phoneCasa.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txt_contraseña.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(43, 43, 43)
                                .addComponent(jLabel4))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel5)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel7)
                                        .addComponent(jLabel6)))))
                        .addGap(57, 57, 57))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel10)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txt_email)
                                .addComponent(cbx_rol, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(txt_nombre)
                                    .addGap(62, 62, 62))
                                .addComponent(txt_apellido)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(txt_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                    .addComponent(jdc_nacimineto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(jLabel11))
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(jdc_ingreso, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(18, 18, 18)
                                                    .addComponent(jLabel9)))
                                            .addGap(4, 4, 4)))
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(cbx_sexo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txt_sueldo))))
                            .addComponent(txt_contraseña, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 858, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txt_phoneCasa, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txt_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txt_apellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel11)
                                        .addComponent(txt_sueldo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel9)
                                        .addComponent(cbx_sexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel8)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(jdc_nacimineto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jdc_ingreso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(24, 24, 24)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txt_contraseña, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(cbx_rol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txt_phoneCasa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jButton3.setText("actualizar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton1.setText("insertar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("mostrar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setText("telefono movil");

        txt_phoneMovil.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setText("direccion");

        area_direccion.setColumns(20);
        area_direccion.setRows(5);
        jScrollPane2.setViewportView(area_direccion);

        jButton4.setText("eliminar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addGap(28, 28, 28)
                                .addComponent(jButton2)
                                .addGap(37, 37, 37)
                                .addComponent(jButton3)
                                .addGap(36, 36, 36)
                                .addComponent(jButton4))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(jLabel13)
                                .addGap(34, 34, 34)
                                .addComponent(txt_phoneMovil, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(92, 92, 92)
                                .addComponent(jLabel14)))
                        .addGap(38, 38, 38)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(txt_phoneMovil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(71, 71, 71))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
insertar();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
mostrar();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void TABLA1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TABLA1MouseClicked
      try{
        int codigo = this.TABLA1.getSelectedRow();
      Date date_birth= new SimpleDateFormat("yyyy-MM-dd").parse((String)modelo.getValueAt(codigo, 7));
      Date date_start= new SimpleDateFormat("yyyy-MM-dd").parse((String)modelo.getValueAt(codigo, 10));
      String id=this.TABLA1.getValueAt(codigo,0).toString();
      String nombre=this.TABLA1.getValueAt(codigo,1).toString();
      
     String usuario=this.TABLA1.getValueAt(codigo,2).toString();
      String contraseña=TABLA1.getValueAt(codigo,3).toString();
      String rol=TABLA1.getValueAt(codigo,4).toString();
      String apellido=TABLA1.getValueAt(codigo,5).toString();
     String sexo=this.TABLA1.getValueAt(codigo,6).toString();
      String fechaIngreso=this.TABLA1.getValueAt(codigo,7).toString();
       String email=this.TABLA1.getValueAt(codigo,8).toString();
      String sueldo=this.TABLA1.getValueAt(codigo,9).toString();
      String fechaNacimiento=this.TABLA1.getValueAt(codigo,10).toString();
     
      String direccion=this.TABLA1.getValueAt(codigo,11).toString();
      String telefono=this.TABLA1.getValueAt(codigo,12).toString();
      String telefonoCelular=this.TABLA1.getValueAt(codigo,13).toString();
     
      this.txt_id.setText(id);
      this.txt_nombre.setText(nombre);
        this.txt_usuario.setText(usuario); 
        this.txt_contraseña.setText(contraseña);
        this.cbx_rol.setSelectedItem(rol);
         this.txt_apellido.setText(apellido);
         this.jdc_nacimineto.setDate(date_birth);
         this.jdc_ingreso.setDate(date_start);
           this.cbx_sexo.setSelectedItem(sexo);
           //  this.txt_fechaIngreso.setText(fechaIngreso);
        this.txt_email.setText(email);
        this.txt_sueldo.setText(sueldo);
        // this.txt_fechaNac.setText(fechaNacimiento);
       this.area_direccion.setText(direccion);
        this.txt_phoneCasa.setText(telefono);
       this.txt_phoneMovil.setText(telefonoCelular);
        
      }catch(Exception e){
          
      }
        
        
    
                                   

    }//GEN-LAST:event_TABLA1MouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        atualizar();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void txt_idMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_idMouseEntered
        
            int in=0;
            ArrayList<Integer> numeros= new ArrayList<Integer>();
            try {
            Connection con =cn.conectar();
            PreparedStatement pst =con.prepareStatement("select id_vendedor from vendedor");
            ResultSet rs =pst.executeQuery();
           
               while (rs.next()) {
                   numeros.add(Integer.parseInt(rs.getString("id_vendedor")));
                   
                }
                
                 in =numeros.get(numeros.size()-1)+1;
                
           
        } catch (Exception ex) {
           
        }
      
               
              
                String identificador=String.valueOf(in);
                 this.txt_id.setText(identificador);
                this.txt_id.setEditable(true);
    }//GEN-LAST:event_txt_idMouseEntered
 public  boolean validarEmail(String email){
        
        Pattern patron =  Pattern.compile("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$");
        Matcher  matcher=patron.matcher(email);
        return matcher.find();
    }
    private void txt_emailFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_emailFocusLost
       
         if (validarEmail(this.txt_email.getText())) {
            
        }else{
             JOptionPane.showMessageDialog(null, "el formato de correo electronico es incorrecto");
             this.txt_email.setText("");
         }
    }//GEN-LAST:event_txt_emailFocusLost

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
       try{
            if (this.txt_id.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "debes de elegir un registro a eliminar");
        }else{
            eliminar();
        }
       }catch(Exception e){
           
       }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void txt_usuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_usuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_usuarioActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
   
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run()  {
                new Usuarios().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JTable TABLA1;
    private javax.swing.JTextArea area_direccion;
    private javax.swing.JComboBox<String> cbx_rol;
    private javax.swing.JComboBox<String> cbx_sexo;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private com.toedter.calendar.JDateChooser jdc_ingreso;
    private com.toedter.calendar.JDateChooser jdc_nacimineto;
    private javax.swing.JTextField txt_apellido;
    private javax.swing.JPasswordField txt_contraseña;
    private javax.swing.JTextField txt_email;
    private javax.swing.JTextField txt_id;
    private javax.swing.JTextField txt_nombre;
    private javax.swing.JTextField txt_phoneCasa;
    private javax.swing.JTextField txt_phoneMovil;
    private javax.swing.JTextField txt_sueldo;
    private javax.swing.JTextField txt_usuario;
    // End of variables declaration//GEN-END:variables
}
