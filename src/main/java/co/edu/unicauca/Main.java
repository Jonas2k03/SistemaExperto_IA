package co.edu.unicauca;

import co.edu.unicauca.modelo.Paciente;
import co.edu.unicauca.modelo.EscenciaFloral;
import co.edu.unicauca.service.PacientService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        // Iniciar el contexto de Spring
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("co.edu.unicauca"); // Escanear el paquete de tu aplicación
        context.refresh();

        // Obtener el servicio PacientService desde el contexto de Spring
        PacientService pacientService = context.getBean(PacientService.class);

        // Crear un paciente con el síntoma "miedo"
        Paciente paciente = new Paciente("Juan", Arrays.asList("miedo"));

        // Aplicar las reglas de Drools al paciente
        pacientService.applyRules(paciente);

        // Cerrar el contexto de Spring
        context.close();
    }
}
