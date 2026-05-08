//datos de prueba
const atletasDePrueba =[
    {id: 1, nombre:"Julian", identificacion: "123345", categoria:"Sub-23", especialidad:"Sprint"},
    {id: 45, nombre:"yotas marcelo", identificacion:"1515", categoria:"Benjamin", especialidad:"Ultraman"}
];

/*  
 * se hace la tabla
 */

//dibuja los datos los datos 
//recibe un conjunto de datos (un array) llamado listaAtletas 
//Cada vez que se agrega o borra a alguien, se llama esta función para que actualice la vista
//document.getElementById(...) va al html y agarra específicamente el contenedor de la tabla
//esto toma la lista de personas que estan guardadas y convertirla en etiquetas <tr> y <td>
function DibujarTabla(listaAtletas){
    // se busca el cuerpo de la tabla por su ID
    //Esta es la conexión entre JS y el HTML
    const cuerpoTabla = document.getElementById('tabla-atletas');
    
    //se limpia o borra el contenido de la tabla ya creado para evitar la duplicacion de la informacion
    cuerpoTabla.innerHTML="";
    
    //se recorre la lista de atletas
    // le dice toma la lista de atletas y, por cada atleta individual que encuentres,
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
        <td>
            <button class="btn btn-sm btn-warning" onclick="seleccionarParaEditar(${atleta.id})"></button>
            <button class="btn btn-sm btn-danger" onclick="seleccinar para eliminar(${atleta.id})"></button>
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