package entidades;

/**
 * Created by Isaac Perez on 11/7/2017.
 */
public class movimientoInventario {

    //{ codigoMovimiento,codigoAlmacen, tipoMovimiento,codigoArticulo, cantidad, unidad}
    private String codigoMovimiento;
    private String codigoAlmacen;
    private String codigoArticulo;
    private String tipoMovimiento;
    private int cantidad;
    private String unidad;
    private String fecha;


    public movimientoInventario(String codigoMovimiento, String codigoAlmacen, String codigoArticulo, String tipoMovimiento, int cantidad, String unidad, String fecha) {
        this.codigoMovimiento = codigoMovimiento;
        this.codigoAlmacen = codigoAlmacen;
        this.codigoArticulo = codigoArticulo;
        this.tipoMovimiento = tipoMovimiento;
        this.cantidad = cantidad;
        this.unidad = unidad;
        this.fecha = fecha;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getCodigoMovimiento() {
        return codigoMovimiento;
    }

    public void setCodigoMovimiento(String codigoMovimiento) {
        this.codigoMovimiento = codigoMovimiento;
    }

    public String getCodigoAlmacen() {
        return codigoAlmacen;
    }

    public void setCodigoAlmacen(String codigoAlmacen) {
        this.codigoAlmacen = codigoAlmacen;
    }

    public String getCodigoArticulo() {
        return codigoArticulo;
    }

    public void setCodigoArticulo(String codigoArticulo) {
        this.codigoArticulo = codigoArticulo;
    }

    public String getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(String tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }
}
