const botonBuscar = document.getElementById('boton-buscar');
const inputBuscar = document.getElementById('input-buscar');

document.addEventListener('input', async () => {
    if (inputBuscar.value.length > 2) {
        botonBuscar.classList.remove('btn-outline-danger');
        botonBuscar.classList.remove('disabled');
        botonBuscar.classList.add('btn-danger');
    } else {
        botonBuscar.classList.remove('btn-danger');
        botonBuscar.classList.add('btn-outline-danger');
        botonBuscar.classList.add('disabled');
    }
});