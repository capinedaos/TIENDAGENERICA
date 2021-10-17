var proveedorId = 0;
$("#btnAgregarProveedores").click(function () {
  $("#tablaProveedor tbody tr").each(function () {
    if (
      $(this).find(".id").is(":checked") ||
      $(this).find(".razonSocial").is(":checked") ||
      $(this).find(".ruc").is(":checked")
    ) {
      proveedorId = $(this).find(".id").val();
      var razonSocial = $(this).find(".razonSocial").val().toUpperCase();
      var ruc = $(this).find(".ruc").val().toUpperCase();
      document.getElementById("codigoProveedor").value = proveedorId;
      document.getElementById("nombreProveedor").value =
        razonSocial + " " + ruc;
      console.log(proveedorId + razonSocial);
      return;
    }
  });
  if ($("input[name=ruc]:checked").val() == null) {
    swal("Tienda-Mintic", "Seleccione un Proveedor");
    return;
  }
});

//Variables para el producto
var cantidadTotal = 0;
var total = 0;
var subtotal = 0;
var precio = 0;
var productoId = 0;
var compra = [];
var producto = {};
$("#btnAgregarDatosProducto").click(function (e) {
  e.preventDefault();
  $("#tablaProducto tbody tr").each(function (index) {
    if (
      $(this).find(".id").is(":checked") ||
      $(this).find(".nombre").is(":checked") ||
      $(this).find(".precio").is(":checked")
    ) {
      input1 = document.getElementById("cantidadEnviar").value;

      productoId = $(this).find(".id").val().toLowerCase();
      let nombre = $(this).find(".nombre").val();
      precio = $(this).find(".precio").val();
      var cantidadEnviar = $("#cantidadEnviar").val();
      cantidadTotal = cantidadEnviar;
      subtotal = precio * cantidadTotal;
      console.log(nombre);

      if ($("#cantidadEnviar").val() <= 0 || !Number.isInteger(+input1)) {
        swal("Tienda-Mintic", "Ingrese un valor válido mayor a 0");
        return;
      } else {
        if (checkId(productoId)) {
          swal("Tienda-Mintic", "El producto ya ha sido seleccionado");
        } else {
          var tabla = "<tr>";
          tabla = tabla + "<td for='id'>" + productoId + "</td>";
          tabla = tabla + "<td>" + nombre + "</td>";
          tabla = tabla + "<td>" + precio + "</td>";
          tabla = tabla + "<td>" + cantidadTotal + "</td>";
          tabla = tabla + "<td>" + subtotal + "</td>";
          tabla = tabla + "<td><a class='eliminar'>Eliminar</a></td><tr>";
          $("#detalle").append(tabla); // asigna todos los valores a la tabla
          total = total + subtotal; // calcula el total
          $("#totalCompra").val("S/." + total.toFixed(2) + " soles"); // imprime el total de la venta
          $(document).on("click", "a.eliminar", function () {
            swal({
              title: "Realmente desea eliminar?",
              text: "Al eliminar, ya no podrá recuperar el item eliminado!",
              icon: "warning",
              buttons: true,
              dangerMode: true,
            }).then((willDelete) => {
              if (willDelete) {
                swal("Good! ¡Producto eliminado con éxito!", {
                  icon: "success",
                });
                var idplato = $(this).parents("tr").find("td").eq(0).html();
                var valor = $(this).parents("tr").find("td").eq(4).html(); // valor para restar el total al momento de eliminar un item.
                $(this)
                  .parents("tr")
                  .fadeOut("normal", function () {
                    $(this).remove();
                    total = total - valor;
                    $("#totalCompra").val(total.toFixed(2));
                  });
              } else {
                swal("¡El Producto no ha sido eliminado!");
              }
            });
          });
        }
      }
    }
  });
  if ($("input[name=precio]:checked").val() == null) {
    swal("Tienda-Mintic", "Seleccione un Producto");
    return;
  }
});
var valor = 0;
function checkId(id) {
  let ids = document.querySelectorAll(
    '#tablaDetalleProductoCompra td[for="id"]'
  );
  return [].filter.call(ids, (td) => td.textContent === id).length === 1;
}

var btnGuardarCompra = document.getElementById("btnGuardarCompra");
btnGuardarCompra.addEventListener("click", function () {
  var now = new Date();
  //Fin-Fecha
  if ($("#codigoProveedor").val() <= 0) {
    swal("Tienda-Mintic", "Ingrese un Proveedor para registrar una venta");
    return;
  }
  if ($("#totalCompra").val() <= 0) {
    swal(
      "Tienda-Mintic",
      "Ingrese como mínimo un producto para registrar una compra"
    );
    return;
  } else {
    //Creacion del json
    var compraProducto = [];
    var compra = {};

    var table = document.getElementById("tablaDetalleProductoCompra");
    var rows = table.getElementsByTagName("tr");
    var i, j, cells;
    var customerId;
    var precio;
    var cantidad;
    compra = {
      estado: "Registrado",
      fecha: now,
      proveedor: {
        id: proveedorId,
      },
      compraProducto: [],
    };
    for (i = 0, j = rows.length; i < j; ++i) {
      cells = rows[i].getElementsByTagName("td");
      if (!cells.length) {
        continue;
      }
      compra.compraProducto.push({
        producto: {
          id: (customerId = cells[0].innerHTML),
        },
        cantidad: parseInt((cantidad = cells[3].innerHTML)),
        precio: (precio = cells[2].innerHTML),
      });
      console.log(customerId);
    }
    var r = new XMLHttpRequest();
    r.open("POST", "/compra", true);
    r.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    r.onreadystatechange = function () {
      if (r.readyState != 4 || r.status != 200) return;
      swal({
        title: "¡Compra registrada!",
        text: "Click en el boton para continuar!",
        icon: "success",
        button: "Aceptar!",
      }).then(function () {
        window.location = "listarCompras";
      });
    };
    r.send(JSON.stringify(compra));
  }
});
