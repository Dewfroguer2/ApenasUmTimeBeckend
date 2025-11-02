package ApenasUmTime.Backend.ProjetoBack.cursos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursosRepository extends JpaRepository<Cursos, Integer> {
}
