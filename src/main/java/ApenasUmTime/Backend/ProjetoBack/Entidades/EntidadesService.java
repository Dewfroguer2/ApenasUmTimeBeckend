package ApenasUmTime.Backend.ProjetoBack.Entidades;

import ApenasUmTime.Backend.ProjetoBack.Entidades.EntidadesDTOs.EntidadeDtoComun;
import ApenasUmTime.Backend.ProjetoBack.alunos.Alunos;
import ApenasUmTime.Backend.ProjetoBack.alunos.AlunosRepository;
import ApenasUmTime.Backend.ProjetoBack.alunos.dto.AlunosResponseDTO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntidadesService {

    @Autowired
    private EntidadesRepository entidadeRepositore;

    @Autowired
    private AlunosRepository alunosRepositore;

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

    @Transactional
    public void adicionaAluno(String nameEntidadade, Alunos aluno){
        Entidades entidade = entidadeRepositore.findByName(nameEntidadade);
        if(entidade == null) { 
            throw new RuntimeException("Entidade não encontrada"); 
        }
        
        Alunos alun = alunosRepositore.findByNome(aluno.getNome());
        if(alun == null){ 
            throw new RuntimeException("Esse aluno não existe"); 
        }

        // Verificar se o aluno já está na entidade
        if(entidade.getAlunos().contains(alun)) {
            throw new RuntimeException("Aluno já está cadastrado nesta entidade");
        }

        // Adicionar às listas (o lado dono da relação é Entidades)
        entidade.getAlunos().add(alun);
        
        // Salvar as alterações no banco de dados (salvar apenas a entidade é suficiente para Many-to-Many)
        entidadeRepositore.save(entidade);
    }

    @Transactional
    public void removeAluno(String nameEntidadade, Alunos aluno){
        Entidades entidade = entidadeRepositore.findByName(nameEntidadade);
        if(entidade == null) { 
            throw new RuntimeException("Entidade não encontrada"); 
        }
        
        Alunos alun = alunosRepositore.findByNome(aluno.getNome());
        if(alun == null){ 
            throw new RuntimeException("Esse aluno não existe"); 
        }

        // Remover das listas (o lado dono da relação é Entidades)
        entidade.getAlunos().remove(alun);
        
        // Salvar as alterações no banco de dados (salvar apenas a entidade é suficiente para Many-to-Many)
        entidadeRepositore.save(entidade);
    }

    public void excluiEntidade(String name){
        Entidades entidades = entidadeRepositore.findByName(name);
        entidadeRepositore.delete(entidades);
    }

    public List<AlunosResponseDTO> listarAlunosPorEntidade(String nomeEntidade) {
        Entidades entidade = entidadeRepositore.findByName(nomeEntidade);
        if (entidade == null) {
            throw new RuntimeException("Entidade não encontrada");
        }
        
        return entidade.getAlunos().stream()
                .map(AlunosResponseDTO::new)
                .toList();
    }

}
