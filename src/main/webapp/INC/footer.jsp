<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<footer class="bg-dark p-1 text-white d-flex align-items-center justify-content-center shadow w-100">
    <div class="container text-center">
        <div class="row">
            <div class="col-md-4">
                <h5>Sobre nosotros</h5>
                <p>&copy;DWES - Todos los derechos reservados.</p>
                <p>Autor - Jon Imanol Ruiz Hermoso</p>
            </div>
            <div class="col-md-4">
                <h5>Cont&aacute;ctanos</h5>
                <p>Email: info@iesalbarregas.com</p>
                <p>Tel&eacute;fono: +123 456 7890</p>
            </div>
            <div class="col-md-4 fs-4">
                <h5>S&iacute;guenos</h5>
                <a href="https://www.facebook.com/IESAlbarregas" target="_blank"><i class="fab fa-facebook"></i></a>
                <a href="https://x.com/IESAlbarregas" target="_blank"><i class="fab fa-twitter"></i></a>
                <a href="https://www.instagram.com/ies.albarregas/" target="_blank"><i class="fab fa-instagram"></i></a>
            </div>
        </div>
    </div>
</footer>
<c:if test="${param.index != null}">
    <script src="${applicationScope.contexto}/JS/Filtros/precio.js"></script>
    <script src="${applicationScope.contexto}/JS/Filtros/buscar.js"></script>
</c:if>


