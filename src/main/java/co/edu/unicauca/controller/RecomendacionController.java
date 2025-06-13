package co.edu.unicauca.controller;

import co.edu.unicauca.modelo.Paciente;
import co.edu.unicauca.modelo.EscenciaFloral;
import co.edu.unicauca.service.PacientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/recomendaciones")
public class RecomendacionController {
    @Autowired
    private PacientService pacientService;

    @PostMapping("/obtener")
    public List<EscenciaFloral> obtenerRecomendaciones(@RequestBody Map<String, Object> payload) {
        String nombre = (String) payload.get("nombre");
        @SuppressWarnings("unchecked")
        List<String> sintomas = (List<String>) payload.get("sintomas");
        System.out.println("Síntomas recibidos: " + sintomas);
        
        Paciente paciente = new Paciente(nombre, sintomas);
        pacientService.applyRules(paciente);
        
        List<EscenciaFloral> recomendaciones = paciente.getRecomendaciones();
        
        return recomendaciones;
    }
} 