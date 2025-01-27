<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header class="d-flex justify-content-between align-items-center bg-body header p-1 shadow">
    <form action="FrontController" method="post">
        <button type="submit" name="inicio" class="btn border-0 m-0 bg-body">
            <img src="${applicationScope.contexto}/IMG/logoH.png" alt="logotipo" width="200px"/>
        </button>
    </form>

    <div class="d-flex gap-2">
        <form action="FrontController" method="post">
            <button class="btn btn-outline-danger" type="submit" name="accion" value="inicio">
                <i class="fas fa-home"></i>
            </button>
        </form>
        <div>
            <div class="dropdown">
                <button class="btn btn-outline-danger dropdown-toggle" type="button" id="dropdownMenuButton" data-bs-toggle="dropdown" aria-expanded="false">
                    <i class="fas fa-user"></i>
                </button>
                <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                    <li>
                        <button class="dropdown-item" type="button" data-bs-toggle="modal" data-bs-target="#loginModal">
                            Iniciar Sesi&oacute;n
                        </button>
                    </li>
                    <li>
                        <button class="dropdown-item" type="button" data-bs-toggle="modal" data-bs-target="#registerModal">
                            Registrarse
                        </button>
                    </li>
                </ul>
            </div>

            <div class="modal fade" id="loginModal" tabindex="-1" aria-labelledby="loginModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content border-danger">
                        <div class="modal-header bg-danger text-white">
                            <h5 class="modal-title" id="loginModalLabel">Iniciar Sesi&oacute;n</h5>
                            <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <form action="Acceso" method="post">
                                <div class="mb-3">
                                    <label for="emailIni" class="form-label text-danger">Correo electr&oacute;nico</label>
                                    <input type="email" class="form-control" id="emailIni" placeholder="Introduce tu email" name="email">
                                </div>
                                <div class="mb-3">
                                    <label for="passIni" class="form-label text-danger">Contrase&ntilde;a</label>
                                    <input type="password" class="form-control" id="passIni" name="pass" placeholder="Introduce tu contrase&ntilde;a">
                                </div>
                                <div class="text-center">
                                    <button type="submit" class="btn btn-outline-success w-50 disabled" name="login" id="botonLogin">Iniciar Sesi&oacute;n</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>

            <div class="modal fade" id="registerModal" tabindex="-1" aria-labelledby="registerModalLabel" aria-hidden="true">
                <div class="modal-dialog modal-xl">
                    <div class="modal-content border-danger">
                        <div class="modal-header bg-danger text-white">
                            <h5 class="modal-title" id="registerModalLabel">Registrarse</h5>
                            <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <form action="Acceso" method="post" id="registerForm">
                                <div class="row mb-3">
                                    <div class="col">
                                        <label for="nombre" class="form-label text-danger">Nombre</label>
                                        <input name="nombre" type="text" class="form-control  " id="nombre" placeholder="Juan Pedro" maxlength="20">
                                    </div>
                                    <div class="col">
                                        <label for="apellidos" class="form-label text-danger">Apellidos</label>
                                        <input name="apellidos" type="text" class="form-control  " id="apellidos" placeholder="Gonzalez Rodriguez" maxlength="30">
                                    </div>
                                </div>
                                <div class="row mb-3">
                                    <div class="col">
                                        <label for="nif" class="form-label text-danger">NIF</label>
                                        <input name="nif" type="text" class="form-control  " id="nif" placeholder="01334658" maxlength="9">
                                    </div>
                                    <div class="col">
                                        <label for="telefono" class="form-label text-danger">Tel&eacute;fono</label>
                                        <input name="telefono" type="text" class="form-control  " id="telefono" placeholder="123456789" maxlength="9">
                                    </div>
                                </div>
                                <div class="row mb-3">
                                    <div class="col">
                                        <label for="direccion" class="form-label text-danger">Direcci&oacute;n</label>
                                        <input name="direccion" type="text" class="form-control  " id="direccion" placeholder="Calle X N&uacute;mero X" maxlength="40">
                                    </div>
                                    <div class="col">
                                        <label for="provincia" class="form-label text-danger">Provincia</label>
                                        <input name="provincia" type="text" class="form-control  " id="provincia" placeholder="Badajoz" maxlength="30">
                                    </div>
                                </div>
                                <div class="row mb-3">
                                    <div class="col">
                                        <label for="cp" class="form-label text-danger">C&oacute;digo Postal</label>
                                        <input name="codigoPostal" type="text" class="form-control  " id="cp" placeholder="06800" maxlength="5">
                                    </div>
                                    <div class="col">
                                        <label for="localidad" class="form-label text-danger">Localidad</label>
                                        <input name="localidad" type="text" class="form-control  " id="localidad" placeholder="M&eacute;rida" maxlength="40">
                                    </div>
                                </div>
                                <div class="mb-3">
                                    <label for="email" class="form-label text-danger">Correo electr&oacute;nico</label>
                                    <input name="email" type="email" class="form-control  " id="email" placeholder="info@gmail.com" maxlength="50">
                                </div>
                                <div class="row mb-3">
                                    <div class="col">
                                        <label for="pass" class="form-label text-danger">Contrase&ntilde;a</label>
                                        <input name="contrasena" type="password" class="form-control  " id="pass" placeholder="******" maxlength="100">
                                    </div>
                                    <div class="col">
                                        <label for="pass2" class="form-label text-danger">Repite la contrase&ntilde;a</label>
                                        <input name="contrasena2" type="password" class="form-control  " id="pass2" placeholder="******" maxlength="100">
                                    </div>
                                </div>
                                <div class="text-center">
                                    <button type="submit" class="btn btn-outline-success w-50 disabled" name="registrarse" id="registerBoton">Registrarse</button>
                                </div>
                                <div class="text-center">
                                    <p class="text-danger h6 mt-2">Todos los campos son obligatorios</p>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <script src="${applicationScope.contexto}/JS/Acceso/register.js"></script>
        <script src="${applicationScope.contexto}/JS/Acceso/login.js"></script>


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
