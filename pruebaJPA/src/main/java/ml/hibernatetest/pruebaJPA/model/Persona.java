package ml.hibernatetest.pruebaJPA.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@Entity
public class Persona {

    @Id
    private Long id;

    private String nombre;
    private String apellido;
    private Integer edad;
    @OneToMany
    private List<Mascota> listaMascotas;

    public Persona() {

    }

    public Persona(Long id, String nombre, String apellido, Integer edad, List<Mascota> listaMascotas) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.listaMascotas = listaMascotas;
    }
}
