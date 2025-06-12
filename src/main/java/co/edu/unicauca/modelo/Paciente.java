package co.edu.unicauca.modelo;

import lombok.Data;
import java.util.List;

@Data
public class Paciente {
    private String nombre;
    private List<String> sintomas;
    private List<EscenciaFloral> recomendaciones;

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

    public List<EscenciaFloral> getRecomendaciones() {
        return recomendaciones;
    }

    public void addRecomendacion(EscenciaFloral esencia) {
        if (recomendaciones == null) {
            recomendaciones = new java.util.ArrayList<>();
        }
        recomendaciones.add(esencia);
    }




}
