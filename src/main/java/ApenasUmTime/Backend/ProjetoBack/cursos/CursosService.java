package ApenasUmTime.Backend.ProjetoBack.cursos;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursosService {

    @Autowired
    private CursosRepository cursosRepository;

    public List<Cursos> listarCursos() {
        return cursosRepository.findAll();
    }

    public Cursos adicionarCurso(Cursos curso) {
        return cursosRepository.save(curso);
    }
    public Optional<Cursos> buscarCursoPorId(Integer id) {
        return cursosRepository.findById(id);
    }

    public Cursos salvarCurso(Cursos curso) {
        return cursosRepository.save(curso);
    }

    public Cursos atualizarCurso(Integer id, Cursos cursoAtualizado) {
        return cursosRepository.findById(id)
                .map(curso -> {
                    curso.setNome(cursoAtualizado.getNome());
                    curso.setSemestre(cursoAtualizado.getSemestre());
                    return cursosRepository.save(curso);
                })
                .orElseThrow(() -> new RuntimeException("Curso não encontrado"));
    }

    public void deletarCurso(Integer id) {
        cursosRepository.deleteById(id);
    }


}
