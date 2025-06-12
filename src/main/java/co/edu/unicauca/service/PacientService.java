package co.edu.unicauca.service;

import co.edu.unicauca.config.DroolsBeanFactory;
import co.edu.unicauca.modelo.Paciente;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PacientService {
    @Autowired
    private DroolsBeanFactory droolsBeanFactory;

    public Paciente applyRules(Paciente paciente) {
        KieSession kieSession = droolsBeanFactory.getKieSession();

        try {
            kieSession.insert(paciente);
            kieSession.fireAllRules();
        } finally {
            kieSession.dispose();
        }
        System.out.printf("Paciente: %s%n", paciente.getNombre());
        System.out.printf("Sintomas: %s%n", paciente.getSintomas());
        return paciente;
    }
}
