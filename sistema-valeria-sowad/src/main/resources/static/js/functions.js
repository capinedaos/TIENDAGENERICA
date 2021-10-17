function eliminarpersona(id) {
  swal({
    title: "Esta seguro de Eliminar?",
    text: "Once deleted, you will not be able to recover this imaginary file!",
    icon: "warning",
    buttons: true,
    dangerMode: true,
  }).then((OK) => {
    if (OK) {
      $.ajax({
        url: "/eliminarpersona/" + id,
        success: function (res) {
          console.log(res);
        },
      });
      swal("Poof! Your imaginary file has been deleted!", {
        icon: "success",
      }).then((ok) => {
        if (ok) {
          location.href = "/listarpersona";
        }
      });
    } else {
      swal("Your imaginary file is safe!");
    }
  });
}

function eliminarproveedor(id) {
  swal({
    title: "Esta seguro de Eliminar?",
    text: "Once deleted, you will not be able to recover this imaginary file!",
    icon: "warning",
    buttons: true,
    dangerMode: true,
  }).then((OK) => {
    if (OK) {
      $.ajax({
        url: "/eliminarproveedor/" + id,
        success: function (res) {
          console.log(res);
        },
      });
      swal("Poof! Your imaginary file has been deleted!", {
        icon: "success",
      }).then((ok) => {
        if (ok) {
          location.href = "/listarproveedor";
        }
      });
    } else {
      swal("Your imaginary file is safe!");
    }
  });
}

function eliminarusuario(id) {
  swal({
    title: "Esta seguro de Eliminar?",
    text: "Once deleted, you will not be able to recover this imaginary file!",
    icon: "warning",
    buttons: true,
    dangerMode: true,
  }).then((OK) => {
    if (OK) {
      $.ajax({
        url: "/eliminarusuario/" + id,
        success: function (res) {
          console.log(res);
        },
      });
      swal("Poof! Your imaginary file has been deleted!", {
        icon: "success",
      }).then((ok) => {
        if (ok) {
          location.href = "/listarusuario";
        }
      });
    } else {
      swal("Your imaginary file is safe!");
    }
  });
}

function eliminarplato(id) {
  swal({
    title: "Esta seguro de Eliminar?",
    text: "Once deleted, you will not be able to recover this imaginary file!",
    icon: "warning",
    buttons: true,
    dangerMode: true,
  }).then((OK) => {
    if (OK) {
      $.ajax({
        url: "/eliminarplato/" + id,
        success: function (res) {
          console.log(res);
        },
      });
      swal("Poof! Your imaginary file has been deleted!", {
        icon: "success",
      }).then((ok) => {
        if (ok) {
          location.href = "/listarplato";
        }
      });
    } else {
      swal("Your imaginary file is safe!");
    }
  });
}

("use strict");

var singleUploadForm = document.querySelector("#singleUploadForm");
var singleFileUploadInput = document.querySelector("#singleFileUploadInput");
var singleFileUploadError = document.querySelector("#singleFileUploadError");
var singleFileUploadSuccess = document.querySelector(
  "#singleFileUploadSuccess"
);

function uploadSingleFile(file) {
  var formData = new FormData();
  formData.append("file", file);

  var xhr = new XMLHttpRequest();
  xhr.open("POST", "/api/csv/upload");

  xhr.onload = function () {
    console.log(xhr.responseText);
    var response = JSON.parse(xhr.responseText);
    if (xhr.status == 200) {
      singleFileUploadError.style.display = "none";
      singleFileUploadSuccess.innerHTML =
        "<p>File Uploaded Successfully.</p><p>DownloadUrl : <a href='" +
        response.fileDownloadUri +
        "' target='_blank'>" +
        response.fileDownloadUri +
        "</a></p>";
      singleFileUploadSuccess.style.display = "block";
    } else {
      singleFileUploadSuccess.style.display = "none";
      singleFileUploadError.innerHTML =
        "<p>" + (response && response.message) + "<p>" || "Some Error Occurred";
      singleFileUploadSuccess.style.display = "block";
    }
  };

  xhr.send(formData);
}

singleUploadForm.addEventListener(
  "submit",
  function (event) {
    var files = singleFileUploadInput.files;
    if (files.length === 0) {
      singleFileUploadError.innerHTML = "Please select a file";
      singleFileUploadError.style.display = "block";
    }
    uploadSingleFile(files[0]);
    event.preventDefault();
  },
  true
);
