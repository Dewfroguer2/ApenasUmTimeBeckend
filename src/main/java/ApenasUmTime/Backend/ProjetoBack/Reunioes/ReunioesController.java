package ApenasUmTime.Backend.ProjetoBack.Reunioes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/reunioes")
public class ReunioesController {
    @Autowired
    private ReunioesService reunioesService;

    @GetMapping
    public ResponseEntity<List<Reunioes>> listarReunioes() {
        List<Reunioes> reunioes = reunioesService.reunioesList();
        return ResponseEntity.ok(reunioes);
    }

    @GetMapping("/entidade/{nomeEntidade}")
    public ResponseEntity<List<Reunioes>> listarPorEntidade(@PathVariable String nomeEntidade) {
        try {
            List<Reunioes> reunioes = reunioesService.renioesListPorEntidade(nomeEntidade);
            return ResponseEntity.ok(reunioes);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reunioes> buscarPorId(@PathVariable Integer id) {
        try {
            Reunioes reuniao = reunioesService.getReuniao(id);
            return ResponseEntity.ok(reuniao);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Reunioes> criarReuniao(@RequestBody Reunioes reuniao) {
        try {
            Reunioes novaReuniao = reunioesService.CriaReuniao(reuniao);
            return ResponseEntity.ok(novaReuniao);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reunioes> atualizarReuniao(@PathVariable Integer id, @RequestBody Reunioes reuniao) {
        try {
            Reunioes atualizada = reunioesService.atualizaReuniao(id, reuniao);
            return ResponseEntity.ok(atualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluirReuniao(@PathVariable Integer id) {
        try {
            reunioesService.excluiReuniao(id);
            return ResponseEntity.ok("Reunião excluída com sucesso");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
