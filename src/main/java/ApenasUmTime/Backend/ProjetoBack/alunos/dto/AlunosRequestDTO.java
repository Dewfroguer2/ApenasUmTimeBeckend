package ApenasUmTime.Backend.ProjetoBack.alunos.dto;


import ApenasUmTime.Backend.ProjetoBack.alunos.Alunos;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "AlunosRequestDTO", description = "DTO para requests da classe Alunos")
public record AlunosRequestDTO(

    @Schema(description="Nome do aluno", example="Mordecai")
    String nome,

    @Schema(description="Email do aluno", example="cabelinho@hotmail.com")
    String email,

    @Schema(description="CPF do aluno", example="11111111111")
    String cpf,

    @Schema(description="Semestre do aluno", example="3")
    Integer semestre,

    @Schema(description="Celular do aluno", example="11 4002-8922")
    String celular
) {
    public Alunos toEtity() {
        return new Alunos(nome, email, cpf, semestre, celular);
    }
}


