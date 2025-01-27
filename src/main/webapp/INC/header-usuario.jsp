<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<header class="d-flex justify-content-between align-items-center bg-body header p-1 shadow">
  <div class="d-flex align-items-center">
    <form action="FrontController" method="post">
      <button type="submit" name="accion" class="btn border-0 m-0 bg-body" value="inicio">
        <img src="${applicationScope.contexto}/IMG/logoH.png" alt="logotipo" width="200px"/>
      </button>
    </form>
    <div class="d-flex flex-column align-self-end">
      <p class="m-0">Bienvenido, ${sessionScope.usuario.nombre}</p>
      <p class="small text-end"> &Uacute;ltimo acceso:
        <fmt:formatDate type="both" dateStyle="medium" timeStyle="medium" value="${sessionScope.usuario.ultimoAcceso}" />
      </p>
    </div>
  </div>
  <div class="d-flex gap-2">

    <form action="FrontController" method="post">
      <button class="btn btn-outline-danger" type="submit" name="accion" value="inicio">
        <i class="fas fa-home"></i>
      </button>
    </form>
    <form action="FrontController" method="post">
        <button class="btn btn-outline-danger" type="submit" name="accion" value="usuario">
          <i class="fas fa-user-cog"></i>
        </button>
    </form>

    <form action="Historial" method="post">
      <button class="btn btn-outline-danger" type="submit" name="accion" value="pedidos">
        Pedidos
      </button>
    </form>

    <form method="post" action="CerrarSesion">
      <button class="btn btn-outline-danger" type="submit" name="exit">
        <i class="fas fa-sign-out-alt"></i>
      </button>
    </form>

    <form action="FrontController" method="post">
      <button class="btn btn-outline-danger" type="submit" name="accion" value="carrito">
        <i class="fas fa-shopping-cart"></i>
        <c:if test="${sessionScope.pedido != null}">
          <span class="badge bg-danger text-white rounded">${sessionScope.pedido.lineasPedidos.size()}</span>
        </c:if>
      </button>
    </form>
  </div>

</header>
