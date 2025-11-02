package ApenasUmTime.Backend.ProjetoBack.alunos;


import ApenasUmTime.Backend.ProjetoBack.alunos.dto.AlunosRequestDTO;
import ApenasUmTime.Backend.ProjetoBack.alunos.dto.AlunosResponseDTO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlunosService {

    @Autowired
    private AlunosRepository repository;


    public List<AlunosResponseDTO> listarAlunos() {
        return repository.findAll()
                .stream()
                .map(AlunosResponseDTO::new)
                .collect(Collectors.toList());
    }

    public AlunosResponseDTO buscarPorCpf(String cpf) {
        Alunos aluno = repository.findByCpf(cpf);
        if (aluno == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aluno não encontrado.");
        }
        return new AlunosResponseDTO(aluno);
    }


    @Transactional
    public AlunosResponseDTO cadastrarAlunos(AlunosRequestDTO alunoDTO) {
        if (alunoDTO.cpf() == null || alunoDTO == null || alunoDTO.cpf().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nenhum dos atributos da entidade aluno pode ser nulo.");
        }

        Alunos existente = repository.findByCpf(alunoDTO.cpf());
        if (existente != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Esse aluno já está cadastrado.");
        }

        Alunos novoAluno = new Alunos();
        novoAluno.setCpf(alunoDTO.cpf());
        novoAluno.setNome(alunoDTO.nome());
        novoAluno.setEmail(alunoDTO.email());
        novoAluno.setCelular(alunoDTO.celular());
        novoAluno.setSemestre(alunoDTO.semestre());

        Alunos salvo = repository.save(novoAluno);

        return new AlunosResponseDTO(salvo);
    }


    public AlunosResponseDTO editarAlunos(AlunosRequestDTO alunoDTO) {
        Alunos existente = repository.findByCpf(alunoDTO.cpf());
        if (existente == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Esse aluno não está cadastrado");
        }

        if (alunoDTO.cpf() != null) {existente.setCpf(alunoDTO.cpf());}

        if (alunoDTO.nome() != null) {existente.setNome(alunoDTO.nome());}

        if (alunoDTO.celular() != null) {existente.setCelular(alunoDTO.celular());}

        if (alunoDTO.semestre() != null) {existente.setSemestre(alunoDTO.semestre());}

        if (alunoDTO.email() != null) {existente.setEmail(alunoDTO.email());}

        Alunos alutoEditado = repository.save(existente);

        return new  AlunosResponseDTO(alutoEditado);
    }


}
