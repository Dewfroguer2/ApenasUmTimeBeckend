package ApenasUmTime.Backend.ProjetoBack.eventos;


import ApenasUmTime.Backend.ProjetoBack.Entidades.Entidades;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;


@Entity
@Table(name="eventos")
public class Eventos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private LocalDate data;

    @Column(nullable = false)
    private Integer numeroParticipantes;

    @ManyToMany(mappedBy="entidades")
    private List<Entidades> entidadeRelacionada;

    public Eventos() {}

    public Eventos(String titulo, String descricao, LocalDate data, Integer numeroParticipantes) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.data = data;
        this.numeroParticipantes = numeroParticipantes;
    }

    public Integer getId() {return this.id;}

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getNumeroParticipantes() {
        return numeroParticipantes;
    }

    public void setNumeroParticipantes(Integer numeroParticipantes) {
        this.numeroParticipantes = numeroParticipantes;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public List<Entidades> getEntidadeRelacionada() {
        return this.entidadeRelacionada;
    }
}
