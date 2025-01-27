const inputs = document.querySelectorAll('#formDatos input');

inputs.forEach((input) => {
    input.addEventListener('input', (e) => {
        e.target.classList.remove('border-danger');
        e.target.classList.add('border-success')
    });
});