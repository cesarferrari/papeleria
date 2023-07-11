
package modelo;


public class ConsultasSQL {
    private String usuario="";
    private String contraseña="";
    private String rol="";
    private String SQLMostrar="";
 private String sqlUpdate="";
   private String sqlActualizar="";
   private String sqlDelete="";
   private String SQLinsert="";

    public String getSqlUpdate() {
        return sqlUpdate;
    }

    public void setSqlUpdate(String sqlUpdate) {
        this.sqlUpdate = sqlUpdate;
    }

    public String getSqlActualizar() {
        return sqlActualizar;
    }

    public void setSqlActualizar(String sqlActualizar) {
        this.sqlActualizar = sqlActualizar;
    }

    public String getSqlDelete() {
        return sqlDelete;
    }

    public void setSqlDelete(String sqlDelete) {
        this.sqlDelete = sqlDelete;
    }

    public String getSQLinsert() {
        return SQLinsert;
    }

    public void setSQLinsert(String SQLinsert) {
        this.SQLinsert = SQLinsert;
    }
    public String getSQLMostrar() {
        return SQLMostrar;
    }

    public void setSQLMostrar(String SQLMostrar) {
        this.SQLMostrar = SQLMostrar;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
    
}
