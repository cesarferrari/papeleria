
package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.*;
import vista.*;

public class Modelo extends Conexion{
     private boolean name=false;
private boolean prov=false;
private String id="";
private String usuario;
private String contraseña;
private String rol;
   public String user="";
public boolean banda=false;
    Productos pr=new Productos();
    ConsultasSQL sql= new ConsultasSQL();
 // JTable TABLA=new JTable();
    public Modelo(){
        
    }
  public void login(Productos pr){
      usuario=pr.getUsuario();
      contraseña=pr.getContraseña();
      rol=pr.getRol();
      JOptionPane.showMessageDialog(null, usuario+" "+contraseña+" "+rol );
  }
  public boolean buscar(Productos pr){
       boolean bandera=false;
      try {
             Connection cnx=  super.conectar();
            Statement pst=cnx.createStatement();
             ResultSet rs=pst.executeQuery("select*from vendedor");
             String inicio[]=new String[3];
             while (rs.next()) {
                 inicio[0]=rs.getString("usuario");
                 inicio[1]=rs.getString("contraseña");
                 inicio[2]=rs.getString("rol");
                 if (pr.getContraseña()==null) {
                     pr.setContraseña("");
                 }
                 if (inicio[0].equalsIgnoreCase(pr.getUsuario())&&inicio[1].equalsIgnoreCase(pr.getContraseña())
                         &&inicio[2].equalsIgnoreCase(pr.getRol())) {
                    
                    pr. setUsuario(inicio[0]);
                    pr.setRol(inicio[1]);
                     
                     Ventas.user=pr.getUsuario();
                     Compras.user=pr.getUsuario();
                     Contraseña.user=pr.getUsuario();
                     FRProducto.user=pr.getUsuario();
                     Principal.user=pr.getUsuario();
                     ventas_vendedor.user=pr.getUsuario();
                     
                     bandera=true;
                 }
             }
            
         } catch (NumberFormatException e) {
             Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, e);
         }catch(SQLException ex){
             
         }
      return bandera;
  }
  public boolean acceso(){
        boolean bandera=false;   
     
        
        JOptionPane.showMessageDialog(null,rol);
        return bandera;
  }
    
        
    
        public boolean actualizar(
        ){
      
   //   Modelo cnx=new Modelo();
    //    Connection conectar=cnx.conectar();
        String SQL=("update oficina set nombre='"+pr.getNombre()+"',descripcion='"+pr.getDescripcion()+"',stock="
                      + pr.getStock()+",precio="+pr.getPrecio()+",precioVenta="+pr.getPrecioVenta()+",proveedor='"+pr.getProveedor()
              +"where id="+pr.getId());
        try{
            PreparedStatement pst=conectar.prepareStatement( SQL);
            pst.executeUpdate();
             JOptionPane.showMessageDialog(null, "los datos se actualizaron correctamente"+"mensaje"+JOptionPane.INFORMATION_MESSAGE);
          //  tabla1("select*from productos");
            pst.close();
           
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        return true;
    }
    public void insertar(Productos pr){
   Modelo cnx= new Modelo();
    Connection conex=cnx.conectar();
    String insertar="insert into productos(nombre,stock,descripcion,proveedor,precio,precioCompra,idproveedor,categoria,codigo)values(?,?,?,?,?,?,1,?,?)";
 // SQLinsertar=  "insert into productos(id,nombre,descripcion,proveedor,stock,precio,precioCompra)values(?,?,?,?,?,?,?)";
 //  String sql2="select*from oficina";
   
 //   String nombre_siguiente=txt_nombre.getText();
   // String proveedor_siguiente=txt_proveedor.getText();
           
     try{
       // Statement     st = conex.createStatement();
      //  ResultSet rs=st.executeQuery(sql2);
         PreparedStatement pst=conex.prepareStatement(insertar);
       //  pst.setInt(1,pr.getId() );
        
         pst.setString(1, pr.getNombre());
         pst.setString(2, pr.getDescripcion());
          pst.setString(3,pr.getProveedor());
          pst.setInt(4,pr.getStock() );
            pst.setDouble(5,pr.getPrecio());
              pst.setDouble(6,pr.getPrecioVenta());
              pst.setString(7, pr.getCategoria());
               pst.setString(8, pr.getCodigo());
        
        
       
       
       
       /* while (rs.next()) {
             
                   rs.getString("id");
                rs.getString("nombre");
            rs.getString("descripcion");
             rs.getString("stock");
            
                        rs.getString("precio");
                        rs.getString("precioCompra");
                        rs.getString("proveedor");
                   txtArea.append(rs.getString("id")+" "+rs.getString("nombre")+" "+rs.getString("descripcion")
                                +" "+rs.getString("proveedor")+" "+rs.getString("stock")+" "+
                 rs.getString("precioCompra")+" "+rs.getString("precio")+" \n");
             
                
              if (name==true) {
                  if (prov==true) {
                      
                  }
                    JOptionPane.showMessageDialog(null, "datos duplicados","mensaje error",JOptionPane.ERROR_MESSAGE);
                    fr.txt_id.setText(id);
        fr.txt_nombre.setText("");
        fr.txt_descripcion.setText("");
        fr.txt_proveedor.setText("");
        fr.txt_precio.setText("");
      
        fr.txt_stock.setText("");
       fr. txt_precioVenta.setText("");
              }*/
                      
           
             
              
             
        
       int numero=pst.executeUpdate();
         if (numero>0) {
            
             JOptionPane.showMessageDialog(null, "datos ingresados correctamente", "mensaje", JOptionPane.INFORMATION_MESSAGE);
      
         }else{
             JOptionPane.showMessageDialog(null, "datos duplicados","mensaje error",JOptionPane.ERROR_MESSAGE);
         }
     }catch(NumberFormatException e){
        JOptionPane.showMessageDialog(null, e+"el formatos de datos numericos es incorrecto", "mensaje error", JOptionPane.ERROR_MESSAGE);
     
     }catch(SQLException es){
          es.printStackTrace();
     }
   
}
     public boolean eliminar(Productos pr){
        
          pr.getSeleccion();
       
         pr.getAux() ;
        
        Conexion cnx= new Conexion();
        Connection conectar=cnx.conectar();
        try{
            PreparedStatement pst=conectar.prepareStatement(sql.getSqlDelete()+pr.getAux());
            pst.executeUpdate();
             JOptionPane.showMessageDialog(null, "los datos se eliminaron correctamente"+"mensaje"+JOptionPane.INFORMATION_MESSAGE);
           
             conectar.close();
           
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        return true;
    }
}
