package ApenasUmTime.Backend.ProjetoBack.alunos;


import ApenasUmTime.Backend.ProjetoBack.Entidades.Entidades;
import ApenasUmTime.Backend.ProjetoBack.cursos.Cursos;
import jakarta.persistence.*;


import java.util.Collection;
import java.util.List;

@Entity
@Table(name="alunos")
public class Alunos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String email;

    @Column(unique = true, nullable = false)
    private String cpf;

    @ManyToOne
    @JoinColumn(name = "curso", nullable = false)
    private Cursos curso;

    @ManyToMany(mappedBy = "alunosList")
    private List<Entidades> entidades;


    @Column(nullable = false)
    private Integer semestre;

    @Column(nullable = false)
    private String celular;


    public Alunos() {}

    public Alunos(String nome, String email, String cpf, Integer semestre, String celular) {
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.semestre = semestre;
        this.celular = celular;
    }

    public String getNome() {return nome;}
    public void setNome(String nome) {this.nome = nome;}

    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}

    public String getCpf() {return cpf;}
    public void setCpf(String cpf) {this.cpf = cpf;}

    public Integer getSemestre() {return semestre;}
    public void setSemestre(Integer semestre) {this.semestre = semestre;}

    public String getCelular() {return celular;}
    public void setCelular(String celular) {this.celular = celular;}

    public Collection<Entidades> getEntidade() {return entidades;}
}
