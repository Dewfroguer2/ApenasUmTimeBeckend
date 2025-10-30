package ApenasUmTime.Backend.ProjetoBack.alunos.dto;


import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "AlunoReponseDTO", description = "DTO para response da classe Alunos")
public class AlunosResponseDTO {
    @Schema(description="Nome do aluno", example="Mordecai")
    String nome;

    @Schema(description="Email do aluno", example="cabelinho@hotmail.com")
    String email;

    @Schema(description="Semestre do aluno", example="3")
    String semestre;

    @Schema(description="Celular do aluno", example="cabelinho@hotmail.com")
    String celular;

}
