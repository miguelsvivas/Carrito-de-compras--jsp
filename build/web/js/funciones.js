$(document).ready(function () {

    $("tr #btnDelete").click(function () {
        var idp = $(this).parent().find("#idp").val();
        swal({
            title: "Esta seguro de eliminar el producto?",
            text: "",
            icon: "warning",
            buttons: true,
            dangerMode: true,
        })
                .then((willDelete) => {
                    if (willDelete) {
                        eliminar(idp);
                        swal("El producto fue eliminado!", {
                            icon: "success",
                        }).then((willDelete)=>{
                            if(willDelete){
                                parent.location.href="Controlador?accion=Carrito";
                                
                            }
                            
                        });
                    } else {
                        swal("El producto no fue eliminado!");
                    }
                });
        

    });

    function eliminar(idp) {
        var url = "Controlador?accion=Delete";
        $.ajax({
            type: 'POST',
            url: url,
            data: "idp=" + idp,
            success: function (data, textStatus, jqXHR) {
            }
        });
    }
    
    $("tr #Cantidad").click(function (){
        
        var idp = $(this).parent().find("#idpro").val();
        var cantidad = $(this).parent().find("#Cantidad").val();
        var url="Controlador?accion=ActualizarCantidad";
        $.ajax({
            type: 'POST',
            url: url,
            data: "idp=" + idp + "&Cantidad=" + cantidad,
            success: function (data, textStatus, jqXHR){
                
                location.href="Controlador?accion=Carrito";
            }
            
        });
        
    });

});


