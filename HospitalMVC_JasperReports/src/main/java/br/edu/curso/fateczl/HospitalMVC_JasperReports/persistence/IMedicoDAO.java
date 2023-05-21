package br.edu.curso.fateczl.HospitalMVC_JasperReports.persistence;

import java.sql.SQLException;
import java.util.List;

import br.edu.curso.fateczl.HospitalMVC_JasperReports.model.Medico;

public interface IMedicoDAO {
	
	public List<Medico> lista_Medicos() throws SQLException, ClassNotFoundException;

}
