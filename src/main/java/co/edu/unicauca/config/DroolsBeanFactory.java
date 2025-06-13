package co.edu.unicauca.config;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieRepository;
import org.kie.api.builder.ReleaseId;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;
import org.springframework.stereotype.Component;

@Component
public class DroolsBeanFactory {

    private String ruleFile = "co/edu/unicauca/rules/reglas.drl";
    private final KieServices kieServices = KieServices.Factory.get();

    private KieFileSystem getKieFileSystem() {
        System.out.println("=== INICIO CARGA DE REGLAS ===");
        System.out.println("Cargando reglas desde: " + ruleFile);
        KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
        kieFileSystem.write(ResourceFactory.newClassPathResource(ruleFile));
        System.out.println("Reglas cargadas en KieFileSystem");
        return kieFileSystem;
    }

    public KieSession getKieSession() {
        System.out.println("=== INICIO CREACIÓN DE SESIÓN DROOLS ===");
        KieBuilder kb = kieServices.newKieBuilder(getKieFileSystem());
        System.out.println("Iniciando build de reglas...");
        kb.buildAll();
        System.out.println("Build completado. Verificando errores...");
        if (kb.getResults().hasMessages(org.kie.api.builder.Message.Level.ERROR)) {
            System.out.println("ERRORES EN LA COMPILACIÓN DE REGLAS:");
            kb.getResults().getMessages(org.kie.api.builder.Message.Level.ERROR).forEach(error -> 
                System.out.println("Error: " + error.getText())
            );
        } else {
            System.out.println("No se encontraron errores en la compilación de reglas");
        }
        
        KieRepository kieRepository = kieServices.getRepository();
        ReleaseId krDefaultReleaseId = kieRepository.getDefaultReleaseId();
        KieContainer kieContainer = kieServices.newKieContainer(krDefaultReleaseId);
        System.out.println("Sesión Drools creada exitosamente");
        System.out.println("=== FIN CREACIÓN DE SESIÓN DROOLS ===");
        return kieContainer.newKieSession();
    }

}