package clinica;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorClinica {
    private ArrayList<Medico> medicos;
    private ArrayList<Paciente> pacientes;
    private ArrayList<Agendamento> agendamentos;

    public GerenciadorClinica() {
        medicos = new ArrayList<Medico>();
        pacientes = new ArrayList<Paciente>();
        agendamentos = new ArrayList<Agendamento>();
    }
    
    public boolean cadastrarMedico(String nome, String crm, String especialidade) {
    	Medico novoMedico = new Medico(nome, crm, especialidade);
    	for (Medico medico : medicos) {
    		if (medico.getCrm().equalsIgnoreCase(novoMedico.getCrm())) {
    			return false;
    		}
    	}
    	medicos.add(novoMedico);
    	return true;
    }
    
    public boolean cadastrarPaciente(LocalDate dataDeNascimento, String nome, String cpf) {
        Paciente novoPaciente = new Paciente(dataDeNascimento, nome, cpf);
        for (Paciente paciente : pacientes) {
    		if (paciente.getCpf().equalsIgnoreCase(novoPaciente.getCpf())) {
    			return false;
    		}
    	}
        pacientes.add(novoPaciente);
        return true;
    }
    
    public boolean realizarAgendamento(String crm, String cpf, LocalDateTime data) {
    	Medico medicoEncontrado = null;
    	for (Medico medico : medicos) {
    		if (medico.getCrm().equalsIgnoreCase(crm)) {
    			medicoEncontrado = medico;
    		}
    	}
    	Paciente pacienteEncontrado = null;
    	for (Paciente paciente : pacientes) {
    		if (paciente.getCpf().equalsIgnoreCase(cpf)) {
    			pacienteEncontrado = paciente;
    		}
    	}
    	if (pacienteEncontrado == null || medicoEncontrado == null) {
    		return false;
    	}
    	
    	for (Agendamento agendamento : agendamentos) {
    		if (agendamento.getMedico().getCrm().equalsIgnoreCase(crm) && agendamento.getData().equals(data)) {
    			return false;
    		}
    	}
    	
    	Agendamento agendamento = new Agendamento(pacienteEncontrado, medicoEncontrado, data);
    	agendamentos.add(agendamento);
    	return true;
    }
    
    public List<Agendamento> consultarAgendamentosPorCPF(String cpf) {
    	List<Agendamento> agendamentosDoPaciente = new ArrayList<Agendamento>();
    	for (Agendamento agendamento : agendamentos) {
    		if (agendamento.getPaciente().getCpf().equalsIgnoreCase(cpf)) {
    			agendamentosDoPaciente.add(agendamento);
    		}
    	}
    	return agendamentosDoPaciente;
    }
    
    public boolean cancelarAgendamento(Agendamento agendamentoCancelado) {
    	return agendamentos.remove(agendamentoCancelado);
    }
    
	public boolean reagendarAgendamento(Agendamento agendamento, LocalDateTime novaData) {
		if (realizarAgendamento(agendamento.getMedico().getCrm(), agendamento.getPaciente().getCpf(), novaData)) {
			return cancelarAgendamento(agendamento);
		}
		return false;
	}
	
	public String listarTodasConsultas() {
		StringBuilder sb = new StringBuilder();
		for (Agendamento agendamento : agendamentos) {
			sb.append(agendamento.toString()).append("\n");
		}
		return sb.toString();
	}
    
    public void carregarPacientesDoArquivo() {
        try {
            String file = "pacientes.txt";

            BufferedReader reader = new BufferedReader(new FileReader(file));
            String currentLine = reader.readLine();
            
            while (reader.ready()) {
            	LocalDate dataDeNascimento = null;
                currentLine = reader.readLine();
                String[] pacienteDoArquivo = currentLine.split(";");
                String[] dataSeparada = pacienteDoArquivo[0].split("-");
    			dataDeNascimento = LocalDate.of(Integer.parseInt(dataSeparada[2]), Integer.parseInt(dataSeparada[1]), Integer.parseInt(dataSeparada[0]));
                Paciente paciente = new Paciente(dataDeNascimento, pacienteDoArquivo[1], pacienteDoArquivo[2]);
                pacientes.add(paciente);
            }
            reader.close();
        } catch (Exception e) {
            System.out.println("Erro ao carregar o arquivo de pacientes.");
        }
    }

    public void carregarMedicosDoArquivo() {
        try {
            String file = "medicos.txt";

            BufferedReader reader = new BufferedReader(new FileReader(file));
            String currentLine = reader.readLine();
            while (reader.ready()) {
                currentLine = reader.readLine();
                String[] medicoDoArquivo = currentLine.split(";");
                Medico medico = new Medico(medicoDoArquivo[0], medicoDoArquivo[1], medicoDoArquivo[2]);
                medicos.add(medico);
            }
            reader.close();
        } catch (Exception e) {
            System.out.println("Erro ao carregar o arquivo de medicos.");
        }
    }
    
    public void carregarAgendamentosDoArquivo() {
        try {
            String file = "agendamentos.txt";

            BufferedReader reader = new BufferedReader(new FileReader(file));
            String currentLine = reader.readLine();
            
            while (reader.ready()) {
            	LocalDateTime data = null;
                currentLine = reader.readLine();
                String[] agendamentoDoArquivo = currentLine.split(";");
                
                String[] dataSeparada = agendamentoDoArquivo[2].split("-");
                data = LocalDateTime.of(Integer.parseInt(dataSeparada[2]), Integer.parseInt(dataSeparada[1]), Integer.parseInt(dataSeparada[0]), Integer.parseInt(dataSeparada[3]), Integer.parseInt(dataSeparada[4]));
                
                Paciente pacienteDoArquivo = null;
                for (Paciente paciente : pacientes) {
                	if (paciente.getCpf().equalsIgnoreCase(agendamentoDoArquivo[0])) {
                		pacienteDoArquivo = paciente;
                	}
                }
                Medico medicoDoArquivo = null;
                for (Medico medico : medicos) {
                	if (medico.getCrm().equalsIgnoreCase(agendamentoDoArquivo[1])) {
                		medicoDoArquivo = medico;
                	}
                }
                if (pacienteDoArquivo != null && medicoDoArquivo != null) {
	                Agendamento agendamento = new Agendamento(pacienteDoArquivo, medicoDoArquivo, data);
	                agendamentos.add(agendamento);
                }
            }
            reader.close();
        } catch (Exception e) {
            System.out.println("Erro ao carregar o arquivo de pacientes.");
        }
    }
    
    public void salvarPacientesNoArquivo() {
        try {
            FileWriter fileWriter = new FileWriter("pacientes.txt");
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.println("dataDeNascimento;nome;cpf");
            for (Paciente paciente : pacientes) {
            	LocalDate dataDeNascimento = paciente.getDataDeNascimento();
                printWriter.println(dataDeNascimento.getDayOfMonth() + "-" + dataDeNascimento.getMonthValue() + "-" + dataDeNascimento.getYear()
                		+ ";" + paciente.getNome() + ";" + paciente.getCpf());
            }
            printWriter.close();
        } catch (Exception e) {
            System.out.println("Erro ao salvar o arquivo de pacientes.");
        }
    }
    
    public void salvarMedicosNoArquivo() {
        try {
            FileWriter fileWriter = new FileWriter("medicos.txt");
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.println("nome;crm;especialidade");
            for (Medico medico : medicos) {
                printWriter.println(medico.getNome() + ";" + medico.getCrm() + ";" + medico.getEspecialidade());
            }
            printWriter.close();
        } catch (Exception e) {
            System.out.println("Erro ao salvar o arquivo de medicos.");
        }
    }
    
    public void salvarAgendamentosNoArquivo() {
        try {
            FileWriter fileWriter = new FileWriter("agendamentos.txt");
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.println("cpf;crm;data");
            for (Agendamento agendamento : agendamentos) {
            	LocalDateTime data = agendamento.getData();
                printWriter.println(agendamento.getPaciente().getCpf() + ";" + agendamento.getMedico().getCrm() 
                		+ ";" + data.getDayOfMonth() + "-" + data.getMonthValue() + "-" + data.getYear() 
                		+ "-" + data.getHour() + "-" + data.getMinute());
            }
            printWriter.close();
        } catch (Exception e) {
            System.out.println("Erro ao salvar o arquivo de agendamentos.");
        }
    }
}
