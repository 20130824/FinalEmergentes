<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Movimientos</title>

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
    <h2>Movimientos</h2>
    <form method='post' action='/movimiento' style="padding-bottom:20px">
        <fieldset>
            <div class="form-group">
                <label>Codigo Movimiento</label>
                <input type="text" class="form-control" name="codigoMovimiento">
            </div>

            <div class="form-group">
                <label>Tipo Movimiento</label>
                <select class="form-control" name="tipoMovimiento">
                    <option value="ENTRADA">Entrada</option>
                    <option value="SALIDA">Salida</option>
                </select>
            </div>

            <div class="form-group">
                <label>Articulo</label>
                <select class="form-control" name="articuloIndex">
                    <#list articulos as articulo>
                        <option value="${articulo_index}">${articulo.descripcion}</option>
                    </#list>
                </select>
            </div>

            <div class="form-group">
                <label>Cantidad</label>
                <input type="number" class="form-control" name="cantidad">
            </div>

            <div class="form-group">
                <label>Fecha</label>
                <input type="date" class="form-control" name="fecha">
            </div>
             <div class="form-group">
                <input type="submit" class="form-control btn-success" value="Crear">
            </div>
        </fieldset>
    </form>


</div


</body>
</html>