package ml.hibernatetest.pruebaJPA.repository;

import ml.hibernatetest.pruebaJPA.model.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IMascotaRepository extends JpaRepository <Mascota, Long> {

    @Query(value = "SELECT COALESCE(MIN(t1.id_mascota + 1), 1) " +
            "FROM mascota t1 " +
            "WHERE NOT EXISTS (SELECT 1 FROM mascota t2 WHERE t2.id_mascota = t1.id_mascota + 1)",
            nativeQuery = true)
    Long findNextAvailableId();

}
