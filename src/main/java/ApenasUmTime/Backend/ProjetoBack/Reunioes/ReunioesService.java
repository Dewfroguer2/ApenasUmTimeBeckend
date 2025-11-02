package ApenasUmTime.Backend.ProjetoBack.Reunioes;

import ApenasUmTime.Backend.ProjetoBack.Entidades.Entidades;
import ApenasUmTime.Backend.ProjetoBack.Entidades.EntidadesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReunioesService {

    @Autowired
    private ReunioesRepository reunioesRepository;

    @Autowired
    private EntidadesRepository entidadesRepository;

    public List<Reunioes> reunioesList(){ return reunioesRepository.findAll(); }

    public List<Reunioes> renioesListPorEntidade(String entidade) { return entidadesRepository.findByName(entidade).getReunioslist(); }

    public Reunioes getReuniao(Integer idReuniao){ return reunioesRepository.findById(idReuniao)
            .orElseThrow(() -> new RuntimeException("Reunião não encontrada para esse id")); }

    public Reunioes CriaReuniao(Reunioes reuniao) {
        Entidades entidade = entidadesRepository.findByName(reuniao.getEntidade().getName());
        if (entidade == null){
            throw new RuntimeException("Erro ao salvar peegar a entidade");
        }
        entidade.getReunioslist().add(reuniao);
        reuniao.setEntidade(entidade);
        reunioesRepository.save(reuniao);
        return reuniao;
    }

    public void excluiReuniao(Integer idReuniao){
        Reunioes reuniao = reunioesRepository.findById(idReuniao)
                        .orElseThrow(() -> new RuntimeException("Erro ao excluir reunião. Id não válido"));
        reunioesRepository.delete(reuniao);
    }

    public Reunioes atualizaReuniao(Integer idReuniao, Reunioes dadosAtualizados) {
        Reunioes reuniaoExistente = reunioesRepository.findById(idReuniao)
                .orElseThrow(() -> new RuntimeException("Reunião não encontrada"));

        reuniaoExistente.setTitulo(dadosAtualizados.getTitulo());
        reuniaoExistente.setDescricao(dadosAtualizados.getDescricao());
        reuniaoExistente.setData(dadosAtualizados.getData());
        reuniaoExistente.setLocal(dadosAtualizados.getLocal());

        return reunioesRepository.save(reuniaoExistente);
    }


}
