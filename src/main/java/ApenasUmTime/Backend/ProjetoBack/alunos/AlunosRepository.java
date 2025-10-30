package ApenasUmTime.Backend.ProjetoBack.alunos;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunosRepository extends JpaRepository<Alunos,Integer> {
    public Alunos findByCpf(String cpf);
}
