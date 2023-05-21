package br.edu.curso.fateczl.HospitalMVC_JasperReports.persistence;

import java.sql.SQLException;

public interface IConsultaDAO {
	
	public String inserir_consulta(String nome_paciente, String nome_medico, int cid, String tratamento) throws SQLException, ClassNotFoundException;

}
