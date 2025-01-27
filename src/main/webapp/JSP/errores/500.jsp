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
        <h1 class="text-center mt-5">Error 500</h1>
        <p class="text-center">Ha ocurrido un error en el servidor. Por favor, inténtelo de nuevo más tarde.</p>
        <div class="text-center">
            <button class="btn btn-primary" name="accion" value="inicio" type="submit">Volver a la página principal</button>
        </div>
    </form>
</body>
</html>