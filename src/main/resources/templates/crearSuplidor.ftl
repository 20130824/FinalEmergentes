<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Crear Suplidor</title>

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
    <h2>Crear Suplidor</h2>
    <form method='post' action='/crearSupidor' style="padding-bottom:20px">
        <fieldset>
            <div class="form-group">
                <label>Articulo</label>
                <select class="form-control" name="codigoArticulo">
                </select>
            </div>

            <div class="form-group">
                <label>Codigo</label>
                <input type="text" class="form-control" name="codigoSuplidor">
            </div>


            <div class="form-group">
                <label>Tiempo Entrega</label>
                <input type="number" class="form-control" name="tiempoEntrega">
            </div>

            <div class="form-group">
                <label>Precio Compra</label>
                <input type="text" class="form-control" name="precio">
            </div>

            <div class="form-group">
                <input type="submit" class="form-control btn-success" value="Aceptar">
            </div>
        </fieldset>
    </form>


</div


</body>
</html>