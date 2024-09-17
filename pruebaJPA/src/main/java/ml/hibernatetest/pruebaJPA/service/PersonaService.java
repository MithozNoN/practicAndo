package ml.hibernatetest.pruebaJPA.service;

import ml.hibernatetest.pruebaJPA.model.Persona;
import ml.hibernatetest.pruebaJPA.repository.IPersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Persona editPersona(Long idOriginal, Long nuevaId, String nuevoNombre, String nuevoApellido, int nuevaEdad) {
        // Busca la persona original
        Persona perso = this.findPersona(idOriginal);

        if (perso == null) {
            throw new IllegalArgumentException("Persona con id " + idOriginal + " no encontrada.");
        }

        // Si el id ha cambiado y nuevaId es diferente de null
        if (nuevaId != null && !idOriginal.equals(nuevaId)) {
            // Verifica si ya existe una persona con el nuevo id
            if (this.findPersona(nuevaId) != null) {
                throw new IllegalArgumentException("Persona con id " + nuevaId + " ya existe.");
            }

            // Elimina la persona con el id original
            this.deletePersona(idOriginal);

            // Crea una nueva persona con el nuevo id
            Persona nuevaPersona = new Persona();
            nuevaPersona.setId(nuevaId);
            nuevaPersona.setNombre(nuevoNombre != null ? nuevoNombre : perso.getNombre());
            nuevaPersona.setApellido(nuevoApellido != null ? nuevoApellido : perso.getApellido());
            nuevaPersona.setEdad(nuevaEdad > 0 ? nuevaEdad : perso.getEdad());

            // Guarda la nueva persona con el id modificado
            this.savePersona(nuevaPersona);
            return nuevaPersona;
        } else {
            // Si el id no ha cambiado, actualiza los demás atributos
            if (nuevoNombre != null) perso.setNombre(nuevoNombre);
            if (nuevoApellido != null) perso.setApellido(nuevoApellido);
            if (nuevaEdad > 0) perso.setEdad(nuevaEdad);

            // Guarda la persona con los datos actualizados
            this.savePersona(perso);
            return perso;
        }
    }
}
