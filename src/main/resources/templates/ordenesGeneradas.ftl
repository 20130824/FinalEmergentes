<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Ordenes Generadas</title>

    <!-- Bootstrap -->
    <link href="/css/bootstrap.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>

    <div class="col-md-6 col md col-md-offset-3">
    <h2>Ordenes Generadas</h2>
    </div>
<div style="padding-top: 2%" class="col-md-8 col-md-offset-3" >
    <#list ordenes as orden>
           <div  class="col-md-2">
            <label>
                No. Orden
            </label>
            <p>${orden.codigoOrdenCompra}</p>
        </div>
        <div class="col-md-2">
            <label>
                Suplidor
            </label>
            <p>${orden.codigoSuplidor}</p>
        </div>
        <div class="col-md-2">
            <label>
                Fecha Orden
            </label>
            <p>${orden.fechaOrden}</p>
        </div>
        <div class="col-md-2">
            <label>
                Monto Total
            </label>
            <p>${orden.montoTotal}</p>
        </div>
    </div>
            <table style="padding-top: 2%" class="table table-striped" >
                <thead>
                <tr>
                    <th>Componente</th>
                    <th>Cantidad</th>
                    <th>Precio</th>
                    <th>Importe</th>
                </tr>
                </thead>
                <tbody align="left">
                <#list orden.articulos as articulo>
                <tr>
                    <td>${articulo.codigoArticulo}</td>
                    <td>${articulo.cantidadOrdenada}</td>
                    <td>${articulo.precioCompra}</td>
                    <td>${articulo.precioCompra * articulo.cantidadOrdenada}</td>
                    </tr>
                </#list>
                </tbody>
            </table>


</#list>
</div>
</body>
</html>