package ml.hibernatetest.pruebaJPA.service;

import ml.hibernatetest.pruebaJPA.model.Mascota;

import java.util.List;

public interface IMascotaService {

    //Método para traer a todas las mascotas
    //Lectura

    public List<Mascota> getMascotas();

    //Alta
    public void saveMascota(Mascota masco);

    //Baja
    public void deleteMascota(Long id_mascota);

    //Lectura de un solo objeto
    public Mascota findMascota(Long id_mascota);

    //Edición - Modificación
    public void editMascota (Long idOriginal,
                                String nuevoNombre,
                                String nuevoEspecie,
                                String nuevaRaza,
                                String nuevoColor);
}
