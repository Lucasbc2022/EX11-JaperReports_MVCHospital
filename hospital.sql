CREATE DATABASE hospital
GO

USE hospital
GO

CREATE TABLE Medico(
Id             INT NOT NULL,
nome           VARCHAR(100) NOT NULL
PRIMARY KEY(Id)
)
GO

CREATE TABLE Especialidade (
Id                     INT NOT NULL,
especialidade          VARCHAR(50) NOT NULL
PRIMARY KEY(Id)
)
GO

CREATE TABLE Paciente (
Id                  INT NOT NULL,
nome               VARCHAR(100) NOT NULL,
data_nasc           DATE NOT NULL
PRIMARY KEY(Id)
)
GO

CREATE TABLE Medico_Especialidade (
IdMedico            INT NOT NULL,
IdEspecialidade     INT NOT NULL
PRIMARY KEY(IdMedico, IdEspecialidade)
FOREIGN KEY(IdMedico) REFERENCES Medico(Id),
FOREIGN KEY(IdEspecialidade) REFERENCES Especialidade(Id)
)
GO

CREATE TABLE Medico_Paciente (
IdMedico          INT NOT NULL,
IdPaciente        INT NOT NULL,
dataHora          DATETIME NOT NULL,
CID               INT NOT NULL,
tratamento        VARCHAR(50) NOT NULL
PRIMARY KEY(IdMedico, IdPaciente, dataHora)
FOREIGN KEY(IdMedico) REFERENCES Medico(Id),
FOREIGN KEY(IdPaciente) REFERENCES Paciente(Id)
)
GO



INSERT INTO Medico (Id, nome)
VALUES             (1, 'Dr Phil'),
				   (2, 'Dr House')

INSERT INTO Especialidade (Id, especialidade)
VALUES                    (1, 'Psiquiatria'),
                          (2, 'Todas')


INSERT INTO Medico_Especialidade (IdMedico, IdEspecialidade)
VALUES                           (1, 1),
                                 (2, 2)

SELECT m.nome,
       e.especialidade
FROM Medico m, Especialidade e, Medico_Especialidade me
WHERE m.Id = me.IdMedico
  AND e.Id = me.IdEspecialidade


SELECT * FROM Paciente

INSERT INTO Paciente (Id, nome, data_nasc)
VALUES               (1, 'Lucas', '29/08/2001'),
                     (2, 'Gabriel', '05/06/2003')

DROP PROCEDURE sp_insere_cid_tratamento

CREATE PROCEDURE sp_insere_cid_tratamento (@nome_paciente VARCHAR(30),
                                           @nome_medico VARCHAR(30),
										   @CID INT,
										   @tratamento VARCHAR(50))
AS
BEGIN
     DECLARE @id_paciente INT,
	         @id_medico INT,
			 @data_hora DATETIME

	    SET @id_paciente = (SELECT Id FROM Paciente WHERE nome = @nome_paciente)
		SET @id_medico = (SELECT Id FROM Medico WHERE nome = @nome_medico)
		SET @data_hora = GETDATE()

		PRINT(@id_medico)
		PRINT(@id_paciente)
		PRINT(@data_hora)
		PRINT(@CID)
		PRINT(@tratamento)

		INSERT INTO Medico_Paciente (IdMedico, IdPaciente, dataHora, CID, tratamento)
		VALUES                      (@id_medico, @id_paciente, @data_hora, @CID, @tratamento)
END

EXEC sp_insere_cid_tratamento 'Lucas', 'Dr Phil', 1011, 'placebo'
EXEC sp_insere_cid_tratamento 'Lucas', 'Dr Phil', 1011, 'nenhum'
EXEC sp_insere_cid_tratamento 'Lucas', 'Dr Phil', 1011, '5555555'

SELECT p.nome AS nome_paciente,
       p.data_nasc,
	   m.nome AS nome_medico,
	   e.especialidade,
	   CONVERT(CHAR(10),CONVERT(DATE, mp.dataHora, 103), 103) AS data,
	   CAST(CONVERT(TIME, mp.dataHora, 103) AS VARCHAR(5)) AS hora,
	   mp.CID,
	   mp.tratamento
FROM Paciente p, Medico m, Especialidade e, Medico_Especialidade me, Medico_Paciente mp
WHERE p.id = mp.IdPaciente
  AND m.Id = mp.IdMedico
  AND m.Id = me.IdMedico
  AND e.Id = me.IdEspecialidade
  AND mp.tratamento = 'placebo'



SELECT * FROM Paciente
SELECT * FROM Medico
SELECT * FROM Especialidade
SELECT * FROM Medico_Especialidade
SELECT * FROM Medico_Paciente


