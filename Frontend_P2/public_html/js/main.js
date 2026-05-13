const API_URL = "http://localhost:8080/api/atletas";

//datos de prueba.
//let significa que la variable puede cambiar.
//Es un array de objetos, cada objeto es un atleta con sus propiedades. 
//Se usa let y no const porque el array se reasigna al filtrar o eliminar.
let listaAtletas =[
    {id: 1, nombre:"Julian", identificacion: "123345", edad:"18", genero:"M",categoria:"Sub-23", especialidad:"Media Distancia"},
    {id: 45, nombre:"yotas marcelo", identificacion:"1515", edad:"26", genero:"F" ,categoria:"Benjamin", especialidad:"Ultraman"}
];

//le asignamos colores a las especialidades 
//Mapea cada especialidad a una clase CSS de Bootstrap que define el color del borde. 
//Se usa en CrearTarjetas y CrearCarrusel
const coloresEspecialidad = {

    'Super Sprint':     'border-success',
    'Sprint':           'border-warning',
    'Olimpica':         'border-info',
    'Media Distancia':  'border-secondary',
    'Larga Distancia':  'border-danger',
    'Ultraman':         'border-dark'
};

// Iconos por especialidad
//Se usa solo en CrearCarrusel
const iconosEspecialidad = {
    'Super Sprint':   'bi-wind', 
    'Sprint': 'bi-lightning',
    'Olimpica':  'bi-bicycle',
    'Media Distancia': 'bi-person-walking',
    'Larga Distancia':  'bi-person-arms-up',
    'Ultraman':  'bi-fire'
};

//calcula la categoria del atleta a registrar de forma automatica
function calcularCategoria(edad) {
    edad = parseInt(edad);
    if (edad === 7)          return 'Pre-benjamin';
    if (edad <= 9)           return 'Benjamin';
    if (edad <= 11)          return 'Alevin';
    if (edad <= 13)          return 'Infantil';
    if (edad <= 15)          return 'Cadetes';
    if (edad <= 17)          return 'Juvenil';
    if (edad <= 19)          return 'Junior';
    if (edad <= 23)          return 'Sub-23';
    if (edad <= 39)          return 'Absoluta';
    if (edad >= 40)          return 'Veteranos';   // 40 en adelante, veteranos juntos
    return 'Sin categoria';
}

// oculta la parte de actualizar y eliminar de la lista.html
//Los if evitan errores en páginas donde esos elementos no existen.
//Comprueba si el elemento realmente existe en la página antes de actuar (para que no falle con las otras paginas)
function ocultarSecciones() {
    const eliminar    = document.getElementById('contenedor-eliminar');
    const actualizar  = document.getElementById('contenedor-actualizar');
    if (eliminar)  eliminar.classList.add('d-none');
    if (actualizar) actualizar.classList.add('d-none');
}

// traduce el tipo de mensaje a la clase CSS de Bootstrap correspondiente.
function mostrarMensaje(tipo, texto) {
    // mapeo de tipo a clase Bootstrap
    const clases = {
        'exitoso': 'alert-success',
        'warning': 'alert-warning',
        'error':   'alert-danger',
        'sucess':  'alert-success'
    };

    //Busca la clase para el tipo recibido. Si el tipo no existe en el objeto, usa 'alert-info' como valor por defecto gracias al operador ||.
    const claseAlerta = clases[tipo] || 'alert-info';
    //Crea un elemento <div> nuevo en memoria
    const alerta = document.createElement('div');
    //se coloca en la esquina superior derecha
    alerta.className = `alert ${claseAlerta} alert-dismissible fade show position-fixed top-0 end-0 m-3`;
    alerta.style.zIndex = 9999;
    alerta.innerHTML = `
        ${texto}
        <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
    `;
    //se inserta el <div> al final del <body>
    document.body.appendChild(alerta);

    // desaparece automáticamente en 3 segundos
    setTimeout(() => alerta.remove(), 3000);
}    

let especialidadActiva = 'Todas';

