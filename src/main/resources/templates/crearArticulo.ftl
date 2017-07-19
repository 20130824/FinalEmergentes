<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Crear Articulo</title>

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
<div class="col-md-4 col-md-offset-4">
    <h2>Crear Articulo</h2>
    <form method="post" action="/agregarAlmacen" >
    <div class="form-group">
        <label>
            Almacen:
        </label>
        <select class="form-control" name="almacenIndex">
            <#list almacenes as almacen>
                <option value="${almacen_index}">${almacen.codigoAlmacen}</option>
            </#list>
        </select>

    </div>
        <div class="form-group">
            <input type="submit" class="form-control btn-danger" value="Agregar Almacen">
        </div>
    </form>
    <table class="table table-striped" >

        <thead>
        <tr>
            <th>Codigo Almacen</th>
            <th>Balance Actual</th>
        </tr>
        </thead>
        <tbody align="left">
        <#list almacenesArticulos as almacenArticulo>

        <#--list articulos as articulo-->
        <tr>
            <td>${almacenArticulo.codigoAlmacen}</td>
            <td>${almacenArticulo.balanceActual}</td>

        </tr>
        </#list>
        <#--/#list-->
        </tbody>
    </table>
    <form method='post' action='/crearArticulo' style="padding-bottom:20px">
        <fieldset>
            <div class="form-group">
                <label>Codigo</label>
                <input type="text" class="form-control" name="codigo">
            </div>

            <div class="form-group">
                <label>Descripcion</label>
                <input type="text" class="form-control" name="descripcion">
            </div>


                   <div class="form-group">
                <label>Unidad</label>
                <input type="text" class="form-control" name="unidad">
            </div>

            <div class="form-group">
                <input type="submit" class="form-control btn-success" value="Crear">
            </div>
        </fieldset>
    </form>


</div


</body>
</html>