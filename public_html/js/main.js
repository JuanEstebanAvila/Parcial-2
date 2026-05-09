//datos de prueba
const atletasDePrueba =[
    {id: 1, nombre:"Julian", identificacion: "123345", categoria:"Sub-23", especialidad:"Sprint"},
    {id: 45, nombre:"yotas marcelo", identificacion:"1515", categoria:"Benjamin", especialidad:"Ultraman"}
];
//recarga
function refrescarInterfaz() {
    renderizarTabla(listaAtletas);
    renderizarTarjetas(listaAtletas);
}

//la eliminacion antes de la confirmacion
function prepararEliminacion(idRecibido){
    //se busca el atleta, guarda la informacion en atletaencontrado
    //Recorre el arreglo devuelve el objeto completo del atleta que coincida con el id
    const atletaEncontrado = listaAtletas.find(a=> a.id === idRecibido);
    
    if (atletaEncontrado){
        // se llenan los textos de confirmacion que se muestran 
        //eliminacion.html
        //innerText Cambia solo el texto dentro de una etiqueta evitando que se inyecte HTML
        document.getElementById('eliminar-nombre-texto').innerText = atletaEncontrado.nombre;
        document.getElementById('eliminar-id-texto').innerText = atletaEncontrado.identificacion;
        
        //se muestra el panel de eliminacion, se quita el d-none. se ocultan lo otro
        document.getElementById('contenedor-eliminar').classList.remove('d-none');
        //se le agrega el d-none a pesar que ya tenga uno, el otro es que al refrescar no se vea este se recarga y se quita
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
function ejecutarEliminacionReal(idABorrar){
    //se filtra para quedarse con todo menos lo que sea el mismo id
    listaAtletas = listaAtletas.filter(a => a.id !== idABorrar);
    
    //se limpia la interfaz
    ocualtarSecciones(); //se endonde el de alerta
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
        
        document.getElementById('contenedor-actualizar').scrollIntoView({behavior: 'smoot'});
    }
}
//document.getElementById('form-actualizar').addEventListener('submit', function(e) {
//    e.preventDefault(); // Evitamos que la página se recargue
//
//    // 3. Obtenemos el ID que guardamos en el campo oculto
//    const idABuscar = parseInt(document.getElementById('update-id-original').value);
//
//    // 4. Buscamos la posición (índice) de ese atleta en nuestro arreglo
//    const indice = listaAtletas.findIndex(a => a.id === idABuscar);
//
//    if (indice !== -1) {
//        // 5. Actualizamos los campos uno por uno
//        listaAtletas[indice].nombre = document.getElementById('update-nombre').value;
//        listaAtletas[indice].identificacion = document.getElementById('update-identificacion').value;
//        listaAtletas[indice].categoria = document.getElementById('update-categoria').value;
//        listaAtletas[indice].especialidad = document.getElementById('update-especialidad').value;
//
//        // 6. Finalizamos la operación
//        ocultarSecciones();
//        refrescarInterfaz();
//        mostrarMensaje('warning', 'Información del atleta actualizada correctamente.');
//    }
//});
/*  
 * se hace la tabla
 */

//dibuja los datos los datos 
//recibe un conjunto de datos (un array) llamado listaAtletas 
//Cada vez que se agrega o borra a alguien, se llama esta función para que actualice la vista
//document.getElementById(...) va al html y agarra específicamente el contenedor de la tabla
//esto toma la lista de personas que estan guardadas y convertirla en etiquetas <tr> y <td>
function CrearTabla(listaAtletas){
    // se busca el cuerpo de la tabla por su ID
    //Esta es la conexión entre JS y el HTML
    const cuerpoTabla = document.getElementById('tabla-atletas');
    
    //se limpia o borra el contenido de la tabla ya creado para evitar la duplicacion de la informacion
    cuerpoTabla.innerHTML="";
    
    //se recorre la lista de atletas
    // le dice toma la lista de atletas y por cada atleta individual que encuentres,
    // ejecuta el código que está entre llaves
    ListaTablas.forEach(atleta => {
        //se crea una fila(tr) en memoria
        const fila = document.createElement('tr');
        
        //se llena la fila con celdas (td). 
        //toma una fila existente en una tabla HTML (<tr>) y llena su interior automáticamente con celdas (<td>
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
            <button class="btn btn-sm btn-danger" onclick="prepararEliminacion(${atleta.id}
                <i class="bi bi-trash"></i>
            </button>
        </td>
        `;
        //se agrega la fila al cuerpo
        //toma la fila que acabamos de armar con sus datos y botones, y la "pega" físicamente
        //dentro del HTML para que el usuario la vea
        cuerpo.Tabla.appendChild(fila);
    });
}
//se ejecuta cargar la pagina
//espera a que todo el HTML se haya dibujado y los archivos CSS se hayan cargado antes de intentar llenar la tabla
document.addEventListener('DOMContentLoaded',()=>{
    Dibujartabla(atletaDePrueba);
});

// se crean las tarjetas de forma automatica
function CrearTarjetas(listaAtletas){
    //busca en el html id=vista-tarjetas
    const contenedor = document.getElementById('vista-tarjetas');
    //limpia el contenido previo
    contenedor.innerHTML ="";
    
    listaAtletas.forEach(atleta=>{
        //le asignamos colores a las especialidades
        const colorClase = atleta.especialidad === 'Ultraman' ? 'border-primary':'border-primary';
        //El operador += significa añadir a lo que ya existe
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
                    <small class="text-uppercase fw-bold text-seconsary" style="font-size: 0.7rem;">
                        Especialidad: ${atleta.especialidad}
                    </small>
                </div>
            </div>
        </div>
        `;
    });
}

//los listeners de los botones que creamos para listado.html
const btnTabla = document.getElementById('vista-table-btn');
const btnTarjetas = document.getElementById('vista-cards-btn');
const vistaTabla = document.getElementById('vista-tabla');
const vistaTarjetas = document.getElementsByClassName('vista-tarjetas');

//cambio de forma de visualizar tabla y cartas
//Sirve para que la pagina decida qué dibujar en la pantalla dependiendo el estado de la variable llamada vista
//Comprueba si el valor de esa variable es exactamente el texto "tabla"
function cambiarVista(vista){
    if (vista === 'tabla'){
        //mostrar tabla, se ocultan las tarjetas
        vistaTabla.classList.remove('d-none');
        vistaTarjetas.classList.add('d-node');
        //se muestra activado boton de tabla desactivado boton tarjetas
        btnTabla.classList.add('active');
        btnTarjetas.classList.remove('active');
    }else{
        //contrario 
        vistaTabla.classList.add('d-none');
        vistaTarjetas.classList.remove('d-node');

        btnTabla.classList.remove('active');
        btnTarjetas.classList.add('active');
    }
}
//se asigna eventos a botones
btnTabla.addEventListener('click',()=> cambiarVista('tabla'));
//solo es si con el tabla este tarjetas es para el else
btnTarjetas.addEventListener('click',()=> cambiarVista('tarjetas'));