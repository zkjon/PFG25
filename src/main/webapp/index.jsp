<jsp:directive.page contentType="text/html" pageEncoding="UTF-8"/>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contexto" value="${pageContext.request.contextPath}" scope="application" />
<!DOCTYPE html>
<html lang="es">
    <head>
        <c:import url="/INC/head.jsp">
            <c:param name="titulo" value="Bits&Chips"/>
        </c:import>
    </head>
    <body>

    <c:if test="${sessionScope.usuario == null}">
        <c:import url="/INC/header-anonimo.jsp"/>
    </c:if>
    <c:if test="${sessionScope.usuario != null}">
        <c:import url="/INC/header-usuario.jsp"/>
    </c:if>
    <c:if test="${sessionScope.error != null}">
        <div class="alert alert-danger alert-dismissible fade show position-fixed top-0 end-0 m-3" role="alert" id="errorAlert">
                ${sessionScope.error}
            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
        </div>
        <script>
            setTimeout(() => {
                const alertElement = document.getElementById('errorAlert');
                if (alertElement) {
                    alertElement.classList.remove('show');
                    alertElement.classList.add('fade');
                    setTimeout(() => alertElement.remove(), 500);
                }
            }, 2000);
        </script>
    </c:if>
    <c:if test="${requestScope.success != null}">
        <div class="alert alert-success alert-dismissible fade show position-fixed top-0 end-0 m-3" role="alert" id="successAlert">
            ${requestScope.success}
            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
        </div>
        <script>
            setTimeout(() => {
                const alertElement = document.getElementById('successAlert');
                if (alertElement) {
                    alertElement.classList.remove('show');
                    alertElement.classList.add('fade');
                    setTimeout(() => alertElement.remove(), 500);
                }
            }, 2000);
        </script>
    </c:if>



    <main class="mt-3 p-2">
        <section class="container-fluid">
            <article class="row">

                <%-- Filtros --%>
                <aside class="col-lg-2 col-md-4 col-12 mb-3">
                    <h2 class="text-start text-dark">B&uacute;squeda</h2>

                    <form action="BusquedaController" class="input-group" method="post">
                        <input id="input-buscar" type="text" class="form-control" name="busqueda" placeholder="Buscar" aria-label="Buscar" aria-describedby="boton-buscar">
                        <button class="btn btn-outline-danger disabled" type="submit" id="boton-buscar">
                            <i class="fas fa-search"></i>
                        </button>
                    </form>

                    <h2 class="text-start text-dark mt-3">Filtros</h2>

                    <form action="FiltroController" method="post">
                        <div class="mt-3">
                            <label for="inputPrecio" class="h6 d-flex justify-content-between">
                                <span>Precio</span>
                                <span class="text-end badge bg-danger" id="displayPrecio">
                                    ${ requestScope.precio != null ? requestScope.precio : "2000,00&euro;"  }
                                </span>
                            </label>
                            <input type="range" class="form-range" min="0" max="2000" value="${ requestScope.precio != null ? requestScope.precio : 2000  }" step="0.01" id="inputPrecio" name="precio">
                            <p class="h6 text-end">Max.: <fmt:formatNumber type="currency" value="2000.00" /></p>
                        </div>

                        <div class="accordion mt-3" id="accordionCategoria">
                            <div class="accordion-item">
                                <h2 class="accordion-header" id="headingCategoria">
                                    <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseCategoria" aria-expanded="true" aria-controls="collapseCategoria">
                                        Categor&iacute;as
                                    </button>
                                </h2>
                                <div id="collapseCategoria" class="accordion-collapse collapse" aria-labelledby="headingCategoria" data-bs-parent="#accordionCategoria">
                                    <div class="accordion-body">
                                        <ul class="list-group list-group-flush">
                                            <c:forEach var="c" items="${applicationScope.categorias}">
                                                <li class="list-group-item">
                                                    <input type="checkbox" name="categoria" value="${c.id}" id="categoria-${c.id}">
                                                    <label for="categoria-${c.id}">${c.nombre}</label>
                                                </li>
                                            </c:forEach>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="accordion mt-3" id="accordionMarca">
                            <div class="accordion-item">
                                <h2 class="accordion-header" id="headingMarca">
                                    <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseMarca" aria-expanded="true" aria-controls="collapseMarca">
                                        Marcas
                                    </button>
                                </h2>
                                <div id="collapseMarca" class="accordion-collapse collapse" aria-labelledby="headingMarca" data-bs-parent="#accordionMarca">
                                    <div class="accordion-body">
                                        <ul class="list-group list-group-flush">
                                            <c:forEach var="m" items="${applicationScope.marcas}">
                                                <li class="list-group-item">
                                                    <input type="checkbox" name="marca" value="${m}" id="marca-${m}">
                                                    <label for="marca-${m}">${m}</label>
                                                </li>
                                            </c:forEach>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <button type="submit" class="btn btn-danger w-100 mt-3">Filtrar</button>
                    </form>
                </aside>

                <%-- Productos --%>
                    <div class="col-lg-10 col-md-8 col-12">
                        <form action="Carrito" method="post">
                            <div class="row">
                                <c:forEach var="p" items="${requestScope.productos}">
                                    <div class="col-xl-3 col-md-6 col-sm-6 col-12 mb-4">
                                        <div class="card h-100 border-dark rounded">
                                            <a href="#productModal${p.id}" data-bs-toggle="modal">
                                                <img src="IMG/imagen/productos/${p.imagen}.jpg"
                                                     class="card-img-top rounded-top card-img img-fluid"
                                                     alt="${p.nombre}"
                                                     style="height: 200px; object-fit: cover;">
                                            </a>
                                            <div class="card-body d-flex flex-column p-4">
                                                <h5 class="card-title text-center text-dark font-weight-bold">${p.nombre}</h5>
                                                <p class="card-text text-center small">Haga clic en la imagen para ver más información...</p>
                                                <div class="d-flex justify-content-between align-items-center mt-auto">
                                                    <span class="text-primary h5 font-weight-bold">
                                                        <fmt:formatNumber value="${p.precio}" type="currency" />
                                                    </span>
                                                    <button type="submit" class="btn btn-success d-flex align-items-center px-4 py-2" name="agregar" value="${p.id}">
                                                        <i class="fas fa-cart-plus mr-2"></i>
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <!-- Modal específico para cada producto -->
                                    <div class="modal fade" id="productModal${p.id}" tabindex="-1" aria-labelledby="productModalLabel${p.id}" aria-hidden="true">
                                        <div class="modal-dialog modal-dialog-centered modal-xl">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <h5 class="modal-title" id="productModalLabel${p.id}">${p.nombre}</h5>
                                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                </div>
                                                <div class="modal-body d-flex justify-content-between align-items-center">
                                                    <img src="IMG/imagen/productos/${p.imagen}.jpg" class="img-fluid rounded mb-3" alt="${p.nombre}">
                                                    <div>
                                                        <p>${p.descripcion}</p>
                                                        <p class="text-center text-primary h5 font-weight-bold">
                                                            <fmt:formatNumber value="${p.precio}" type="currency" />
                                                        </p>
                                                    </div>
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                        </form>
                    </div>
            </article>
        </section>
    </main>


    <c:import url="/INC/footer.jsp">
        <c:param name="index" value="true" />
    </c:import>
    </body>
</html>