//Crea el carrusel del registro.
//Busca el contenedor del carrusel. Si no existe (estamos en una pagina diferente a listado.html),sale inmediatamente 
//con return para no generar errores.
function CrearCarrusel(){
    const seguimiento = document.getElementById('carrusel-t');
    if (!seguimiento) return;

    //extrae solo las especialidades de cada atleta como array
    const especialidades = ['todas', ...new Set (listaAtletas.map(a => a.especialidad))];
    //se limpia el contenido anterior para evitar que se duplique al refrescar
    seguimiento.innerHTML = '';
    //filtra por especialidad
    especialidades.forEach(esp => {
        const cantidad = esp === 'todas'
        ? listaAtletas.length
        : listaAtletas.filter(a => a.especialidad === esp).length;
        //busca el color y icono de esa especialidad sino por defecto
        const colorBorde = coloresEspecialidad[esp]|| 'border-primary';
        const icono = iconosEspecialidad[esp]|| 'bi-star';
        const activo = especialidadActiva === esp ? 'border-3 shadow-sm' : '';
        //el html del carrusel
        seguimiento.innerHTML += ` 
        <div class="card text center p-2 flex-shrink-0 ${colorBorde} ${activo}"
            style="min-width:120px; cursor:pointer; border-top: 4px solid; border-top-color: inherit;"
            onclick="filtrarPorEspecialidad('${esp}')"
            id="esp-card-${esp}">
            <i class="bi ${icono} fs-4 mb-1"></i>
            <div class="fw-semibold small">${esp}</div>
            <div class="text-muted" style="font-size:0.75rem">${cantidad} atleta${cantidad !== 1 ? 's' : ''}</div>
        </div>`;
    });
}

//filtra por especialidad que se selecciona de lista
//se actualiza la variable global para que CrearCarrusel sepa cual marcar como activa al darle click
function filtrarPorEspecialidad(esp){
    especialidadActiva = esp;
    //filtro (filter crea un nuevo array)
    const lista = esp === 'todas'
    ? listaAtletas
    : listaAtletas.filter(a => a.especialidad === esp);
    //se crean de nuevo tabla y tarjetas con la lista filtrada, y recarga el carrusel para actualizar 
    //cual esta marcado como activo
    CrearTabla(lista);
    CrearTarjetas(lista);
    CrearCarrusel(); // refresca el carrusel para que se marque
}

//recarga la interfaz, evita bloqueos o fallos
//esto evita errores en páginas como consulta.html o registro.html donde la tabla y las tarjetas no existen.
function refrescarInterfaz() {
    CrearCarrusel();
    if (document.getElementById('tabla-atletas-body')) CrearTabla(listaAtletas);
    if (document.getElementById('vista-tarjetas'))     CrearTarjetas(listaAtletas);
}

//crea los datos 
//Cada vez que se agrega o borra a alguien, se llama esta función para que actualice la vista
//document.getElementById(...) va al html y agarra especificamente el contenedor de la tabla
//esto toma la lista de personas que estan guardadas y convertirla en etiquetas <tr> y <td>
function CrearTabla(listaAtletas){
    // se busca el cuerpo de la tabla por su ID
    const cuerpoTabla = document.getElementById('tabla-atletas-body');
    
    //se limpia o borra el contenido de la tabla ya creado para evitar la duplicacion de la informacion
    cuerpoTabla.innerHTML="";
    
    listaAtletas.forEach(atleta => {
        //se crea una fila(tr) en memoria
        //cada atleta crea un elemento <tr> en memoria.
        const fila = document.createElement('tr');
        
        //se llena la fila con celdas (td)
        //lena la fila con los datos del atleta
        //toma una fila existente en una tabla HTML (<tr>) y llena su interior automáticamente con celdas <td>
        fila.innerHTML=`
        <td> <img src="img/atleta-default.png" width="40" class="rounded-circle"></td>
        <td> ${atleta.identificacion}</td>
        <td>${atleta.nombre}</td>
        <td><span class="badge bg-info text-dark">${atleta.categoria}</span></td>
        <td>${atleta.especialidad}</td>
        <td class="text-end">
            <button class="btn btn-sm btn-warning" onclick="prepararModificacion(${atleta.id})">
                <i class="bi bi-pencil"></i>
            </button>
            <button class="btn btn-sm btn-danger" onclick="prepararEliminacion(${atleta.id})">
                <i class="bi bi-trash"></i>
            </button>
        </td>
        `;
        //se agrega la fila al cuerpo
        //toma la fila que acabamos de armar con sus datos y botones, y la "pega" físicamente
        //dentro del HTML para que el usuario la vea
        cuerpoTabla.appendChild(fila);
    });
}

