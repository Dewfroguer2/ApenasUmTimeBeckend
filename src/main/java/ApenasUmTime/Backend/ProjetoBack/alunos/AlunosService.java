package ApenasUmTime.Backend.ProjetoBack.alunos;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AlunosService {

    @Autowired
    private AlunosRepository repository;


    public List<Alunos> listarAlunos() {
        return repository.findAll();
    }

    public Alunos buscarPorCpf(String cpf) {
        Alunos aluno = repository.findByCpf(cpf);
        if (aluno == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aluno não encontrado.");
        }
        return aluno;
    }


    public Alunos cadastrarAlunos(Alunos aluno) {
        if (aluno.getCpf() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O CPF do aluno não pode ser nulo.");
        }

        Alunos existente = repository.findByCpf(aluno.getCpf());
        if (existente != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Esse aluno já está cadastrado.");
        }

        return repository.save(aluno);
    }


    public Alunos editarAlunos(Alunos aluno) {
        Alunos existente = repository.findByCpf(aluno.getCpf());
        if (existente == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Esse aluno não está cadastrado");
        }

        if (aluno.getCpf() != null) {existente.setCpf(aluno.getCpf());}

        if (aluno.getNome() != null) {existente.setNome(aluno.getNome());}

        if (aluno.getCelular() != null) {existente.setCelular(aluno.getCelular());}

        if (aluno.getSemestre() != null) {existente.setSemestre(aluno.getSemestre());}

        if (aluno.getEmail() != null) {existente.setEmail(aluno.getEmail());}

        return repository.save(existente);
    }


}
