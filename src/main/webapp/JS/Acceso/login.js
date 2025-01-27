document.getElementById('emailIni').addEventListener('input', () => {
    const email = document.getElementById('emailIni');
    const regex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
    const isValid = regex.test(email.value);

    if (isValid) {
        email.classList.add('border-success');
        email.classList.remove('border-danger');
    } else {
        email.classList.add('border-danger');
        email.classList.remove('border-success');
    }

    estadoBotonLogin();
});
document.getElementById('passIni').addEventListener('input', () => {
   estadoBotonLogin();
});

function estadoBotonLogin() {
    const email = document.getElementById('emailIni');
    const pass = document.getElementById('passIni');
    const boton = document.getElementById('botonLogin');

    if (email.classList.contains('border-success') && pass.value !== '') {
        boton.classList.remove('btn-outline-success');
        boton.classList.remove('disabled');
        boton.classList.add('btn-success');
    } else {
        boton.classList.remove('btn-success');
        boton.classList.add('btn-outline-success');
        boton.classList.add('disabled');
    }
}