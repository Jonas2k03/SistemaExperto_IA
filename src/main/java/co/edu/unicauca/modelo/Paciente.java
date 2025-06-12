package co.edu.unicauca.modelo;

import lombok.Data;
import java.util.List;

@Data
public class Paciente {
    private String nombre;
    private List<String> sintomas;

    public Paciente(String nombre, List<String> sintomas) {
        this.nombre = nombre;
        this.sintomas = sintomas;
    }

    public String getNombre() {
        return nombre;
    }

    public List<String> getSintomas() {
        return sintomas;
    }




}
