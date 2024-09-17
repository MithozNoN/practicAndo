package ml.hibernatetest.pruebaJPA.service;

import ml.hibernatetest.pruebaJPA.model.Persona;
import java.util.List;

public interface IPersonaService {

    //Método para traer a todas las personas
    //Lectura
    public List<Persona> getPersonas();

    //alta
    public void savePersona(Persona perso);

    //baja
    public void deletePersona(Long id);

    //lectura de un solo objeto

    public Persona findPersona(Long id);

    //edicioón/modificación

    public Persona editPersona (Long id, Long idNueva,
                                String nuevoNombre,
                                String nuevoApellido,
                                int nuevaEdad);
}