// se crean las tarjetas de forma automatica
function CrearTarjetas(listaAtletas){
    const contenedor = document.getElementById('vista-tarjetas');
    //limpia el contenido previo
    contenedor.innerHTML ="";
    
    listaAtletas.forEach(atleta=>{
        //busca color especialidad
        const colorClase = coloresEspecialidad[atleta.especialidad] || 'border-primary';
        // se agrega la tarjeta al contenedor              
        contenedor.innerHTML +=`
        <div class="col-3">
            <div class="card h-100 card-atleta shadow-sm border-top ${colorClase} border-4">
                <img src="img/atleta-placeholder.jpg" class="card-img-top foto-atleta" alt="${atleta.nombre}">
                <div class="card-body text-center">
                    <span class="badge rounded-pill bg-light text-primary mb-2 border border-primary">
                        ${atleta.categoria}
                    </span>
                    <h5 class="card-title fw-bold mb-1">${atleta.nombre}</h5>
                    <p class="small text-muted mb-3">ID:${atleta.identificacion}</p>
                    <div class="d-flex justify-content-center gap-2">
                        <button class="btn btn-sm btn-outline-warning" onclick="prepararModificacion(${atleta.id})">
                            <i class="bi bi-pencil"></i>
                        </button>
                        <button class="btn btn-sm btn-outline-danger" onclick="prepararEliminacion(${atleta.id})">
                            <i class="bi bi-trash"></i>
                        </button>
                    </div>
                </div>
                <div class="card-footer bg-white border-0 text-center pb-3">
                    <small class="text-uppercase fw-bold text-secondary" style="font-size: 0.7rem;">
                        Especialidad: ${atleta.especialidad}
                    </small>
                </div>
            </div>
        </div>
        `;
    });
}

//la eliminacion antes de la confirmacion
function prepararEliminacion(idRecibido){
    //se busca el atleta, guarda la informacion en atletaencontrado
    //Recorre el arreglo devuelve el objeto completo del atleta que coincida con el id
    const atletaEncontrado = listaAtletas.find(a=> a.id === idRecibido);
    
    if (atletaEncontrado){
        // se llenan los textos de confirmacion que se muestran 
        //innerText Cambia solo el texto dentro de una etiqueta evitando que se inyecte HTML
        document.getElementById('eliminar-nombre-texto').innerText = atletaEncontrado.nombre;
        document.getElementById('eliminar-id-texto').innerText = atletaEncontrado.identificacion;
        
        //se muestra el panel de eliminacion, se quita el d-none. se ocultan lo otro
        document.getElementById('contenedor-eliminar').classList.remove('d-none');
        //se le agrega el d-none a pesar que ya tenga uno, el otro es que al refrescar no se vea. este se recarga la pagina y se quita
        document.getElementById('contenedor-actualizar').classList.add('d-none');
        
        //botones de realizar accion de borrado
        const btnFinal = document.getElementById('btn-confirmar-borrado');
        btnFinal.onclick =() => ejecutarEliminacionReal(idRecibido);
        
        //funcion que se encontro para una mejora visual 
        //Scroll suave hacia el panel para que el usuario lo vea
        //se encarga de que el navegador haga "scroll" (desplace la pantalla) automaticamente hasta el elemento que acabas de mostrar.
        document.getElementById('contenedor-eliminar').scrollIntoView({behavior: 'smooth'});
    }
}

//crea un nuevo array con todos los atletas excepto el que tiene ese id. 
//Se reasigna a listaAtletas reemplazando el array anterior.
function ejecutarEliminacionReal(idABorrar){
    //se filtra para quedarse con todo menos lo que sea el mismo id
    listaAtletas = listaAtletas.filter(a => a.id !== idABorrar);
    
    //se limpia la interfaz
    ocultarSecciones(); //se esconde el de alerta
    refrescarInterfaz();//vuelve a dibujar la tabla sin el atleta
    mostrarMensaje('exitoso','atleta eliminado');
}

function prepararModificacion(idRecibido){
    //se busca el atleta, guarda la informacion en atletaencontrado
    //Recorre el arreglo devuelve el objeto completo del atleta que coincida con el id
    const atleta = listaAtletas.find(a => a.id === idRecibido);
    
    if (atleta){
        //se carga los datos actuales en los inputs del formulario de actualización
        document.getElementById('modificacion-id-original').value = atleta.id;
        document.getElementById('modificacion-nombre').value = atleta.nombre;
        document.getElementById('modificacion-identificacion').value = atleta.identificacion;
        document.getElementById('modificacion-categoria').value = atleta.categoria;
        document.getElementById('modificacion-especialidad').value = atleta.especialidad;
        
        //se muestra el formulario de actualizacion y se oculta el de eliminar
        document.getElementById('contenedor-actualizar').classList.remove('d-none');
        document.getElementById('contenedor-eliminar').classList.add('d-none');
        
        document.getElementById('contenedor-actualizar').scrollIntoView({behavior: 'smooth'});
    }
}

