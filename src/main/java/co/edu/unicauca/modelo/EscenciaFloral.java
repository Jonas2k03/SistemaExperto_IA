package co.edu.unicauca.modelo;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class EscenciaFloral {

    private String nombre;
    private String imagen;

    public String getNombre() {
        return nombre;
    }

    public String getImagen() {
        return imagen;
    }



}
