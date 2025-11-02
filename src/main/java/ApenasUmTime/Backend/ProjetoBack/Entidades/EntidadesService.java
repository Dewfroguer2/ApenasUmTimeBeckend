package ApenasUmTime.Backend.ProjetoBack.Entidades;

import ApenasUmTime.Backend.ProjetoBack.Entidades.EntidadesDTOs.EntidadeDtoComun;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntidadesService {

    @Autowired
    private EntidadeRepository entidadeRepositore;

    @Autowired
    private AlunosRepositore alunosRepositore;

    public List<EntidadeDtoComun> listaEntidades() {
        return entidadeRepositore.findAll()
                .stream()
                .map(c -> new EntidadeDtoComun(
                        c.getName(),
                        c.getNicho(),
                        c.getAreas()
                )).toList();
    }

    public Entidades getEntidade(String nome){
        return entidadeRepositore.findByName(nome);
    }

    public EntidadeDtoComun criaEntidade(EntidadeDtoComun dto){
        if (dto.nome() == null || dto.area() == null || dto.nicho() == null){
            throw new RuntimeException("Preencha todos os campos");
        }
        Entidades entidade = new Entidades();
        entidade.setName(dto.nome());
        entidade.setAreas(dto.area());
        entidade.setNicho(dto.nicho());

        entidadeRepositore.save(entidade);
        return dto;
    }

    public void adicionaAluno(String nameEntidadade, Alunos aluno){
        Entidades entidade = entidadeRepositore.findByName(nameEntidadade);
        Alunos alun = alunosRepositore.findByNome(aluno.getName());

        if(alun == null){ throw new RuntimeException("Esse aluno não existe"); }

        alun.getEntidade().add(entidade);
        entidade.getAlunos().add(alun);
    }

    public void removeAluno(String nameEntidadade, Alunos aluno){
        Entidades entidade = entidadeRepositore.findByName(nameEntidadade);
        Alunos alun = alunosRepositore.findByNome(aluno.getName());

        if(alun == null){ throw new RuntimeException("Esse aluno não existe"); }

        alun.getEntidade().remove(entidade);
        entidade.getAlunos().remove(alun);
    }

    public void excluiEntidade(String name){
        Entidades entidades = entidadeRepositore.findByName(name);
        entidadeRepositore.delete(entidades);
    }

}
