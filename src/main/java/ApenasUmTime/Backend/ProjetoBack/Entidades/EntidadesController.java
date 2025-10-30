package ApenasUmTime.Backend.ProjetoBack.Entidades;

import ApenasUmTime.Backend.ProjetoBack.Entidades.EntidadesDTOs.EntidadeDtoComun;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/entidades")
public class EntidadeController {

    @Autowired
    private EntidadesService entidadesService;

    @GetMapping
    public ResponseEntity<List<EntidadeDtoComun>> listarEntidades() {
        List<EntidadeDtoComun> entidades = entidadesService.listaEntidades();
        return ResponseEntity.ok(entidades);
    }

    @GetMapping("/{nome}")
    public ResponseEntity<Entidades> buscarEntidade(@PathVariable String nome) {
        Entidades entidade = entidadesService.getEntidade(nome);
        if (entidade == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(entidade);
    }

    @PostMapping
    public ResponseEntity<EntidadeDtoComun> criarEntidade(@RequestBody EntidadeDtoComun dto) {
        try {
            EntidadeDtoComun novaEntidade = entidadesService.criaEntidade(dto);
            return ResponseEntity.ok(novaEntidade);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/{nomeEntidade}/adicionar-aluno")
    public ResponseEntity<String> adicionarAluno( @PathVariable String nomeEntidade, @RequestBody Alunos aluno
    ) {
        try {
            entidadesService.adicionaAluno(nomeEntidade, aluno);
            return ResponseEntity.ok("Aluno adicionado com sucesso à entidade " + nomeEntidade);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/{nomeEntidade}/remover-aluno")
    public ResponseEntity<String> removerAluno( @PathVariable String nomeEntidade, @RequestBody Alunos aluno ) {
        try {
            entidadesService.removeAluno(nomeEntidade, aluno);
            return ResponseEntity.ok("Aluno removido com sucesso da entidade " + nomeEntidade);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{nomeEntidade}")
    public ResponseEntity<String> excluirEntidade(@PathVariable String nomeEntidade) {
        try {
            entidadesService.excluiEntidade(nomeEntidade);
            return ResponseEntity.ok("Entidade excluída com sucesso!");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
