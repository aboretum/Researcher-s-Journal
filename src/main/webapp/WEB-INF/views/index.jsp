
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>

<% String requestPage = request.getParameter("page"); %>
<% String sessionUsername = (String)session.getAttribute("username"); %>

<% if(sessionUsername == null){ %>
	<jsp:include page="login2.jsp" flush="true" />
	
<% }else{ %>

	<jsp:include page="main.jsp" flush="true" />
<% } %>



