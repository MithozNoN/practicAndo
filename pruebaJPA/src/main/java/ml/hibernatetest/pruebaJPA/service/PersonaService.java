package ml.hibernatetest.pruebaJPA.service;

import ml.hibernatetest.pruebaJPA.model.Persona;
import ml.hibernatetest.pruebaJPA.repository.IPersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonaService implements IPersonaService{

    @Autowired
    private IPersonaRepository personaRepo;


    @Override
    public List<Persona> getPersonas() {
        List<Persona> listaPersonas = personaRepo.findAll();
        return listaPersonas;
    }

    @Override
    public void savePersona(Persona perso) {
        // Si la persona no tiene un ID, asignamos el próximo ID disponible.
        if (perso.getId() == null) {
            Long nextId = personaRepo.findNextAvailableId();
            perso.setId(nextId);
        }
        personaRepo.save(perso);
    }

    @Override
    public void deletePersona(Long id) {
        personaRepo.deleteById(id);
    }

    @Override
    public Persona findPersona(Long id) {
        Persona perso = personaRepo.findById(id).orElse(null);
        return perso;
    }

    @Override
    public void editPersona(Long idOriginal,
                            String nuevoNombre,
                            String nuevoApellido,
                            Integer nuevaEdad) {
        //Busco el objeto original
        Persona perso = this.findPersona(idOriginal);

        //Comprobamos que el elemento exista, si existe, sobreeescribe la información
        if (perso != null) {
            // Solo actualiza los valores que no sean null
            if (nuevoNombre != null) {
                perso.setNombre(nuevoNombre);
            }
            if (nuevoApellido != null) {
                perso.setApellido(nuevoApellido);
            }
            if (nuevaEdad != null) {
                perso.setEdad(nuevaEdad);
            }
            this.savePersona(perso);
        }
    }

    @Override
    public void editPersona(Persona per) {
        this.savePersona(per);
    }
}
