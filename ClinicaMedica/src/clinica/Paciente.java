package clinica;

import java.time.LocalDate;

public class Paciente {
    private LocalDate dataDeNascimento;
    private String nome;
    private String cpf;

    public Paciente(LocalDate dataDeNascimento, String nome, String cpf) {
        this.dataDeNascimento = dataDeNascimento;
        this.nome = nome;
        this.cpf = cpf;
    }

    public LocalDate getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void setDataDeNascimento(LocalDate dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public String toString() {
        return "Paciente {" +
                "dataDeNascimento='" + dataDeNascimento.toString() + '\'' +
                ", nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                '}';
    }
}
