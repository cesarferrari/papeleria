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
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.*;
import static vista.CompraX.cliente;
public class FRProducto extends javax.swing.JFrame {
TextAutoCompleter ac;
  Conexion con= new Conexion();
  Statement st;
  ResultSet rs;
  Connection cnx;
  DefaultTableModel model,mod;
  String sql="";
    public static String user="";
public static int cliente=1;
    public FRProducto() {
        initComponents();
        
    }

    public  void radioClientesId(String consulta){
     
      //  sql="select id_cliente,nombre,identificacion,direccion,telefono from clientes where id_cliente=1";
        model= new DefaultTableModel();
        String titulos[]={"codigo","producto","descripcion","proveedor","fabricante","categoria","stock","precio Venta","precio Compra"};
        model.setColumnIdentifiers(titulos);
        cnx=con.conectar();
        try{
       // st=cnx.createStatement();
       PreparedStatement pst= cnx.prepareStatement(consulta);
        rs=pst.executeQuery();
        String num[]=new String[11];
            while (rs.next()) {
               num[0]= rs.getString("codigo");
                num[1]=rs.getString("nombre");
                 num[2]= rs.getString("descripcion");
                num[3]=rs.getString("proveedor");
            
                 num[4]= rs.getString("proveedores.nombre");
                num[5]=rs.getString("categoria.categoria");
                 num[6]= rs.getString("stock");
                num[7]=rs.getString("precio");
                 num[8]= rs.getString("precioCompra");
              
                
               
                model.addRow(num);
            }
            this.Tabla1.setModel(model);
            cnx.close();
        }catch(Exception e){
            try {
                JOptionPane.showMessageDialog(null, e);
                cnx.close();
            } catch (SQLException ex) {
                Logger.getLogger(FRProducto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
     public  void radioClientesId(){
     
      //  sql="select id_cliente,nombre,identificacion,direccion,telefono from clientes where id_cliente=1";
     
        String sql=" select productos1.codigo,productos1.nombre,productos1.descripcion,productos1.proveedor,proveedores.nombre,categoria.categoria,"
                    + "productos1.stock,productos1.precio,productos1.precioCompra from productos1 inner join proveedores on proveedores.idproveedor=productos1.idproveedor"
                     + " inner join categoria on categoria.idcategoria=productos1.categoria where productos1.codigo='"+this.txt_busqueda.getText()+"'";; 
        cnx=con.conectar();
        try{
       // st=cnx.createStatement();
       PreparedStatement pst= cnx.prepareStatement(sql);
        rs=pst.executeQuery();
        String num[]=new String[11];
            while (rs.next()) {
               num[0]= rs.getString("codigo");
                num[1]=rs.getString("nombre");
                 num[2]= rs.getString("descripcion");
                num[3]=rs.getString("proveedor");
            
                 num[4]= rs.getString("proveedores.nombre");
                num[5]=rs.getString("categoria.categoria");
                 num[6]= rs.getString("stock");
                num[7]=rs.getString("precio");
                 num[8]= rs.getString("precioCompra");
                 
                 
               this.txt_codigo.setText(num[0]);
               this.txt_nombre.setText(num[1]);
                this.txt_descripcion.setText(num[2]);
                this.txt_proveedor.setText(num[3]);
                this.cbx_proveedor.setSelectedItem(num[4]); 
                
                this.cbx_categoria.setSelectedItem(num[5]);
                this.txt_stock.setText(num[6]);
               this.txt_precio.setText(num[7]);
              this.txt_precioCompra.setText(num[8]);
      // this.txt_cuenta1.setText("");
    //   this.txt_cuenta2.setText(null);
       
      
       
       
      // 
        // 
               
             //   mod.addRow(num);
            }
           // this.Tabla1.setModel(mod);
            cnx.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
            try {
                cnx.close();
            } catch (SQLException ex) {
                Logger.getLogger(FRProducto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public void completar(String name){
         ac=new TextAutoCompleter(txt_seleccion);
             Conexion con = new Conexion();
             
              
       Connection cnx=con.conectar();
       String sql="select * from productos1";
       try{
           Statement st =cnx.createStatement();
           ResultSet rs =st.executeQuery(sql);
           String numero[]= new String[1];
          
          
           while (rs.next()) {
             
              ac.addItem(rs.getString(name));
         
              
           }
           cnx.close();
       }catch(Exception e){
           JOptionPane.showMessageDialog(null, e);
             try {
                 cnx.close();
             } catch (SQLException ex) {
                 Logger.getLogger(FRProducto.class.getName()).log(Level.SEVERE, null, ex);
             }
       }
        
        limpiar();
       
    }
       public void completar1(String name){
         ac=new TextAutoCompleter(txt_seleccion);
             Conexion con = new Conexion();
             
              
       Connection cnx=con.conectar();
       String sql="select * from productos1";
       try{
           Statement st =cnx.createStatement();
           ResultSet rs =st.executeQuery(sql);
           String numero[]= new String[1];
          
          
           while (rs.next()) {
             
              ac.addItem(rs.getString(name));
         
              
           }
           cnx.close();
       }catch(Exception e){
           JOptionPane.showMessageDialog(null, e);
             try {
                 cnx.close();
             } catch (SQLException ex) {
                 Logger.getLogger(FRProducto.class.getName()).log(Level.SEVERE, null, ex);
             }
       }
        
        limpiar();
       
    }
        public String vta(){
        String vta="";
        //int select=this.Tabla.getRowCount();
        ArrayList<String> lista=new ArrayList<>();
       
            lista.add(this.txt_nombre.getText());
     
        
       
      
        return vta;
    }
public int codigoCompra(){
    int code=0;
    try{
        Connection cnx=con.conectar();
        PreparedStatement pst=cnx.prepareStatement("select codigo from compra order by  codigo  desc limit 1;");
        ResultSet rs=pst.executeQuery();
        while(rs.next()){
            code=Integer.parseInt(rs.getString("codigo"));
        }
        code++;
    }catch(Exception e){
        
    }
    return code;
}
 public String venta(){
        String vta="";
   String prog=""+proveedorX();
  
            vta="/"+this.txt_codigo.getText()+"/"+this.txt_stock.getText()+"/"+this.txt_nombre.getText()+"/"+this.txt_precio.getText()+"!";
     
        
       
      
        return vta;
    }
  public void  insertaDetalleCompra(){
       Connection cnx=con.conectar();
       String sql="insert into detalle_compra(codigo_producto,cantidad,producto,precio,fecha)values(?,?,?,?,now())";
       try{
            if (txt_codigo.getText().isEmpty()||txt_descripcion.getText().isEmpty()||txt_proveedor.getText().isEmpty()||
                      txt_stock.getText().isEmpty()||txt_precio.getText().isEmpty()||txt_precioCompra.getText().isEmpty()||txt_nombre.getText().isEmpty()) {
           //  JOptionPane.showMessageDialog(null,"debes de llenar todos los campos ","mensaje error",JOptionPane.ERROR_MESSAGE);
              }else
           if(codigo_existente().equalsIgnoreCase(this.txt_codigo.getText())){
          //  JOptionPane.showMessageDialog(null,"el codigo de producto ya existente");   
               
           }else if(this.cbx_proveedor.getSelectedItem().toString().equalsIgnoreCase("selecciona un proveedor")||
                   this.cbx_categoria.getSelectedItem().toString().equalsIgnoreCase("selecciona una categoria")){
              // JOptionPane.showMessageDialog(null,"categoria o provvedor"); 
           }else{
           LocalDate fecha=  LocalDate.now();
           PreparedStatement pst=cnx.prepareStatement(sql);
           double subtotal=0.0;
            subtotal=Double.parseDouble(this.txt_precioCompra.getText())*Double.parseDouble(this.txt_stock.getText());
           pst.setString(1,this.txt_codigo.getText());
            pst.setString(2, this.txt_stock.getText());
             pst.setString(3, this.txt_nombre.getText());
             pst.setString(4,""+subtotal);
            //  pst.setDouble(4,Double.parseDouble(this.txt_precioCompra.getText())*Double.parseDouble(this.txt_stock.getText()));
              
                
                
                int val=pst.executeUpdate();
                if(val>0){
                    JOptionPane.showMessageDialog(null,"insertado");
                }else{
                    JOptionPane.showMessageDialog(null,"no tienes artuculos para regitrar");
                }
           }
       }catch(SQLIntegrityConstraintViolationException ex){
              JOptionPane.showMessageDialog(null, "debes seleccionar una categoria y proveedor");
               try {
                         cnx.close();
                     } catch (SQLException exq) {
                         Logger.getLogger(FRProducto.class.getName()).log(Level.SEVERE, null, ex);
                     }
              
          }catch(Exception e){
              JOptionPane.showMessageDialog(null, e);
               try {
                         cnx.close();
                     } catch (SQLException ex) {
                         Logger.getLogger(FRProducto.class.getName()).log(Level.SEVERE, null, ex);
                     }
          }
       
   }
   public void  insertaVenta(){
       Connection cnx=con.conectar();
       String sql="insert into compra(id_proveedor,empleado,productos,total,codigo,fecha)values(?,?,?,?,?,now())";
       try{
            if (txt_codigo.getText().isEmpty()||txt_descripcion.getText().isEmpty()||txt_proveedor.getText().isEmpty()||
                      txt_stock.getText().isEmpty()||txt_precio.getText().isEmpty()||txt_precioCompra.getText().isEmpty()||txt_nombre.getText().isEmpty()) {
             //JOptionPane.showMessageDialog(null,"debes de llenar todos los campos ","mensaje error",JOptionPane.ERROR_MESSAGE);
              }else if(codigo_existente().equalsIgnoreCase(this.txt_codigo.getText())){
            //JOptionPane.showMessageDialog(null,"el codigo de producto ya existente");   
               
           }else if(this.cbx_proveedor.getSelectedItem().toString().equalsIgnoreCase("selecciona un proveedor")||
                   this.cbx_categoria.getSelectedItem().toString().equalsIgnoreCase("selecciona una categoria")){
               // JOptionPane.showMessageDialog(null,"categoria o provvedor"); 
           }else{
           LocalDate fecha=  LocalDate.now();
           PreparedStatement pst=cnx.prepareStatement(sql);
           double subtotal=0.0;
            subtotal=Double.parseDouble(this.txt_precioCompra.getText())*Double.parseDouble(this.txt_stock.getText());
           pst.setInt(1,proveedorX());
            pst.setString(2, user);
             pst.setString(3, venta());
             pst.setString(4,""+subtotal);
            //  pst.setDouble(4,Double.parseDouble(this.txt_precioCompra.getText())*Double.parseDouble(this.txt_stock.getText()));
               pst.setInt(5, codigoCompra());
                
           
                int val=pst.executeUpdate();
                if(val>0){
                    JOptionPane.showMessageDialog(null,"insertado");
                }else{
                    JOptionPane.showMessageDialog(null,"no tienes artuculos para regitrar");
                }
           }
       }catch(SQLIntegrityConstraintViolationException ex){
              JOptionPane.showMessageDialog(null, "debes seleccionar una categoria y proveedor");
               try {
                         cnx.close();
                     } catch (SQLException exq) {
                         Logger.getLogger(FRProducto.class.getName()).log(Level.SEVERE, null, ex);
                     }
              
          }catch(Exception e){
              JOptionPane.showMessageDialog(null, e);
               try {
                         cnx.close();
                     } catch (SQLException ex) {
                         Logger.getLogger(FRProducto.class.getName()).log(Level.SEVERE, null, ex);
                     }
          }
   }
   
 
     public void completarProveedor(){
         ac=new TextAutoCompleter(txt_seleccion);
             Conexion con = new Conexion();
             
              
       Connection cnx=con.conectar();
       String sql="select* from proveedores";
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
             try {
                 cnx.close();
             } catch (SQLException ex) {
                 Logger.getLogger(FRProducto.class.getName()).log(Level.SEVERE, null, ex);
             }
       }
        
        limpiar();
       
    }
      public void completarCategoria(){
         ac=new TextAutoCompleter(txt_seleccion);
             Conexion con = new Conexion();
             
              
       Connection cnx=con.conectar();
       String sql="select* from categoria";
       try{
           Statement st =cnx.createStatement();
           ResultSet rs =st.executeQuery(sql);
           String numero[]= new String[1];
          
          
           while (rs.next()) {
             
              ac.addItem(rs.getString("categoria"));
         
              
           }
           cnx.close();
       }catch(Exception e){
           JOptionPane.showMessageDialog(null, e);
             try {
                 cnx.close();
             } catch (SQLException ex) {
                 Logger.getLogger(FRProducto.class.getName()).log(Level.SEVERE, null, ex);
             }
       }
        
        limpiar();
       
    }
    public void limpiar(){
      
      this.txt_stock.setText(null);
       this.txt_codigo.setText(null);
       this.txt_precio.setText("");
      // this.txt_cuenta1.setText("");
    //   this.txt_cuenta2.setText(null);
       this.txt_proveedor.setText(null);
       this.txt_descripcion.setText("");
       this.txt_precioCompra.setText("");
       this.txt_nombre.setText("");
       this.cbx_categoria.setSelectedItem("selecciona una categoria");
        this.cbx_proveedor.setSelectedItem("selecciona un proveedor");
         }
    public void llenaCajas(){  
        String titulo[]=new String[7];
        String sql2="select* from productos1 where codigo=12";
        String sql="select productos1.codigo,productos1.nombre,productos1.descripcion,productos1.proveedor,proveedores.nombre,categoria.categoria,"
                     + "productos1.stock,productos1.precio,productos1.precioCompra from productos1 inner join proveedores on proveedores.idproveedor=productos1.idproveedor"
                     + " inner join categoria on categoria.idcategoria=productos1.categoria where productos1.codigo=89";
        try{
              Connection cnx=con.conectar();
       Statement st=cnx.createStatement();
            
            ResultSet rs;
            rs = st.executeQuery(sql);
         
             //       pst.execute();
            while(rs.next()){
             titulo[0]=  rs.getString("stock");
      titulo[1]=rs.getString("codigo");
      titulo[2]=rs.getString("precio");
      // this.txt_cuenta1.setText("");
    //   this.txt_cuenta2.setText(null);
      titulo[3]= rs.getString("proveedor");
      titulo[4]= rs.getString("descripcion");
       titulo[5]=rs.getString("precioCompra");
       titulo[6]=rs.getString("nombre");
       titulo[7]=rs.getString("categoria.categoria");
        titulo[8]=rs.getString("proveedores.nombre"); 
            }
                    this.txt_stock.setText(titulo[0]);
       this.txt_codigo.setText(titulo[1]);
       this.txt_precio.setText(titulo[2]);
      // this.txt_cuenta1.setText("");
    //   this.txt_cuenta2.setText(null);
       this.txt_proveedor.setText(titulo[3]);
       this.txt_descripcion.setText(titulo[4]);
       this.txt_precioCompra.setText(titulo[5]);
       this.txt_nombre.setText(titulo[6]);
      // this.cbx_categoria.addItem(titulo[7]);
        // this.cbx_proveedor.addItem(titulo[8]); 
            for (String string : titulo) {
                JOptionPane.showMessageDialog(null,string+"brain");
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }
       
    public void actualizar_producto(){
        Connection cnx=con.conectar();
   
    String sql="update productos1 set nombre=?,descripcion=?,proveedor=?,precio=?,precioCompra=?,idproveedor=?,categoria=? where codigo=?";
    try{
        PreparedStatement pst=cnx.prepareStatement(sql);
       
         pst.setString(1,this.txt_nombre.getText());
          pst.setString(2, this.txt_descripcion.getText());
            pst.setString(3, this.txt_proveedor.getText());
         //     pst.setInt(4, Integer.parseInt(this.txt_stock.getText()));
                pst.setDouble(4, Double.parseDouble(this.txt_precio.getText()));
                  pst.setDouble(5, Double.parseDouble(this.txt_precioCompra.getText()));
                    pst.setInt(6, proveedorX());
                      pst.setInt(7, categoriaX());
                        pst.setString(8, this.txt_codigo.getText());
                       if(!codigo_existente().equalsIgnoreCase(this.txt_codigo.getText())){
                           JOptionPane.showMessageDialog(null,"no existe el codigo del producto a actualizar");
                       }else{
                          
                        this.txt_codigo.setEditable(true);
         pst.execute();
             JOptionPane.showMessageDialog(null, "datos actualizados  correctamente");
             limpiar();
        
       cnx.close();
                       }
                     
                            
       
    }catch(SQLIntegrityConstraintViolationException ex){
              JOptionPane.showMessageDialog(null, "debes seleccionar una categoria y proveedor");
            try {
                cnx.close();
            } catch (SQLException ex1) {
                Logger.getLogger(FRProducto.class.getName()).log(Level.SEVERE, null, ex1);
            }
          }catch(Exception e){
       JOptionPane.showMessageDialog(null, e);
            try {
                cnx.close();
            } catch (SQLException ex) {
                Logger.getLogger(FRProducto.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    } 
  public void actualizar_producto1(){
        Connection cnx=con.conectar();
   int select=this.Tabla1.getSelectedRow();
    String sql="update productos1 set nombre=?,descripcion=?,proveedor=?,stock=?,precio=?,precioCompra=? where codigo=?";
    try{
        PreparedStatement pst=cnx.prepareStatement(sql);
       
         pst.setString(1,this.Tabla1.getValueAt(select,1).toString());
          pst.setString(2, this.Tabla1.getValueAt(select,2).toString());
            pst.setString(3, this.Tabla1.getValueAt(select,3).toString());
              pst.setInt(4, Integer.parseInt(this.Tabla1.getValueAt(select,6).toString()));
                pst.setDouble(5, Double.parseDouble(this.Tabla1.getValueAt(select, 7).toString()));
                  pst.setDouble(6, Double.parseDouble(this.Tabla1.getValueAt(select, 8).toString()));
                   
                        pst.setString(7,this.Tabla1.getValueAt(select, 0).toString());
                       
         pst.execute();
             JOptionPane.showMessageDialog(null, "datos actualizados  correctamente");
             limpiar();
        
       cnx.close();
                       
                     
                            
       
    }catch(SQLIntegrityConstraintViolationException ex){
              JOptionPane.showMessageDialog(null, "debes seleccionar una categoria y proveedor");
            try {
                cnx.close();
            } catch (SQLException ex1) {
                Logger.getLogger(FRProducto.class.getName()).log(Level.SEVERE, null, ex1);
            }
          }catch(Exception e){
       JOptionPane.showMessageDialog(null, e);
            try {
                cnx.close();
            } catch (SQLException ex) {
                Logger.getLogger(FRProducto.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    } 
 
     
       public void categoria(){
           
             Connection cnx=con.conectar();
       String sql="select idcategoria,categoria from categoria";
       try{
           Statement st =cnx.createStatement();
           ResultSet rs =st.executeQuery(sql);
           String numero[]= new String[2];
          
          
           while (rs.next()) {
                 numero[0]=rs.getString("idcategoria");
             this.cbx_categoria.addItem(rs.getString("categoria"));
           
              
           }
           cnx.close();
       }catch(Exception e){
           JOptionPane.showMessageDialog(null, e);
                 try {
                     cnx.close();
                 } catch (SQLException ex) {
                     Logger.getLogger(FRProducto.class.getName()).log(Level.SEVERE, null, ex);
                 }
       }
       }
        public int categoriaX(){
           int categox=0;
             Connection cnx=con.conectar();
       String sql="select idcategoria,categoria from categoria";
       try{
           Statement st =cnx.createStatement();
           ResultSet rs =st.executeQuery(sql);
           String numero[]= new String[2];
          
          
           while (rs.next()) {
                 numero[0]=rs.getString("idcategoria");
                 numero[1]=rs.getString("categoria");
                if ( this.cbx_categoria.getSelectedItem().toString().equalsIgnoreCase(numero[1])) {
                   categox=Integer.parseInt(numero[0]);
               }
        //     this.cbx_categoria.addItem(rs.getString("categoria"));
           
              
           }
           cnx.close();
       }catch(Exception e){
           JOptionPane.showMessageDialog(null, e);
               try {
                   cnx.close();
               } catch (SQLException ex) {
                   Logger.getLogger(FRProducto.class.getName()).log(Level.SEVERE, null, ex);
               }
       }
       return categox;
       }
   
          public void proveedor(){
                 Connection cnx=con.conectar();
       String sql="select idproveedor,nombre from proveedores";
       try{
           Statement st =cnx.createStatement();
           ResultSet rs =st.executeQuery(sql);
           String numero[]= new String[1];
          
          
           while (rs.next()) {
               numero[0]=rs.getString("idproveedor");
              this.cbx_proveedor.addItem(rs.getString("nombre"));
           }
           cnx.close();
       }catch(Exception e){
           JOptionPane.showMessageDialog(null, e);
                     try {
                         cnx.close();
                     } catch (SQLException ex) {
                         Logger.getLogger(FRProducto.class.getName()).log(Level.SEVERE, null, ex);
                     }
       }
          }
           public int proveedorX(){
               int proverX=0;
                 Connection cnx=con.conectar();
       String sql="select idproveedor,nombre from proveedores";
       try{
           Statement st =cnx.createStatement();
           ResultSet rs =st.executeQuery(sql);
           String numero[]= new String[2];
           while (rs.next()) {
            numero[0]=rs.getString("idproveedor");
                 numero[1]=rs.getString("nombre");
                if ( this.cbx_proveedor.getSelectedItem().toString().equalsIgnoreCase(numero[1])) {
                   proverX=Integer.parseInt(numero[0]);
               }
           }
           cnx.close();
       }catch(Exception e){
           JOptionPane.showMessageDialog(null, e);
            try {
                         cnx.close();
                     } catch (SQLException ex) {
                         Logger.getLogger(FRProducto.class.getName()).log(Level.SEVERE, null, ex);
                     }
       }
       return proverX;
          }
    public void reporte(){
         String cons="";
            
         if(this.radio_nombre.isSelected()){
              cons="select productos1.codigo,productos1.nombre,productos1.descripcion,productos1.proveedor,proveedores.nombre,categoria.categoria,"
                     + "productos1.stock,productos1.precio,productos1.precioCompra from productos1 inner join proveedores on proveedores.idproveedor"
                     + "=productos1.idproveedor inner join categoria on categoria.idcategoria=productos1.categoria where productos1.nombre='"+this.txt_seleccion.getText()+"'";
            radioClientesId(cons);
            this.txt_seleccion.setText("");
       
        }else if(this.radio_identificador.isSelected()){
              cons=" select productos1.codigo,productos1.nombre,productos1.descripcion,productos1.proveedor,proveedores.nombre,categoria.categoria,"
                     + "productos1.stock,productos1.precio,productos1.precioCompra from productos1 inner join proveedores on proveedores.idproveedor=productos1.idproveedor"
                     + " inner join categoria on categoria.idcategoria=productos1.categoria where productos1.codigo='"+this.txt_seleccion.getText()+"'";
            radioClientesId(cons);
            this.txt_seleccion.setText("");
        }else if(this.radio_proveedor.isSelected()){
              cons=" select productos1.codigo,productos1.nombre,productos1.descripcion,productos1.proveedor,proveedores.nombre,categoria.categoria,"
                     + "productos1.stock,productos1.precio,productos1.precioCompra from productos1 inner join proveedores on proveedores.idproveedor=productos1.idproveedor"
                     + " inner join categoria on categoria.idcategoria=productos1.categoria where productos1.proveedor='"+this.txt_seleccion.getText()+"'";
            radioClientesId(cons);
            this.txt_seleccion.setText("");
        }else if(this.radio_fabricante.isSelected()){
              cons=" select productos1.codigo,productos1.nombre,productos1.descripcion,productos1.proveedor,proveedores.nombre,categoria.categoria,"
                     + "productos1.stock,productos1.precio,productos1.precioCompra from productos1 inner join proveedores on proveedores.idproveedor=productos1.idproveedor"
                     + " inner join categoria on categoria.idcategoria=productos1.categoria where proveedores.nombre='"+this.txt_seleccion.getText()+"'";
            radioClientesId(cons);
            this.txt_seleccion.setText("");
        }else if(this.radio_categoria.isSelected()){
              cons=" select productos1.codigo,productos1.nombre,productos1.descripcion,productos1.proveedor,proveedores.nombre,categoria.categoria,"
                     + "productos1.stock,productos1.precio,productos1.precioCompra from productos1 inner join proveedores on proveedores.idproveedor=productos1.idproveedor"
                     + " inner join categoria on categoria.idcategoria=productos1.categoria where categoria.categoria='"+this.txt_seleccion.getText()+"'";
            radioClientesId(cons);
            this.txt_seleccion.setText("");
        }
    }
    public String codigo_existente(){
                   String codigo=this.txt_codigo.getText();
            String cadena="";
       Connection cnx=con.conectar();
       String sql="select codigo  from productos1 where codigo="+codigo;
       try{
           Statement st =cnx.createStatement();
           ResultSet rs =st.executeQuery(sql);
           String numero[]= new String[1];
          
          
           while (rs.next()) {
             
            cadena=  numero[0]=rs.getString("codigo");
             
             
           }
           cnx.close();
       }catch(Exception e){
        //   JOptionPane.showMessageDialog(null, "debe ingresar un codigo "+e);
            try {
                         cnx.close();
                     } catch (SQLException ex) {
                         Logger.getLogger(FRProducto.class.getName()).log(Level.SEVERE, null, ex);
                     }
       }
       return cadena;
    }
   
    public void tabla1(){
          int seleccion=this.Tabla1.getSelectedRow();
        this.txt_nombre.setText(this.Tabla1.getValueAt(seleccion, 1).toString());
        this.txt_codigo.setText(this.Tabla1.getValueAt(seleccion, 0).toString());
        this.txt_descripcion.setText(this.Tabla1.getValueAt(seleccion, 2).toString());
        this.txt_proveedor.setText(this.Tabla1.getValueAt(seleccion, 3).toString());
        this.txt_precio.setText(this.Tabla1.getValueAt(seleccion, 7).toString());
        this.txt_precioCompra.setText(this.Tabla1.getValueAt(seleccion, 8).toString());
        this.txt_stock.setText(this.Tabla1.getValueAt(seleccion, 6).toString());
        this.cbx_categoria.setSelectedItem(this.Tabla1.getValueAt(seleccion, 5).toString());
          this.cbx_proveedor.setSelectedItem(this.Tabla1.getValueAt(seleccion, 4).toString());
        
    }
    
           public void insertarProducto(){
                Connection cnx=con.conectar();
          String sql="insert into productos1(nombre,descripcion,proveedor,stock,precio,precioCompra,idProveedor,categoria,codigo)"
                  + "values(?,?,?,?,?,?,?,?,?)";
          
          try{
              PreparedStatement pst=cnx.prepareStatement(sql);
              if (txt_codigo.getText().isEmpty()||txt_descripcion.getText().isEmpty()||txt_proveedor.getText().isEmpty()||
                      txt_stock.getText().isEmpty()||txt_precio.getText().isEmpty()||txt_precioCompra.getText().isEmpty()||txt_nombre.getText().isEmpty()) {
             JOptionPane.showMessageDialog(null,"debes de llenar todos los campos ","mensaje error",JOptionPane.ERROR_MESSAGE);
              }else if(codigo_existente().equalsIgnoreCase(this.txt_codigo.getText())){
                  JOptionPane.showMessageDialog(null,"el codigo ya existe en la base de datos ");
                  this.txt_codigo.setText("");
              }else{
                     
               pst.setString(1,this.txt_nombre.getText());
                pst.setString(2, this.txt_descripcion.getText());
                 pst.setString(3, this.txt_proveedor.getText());
               pst.setString(4, this.txt_stock.getText());
              pst.setString(5, this.txt_precio.getText());
              pst.setString(6, this.txt_precioCompra.getText());
              pst.setInt(7, proveedorX());
               pst.setInt(8, categoriaX());
              pst.setString(9, this.txt_codigo.getText());
           
              int select=pst.executeUpdate();
               if (select>0) {
                  JOptionPane.showMessageDialog(null, "datos ingresados correctamente");
                  limpiar();
              }else{
                   JOptionPane.showMessageDialog(null, "ingrese todos los campos ","mensaje error",JOptionPane.ERROR_MESSAGE);
               }
              }
           cnx.close();
          }catch(SQLIntegrityConstraintViolationException ex){
              JOptionPane.showMessageDialog(null, "debes seleccionar una categoria y proveedor");
               try {
                         cnx.close();
                     } catch (SQLException exq) {
                         Logger.getLogger(FRProducto.class.getName()).log(Level.SEVERE, null, ex);
                     }
              
          }catch(Exception e){
              JOptionPane.showMessageDialog(null, e);
               try {
                         cnx.close();
                     } catch (SQLException ex) {
                         Logger.getLogger(FRProducto.class.getName()).log(Level.SEVERE, null, ex);
                     }
          }
          
           }
           public void limpia_tabla(){
        
       
         model.getDataVector().removeAllElements();
        this.Tabla1.updateUI();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grupo1 = new javax.swing.ButtonGroup();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        btn_nuevo = new javax.swing.JButton();
        btn_guardar = new javax.swing.JButton();
        btn_modificar = new javax.swing.JButton();
        btn_cancelar = new javax.swing.JButton();
        btn_salir = new javax.swing.JButton();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel7 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        txt_seleccion = new javax.swing.JTextField();
        btn_reporte = new javax.swing.JButton();
        radio_nombre = new javax.swing.JRadioButton();
        radio_identificador = new javax.swing.JRadioButton();
        radio_proveedor = new javax.swing.JRadioButton();
        radio_fabricante = new javax.swing.JRadioButton();
        radio_categoria = new javax.swing.JRadioButton();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        Tabla1 = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txt_codigo = new javax.swing.JTextField();
        txt_proveedor = new javax.swing.JTextField();
        txt_stock = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txt_precio = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txt_precioCompra = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txt_nombre = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        cbx_proveedor = new javax.swing.JComboBox<>();
        cbx_categoria = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        txt_descripcion = new javax.swing.JTextArea();
        txt_busqueda = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Proveedores");
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
        btn_cancelar.setEnabled(false);
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
                .addGap(27, 27, 27)
                .addComponent(btn_guardar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_modificar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_salir, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );

        jPanel7.setBackground(new java.awt.Color(102, 204, 255));

        txt_seleccion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txt_seleccionMouseEntered(evt);
            }
        });
        txt_seleccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_seleccionActionPerformed(evt);
            }
        });
        txt_seleccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_seleccionKeyTyped(evt);
            }
        });

        btn_reporte.setText("Editar");
        btn_reporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_reporteActionPerformed(evt);
            }
        });

        grupo1.add(radio_nombre);
        radio_nombre.setText("Nombre o Producto");

        grupo1.add(radio_identificador);
        radio_identificador.setText("identificador");
        radio_identificador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radio_identificadorActionPerformed(evt);
            }
        });

        grupo1.add(radio_proveedor);
        radio_proveedor.setText("proveedor");
        radio_proveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radio_proveedorActionPerformed(evt);
            }
        });

        grupo1.add(radio_fabricante);
        radio_fabricante.setText("fabricante");
        radio_fabricante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radio_fabricanteActionPerformed(evt);
            }
        });

        grupo1.add(radio_categoria);
        radio_categoria.setText("categoria");
        radio_categoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radio_categoriaActionPerformed(evt);
            }
        });

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Img/Actualizar (2).png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(radio_nombre)
                        .addGap(18, 18, 18)
                        .addComponent(radio_identificador)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(radio_proveedor))
                    .addComponent(txt_seleccion, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(49, 49, 49)
                .addComponent(radio_fabricante)
                .addGap(53, 53, 53)
                .addComponent(radio_categoria)
                .addGap(34, 34, 34)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 256, Short.MAX_VALUE)
                .addComponent(btn_reporte, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(104, 104, 104))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(10, 11, Short.MAX_VALUE)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txt_seleccion, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(radio_categoria)
                            .addComponent(radio_fabricante)
                            .addComponent(radio_proveedor)
                            .addComponent(radio_identificador)
                            .addComponent(radio_nombre)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(btn_reporte, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Tabla1 = new javax.swing.JTable(){
            public boolean isCellEditable(int row,int column){

                if(column==1||column==2||column==3||column==7||column==8){
                    return true;
                }else{
                    return false;
                }

            }
        };
        Tabla1.setFocusable(false);
        Tabla1.getTableHeader().setReorderingAllowed(false);
        Tabla1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Tabla1MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(Tabla1);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 1113, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 55, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("buscar", jPanel7);

        jPanel8.setBackground(new java.awt.Color(102, 204, 255));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("codigo");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Descripcion");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Proveedor");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Stock");

        txt_codigo.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txt_codigo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_codigoFocusLost(evt);
            }
        });

        txt_proveedor.setEditable(false);
        txt_proveedor.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txt_proveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_proveedorActionPerformed(evt);
            }
        });

        txt_stock.setEditable(false);
        txt_stock.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("Precio ");

        txt_precio.setEditable(false);
        txt_precio.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setText("Precio Compra");

        txt_precioCompra.setEditable(false);
        txt_precioCompra.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txt_precioCompra.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_precioCompraFocusLost(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setText("nombre");

        txt_nombre.setEditable(false);
        txt_nombre.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txt_nombre.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_nombreFocusLost(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setText("Categoria");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel14.setText("Fabricante");

        cbx_proveedor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "selecciona un proveedor" }));

        cbx_categoria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "selecciona una categoria" }));

        txt_descripcion.setEditable(false);
        txt_descripcion.setColumns(20);
        txt_descripcion.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txt_descripcion.setRows(5);
        jScrollPane2.setViewportView(txt_descripcion);

        txt_busqueda.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txt_busqueda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_busquedaActionPerformed(evt);
            }
        });
        txt_busqueda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_busquedaKeyTyped(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Img/lupa.png"))); // NOI18N

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel3))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel5)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(txt_stock, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_precio, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane2)))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txt_precioCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbx_proveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbx_categoria, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addComponent(txt_codigo, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                                        .addGap(137, 137, 137))
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addComponent(txt_nombre)
                                        .addGap(6, 6, 6))))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_proveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 182, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(34, 34, 34)
                .addComponent(txt_busqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(184, 184, 184))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txt_busqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel12)
                                .addComponent(txt_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(11, 11, 11)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(txt_codigo, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(jLabel3))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, 30)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(txt_proveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbx_proveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addComponent(cbx_categoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_stock, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_precio, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel5))
                .addGap(31, 31, 31)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_precioCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(69, 69, 69))
        );

        jTabbedPane2.addTab("nuevo/modificar", jPanel8);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1138, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
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
     insertaDetalleCompra();
   insertaVenta();
    insertarProducto();
   
      // JOptionPane.showMessageDialog(null,"porvedor="+venta());
    }//GEN-LAST:event_btn_guardarActionPerformed

    private void radio_identificadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radio_identificadorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radio_identificadorActionPerformed

    private void btn_reporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_reporteActionPerformed