//filtros de la consulta
//se usa en consulta.html y aplicarfiltros()
function consultarAtletas(lista) {
    const cuerpo = document.getElementById('tabla-consulta-body');
    if (!cuerpo) return;
    cuerpo.innerHTML = '';
    //se muestra o oculta el mensaje de "no se encontraron resultados" dependiendo si la lista esta vacia.
    const sinResultados = document.getElementById('mensaje-sin-resultados');

    if (lista.length === 0) {
        sinResultados.classList.remove('d-none');
    } else {
        sinResultados.classList.add('d-none');
        lista.forEach(atleta => {
            const fila = document.createElement('tr');
            fila.innerHTML = `
                <td>${atleta.identificacion}</td>
                <td>${atleta.nombre}</td>
                <td>${atleta.edad || '—'}</td>
                <td>${atleta.genero === 'M' ? 'Masculino' : atleta.genero === 'F' ? 'Femenino' : '—'}</td>
                <td><span class="badge bg-info text-dark">${atleta.categoria}</span></td>
                <td>${atleta.especialidad}</td>
                <td>${atleta.esCross ? '<span class="badge bg-success">Sí</span>' : '<span class="badge bg-secondary">No</span>'}</td>
            `;
            cuerpo.appendChild(fila);
        });
    }
}

function aplicarFiltros() {
    //.trim() elimina espacios al inicio y al final
    const buscarId    = document.getElementById('buscar-id')?.value.trim().toLowerCase();
    const genero      = document.getElementById('filtro-genero')?.value;
    const categoria   = document.getElementById('filtro-categoria')?.value.toLowerCase();
    const especialidad = document.getElementById('filtro-especialidad')?.value;
    const cross = document.getElementById('filtro-cross')?.value;
    //crea una copia del array original
    //se reducira con cada filtro
    let lista = [...listaAtletas];
    //includes busca si la identificación contiene el texto escrito 
    if (buscarId)     lista = lista.filter(a => a.identificacion.toLowerCase().includes(buscarId));
    if (genero)       lista = lista.filter(a => a.genero === genero);
    if (categoria)    lista = lista.filter(a => a.categoria.toLowerCase() === categoria);
    if (especialidad) lista = lista.filter(a => a.especialidad === especialidad);
    //convierte true/false a texto 'true'/'false' para compararlo con el valor del select que tambien es texto.
    if (cross !== '') lista = lista.filter(a => String(a.esCross) === cross);

    consultarAtletas(lista);
}

const countAtletas = document.getElementById('count-atletas');
if (countAtletas) countAtletas.textContent = listaAtletas.length;

//maneja la seleccion visual 
function seleccionarEspecialidad(valor) {
    document.getElementById('input-especialidad').value = valor;
    //decuelve todas las tarjetas y se les quita la clase selected a todas para limpiar la selección anterior
    document.querySelectorAll('.specialty-card').forEach(c => c.classList.remove('selected'));
    //recorre todos los slides(lo que desliza), encuentra el que tiene el data-especialidad igual al valor seleccionado, 
    //y le agrega la clase selected a su tarjeta interna para mostrar el borde amarillo.
    document.querySelectorAll('.swiper-slide').forEach(slide => {
        if (slide.getAttribute('data-especialidad') === valor) {
            slide.querySelector('.specialty-card').classList.add('selected');
        }
    });

    const indicador = document.getElementById('especialidad-seleccionada');
    const texto = document.getElementById('texto-especialidad');
    if (indicador && texto) {
        texto.textContent = valor;
        indicador.classList.remove('d-none');
    }
}

// para el formulario de registro (index.html o registro.html)
const formRegistro = document.getElementById('form-registro');

if (formRegistro){
    formRegistro.addEventListener('submit', function(e){
        e.preventDefault();
        
        //se crea el nuevo objeto de atleta capturando los valores
        const nuevoAtleta ={
            //se crea un id respecto de tiempo siempre seran distintos
            //es una forma que vi por un video 
            id:             Date.now(),
            nombre:         document.getElementById('nombre').value,
            identificacion: document.getElementById('identificacion').value,
            edad:           document.getElementById('edad').value,
            genero:         document.getElementById('genero').value,
            categoria:      calcularCategoria(document.getElementById('edad').value), // calculo de categoria
            especialidad:   document.getElementById('input-especialidad').value,      // lee el swiper o slides 
            esCross:        document.getElementById('esCross').checked
        };
        //.push añade uno o más elementos al final del la lista de atletas 
        listaAtletas.push(nuevoAtleta);
        
        formRegistro.reset();
        mostrarMensaje('sucess', 'se guardo atleta nuevo');
        // El typeof verifica que si existe y es una funcion antes de llamarla, evitando el error refrescarInterfaz is not a function
        if (typeof refrescarInterfaz === "function"){
            refrescarInterfaz();
        }
    });
}

