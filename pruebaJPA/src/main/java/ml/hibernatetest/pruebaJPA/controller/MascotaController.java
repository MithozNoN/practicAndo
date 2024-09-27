package ml.hibernatetest.pruebaJPA.controller;

import ml.hibernatetest.pruebaJPA.model.Mascota;
import ml.hibernatetest.pruebaJPA.service.IMascotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MascotaController {
    @Autowired
    private IMascotaService mascoServ;

    @GetMapping("/mascotas/traer")
    public List<Mascota> getMascotas() {
        return mascoServ.getMascotas();
    }

    @PostMapping("/mascotas/crear")
    public String savePersona (@RequestBody Mascota masco) {
        mascoServ.saveMascota(masco);

        return "La mascota fue creada correctamente";
    }

    @DeleteMapping("/mascotas/eliminar/{id}")
    public String deletPersona(@PathVariable Long id) {
        mascoServ.deleteMascota(id);

        return "La mascota ha sido eliminada correctamente";
    }

    @PutMapping("/mascotas/modificar/{id_original}")
    public Mascota editPersona (@PathVariable Long id_original,
                                @RequestParam(required = false, name = "nombre") String nuevoNombre,
                                @RequestParam(required = false, name = "especie") String nuevaEspecie,
                                @RequestParam(required = false, name = "raza") String nuevaRaza,
                                @RequestParam(required = false, name = "color") String nuevoColor){

        mascoServ.editMascota(id_original, nuevoNombre, nuevaEspecie, nuevaRaza, nuevoColor );

        Mascota masco = mascoServ.findMascota(id_original);

        return masco;
    }
}
