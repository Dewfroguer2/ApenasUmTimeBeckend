package ApenasUmTime.Backend.ProjetoBack.Entidades;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntidadesRepository extends JpaRepository<Entidades, Integer> {
    Entidades findByName(String nome);
}
