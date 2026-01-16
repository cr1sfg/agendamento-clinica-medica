package clinica;

import java.time.LocalDateTime;

public class Agendamento {

	private Paciente paciente;
	private Medico medico;
	private LocalDateTime data;
	
	public Agendamento(Paciente paciente, Medico medico, LocalDateTime data) {
		this.paciente = paciente;
		this.medico = medico;
		this.data = data;
	}
	public Paciente getPaciente() {
		return paciente;
	}
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	public Medico getMedico() {
		return medico;
	}
	public void setMedico(Medico medico) {
		this.medico = medico;
	}
	public LocalDateTime getData() {
		return data;
	}
	public void setData(LocalDateTime data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "Agendamento [" + paciente.toString() + ", " + medico.toString() + ", data=" + data.toString() + "]";
	}
	
	
}
