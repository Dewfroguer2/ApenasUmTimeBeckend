package ApenasUmTime.Backend.ProjetoBack.cursos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cursos")
public class CursosController {

    @Autowired
    private CursosService cursosService;

    @GetMapping
    public List<Cursos> listarCursos() {
        return cursosService.listarCursos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cursos> buscarCursoPorId(@PathVariable Integer id) {
        return cursosService.buscarCursoPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Cursos adicionarCurso(@RequestBody Cursos curso) {
        return cursosService.adicionarCurso(curso);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cursos> atualizarCurso(@PathVariable Integer id, @RequestBody Cursos curso) {
        try {
            return ResponseEntity.ok(cursosService.atualizarCurso(id, curso));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCurso(@PathVariable Integer id) {
        cursosService.deletarCurso(id);
        return ResponseEntity.noContent().build();
    }
}
