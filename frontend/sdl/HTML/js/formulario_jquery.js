$.validator.addMethod("terminaPor", function(value, element, parametro){
    if (value.endsWith(parametro)) {
        return true;
    }
    return false;
}, "Debe terminar por {0}")



$("#formulario_contacto").validate({
    rules:{
        nombre:{
            required: true,
            minlength: 3,
            maxlength:30
        },
        email:{
            required: true,
            email: true,
            terminaPor: "duoc.cl"
        },
        tipo_solicitud:{
            required: true
        },
        mensaje:{
            required: true,
            minlength: 5,
            maxlength: 200
        }
    }
})

$("#guargar").click(function(){
    if ($("#formulario_contacto").valid() == false) {
        return;
    }

    let nombre = $("#nombre").val()
    let email = $("#email").val()
    let tipoSolicitud = $("#tipo_solicitud").val()
    let mensaje = $("#mensaje").val()
    let avisos = $("#avisos").is(":checked")       
})