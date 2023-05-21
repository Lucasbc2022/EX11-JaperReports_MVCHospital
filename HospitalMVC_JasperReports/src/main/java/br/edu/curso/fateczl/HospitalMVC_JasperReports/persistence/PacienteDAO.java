package br.edu.curso.fateczl.HospitalMVC_JasperReports.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.edu.curso.fateczl.HospitalMVC_JasperReports.model.Paciente;

@Repository
public class PacienteDAO implements IPacienteDAO{
    
	@Autowired
	GenericDAO gDAO;
	@Override
	public List<Paciente> lista_Paciente() throws SQLException, ClassNotFoundException {
		List<Paciente> pacientes = new ArrayList<>();
		Connection c = gDAO.getConnection();
		String sql = "SELECT Id, nome, data_nasc FROM Paciente";
		
		PreparedStatement ps = c.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
		Paciente paciente = new Paciente();
		paciente.setId((rs.getInt("Id")));
		paciente.setNome(rs.getString("nome"));
		paciente.setData_nasc(LocalDate.parse(rs.getString("data_nasc")));
		pacientes.add(paciente);	
		}
		c.close();
		ps.close();
		rs.close();
		
		return pacientes;
	}

}
