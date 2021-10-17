var btnGuardarPago = document.getElementById("btnGuardarPago");
btnGuardarPago.addEventListener("click", function () {
  let ventaId = document.getElementById("ventaId").value;

  var total = document.getElementById("total").value;
  var subTotal = document.getElementById("subTotal").value;
  var igv = document.getElementById("igv").value;

  console.log(total);
  console.log(igv);
  console.log(subTotal);
  var pago = {
    total: total,
    subTotal: subTotal,
    igv,
    tipo_pago: "efectivo",
    venta: {
      id: ventaId,
    },
  };

  console.log(ventaId);
  console.log(pago);

  var r = new XMLHttpRequest();
  r.open("POST", "/guardarPago", true);
  r.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
  r.onreadystatechange = function () {
    if (r.readyState != 4 || r.status != 200) return;
    swal({
      title: "¡Pago exitoso!",
      text: "Click en el boton para continuar!",
      icon: "success",
      button: "Aceptar!",
    }).then(function () {
      window.location = "../noPagado";
    });
  };
  r.send(JSON.stringify(pago));
});
