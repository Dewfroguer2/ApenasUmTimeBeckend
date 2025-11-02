package ApenasUmTime.Backend.ProjetoBack.Reunioes;

import ApenasUmTime.Backend.ProjetoBack.Entidades.Entidades;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Reunioes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String titulo;

    private String descricao;

    @Column(nullable = false)
    private LocalDate data;

    private String local;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_entidade", nullable = false) // FK para Entidades.id
    private Entidades entidade;


    public LocalDate getData() { return data; }

    public void setData(LocalDate data) { this.data = data; }

    public String getDescricao() { return descricao; }

    public void setDescricao(String descricao) { this.descricao = descricao; }

    public Entidades getEntidade() { return entidade; }

    public void setEntidade(Entidades entidade) { this.entidade = entidade; }

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public String getLocal() { return local; }

    public void setLocal(String local) { this.local = local; }

    public String getTitulo() { return titulo; }

    public void setTitulo(String titulo) { this.titulo = titulo; }
}