actualizar_producto1();     
    }//GEN-LAST:event_btn_reporteActionPerformed

    private void btn_nuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_nuevoActionPerformed
       this.btn_guardar.setEnabled(true);
       this.btn_modificar.setEnabled(true);
       this.jTabbedPane2.setSelectedIndex(1);
       this.txt_nombre.setEditable(true);
       this.btn_cancelar.setEnabled(true);
      // this.txt_codigo.setEditable(true);
       this.txt_descripcion.setEditable(true);
       this.txt_precio.setEditable(true);
       this.txt_precioCompra.setEditable(true);
       this.txt_stock.setEditable(true);
       this.txt_proveedor.setEditable(true);
       proveedor();
       categoria();
    }//GEN-LAST:event_btn_nuevoActionPerformed

    private void btn_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelarActionPerformed
       limpiar();
      
       this.txt_busqueda.setText("");
       this.txt_codigo.setEditable(true);
          this.jTabbedPane2.setSelectedIndex(1);
    }//GEN-LAST:event_btn_cancelarActionPerformed

    private void btn_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_salirActionPerformed
       dispose();
    }//GEN-LAST:event_btn_salirActionPerformed

    private void Tabla1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Tabla1MouseClicked
 
       
          tabla1();
          this.jTabbedPane2.setSelectedIndex(1);
      
    }//GEN-LAST:event_Tabla1MouseClicked

    private void btn_modificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_modificarActionPerformed
