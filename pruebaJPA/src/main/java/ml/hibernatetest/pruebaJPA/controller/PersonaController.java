package ml.hibernatetest.pruebaJPA.controller;
import ml.hibernatetest.pruebaJPA.model.Persona;
import ml.hibernatetest.pruebaJPA.service.IPersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonaController {

    @Autowired
    private IPersonaService persoServ;

    @GetMapping("/personas/traer")
    public List<Persona> getPersonas() {
        return persoServ.getPersonas();

    }

    @PostMapping("/personas/crear")
    public String savePersona (@RequestBody Persona perso) {
        persoServ.savePersona(perso);

        return "La persona fue creada correctamente";
    }

    @DeleteMapping("/personas/eliminar/{id}")
    public String deletPersona(@PathVariable Long id) {
        persoServ.deletePersona(id);

        return "La persona ha sido eliminada correctamente";
    }

    @PutMapping("/personas/modificar/{id}")
    public Persona editPersona (@PathVariable Long id,
                                @RequestParam(required = false, name = "id") Long nuevaId,
                                @RequestParam(required = false, name = "nombre") String nuevoNombre,
                                @RequestParam(required = false, name = "apellido") String nuevoApellido,
                                @RequestParam(required = false, name = "edad") int nuevaEdad) {
        Persona personaModificada = persoServ.editPersona(id, nuevaId,nuevoNombre, nuevoApellido, nuevaEdad);
        return personaModificada;

    }

}
