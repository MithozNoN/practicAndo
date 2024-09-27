package ml.hibernatetest.pruebaJPA.service;

import ml.hibernatetest.pruebaJPA.model.Mascota;
import ml.hibernatetest.pruebaJPA.repository.IMascotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MascotaService implements IMascotaService{

    @Autowired
    private IMascotaRepository mascotaRepo;

    @Override
    public List<Mascota> getMascotas() {
        List<Mascota> listaMascotas = mascotaRepo.findAll();
        return listaMascotas;
    }

    @Override
    public void saveMascota(Mascota masco) {
        if(masco.getId_mascota() == null) {
            Long nextId = mascotaRepo.findNextAvailableId();
            masco.setId_mascota(nextId); // Asigna el nuevo ID a la mascota
        }
        mascotaRepo.save(masco);
    }

    @Override
    public void deleteMascota(Long id_mascota) {
        mascotaRepo.deleteById(id_mascota);
    }

    @Override
    public Mascota findMascota(Long id_mascota) {
        return mascotaRepo.findById(id_mascota).orElse(null);
    }

    @Override
    public void editMascota(Long idOriginal,
                            String nuevoNombre,
                            String nuevaEspecie,
                            String nuevaRaza,
                            String nuevoColor) {
        //Busco el objeto original
        Mascota masco = this.findMascota(idOriginal);

        //Comprobamos que el elemento exista, si existe, sobreeescribe la información
        if (masco != null) {
            if(nuevoNombre != null) {
                masco.setNombre(nuevoNombre);
            }
            if(nuevoNombre != null) {
                masco.setEspecie(nuevaEspecie);
            }
            if(nuevoNombre != null) {
                masco.setRaza(nuevaRaza);
            }
            if(nuevoNombre != null) {
                masco.setColor(nuevoColor);
            }
            //Guardar los cambios
            this.saveMascota(masco);
        }
    }
}
