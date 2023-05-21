<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href='<c:url value="./resources/css/styles.css"/>'>
<title>Atendimento</title>
</head>
<body>
      <div>
          <jsp:include page="menu.jsp"/>
      </div>
teste......


    <div align="center">
          <form action="relatorio" method="post" target="_blank">
                <table>
                 <tr>
                 <br />
                 <td>
                     <input type="text" id="medico" name="medico" placeholder="Informe o Nome do Medico">
                     <!--  
                     <select id="paciente" name="paciente">
                            <option value="0">Selecione o Paciente</option>
                            <c:if test="${not empty pacientes }">
                                <c:forEach items="${pacientes }" var="p">
                                          <option><c:out value="${p.nome }"></c:out></option>
                                </c:forEach>
                            </c:if>           
                     </select> -->
                 </td> 
                          
                 <td>
                 <input type="text" id="paciente" name="paciente" placeholder="Informe o Nome do Paciente">
                 <!-- 
                     <select id="medico" name="medico">
                            <option value="0">Selecione o Medico</option>
                            <c:if test="${not empty medicos }">
                                <c:forEach items="${medicos }" var="m">
                                          <option><c:out value="${m.nome }"></c:out></option>
                                </c:forEach>
                            </c:if>           
                     </select> -->
                 </td>                 
 
                 
                 </tr>  
                 <tr>
                     <td><input type="number" min="0" step="1" id="cid" name="cid" placeholder="Informe o CID"></td>
                     <td><input type="text" id="tratamento" name="tratamento" placeholder="Informe o Tratamento"></td>
                     <td><input type="submit" id="botao" name="botao" value="Gerar"></td>
                     <td><input type="submit" id="botao" name="botao" value="Exibir"></td>
                 </tr>
                 
                 </table>        
          </form>
     </div>
     
	 <div align="center">
		<c:if test="${not empty erro }">
			<H2><c:out value="${erro }" /></H2>
		</c:if>
		<c:if test="${not empty saida }">
			<H2><c:out value="${saida }" /></H2>
		</c:if>
	</div>     
</body>
</html>