//se ejecuta cargar la pagina
//espera a que todo el HTML se haya dibujado y los archivos CSS se hayan cargado antes de intentar llenar la tabla
//los listeners de los botones que creamos para listado.html
// todo el codigo dentro solo se ejecuta cuando el navegador termino de leer y construir todo el HTML. 
// sin esto, getElementById devolveria null porque los elementos aún no existen.
document.addEventListener('DOMContentLoaded', () => {
    
    refrescarInterfaz();
    //cantidad de atletas que aparece en el index
    const countAtletas = document.getElementById('count-atletas');
    if (countAtletas) countAtletas.textContent = listaAtletas.length;

    const btnTabla = document.getElementById('vista-table-btn');
    const btnTarjetas = document.getElementById('vista-cards-btn');
    const vistaTabla = document.getElementById('vista-tabla');
    const vistaTarjetas = document.getElementById('vista-tarjetas');
    
//cambio de forma de visualizar tabla y cartas
//Sirve para que la pagina decida qué dibujar en la pantalla dependiendo el estado de la variable llamada vista
//Comprueba si el valor de esa variable es exactamente el texto "tabla"

function cambiarVista(vista){
    if (vista === 'tabla'){
        //mostrar tabla, se ocultan las tarjetas
        vistaTabla.classList.remove('d-none');
        vistaTarjetas.classList.add('d-none');
        //se muestra activado boton de tabla desactivado boton tarjetas
        btnTabla.classList.add('active');
        btnTarjetas.classList.remove('active');
    }else{
        //contrario 
        vistaTabla.classList.add('d-none');
        vistaTarjetas.classList.remove('d-none');

        btnTabla.classList.remove('active');
        btnTarjetas.classList.add('active');
    }
}
if (btnTabla) btnTabla.addEventListener('click', () => cambiarVista('tabla'));
if (btnTarjetas) btnTarjetas.addEventListener('click', () => cambiarVista('tarjetas'));
//se usa para el calculo de la categoria segun edad
const inputEdad = document.getElementById('edad');
    if (inputEdad) {
        inputEdad.addEventListener('input', function() {
            const categoria = calcularCategoria(this.value);
            const indicador = document.getElementById('categoria-calculada');
            if (indicador && this.value >= 7) {
                indicador.textContent = 'Categoría: ' + categoria;
                indicador.classList.remove('d-none');
            }
    });
}

// Listeners de consulta — solo si estamos en consulta.html
const btnBuscarId = document.getElementById('btn-buscar-id');
const btnLimpiar = document.getElementById('btn-limpiar-filtros');
const filtroGenero = document.getElementById('filtro-genero');
const filtroCategoria = document.getElementById('filtro-categoria');
const filtroEspecialidad = document.getElementById('filtro-especialidad');

if (btnBuscarId) {
    btnBuscarId.addEventListener('click', aplicarFiltros);
}
if (btnLimpiar) {
    btnLimpiar.addEventListener('click', () => {
        document.getElementById('buscar-id').value = '';
        document.getElementById('filtro-genero').value = '';
        document.getElementById('filtro-categoria').value = '';
        document.getElementById('filtro-especialidad').value = '';
        consultarAtletas(listaAtletas);
    });
}
// Los selects filtran en tiempo real al cambiar
//change es el evento que dispara un <select>
if (filtroGenero)       filtroGenero.addEventListener('change', aplicarFiltros);
if (filtroCategoria)    filtroCategoria.addEventListener('change', aplicarFiltros);
if (filtroEspecialidad) filtroEspecialidad.addEventListener('change', aplicarFiltros);

// Cargar todos al entrar a la página
if (document.getElementById('tabla-consulta-body')) {
    consultarAtletas(listaAtletas);
}

const mySwiperEl = document.querySelector('.mySwiper');
//Si mySwiperEl es null (porque no estamos en registro.html), no entra al bloque y evita errores en las demas paginas
if (mySwiperEl) {
    //Inicializa la librería Swiper en el elemento con clase mySwiper
    new Swiper(".mySwiper", {
        grabCursor: true,
        slidesPerView: 3,
        spaceBetween: 20,
        pagination: {
            el: ".swiper-pagination",
            clickable: true
        }
    });
    //Agrega un listener de clic en el contenedor del swiper
    mySwiperEl.addEventListener('click', function(event) {
        const slideClickeado = event.target.closest('.swiper-slide');
        //se lee el atributo personalizado pusimos en cada slide en el HTML
        if (slideClickeado) {
            const valor = slideClickeado.getAttribute('data-especialidad');
            if (valor) seleccionarEspecialidad(valor);
        }
    });
}
 
});