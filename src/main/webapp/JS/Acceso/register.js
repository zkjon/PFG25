const password = document.getElementById('pass');
const password2 = document.getElementById('pass2');
const boton = document.getElementById('registerBoton');
const dniField = document.getElementById('nif');

const letrasDNI = "TRWAGMYFPDXBNJZSQVHLCKE";

const calcularLetraDNI = (dni) => {
    const numero = parseInt(dni, 10);
    if (!isNaN(numero) && dni.length === 8) {
        return letrasDNI[numero % 23];
    }
    return "";
};

const validateField = (celda, regex) => {
    const isValid =
        typeof regex === 'function'
            ? regex(celda.value)
            : regex.test(celda.value);

    if (isValid) {
        celda.classList.add('border-success');
        celda.classList.remove('border-danger');
    } else {
        celda.classList.add('border-danger');
        celda.classList.remove('border-success');
    }

    estadoBoton();
};

const validaciones = {
    nombre: /^[a-zA-Z\s]{1,20}$/,
    apellidos: /^[a-zA-Z\s]{1,30}$/,
    nif: /^[0-9]{8}[A-Za-z]$/,
    telefono: /^[0-9]{9}$/,
    direccion: /^.{1,40}$/,
    provincia: /^[a-zA-Z\s]{1,30}$/,
    cp: /^[0-9]{5}$/,
    localidad: /^[a-zA-Z\s]{1,40}$/,
    email: /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/,
    pass: /^.{6,100}$/,
    pass2: value => value === password.value
};

const estadoBoton = () => {
    const allValid = Object.keys(validaciones).every(key => {
        const celda = document.getElementById(key);
        return typeof validaciones[key] === 'function'
            ? validaciones[key](celda.value)
            : validaciones[key].test(celda.value);
    });

    if (allValid) {
        boton.classList.remove('disabled');
        boton.classList.remove('btn-outline-success');
        boton.classList.add('btn-success');
    } else {
        boton.classList.remove('btn-success');
        boton.classList.add('btn-outline-success');
        boton.classList.add('disabled');
    }
};

Object.keys(validaciones).forEach(key => {
    const celda = document.getElementById(key);
    celda.addEventListener('input', () => validateField(celda, validaciones[key]));
});

password.addEventListener('input', () => validateField(password2, validaciones.pass2));

dniField.addEventListener('input', () => {
    const dniNumbers = dniField.value.slice(0, 8);
    if (dniNumbers.length === 8) {
        const letra = calcularLetraDNI(dniNumbers);
        if (letra) {
            dniField.value = `${dniNumbers}${letra}`;
        }
    }
    validateField(dniField, validaciones.nif);
});
