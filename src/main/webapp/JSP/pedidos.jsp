<jsp:directive.page contentType="text/html" pageEncoding="UTF-8"/>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <c:import url="/INC/head.jsp">
        <c:param name="titulo" value="Historial"/>
    </c:import>

</head>
<body>

<c:if test="${sessionScope.usuario == null}">
    <c:import url="/INC/header-anonimo.jsp"/>
</c:if>
<c:if test="${sessionScope.usuario != null}">
    <c:import url="/INC/header-usuario.jsp"/>
</c:if>

<main class="mt-5 p-2">
    <div class="container mt-5">
        <!-- Desplegable por cada pedido -->
        <c:forEach var="pedido" items="${requestScope.pedidos}">
            <div class="accordion" id="accordion${pedido.id}">
                <div class="card mb-3">
                    <div class="card-header bg-danger text-white" id="heading${pedido.id}">
                        <h2 class="mb-0">
                            <button class="btn btn-link text-white d-flex justify-content-between align-items-center w-100" type="button" data-bs-toggle="collapse" data-bs-target="#collapse${pedido.id}" aria-expanded="false" aria-controls="collapse${pedido.id}">
                                <span><strong>Pedido nº ${pedido.id}</strong> - ${pedido.fecha}</span>
                                <span class="ms-2">
                                     <i class="fas fa-chevron-down"></i>
                                 </span>
                            </button>
                        </h2>
                    </div>

                    <div id="collapse${pedido.id}" class="collapse" aria-labelledby="heading${pedido.id}" data-bs-parent="#accordion${pedido.id}">
                        <div class="card-body">
                            <!-- Tabla con detalles de los productos del pedido -->
                            <table class="table table-responsive">
                                <thead class="table-primary border-bottom border-1 border-dark">
                                <tr>
                                    <th class="text-center">Imagen</th>
                                    <th class="text-start">Producto</th>
                                    <th class="text-end">Precio unitario</th>
                                    <th class="text-end">Cantidad</th>
                                    <th class="text-end">Subtotal</th>
                                </tr>
                                </thead>
                                <tbody class="table-danger">
                                <c:forEach var="linea" items="${pedido.lineasPedidos}">
                                    <tr>
                                        <td class="text-center">
                                            <img src="${applicationScope.contexto}/IMG/imagen/productos/${linea.producto.imagen}.jpg" alt="${linea.producto.nombre}" class="img-thumbnail" style="width: 50px; height: 50px;" />
                                        </td>
                                        <td class="text-start">${linea.producto.nombre}</td>
                                        <td class="text-end">
                                            <fmt:formatNumber value="${linea.producto.precio}" type="currency" />
                                        </td>
                                        <td class="text-end">${linea.cantidad}</td>
                                        <td class="text-end">
                                            <fmt:formatNumber value="${linea.producto.precio * linea.cantidad}" type="currency" />
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                                <tfoot class="border-top border-2 border-dark fw-bold">
                                <tr>
                                    <td colspan="3" class="border-0"></td>
                                    <td colspan="2" class="table-success h4">Resumen</td>
                                </tr>
                                <tr>
                                    <td colspan="3" class="border-0"></td>
                                    <td class="text-end table-success">Base Imponible:</td>
                                    <td class="text-end table-success">
                                        <fmt:formatNumber value="${pedido.importe}" type="currency" />
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="3" class="border-0"></td>
                                    <td class="text-end table-success">Importe de IVA (21&percnt;):</td>
                                    <td class="text-end table-success">
                                        <fmt:formatNumber value="${pedido.importe * pedido.iva}" type="currency" />
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="3" class="border-0"></td>
                                    <td class="text-end table-success">Total a pagar:</td>
                                    <td class="text-end table-success text-danger">
                                        <fmt:formatNumber value="${pedido.importe + (pedido.importe * pedido.iva)}" type="currency" />
                                    </td>
                                </tr>
                                </tfoot>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</main>



<c:import url="/INC/footer.jsp"/>

</body>
</html>
