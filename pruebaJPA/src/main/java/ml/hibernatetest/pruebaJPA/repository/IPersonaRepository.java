package ml.hibernatetest.pruebaJPA.repository;
import ml.hibernatetest.pruebaJPA.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IPersonaRepository extends JpaRepository<Persona, Long> {

    @Query(value = "SELECT COALESCE(MIN(t1.id + 1), 1) " +
            "FROM persona t1 " +
            "WHERE NOT EXISTS (SELECT 1 FROM persona t2 WHERE t2.id = t1.id + 1)",
            nativeQuery = true)
    Long findNextAvailableId();

}
