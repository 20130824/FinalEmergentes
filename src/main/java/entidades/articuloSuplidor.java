package entidades;

/**
 * Created by Isaac Perez on 11/7/2017.
 */
public class articuloSuplidor {
    //{codigoArticulo,codigoSuplidor,tiempoEntrega,precioCompra}
    private String codigoArticulo;
    private String codigoSuplidor;
    private double tiempoEntrega;
    private double precioCompra;

    public articuloSuplidor() {
    }

    public articuloSuplidor(String codigoArticulo, String codigoSuplidor, int tiempoEntrega, double precioCompra) {
        this.codigoArticulo = codigoArticulo;
        this.codigoSuplidor = codigoSuplidor;
        this.tiempoEntrega = tiempoEntrega;
        this.precioCompra = precioCompra;
    }

    public String getCodigoArticulo() {
        return codigoArticulo;
    }

    public void setCodigoArticulo(String codigoArticulo) {
        this.codigoArticulo = codigoArticulo;
    }

    public String getCodigoSuplidor() {
        return codigoSuplidor;
    }

    public void setCodigoSuplidor(String codigoSuplidor) {
        this.codigoSuplidor = codigoSuplidor;
    }

    public double getTiempoEntrega() {
        return tiempoEntrega;
    }

    public void setTiempoEntrega(double tiempoEntrega) {
        this.tiempoEntrega = tiempoEntrega;
    }

    public double getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(double precioCompra) {
        this.precioCompra = precioCompra;
    }
}