actualizar_producto();        // TODO add your handling code here:
    }//GEN-LAST:event_btn_modificarActionPerformed

    private void txt_precioCompraFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_precioCompraFocusLost
     
    }//GEN-LAST:event_txt_precioCompraFocusLost

    private void txt_proveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_proveedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_proveedorActionPerformed

    private void txt_nombreFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_nombreFocusLost
    
    }//GEN-LAST:event_txt_nombreFocusLost

    private void txt_seleccionMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_seleccionMouseEntered
      if(this.radio_nombre.isSelected()){
          completar("nombre");
      }else if(this.radio_proveedor.isSelected()){
          completar1("proveedor");
      }else if(this.radio_fabricante.isSelected()){
          completarProveedor();
      }else if(this.radio_categoria.isSelected()){
          completarCategoria();
      }
    }//GEN-LAST:event_txt_seleccionMouseEntered

    private void radio_proveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radio_proveedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radio_proveedorActionPerformed

    private void radio_fabricanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radio_fabricanteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radio_fabricanteActionPerformed

    private void radio_categoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radio_categoriaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radio_categoriaActionPerformed

    private void txt_seleccionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_seleccionKeyTyped
        if (evt.getKeyChar()==VK_ENTER) {
           
   reporte();
     
     
            //JOptionPane.showMessageDialog(null, "tecla enter ");
        } 
    }//GEN-LAST:event_txt_seleccionKeyTyped

    private void txt_codigoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_codigoFocusLost
       
   
    }//GEN-LAST:event_txt_codigoFocusLost

    private void txt_busquedaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_busquedaKeyTyped
     if(evt.getKeyChar()==VK_ENTER){
       radioClientesId();
       
     }
    }//GEN-LAST:event_txt_busquedaKeyTyped

    private void txt_seleccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_seleccionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_seleccionActionPerformed

    private void txt_busquedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_busquedaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_busquedaActionPerformed

   
    public static void main(String args[]) {
    
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FRProducto().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Tabla1;
    private javax.swing.JButton btn_cancelar;
    private javax.swing.JButton btn_guardar;
    private javax.swing.JButton btn_modificar;
    private javax.swing.JButton btn_nuevo;
    private javax.swing.JButton btn_reporte;
    private javax.swing.JButton btn_salir;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbx_categoria;
    private javax.swing.JComboBox<String> cbx_proveedor;
    private javax.swing.ButtonGroup grupo1;
    private javax.swing.JLabel jLabel1;
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JRadioButton radio_categoria;
    private javax.swing.JRadioButton radio_fabricante;
    private javax.swing.JRadioButton radio_identificador;
    private javax.swing.JRadioButton radio_nombre;
    private javax.swing.JRadioButton radio_proveedor;
    private javax.swing.JTextField txt_busqueda;
    private javax.swing.JTextField txt_codigo;
    private javax.swing.JTextArea txt_descripcion;
    private javax.swing.JTextField txt_nombre;
    private javax.swing.JTextField txt_precio;
    private javax.swing.JTextField txt_precioCompra;
    private javax.swing.JTextField txt_proveedor;
    private javax.swing.JTextField txt_seleccion;
    private javax.swing.JTextField txt_stock;
    // End of variables declaration//GEN-END:variables
}
