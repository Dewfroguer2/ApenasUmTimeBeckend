package ApenasUmTime.Backend.ProjetoBack.alunos.dto;


import ApenasUmTime.Backend.ProjetoBack.alunos.Alunos;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "AlunoResponseDTO", description = "DTO para response da classe Alunos")
public record AlunosResponseDTO (
    @Schema(description="Nome do aluno", example="Mordecai")
    String nome,

    @Schema(description="Email do aluno", example="cabelinho@hotmail.com")
    String email,

    @Schema(description="Semestre do aluno", example="3")
    Integer semestre,

    @Schema(description="Celular do aluno", example="11 4002-8922")
    String celular
){
    public AlunosResponseDTO (Alunos aluno) {
        this(
                aluno.getNome(),
                aluno.getEmail(),
                aluno.getSemestre(),
                aluno.getCelular()
                );
    }

}
