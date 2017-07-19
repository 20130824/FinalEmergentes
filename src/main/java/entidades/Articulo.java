package entidades;

/**
 * Created by Isaac Perez on 11/7/2017.
 */
public class Articulo {

    //{codigoArticulo,descripcion,[{codigoAlmacen,balanceActual}],unidadCompra}
    private String codigoArticulo;
    private String descripcion;
    private String unidadCompra;
    private String codigoAlmacen;
    private double balanceActual;


    public Articulo(String codigoArticulo, String descripcion, String unidadCompra, String codigoAlmacen, int balanceActual) {
        this.codigoArticulo = codigoArticulo;
        this.descripcion = descripcion;
        this.unidadCompra = unidadCompra;
        this.codigoAlmacen = codigoAlmacen;
        this.balanceActual = balanceActual;
    }

    public String getCodigoAlmacen() {
        return codigoAlmacen;
    }

    public void setCodigoAlmacen(String codigoAlmacen) {
        this.codigoAlmacen = codigoAlmacen;
    }

    public double getBalanceActual() {
        return balanceActual;
    }

    public void setBalanceActual(double balanceActual) {
        this.balanceActual = balanceActual;
    }

    public Articulo() {
    }

    public String getCodigoArticulo() {
        return codigoArticulo;
    }

    public void setCodigoArticulo(String codigoArticulo) {
        this.codigoArticulo = codigoArticulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUnidadCompra() {
        return unidadCompra;
    }

    public void setUnidadCompra(String unidadCompra) {
        this.unidadCompra = unidadCompra;
    }

}
