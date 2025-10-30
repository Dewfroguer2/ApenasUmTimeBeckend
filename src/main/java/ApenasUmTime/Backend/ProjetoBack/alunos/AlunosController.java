package ApenasUmTime.Backend.ProjetoBack.alunos;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunosController {

    @Autowired
    private AlunosService alunosService;

    @GetMapping
    public List<Alunos> listarAlunosController(){
        List<Alunos> alunos = alunosService.listarAlunos();
        return alunos;
    }

    @GetMapping("/{cpf}")
    public Alunos buscarPorCpfController(@PathVariable String cpf){
        Alunos aluno = alunosService.buscarPorCpf(cpf);
        return aluno;
    }

    @PostMapping
    public ResponseEntity<?> cadastrarAlunosController(@RequestBody Alunos aluno){
        Alunos cadastrado = alunosService.cadastrarAlunos(aluno);
        return ResponseEntity.ok(cadastrado);
    }

    @PutMapping("/{cpf")
    public ResponseEntity<?> editarAlunosController(@PathVariable String cpf, @RequestBody Alunos aluno){
        try {
            Alunos alunoEditado = alunosService.editarAlunos(aluno);
            return ResponseEntity.ok(alunoEditado);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}
