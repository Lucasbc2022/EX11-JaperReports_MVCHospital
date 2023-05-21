package br.edu.curso.fateczl.HospitalMVC_JasperReports.controller;


import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.edu.curso.fateczl.HospitalMVC_JasperReports.model.Medico;
import br.edu.curso.fateczl.HospitalMVC_JasperReports.model.Paciente;
import br.edu.curso.fateczl.HospitalMVC_JasperReports.persistence.ConsultarDAO;
import br.edu.curso.fateczl.HospitalMVC_JasperReports.persistence.GenericDAO;
import br.edu.curso.fateczl.HospitalMVC_JasperReports.persistence.MedicoDAO;
import br.edu.curso.fateczl.HospitalMVC_JasperReports.persistence.PacienteDAO;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.util.JRLoader;



@Controller
public class AtendimentoController {
    
	@Autowired
	GenericDAO gDAO;
	
	@Autowired
	MedicoDAO mDAO;
	
	@Autowired
	PacienteDAO pDAO;
	
	@Autowired
	ConsultarDAO cDAO;

	@RequestMapping(name = "atendimento", value = "/atendimento", method = RequestMethod.GET)
	public ModelAndView init(ModelMap model) {
		List<Medico> medicos = new ArrayList<>();
		List<Paciente> pacientes = new ArrayList<>();
		
		String saida = "";
		String erro = "";
		
		try {
			medicos = mDAO.lista_Medicos();
			pacientes = pDAO.lista_Paciente();
			
			System.out.println(medicos);
			System.out.println(pacientes);
		} catch (SQLException | ClassNotFoundException e) {
			erro = e.getMessage();
		} finally {
			model.addAttribute("medicos", medicos);
			model.addAttribute("pacientes", pacientes);
			model.addAttribute("saida", saida);
			model.addAttribute("erro", erro);
		}
		
		return new ModelAndView("atendimento");
	}	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(name = "relatorio", value = "/relatorio", method = RequestMethod.POST)
	public ResponseEntity geraRelatorio(@RequestParam Map<String, String> allParam, ModelMap model) {
		
			String botao = allParam.get("botao");
			
			String medico = allParam.get("medico");
			String paciente = allParam.get("paciente");	
			String cid = allParam.get("cid");
            String tratamento = allParam.get("tratamento");
			
			String saida = "";
            String erro = "";		
		 
		 //Definindo elementos que serão passados como parametros para o Jasper
		 Map<String, Object> param = new HashMap<String, Object>();
		 param.put("tratamento", tratamento);
		 
		 byte[] bytes = null;
		 
		 System.out.println(tratamento);
		 //Inicializando elementos do response
		 InputStreamResource resource = null;
		 HttpStatus status = null;
		 HttpHeaders header = new HttpHeaders();
		 
		 try {
			if(botao.equalsIgnoreCase("gerar")) {
			int CID = Integer.parseInt(cid);
			saida = cDAO.inserir_consulta(paciente, medico, CID, tratamento);
			System.out.println(paciente +" "+ medico +" "+ CID +" "+ tratamento);

			 
			Connection conn = gDAO.getConnection();
			System.out.println(conn);
			File arquivo = ResourceUtils.getFile("classpath:reports/relatorio_hospital.jasper");
			System.out.println(arquivo);
			JasperReport report = 
					(JasperReport) JRLoader.loadObjectFromFile(arquivo.getAbsolutePath());
			System.out.println(report);
			bytes = JasperRunManager.runReportToPdf(report, param, conn);
			System.out.println(bytes);
			System.out.println(bytes.length);
			}			
		} catch (FileNotFoundException | JRException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			erro = e.getMessage();
			status = HttpStatus.BAD_REQUEST;
		} finally {
			model.addAttribute("saida", saida);
			model.addAttribute("erro", erro);
			if(erro.equals("")) {
				InputStream inputStream = new ByteArrayInputStream(bytes);
				System.out.println(inputStream);
				resource = new InputStreamResource(inputStream);
				header.setContentLength(bytes.length);
				header.setContentType(MediaType.APPLICATION_PDF);
				status = HttpStatus.OK;
			}
			
		}
 
		 
		return new ResponseEntity(resource, header, status);		
	}
		
}
