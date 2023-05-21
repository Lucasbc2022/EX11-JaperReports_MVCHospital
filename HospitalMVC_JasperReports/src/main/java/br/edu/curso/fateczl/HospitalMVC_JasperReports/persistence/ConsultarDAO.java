package br.edu.curso.fateczl.HospitalMVC_JasperReports.persistence;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ConsultarDAO implements IConsultaDAO {

	@Autowired
	GenericDAO gDAO;
	
	@Override
	public String inserir_consulta(String nome_paciente, String nome_medico, int cid, String tratamento)
			throws SQLException, ClassNotFoundException {
		Connection c = gDAO.getConnection();
		
		String sp_sql = "{CALL sp_insere_cid_tratamento(?,?,?,?)}";
		
		CallableStatement cs = c.prepareCall(sp_sql);
		cs.setString(1, nome_paciente);
		cs.setString(2, nome_medico);
		cs.setInt(3, cid);
		cs.setString(4, tratamento);
		cs.execute();
		System.out.println(nome_paciente +" "+ nome_medico +" "+ cid +" "+ tratamento);
		
		c.close();
		cs.close();
		return "Consulta Inserida";
	}

}
