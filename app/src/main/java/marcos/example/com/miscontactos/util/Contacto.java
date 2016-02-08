package marcos.example.com.miscontactos.util;

import java.io.Serializable;

/**
 * Created by Marcos on 07/02/2016.
 */
public class Contacto implements Serializable{

    private String nombre,direccion,telefono,email;
    //private Uri imageUri; No es posible serializar objetos Uri, por eso se crea un String
    private String imageUri;

    public Contacto(String nombre, String direccion, String telefono, String email, String imageUri) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.imageUri = imageUri;
    }

    //<editor-fold desc="GETTER METODOS">


    public String getImageUri() {
        return imageUri;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getNombre() {
        return nombre;
    }
    //</editor-fold>


    //<editor-fold desc="SETTER METODOS">

    public void setImageUri(String imageUri) {this.imageUri = imageUri;}

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    //</editor-fold>


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contacto contacto = (Contacto) o;

        if (!nombre.equals(contacto.nombre)) return false;
        if (direccion != null ? !direccion.equals(contacto.direccion) : contacto.direccion != null)
            return false;
        if (telefono != null ? !telefono.equals(contacto.telefono) : contacto.telefono != null)
            return false;
        if (email != null ? !email.equals(contacto.email) : contacto.email != null) return false;
        return !(imageUri != null ? !imageUri.equals(contacto.imageUri) : contacto.imageUri != null);

    }

    @Override
    public int hashCode() {
        int result = nombre.hashCode();
        result = 31 * result + (direccion != null ? direccion.hashCode() : 0);
        result = 31 * result + (telefono != null ? telefono.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (imageUri != null ? imageUri.hashCode() : 0);
        return result;
    }
}

