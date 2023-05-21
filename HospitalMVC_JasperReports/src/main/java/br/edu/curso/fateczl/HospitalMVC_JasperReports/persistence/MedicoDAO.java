package br.edu.curso.fateczl.HospitalMVC_JasperReports.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.edu.curso.fateczl.HospitalMVC_JasperReports.model.Medico;

@Repository
public class MedicoDAO implements IMedicoDAO {

	@Autowired
	GenericDAO gDAO;
	
	@Override
	public List<Medico> lista_Medicos() throws SQLException, ClassNotFoundException {
	    List<Medico> medicos = new ArrayList<>();
	    Connection c = gDAO.getConnection();
	    String sql = "SELECT Id, nome FROM Medico";
	    
	    PreparedStatement ps = c.prepareStatement(sql);
	    ResultSet rs = ps.executeQuery();
	    
	    while(rs.next()) {
	    	Medico medico = new Medico();
	    	medico.setId(rs.getInt("Id"));
	    	medico.setNome(rs.getString("nome"));
	    	medicos.add(medico);
	    }
	    
	    rs.close();
	    ps.close();
	    c.close();
		return medicos;
	}

}
