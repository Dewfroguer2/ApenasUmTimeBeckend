package ApenasUmTime.Backend.ProjetoBack.Entidades;


import ApenasUmTime.Backend.ProjetoBack.Entidades.EntidadesDTOs.EntidadeDtoComun;
import ApenasUmTime.Backend.ProjetoBack.alunos.Alunos;
import ApenasUmTime.Backend.ProjetoBack.alunos.dto.AlunosResponseDTO;
import ApenasUmTime.Backend.ProjetoBack.autenticacao.Usuario;
import ApenasUmTime.Backend.ProjetoBack.autenticacao.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/entidades")
public class EntidadesController {

    @Autowired
    private UsuarioService usuarioService;

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

    @GetMapping("/{nomeEntidade}/alunos")
    public ResponseEntity<List<AlunosResponseDTO>> listarAlunosPorEntidade(
            @PathVariable String nomeEntidade) {
        try {
            List<AlunosResponseDTO> alunos = entidadesService.listarAlunosPorEntidade(nomeEntidade);
            return ResponseEntity.ok(alunos);
        } catch (RuntimeException e) {
            if (e.getMessage().contains("não encontrada")) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping
    public ResponseEntity<EntidadeDtoComun> criarEntidade(
            @RequestBody EntidadeDtoComun dto,
            @RequestHeader("Authorization") String token) {

        try {
            EntidadeDtoComun novaEntidade = entidadesService.criaEntidade(dto);
            return ResponseEntity.ok(novaEntidade);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/{nomeEntidade}/adicionar-aluno")
    public ResponseEntity<String> adicionarAluno(
            @PathVariable String nomeEntidade,
            @RequestBody Alunos aluno,
            @RequestHeader("Authorization") String token) {

        try {
            entidadesService.adicionaAluno(nomeEntidade, aluno);
            return ResponseEntity.ok("Aluno adicionado com sucesso à entidade " + nomeEntidade);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/{nomeEntidade}/remover-aluno")
    public ResponseEntity<String> removerAluno(
            @PathVariable String nomeEntidade,
            @RequestBody Alunos aluno,
            @RequestHeader("Authorization") String token) {

        try {
            entidadesService.removeAluno(nomeEntidade, aluno);
            return ResponseEntity.ok("Aluno removido com sucesso da entidade " + nomeEntidade);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{nomeEntidade}")
    public ResponseEntity<String> excluirEntidade(
            @PathVariable String nomeEntidade,
            @RequestHeader("Authorization") String token) {

        try {
            entidadesService.excluiEntidade(nomeEntidade);
            return ResponseEntity.ok("Entidade excluída com sucesso!");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
