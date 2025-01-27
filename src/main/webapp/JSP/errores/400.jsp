<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:directive.page contentType="text/html" pageEncoding="UTF-8"/>
<!DOCTYPE html>
<html lang="es">
<head>
    <c:import url="/INC/head.jsp">
        <c:param name="titulo" value="Error"/>
    </c:import>
</head>
<body>
<form action="${applicationScope.contexto}/FrontController" method="post" class="bg-white">
    <h1 class="text-center mt-5">Error 404</h1>
    <p class="text-center">No se ha encontrado el archivo.</p>
    <div class="text-center">
        <button class="btn btn-primary" name="accion" value="home" type="submit">Volver a la página principal</button>
    </div>
</form>
</body>
</html>