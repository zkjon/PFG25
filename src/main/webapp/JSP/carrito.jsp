<jsp:directive.page contentType="text/html" pageEncoding="UTF-8"/>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <c:import url="/INC/head.jsp">
            <c:param name="titulo" value="Carrito"/>
        </c:import>
    </head>
    <body>

        <c:if test="${sessionScope.usuario == null}">
            <c:import url="/INC/header-anonimo.jsp"/>
        </c:if>
        <c:if test="${sessionScope.usuario != null}">
            <c:import url="/INC/header-usuario.jsp"/>
        </c:if>


        <main class="mt-3 p-5">
            <table class="table table-responsive">
                <thead class="table-primary border-bottom border-1 border-dark">
                    <tr>
                        <th class="text-center">Imagen</th>
                        <th class="text-start">Producto</th>
                        <th class="text-end">Precio unitario</th>
                        <th class="text-end">Cantidad</th>
                        <th class="text-end">Subtotal</th>
                        <th class="text-center">Control</th>
                    </tr>
                </thead>
                <tbody class="table-danger">
                    <c:forEach items="${sessionScope.pedido.lineasPedidos}" var="linea">
                        <tr>
                            <td class="text-center">
                                <img src="${applicationScope.contexto}/IMG/imagen/productos/${linea.producto.imagen}.jpg" alt="${linea.producto.nombre}" class="img-thumbnail" style="width: 50px; height: 50px;"/>
                            </td>
                            <td class="text-start">${linea.producto.nombre}</td>
                            <td class="text-end">
                                <fmt:formatNumber value="${linea.producto.precio}" type="currency"/>
                            </td>
                            <td class="text-end">${linea.cantidad}</td>
                            <td class="text-end">
                                <fmt:formatNumber value="${linea.producto.precio * linea.cantidad}" type="currency"/>
                            </td>
                            <td class="text-center">
                                <form action="CarritoUpdate" method="post">
                                    <button class="btn btn-success " name="accion" value="sumar-${linea.producto.id}">
                                        <i class="fas fa-plus small"></i>
                                    </button>
                                    <button class="btn btn-warning" name="accion" value="restar-${linea.producto.id}">
                                        <i class="fas fa-minus small"></i>
                                    </button>
                                    <button class="btn btn-danger" name="accion" value="eliminar-${ sessionScope.usuario != null ? linea.id : linea.producto.id }">
                                        <i class="fas fa-trash-alt small"></i>
                                    </button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
                <tfoot class="border-top border-2 border-dark fw-bold">
                    <tr>
                        <td colspan="4" class="border-0"></td>
                        <td colspan="2" class="h4 table-success border-1 border-bottom border-dark">Resumen</td>
                    </tr>
                    <tr>
                        <td colspan="4" class="border-0"></td>
                        <td class="text-end table-success ">Base Imponible:</td>
                        <td class="text-end table-success">
                            <fmt:formatNumber value="${sessionScope.pedido.importe}" type="currency"/>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="4" class="border-0"></td>
                        <td class="text-end table-success">Importe de IVA (21&percnt;):</td>
                        <td class="text-end table-success">
                            <fmt:formatNumber value="${sessionScope.pedido.importe * sessionScope.pedido.iva}" type="currency"/>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="4" class="border-0"></td>
                        <td class="text-end table-success">Total a pagar:</td>
                        <td class="text-end table-success text-danger">
                            <fmt:formatNumber value="${sessionScope.pedido.importe + (sessionScope.pedido.importe * sessionScope.pedido.iva)}" type="currency"/>
                        </td>
                    </tr>
                    <tr class="text-center">
                        <td colspan="4" class="border-0"></td>
                        <td class="border-0">
                            <form action="Pagar" method="post">
                                <button class="btn btn-primary w-100 ${sessionScope.usuario != null ? "" : "disabled"}" type="submit">Completar la compra</button>
                            </form>
                        </td>
                        <td class="border-0">
                            <form action="VaciarCarrito" method="post">
                                <button class="btn btn-danger w-100" name="accion" value="vaciar">Vaciar el carrito</button>
                            </form>
                        </td>
                    </tr>
                <c:if test="${sessionScope.usuario == null}">
                    <tr class="border-0">
                        <td colspan="6" class="text-center">
                            <p class="h3">Debes tener un usuario creado para completar la compra.</p>
                        </td>
                    </tr>
                </c:if>
                </tfoot>
            </table>
        </main>


        <c:import url="/INC/footer.jsp"/>

    </body>
</html>

