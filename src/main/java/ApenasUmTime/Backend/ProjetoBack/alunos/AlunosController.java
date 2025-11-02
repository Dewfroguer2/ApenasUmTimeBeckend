package ApenasUmTime.Backend.ProjetoBack.alunos;


import ApenasUmTime.Backend.ProjetoBack.alunos.dto.AlunosRequestDTO;
import ApenasUmTime.Backend.ProjetoBack.alunos.dto.AlunosResponseDTO;
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
    public List<AlunosResponseDTO> listarAlunosController(){
        return alunosService.listarAlunos();
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<?> buscarPorCpfController(@PathVariable String cpf){
        AlunosResponseDTO aluno = alunosService.buscarPorCpf(cpf);
        if (aluno == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(aluno);
    }

    @PostMapping
    public ResponseEntity<?> cadastrarAlunosController(@RequestBody AlunosRequestDTO alunoDTO){
        AlunosResponseDTO cadastrado = alunosService.cadastrarAlunos(alunoDTO);
        return ResponseEntity.ok(cadastrado);
    }

    @PutMapping("/{cpf")
    public ResponseEntity<?> editarAlunosController(@PathVariable String cpf, @RequestBody AlunosRequestDTO alunoDTO){
        try {
            AlunosResponseDTO alunoEditado = alunosService.editarAlunos(alunoDTO);
            return ResponseEntity.ok(alunoEditado);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}
