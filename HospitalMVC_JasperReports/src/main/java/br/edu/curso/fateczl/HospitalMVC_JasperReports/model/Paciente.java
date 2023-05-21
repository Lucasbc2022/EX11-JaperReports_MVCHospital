package br.edu.curso.fateczl.HospitalMVC_JasperReports.model;

import java.time.LocalDate;

public class Paciente {
	
	private int id;
	private String nome;
	private LocalDate data_nasc;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public LocalDate getData_nasc() {
		return data_nasc;
	}
	public void setData_nasc(LocalDate data_nasc) {
		this.data_nasc = data_nasc;
	}
	@Override
	public String toString() {
		return "Paciente [id=" + id + ", nome=" + nome + ", data_nasc=" + data_nasc + "]";
	}
	
	

}
