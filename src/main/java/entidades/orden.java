package entidades;

/**
 * Created by Isaac Perez on 18/7/2017.
 */
public class orden {
    articuloCompra articulo;
    String fecha;

    public orden() {
    }

    public orden(articuloCompra articulo, String fecha) {

        this.articulo = articulo;
        this.fecha = fecha;
    }

    public articuloCompra getArticulo() {
        return articulo;
    }

    public void setArticulo(articuloCompra articulo) {
        this.articulo = articulo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
