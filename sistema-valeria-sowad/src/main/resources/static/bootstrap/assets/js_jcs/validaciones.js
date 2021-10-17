
$("#btnGuardar").click(function () {

    if ($("#imagen").val() === "") {
        bootbox.alert("Seleccione una imagen");
        return;
    }

});
$("#btnGuardarUsuario").click(function () {

    if ($("#imagenU").val() === "") {
        bootbox.alert("Seleccione una imagen");
        return;
    }

});

$(".btnEliminar").click(function (eve) {
    $("#modal-content").load("/Tarea/Eliminar/" + $(this).data("id"));
});
