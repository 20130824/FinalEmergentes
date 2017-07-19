package entidades;

/**
 * Created by Isaac Perez on 11/7/2017.
 */
public class articuloCompra {
    private String codigoArticulo;
    private int cantidadOrdenada;
    private String fecha;
    private double precioCompra;

    public articuloCompra(String codigoArticulo, int cantidadOrdenada, String fecha, double precioCompra) {
        this.codigoArticulo = codigoArticulo;
        this.cantidadOrdenada = cantidadOrdenada;
        this.fecha = fecha;
        this.precioCompra = precioCompra;
    }

    public String getCodigoArticulo() {
        return codigoArticulo;
    }

    public void setCodigoArticulo(String codigoArticulo) {
        this.codigoArticulo = codigoArticulo;
    }

    public int getCantidadOrdenada() {
        return cantidadOrdenada;
    }

    public void setCantidadOrdenada(int cantidadOrdenada) {
        this.cantidadOrdenada = cantidadOrdenada;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(double precioCompra) {
        this.precioCompra = precioCompra;
    }
}
