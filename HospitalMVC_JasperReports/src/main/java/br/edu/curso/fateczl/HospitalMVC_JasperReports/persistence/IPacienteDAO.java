package br.edu.curso.fateczl.HospitalMVC_JasperReports.persistence;

import java.sql.SQLException;
import java.util.List;

import br.edu.curso.fateczl.HospitalMVC_JasperReports.model.Paciente;

public interface IPacienteDAO {
	
	public List<Paciente> lista_Paciente() throws SQLException, ClassNotFoundException;

}
