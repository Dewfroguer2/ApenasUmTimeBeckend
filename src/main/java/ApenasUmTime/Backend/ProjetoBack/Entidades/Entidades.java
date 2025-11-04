package ApenasUmTime.Backend.ProjetoBack.Entidades;

import ApenasUmTime.Backend.ProjetoBack.Reunioes.Reunioes;
import ApenasUmTime.Backend.ProjetoBack.alunos.Alunos;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Entidades {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String nicho;

    private String areas;

    @OneToMany(mappedBy = "entidade", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reunioes> reunioslist = new ArrayList<>();

    // LADO DONO da @ManyToMany
    @ManyToMany
    @JoinTable(
            name = "alunos_entidades",
            joinColumns = @JoinColumn(name = "entidade_id"),
            inverseJoinColumns = @JoinColumn(name = "aluno_id")
    )
    private List<Alunos> alunosList = new ArrayList<>();

    public List<Alunos> getAlunos() { return alunosList; }
    public void setAlunos(List<Alunos> alunos) { this.alunosList = alunos; }

    public String getAreas() { return areas; }
    public void setAreas(String areas) { this.areas = areas; }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getNicho() { return nicho; }
    public void setNicho(String nicho) { this.nicho = nicho; }

    public List<Reunioes> getReunioslist() { return reunioslist; }
    public void setReunioslist(List<Reunioes> reunioslist) { this.reunioslist = reunioslist; }
}

