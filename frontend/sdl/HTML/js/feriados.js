//let url = "https://api.control-z.cl/api/feriados"
let url = "https://apis.digital.gob.cl/fl/feriados"
$.get(url, function(respuesta){
    //console.log(respuesta)
    /*respuesta.forEach(function(item){
        consolole.log(item)
    })*/
    console.log(respuesta[respuesta.length -1])
},"json")