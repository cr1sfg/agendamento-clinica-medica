package clinica;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class MainMenu {

	public static void main(String[] args) {
		GerenciadorClinica gerenciadorClinica = new GerenciadorClinica();
		gerenciadorClinica.carregarPacientesDoArquivo();
		gerenciadorClinica.carregarMedicosDoArquivo();
		gerenciadorClinica.carregarAgendamentosDoArquivo();
		Scanner scanner = new Scanner(System.in);
		boolean ativo = true;
		while(ativo) {

	        System.out.println("==== Bem-Vindo a Clinica Medica ====");
	        System.out.println("");
	        System.out.println("1 - Cadastrar novo medico;");
	        System.out.println("2 - Cadastrar novo paciente;");
	        System.out.println("3 - Agendar consulta;");
	        System.out.println("4 - Reagendar consulta;");
	        System.out.println("5 - Cancelar consulta;");
	        System.out.println("6 - Listar todas as consultas;");
	        System.out.println("0 - Sair.");
	        System.out.println("");
	        System.out.print("Digite a opcao desejada: ");
	        
	        int opcao = Integer.parseInt(scanner.next());
	        
	        System.out.println("");
	        
	        switch(opcao) {
	        	case 1: {
	        		System.out.print("Digite o nome do medico: ");
	        		String nome = scanner.next();
	        		if (nome.isBlank() || nome.length() < 3) {
	        			System.out.println("Nome invalido. Retornando para o menu.");
	        			break;
	        		}
	        		System.out.print("Digite o CRM do medico: ");
	        		String crm = scanner.next();
	        		try {
	        			Integer.parseInt(crm);
	        		} catch (Exception e) {
	        			System.out.println("CRM invalido. Retornando para o menu.");
	        			break;
	        		}
	        		System.out.print("Digite a especialidade do medico: ");
	        		String especialidade = scanner.next();
	        		if (especialidade.isBlank() || nome.length() < 3) {
	        			System.out.println("Especialidade invalida. Retornando para o menu.");
	        			break;
	        		}
	        		boolean sucesso = gerenciadorClinica.cadastrarMedico(nome, crm, especialidade);
	        		if (sucesso) {
	        			System.out.println("Medico cadastrado.");
	        		} else {
	        			System.out.println("Medico ja existe.");
	        		}
	        		break;
	        	}
	        	case 2: {
	        		System.out.print("Digite o nome do paciente: ");
	        		String nome = scanner.next();
	        		if (nome.isBlank() || nome.length() < 3) {
	        			System.out.println("Nome invalido. Retornando para o menu.");
	        			break;
	        		}
	        		System.out.print("Digite o CPF do paciente (apenas numeros): ");
	        		String cpf = scanner.next();
	        		try {
	        			Long.parseLong(cpf);
	        			if (cpf.length() < 11) {
	        				System.out.println("CPF invalido. Retornando para o menu.");
		        			break;
	        			}
	        		} catch (Exception e) {
	        			System.out.println("CPF invalido. Retornando para o menu.");
	        			break;
	        		}
	        		System.out.print("Digite a data de nascimento do paciente (DD-MM-AAAA): ");
	        		String dataInformada = scanner.next();
	        		LocalDate dataDeNascimento = null;
	        		if (dataInformada.isBlank() || dataInformada.length() < 10) {
	        			System.out.println("Data invalida. Retornando para o menu.");
	        			break;
	        		} else {
	        			String[] dataSeparada = dataInformada.split("-");
	        			dataDeNascimento = LocalDate.of(Integer.parseInt(dataSeparada[2]), Integer.parseInt(dataSeparada[1]), Integer.parseInt(dataSeparada[0]));
	        		}
	        		if (dataDeNascimento.isAfter(LocalDate.now())) {
	        			System.out.println("Data invalida. Retornando para o menu.");
	        			break;
	        		}
	        		boolean sucesso = gerenciadorClinica.cadastrarPaciente(dataDeNascimento, nome, cpf);
	        		if (sucesso) {
	        			System.out.println("Paciente cadastrado.");
	        		} else {
	        			System.out.println("Paciente ja existe.");
	        		}
	        		break;
	        	}
	        	case 3: {
	        		System.out.print("Digite o CPF do paciente (apenas numeros): ");
	        		String cpf = scanner.next();
	        		try {
	        			Long.parseLong(cpf);
	        			if (cpf.length() < 11) {
	        				System.out.println("CPF invalido. Retornando para o menu.");
		        			break;
	        			}
	        		} catch (Exception e) {
	        			System.out.println("CPF invalido. Retornando para o menu.");
	        			break;
	        		}
	        		System.out.print("Digite o CRM do medico: ");
	        		String crm = scanner.next();
	        		try {
	        			Integer.parseInt(crm);
	        		} catch (Exception e) {
	        			System.out.println("CRM invalido. Retornando para o menu.");
	        			break;
	        		}
	        		
	        		System.out.print("Digite a data da consulta (DD-MM-AAAA): ");
	        		String dataInformada = scanner.next();
	        		LocalDateTime dataDaConsulta = null;
	        		if (dataInformada.isBlank() || dataInformada.length() < 10) {
	        			System.out.println("Data invalida. Retornando para o menu.");
	        			break;
	        		} else {
	        			String[] dataSeparada = dataInformada.split("-");
	        			System.out.print("Digite a hora da consulta (HH-mm): ");
		        		String horaInformada = scanner.next();
		        		String[] horaSeparada = horaInformada.split("-");
		        		dataDaConsulta = LocalDateTime.of(Integer.parseInt(dataSeparada[2]), Integer.parseInt(dataSeparada[1]), Integer.parseInt(dataSeparada[0]), Integer.parseInt(horaSeparada[0]), Integer.parseInt(horaSeparada[1]));
	        		}
	        		if (dataDaConsulta != null 
	        				&& (dataDaConsulta.getDayOfWeek() == DayOfWeek.SUNDAY || dataDaConsulta.getDayOfWeek() == DayOfWeek.SATURDAY)) {
	        			
	        			System.out.println("Data invalida, escolha um dia util. Retornando para o menu.");
	        			break;
	        		}
	        		if (dataDaConsulta != null && dataDaConsulta.isBefore(LocalDateTime.now())) {
	        			System.out.println("Data invalida, escolha uma data futura. Retornando para o menu.");
	        			break;
	        		}
	        		if (dataDaConsulta != null && (dataDaConsulta.getHour() < 8 || dataDaConsulta.getHour() > 17)) {
	        			System.out.println("Horario invalido, escolha um horario comercial. Retornando para o menu.");
	        			break;
	        		}
	        		
	        		boolean sucesso = gerenciadorClinica.realizarAgendamento(crm, cpf, dataDaConsulta);
	        		if (sucesso) {
	        			System.out.println("Consulta agendada.");
	        		} else {
	        			System.out.println("Erro ao realizar agendamento.");
	        		}
	        		break;
	        	}
	        	case 4: {
	        		System.out.print("Digite o CPF do paciente (apenas numeros): ");
	        		String cpf = scanner.next();
	        		try {
	        			Long.parseLong(cpf);
	        			if (cpf.length() < 11) {
	        				System.out.println("CPF invalido. Retornando para o menu.");
		        			break;
	        			}
	        		} catch (Exception e) {
	        			System.out.println("CPF invalido. Retornando para o menu.");
	        			break;
	        		}
	        		System.out.println("");
	        		List<Agendamento> agendamentos = gerenciadorClinica.consultarAgendamentosPorCPF(cpf);
	        		if (agendamentos.isEmpty()) {
	        			System.out.println("Nenhuma consulta encontrada com esse CPF. Voltando para o menu");
	        			break;
	        		}
	        		
	        		for (int cont = 0; cont < agendamentos.size(); cont++) {
	        			Agendamento a = agendamentos.get(cont);
	        			System.out.println((cont + 1) + " - " + a.getMedico().getNome() + " - " + a.getData().toString());
	        		}
	        		System.out.println("");
	        		System.out.print("Digite o numero da consulta a ser reagendada: ");
	        		int numeroDeCancelamento = Integer.parseInt(scanner.next());
	        		Agendamento a = agendamentos.get(numeroDeCancelamento - 1);

	        		System.out.print("Digite a nova data (DD-MM-AAAA): ");
	        		String dataInformada = scanner.next();
	        		LocalDateTime novaData = null;
	        		if (dataInformada.isBlank() || dataInformada.length() < 10) {
	        			System.out.println("Data invalida. Retornando para o menu.");
	        			break;
	        		} else {
	        			String[] dataSeparada = dataInformada.split("-");
	        			System.out.print("Digite a hora da consulta (HH-mm): ");
		        		String horaInformada = scanner.next();
		        		String[] horaSeparada = horaInformada.split("-");
		        		novaData = LocalDateTime.of(Integer.parseInt(dataSeparada[2]), Integer.parseInt(dataSeparada[1]), Integer.parseInt(dataSeparada[0]), Integer.parseInt(horaSeparada[0]), Integer.parseInt(horaSeparada[1]));
	        		}

	        		if (novaData != null 
	        				&& (novaData.getDayOfWeek() == DayOfWeek.SUNDAY || novaData.getDayOfWeek() == DayOfWeek.SATURDAY)) {
	        			
	        			System.out.println("Data invalida, escolha um dia util. Retornando para o menu.");
	        			break;
	        		}
	        		if (novaData != null && novaData.isBefore(LocalDateTime.now())) {
	        			System.out.println("Data invalida, escolha uma data futura. Retornando para o menu.");
	        			break;
	        		}
	        		if (novaData != null && (novaData.getHour() < 8 || novaData.getHour() > 17)) {
	        			System.out.println("Horario invalido, escolha um horario comercial. Retornando para o menu.");
	        			break;
	        		}
	        		
	        		boolean sucesso = gerenciadorClinica.reagendarAgendamento(a, novaData);
	        		if (sucesso) {
	        			System.out.println("Consulta reagendada.");
	        		} else {
	        			System.out.println("Erro ao reagendar consulta.");
	        		}
	        		break;
	        	}
	        	case 5: {
	        		System.out.print("Digite o CPF do paciente (apenas numeros): ");
	        		String cpf = scanner.next();
	        		try {
	        			Long.parseLong(cpf);
	        			if (cpf.length() < 11) {
	        				System.out.println("CPF invalido. Retornando para o menu.");
		        			break;
	        			}
	        		} catch (Exception e) {
	        			System.out.println("CPF invalido. Retornando para o menu.");
	        			break;
	        		}
	        		System.out.println("");
	        		List<Agendamento> agendamentos = gerenciadorClinica.consultarAgendamentosPorCPF(cpf);
	        		if (agendamentos.isEmpty()) {
	        			System.out.println("Nenhuma consulta encontrada com esse CPF. Voltando para o menu");
	        			break;
	        		}
	        		
	        		for (int cont = 0; cont < agendamentos.size(); cont++) {
	        			Agendamento a = agendamentos.get(cont);
	        			System.out.println((cont + 1) + " - " + a.getMedico().getNome() + " - " + a.getData().toString());
	        		}
	        		System.out.println("");
	        		System.out.print("Digite o numero da consulta a ser cancelada: ");
	        		int numeroDeCancelamento = Integer.parseInt(scanner.next());
	        		Agendamento a = agendamentos.get(numeroDeCancelamento - 1);
	        		boolean sucesso = gerenciadorClinica.cancelarAgendamento(a);
	        		if (sucesso) {
	        			System.out.println("Consulta cancelada.");
	        		} else {
	        			System.out.println("Erro ao cancelar consulta.");
	        		}
	        		break;
	        	}
	        	case 6: {
	        		System.out.println("Consultas na agenda: ");
	        		System.out.println(gerenciadorClinica.listarTodasConsultas());
	        	}
	        	default: {
	        		ativo = false;
	        	}
	        }
	        System.out.println("");
		}
		gerenciadorClinica.salvarPacientesNoArquivo();
		gerenciadorClinica.salvarMedicosNoArquivo();
		gerenciadorClinica.salvarAgendamentosNoArquivo();
        scanner.close();
	}
}
