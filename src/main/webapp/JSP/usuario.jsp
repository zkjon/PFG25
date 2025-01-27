<jsp:directive.page contentType="text/html" pageEncoding="UTF-8"/>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <c:import url="/INC/head.jsp">
        <c:param name="titulo" value="Usuario"/>
    </c:import>
</head>
<body>

<c:if test="${sessionScope.usuario == null}">
    <c:import url="/INC/header-anonimo.jsp"/>
</c:if>
<c:if test="${sessionScope.usuario != null}">
    <c:import url="/INC/header-usuario.jsp"/>
</c:if>
<c:if test="${requestScope.error != null}">
    <div class="alert alert-danger alert-dismissible fade show position-fixed top-0 end-0 m-3" role="alert"
         id="errorAlert">
            ${requestScope.error}
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
<c:if test="${requestScope.exito != null}">
    <div class="alert alert-success alert-dismissible fade show position-fixed top-0 end-0 m-3" role="alert"
         id="exitoAlert">
            ${requestScope.exito}
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    </div>
    <script>
        setTimeout(() => {
            const alertElement = document.getElementById('exitoAlert');
            if (alertElement) {
                alertElement.classList.remove('show');
                alertElement.classList.add('fade');
                setTimeout(() => alertElement.remove(), 500);
            }
        }, 2000);
    </script>
</c:if>

<main class="mt-3 p-2">
    <section class="container-fluid bg-light p-4">
        <article class="bg-white p-4 rounded shadow">
            <div class="row mb-4">
                <div class="col-12">
                    <h1 class="text-center text-danger">Panel de control</h1>
                </div>
            </div>
            <div class="row">
                <!-- Datos personales -->
                <form action="Actualizar" method="post" class="col-md-6 col-lg-4 mb-4" id="formDatos">
                    <h2 class="text-center text-danger">Datos personales</h2>
                    <div class="form-group row mb-3">
                        <label for="nombre" class="col-4 col-form-label text-danger">Nombre</label>
                        <div class="col-8">
                            <input type="text" class="form-control border-danger" id="nombre" name="nombre"
                                   value="${sessionScope.usuario.nombre}" required>
                        </div>
                    </div>
                    <div class="form-group row mb-3">
                        <label for="apellidos" class="col-4 col-form-label text-danger">Apellidos</label>
                        <div class="col-8">
                            <input type="text" class="form-control border-danger" id="apellidos" name="apellidos"
                                   value="${sessionScope.usuario.apellidos}" required>
                        </div>
                    </div>
                    <div class="form-group row mb-3">
                        <label for="telefono" class="col-4 col-form-label text-danger">Teléfono</label>
                        <div class="col-8">
                            <input type="text" class="form-control border-danger" id="telefono" name="telefono"
                                   maxlength="9" value="${sessionScope.usuario.telefono}">
                        </div>
                    </div>
                    <div class="form-group row mb-3">
                        <label for="direccion" class="col-4 col-form-label text-danger">Dirección</label>
                        <div class="col-8">
                            <input type="text" class="form-control border-danger" id="direccion" name="direccion"
                                   value="${sessionScope.usuario.direccion}" required>
                        </div>
                    </div>
                    <div class="form-group row mb-3">
                        <label for="codigoPostal" class="col-4 col-form-label text-danger">Código postal</label>
                        <div class="col-8">
                            <input type="text" class="form-control border-danger" id="codigoPostal" name="codigoPostal"
                                   maxlength="5" value="${sessionScope.usuario.codigoPostal}" required>
                        </div>
                    </div>
                    <div class="form-group row mb-3">
                        <label for="localidad" class="col-4 col-form-label text-danger">Localidad</label>
                        <div class="col-8">
                            <input type="text" class="form-control border-danger" id="localidad" name="localidad"
                                   value="${sessionScope.usuario.localidad}" required>
                        </div>
                    </div>
                    <div class="form-group row mb-3">
                        <label for="provincia" class="col-4 col-form-label text-danger">Provincia</label>
                        <div class="col-8">
                            <input type="text" class="form-control border-danger" id="provincia" name="provincia"
                                   value="${sessionScope.usuario.provincia}" required>
                        </div>
                    </div>
                    <input type="hidden" name="id" value="${sessionScope.usuario.id}">
                    <input type="hidden" name="email" value="${sessionScope.usuario.email}">
                    <button type="submit" class="btn btn-danger btn-block w-100">Actualizar datos</button>
                </form>

                <!-- Espacio vacío -->
                <div class="col-md-6 col-lg-4 d-none d-lg-block">
                </div>

                <!-- Cambiar contraseña -->
                <form action="ActualizarPassword" method="post" class="col-md-6 col-lg-4" id="formPass">
                    <h2 class="text-center text-danger">Cambiar contraseña</h2>
                    <div class="form-group row mb-3">
                        <label for="password" class="col-4 col-form-label text-danger">Contraseña actual</label>
                        <div class="col-8">
                            <input type="password" class="form-control border-danger" id="password" name="contrasena">
                        </div>
                    </div>
                    <div class="form-group row mb-3">
                        <label for="nuevaPassword" class="col-4 col-form-label text-danger">Nueva contraseña</label>
                        <div class="col-8">
                            <input type="password" class="form-control border-danger" id="nuevaPassword"
                                   name="nuevaContrasena">
                        </div>
                    </div>
                    <div class="form-group row mb-3">
                        <label for="repetirPassword" class="col-4 col-form-label text-danger">Repetir contraseña</label>
                        <div class="col-8">
                            <input type="password" class="form-control border-danger" id="repetirPassword"
                                   name="repetirContrasena">
                        </div>
                    </div>
                    <input type="hidden" name="email" value="${sessionScope.usuario.email}">
                    <button type="submit" class="btn btn-outline-danger disabled btn-block w-100" id="botonPass">Cambiar
                        contraseña
                    </button>
                </form>
            </div>
        </article>
    </section>
</main>

<c:import url="/INC/footer.jsp"/>

<script src="${applicationScope.contexto}/JS/Actualizar/datos.js"></script>
<script src="${applicationScope.contexto}/JS/Actualizar/pass.js"></script>
</body>
</html>

