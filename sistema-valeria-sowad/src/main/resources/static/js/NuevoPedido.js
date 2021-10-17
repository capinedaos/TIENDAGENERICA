var clienteId = 0;
var btnAgregarCliente = document.getElementById("btnAgregarClientes");
btnAgregarCliente.addEventListener("click", function () {
  $("#tablaCliente tbody tr").each(function () {
    if (
      this.querySelector(".id").checked ||
      this.querySelector(".nombre").checked
    ) {
      clienteId = $(this).find(".id").val();
      var nombre = $(this).find(".nombre").val().toUpperCase();
      var apellidos = $(this).find(".apellidos").val().toUpperCase();
      document.getElementById("codigoCliente").value = clienteId;
      document.getElementById("nombreCliente").value = nombre + " " + apellidos;
      console.log(clienteId + nombre + apellidos);

      return;
    }
  });
  if ($("input[name=nombre]:checked").val() == null) {
    swal("Tienda-Mintic", "Seleccione un cliente");
    return;
  }
});

//Variables para el platillo
var cantidadTotal = 0;
var total = 0;
var subtotal = 0;
var precio = 0;
var platilloId = 0;
var venta = [];
var plato = {};

function bereken() {}

var btnAgregarDatosPlatillo = document.getElementById(
  "btnAgregarDatosPlatillo"
);
btnAgregarDatosPlatillo.addEventListener("click", function () {
  $("#tablaPlatillo tbody tr").each(function () {
    if (
      this.querySelector(".id").checked ||
      this.querySelector(".nombre").checked ||
      this.querySelector(".cantidad").checked ||
      this.querySelector(".precio").checked
    ) {
      input1 = document.getElementById("cantidadEnviar").value;

      platilloId = $(this).find(".id").val().toLowerCase();
      let nombre = $(this).find(".nombre").val();
      var cantidad = $(this).find(".cantidad").val();
      precio = $(this).find(".precio").val();
      var cantidadEnviar = $("#cantidadEnviar").val();
      cantidadTotal = cantidadEnviar;
      subtotal = precio * cantidadTotal;
      console.log(nombre);

      if (parseInt(cantidadEnviar) > parseInt(cantidad)) {
        swal("Tienda-Mintic", "Superó al stock");
        return;
      }

      if ($("#cantidadEnviar").val() <= 0 || !Number.isInteger(+input1)) {
        swal("Tienda-Mintic", "Ingrese un valor válido mayor a 0");
        return;
      } else {
        if (checkId(platilloId)) {
          swal("Tienda-Mintic", "El platillo ya ha sido seleccionado");
        } else {
          var tabla = "<tr>";
          tabla = tabla + "<td for='id'>" + platilloId + "</td>";
          tabla = tabla + "<td>" + nombre + "</td>";
          tabla = tabla + "<td>" + precio + "</td>";
          tabla = tabla + "<td>" + cantidadTotal + "</td>";
          tabla = tabla + "<td>" + subtotal + "</td>";
          tabla = tabla + "<td><a class='eliminar'>Eliminar</a></td><tr>";
          $("#detalle").append(tabla); // asigna todos los valores a la tabla
          total = total + subtotal; // calcula el total
          $("#totalVenta").val("S/." + total.toFixed(2) + " soles"); // imprime el total de la venta

          $(document).on("click", "a.eliminar", function () {
            swal({
              title: "Realmente desea eliminar?",
              text: "Al eliminar, ya no podrá recuperar el item eliminado!",
              icon: "warning",
              buttons: true,
              dangerMode: true,
            }).then((willDelete) => {
              if (willDelete) {
                swal("Good! ¡Platillo eliminado con éxito!", {
                  icon: "success",
                });
                var idplato = $(this).parents("tr").find("td").eq(0).html();
                var valor = $(this).parents("tr").find("td").eq(4).html(); // valor para restar el total al momento de eliminar un item.
                $(this)
                  .parents("tr")
                  .fadeOut("normal", function () {
                    $(this).remove();
                    total = total - valor;
                    $("#totalVenta").val(total.toFixed(2));
                  });
              } else {
                swal("¡El platillo no ha sido eliminado!");
              }
            });
          });
        }
      }
    }
  });
  if ($("input[name=cantidad]:checked").val() == null) {
    swal("Tienda-Mintic", "Seleccione un platillo");
    return;
  }
});

function fadeOut(id, speed) {
  var s = document.getElementById(id).style;
  s.opacity = 1;
  (function fade() {
    (s.opacity -= 0.1) < 0.1 ? (s.display = "none") : setTimeout(fade, speed);
  })();
}

var valor = 0;
function checkId(id) {
  let ids = document.querySelectorAll('#tablaDetallePlatoVenta td[for="id"]');
  return [].filter.call(ids, (td) => td.textContent === id).length === 1;
}

var btnGuardarVenta = document.getElementById("btnGuardarVenta");
btnGuardarVenta.addEventListener("click", function () {
  var fecha = new Date();
  //Fin-Fecha
  if ($("#codigoCliente").val() <= 0) {
    swal("Tienda-Mintic", "Ingrese un cliente para registrar una venta");
    return;
  }
  if ($("#totalVenta").val() <= 0) {
    swal(
      "Tienda-Mintic",
      "Ingrese como mínimo un platillo para registrar una venta"
    );
    return;
  } else {
    //Creacion del json
    var ventaPlato = [];
    var venta = {};

    var table = document.getElementById("tablaDetallePlatoVenta");
    var rows = table.getElementsByTagName("tr");
    var i, j, cells;
    var customerId;
    var precio;
    var cantidad;
    venta = {
      estado: "No pagado",
      fecha: fecha,
      cliente: {
        id: clienteId,
      },
      ventaPlato: [],
    };
    for (i = 0, j = rows.length; i < j; ++i) {
      cells = rows[i].getElementsByTagName("td");
      if (!cells.length) {
        continue;
      }
      venta.ventaPlato.push({
        plato: {
          id: (customerId = cells[0].innerHTML),
        },
        cantidad: parseInt((cantidad = cells[3].innerHTML)),
        precio: (precio = cells[2].innerHTML),
      });
      console.log(customerId);
    }
    var r = new XMLHttpRequest();
    r.open("POST", "/venta", true);
    r.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    r.onreadystatechange = function () {
      if (r.readyState != 4 || r.status != 200) return;
      swal({
        title: "¡Venta registrada!",
        text: "Click en el boton para continuar!",
        icon: "success",
        button: "Aceptar!",
      }).then(function () {
        window.location = "noPagado";
      });
    };
    r.send(JSON.stringify(venta));
  }
});
