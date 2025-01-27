document.getElementById("repetirPassword").addEventListener("input", validar);
document.getElementById("password").addEventListener("input", validar);
document.getElementById("nuevaPassword").addEventListener("input", validar);

function validar() {
    const password = document.getElementById("password").value;
    const nuevaPassword = document.getElementById("nuevaPassword").value;
    const repetirPassword = document.getElementById("repetirPassword").value;

    if (password.length > 5 && nuevaPassword.length > 5 && repetirPassword.length > 5) {
        document.getElementById("botonPass").classList.remove('btn-outline-danger');
        document.getElementById("botonPass").classList.remove('disabled');
        document.getElementById("botonPass").classList.add('btn-danger');
    } else {
        document.getElementById("botonPass").classList.remove('btn-danger');
        document.getElementById("botonPass").classList.add('btn-outline-danger');
        document.getElementById("botonPass").classList.add('disabled');
    }
}

