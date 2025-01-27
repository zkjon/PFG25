const inputPrecio = document.getElementById('inputPrecio');
const displayPrecio = document.getElementById('displayPrecio');


inputPrecio.addEventListener('input', async () => {
    let precio = inputPrecio.value;

    precio = new Intl.NumberFormat('es-ES', { style: 'currency', currency: 'EUR'}).format(precio);
    displayPrecio.innerText = precio;

});
