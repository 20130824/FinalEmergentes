package entidades;

import java.util.List;

/**
 * Created by Isaac Perez on 11/7/2017.
 */
public class ordenCompra {
    //{codigoOrdenCompra,codigoSuplidor,fechaOrden,montoTotal,
    // articulos[{codigoArticulo,cantidadOrdenada,unidadCompra,precioCompra}]

    private String codigoOrdenCompra;
    private String codigoSuplidor;
    private String fechaOrden;
    private double montoTotal;
    List<articuloCompra> articulos;

    public ordenCompra(String codigoOrdenCompra, String codigoSuplidor, String fechaOrden, double montoTotal, List<articuloCompra> articulos) {
        this.codigoOrdenCompra = codigoOrdenCompra;
        this.codigoSuplidor = codigoSuplidor;
        this.fechaOrden = fechaOrden;
        this.montoTotal = montoTotal;
        this.articulos = articulos;
    }

    public String getCodigoOrdenCompra() {
        return codigoOrdenCompra;
    }

    public void setCodigoOrdenCompra(String codigoOrdenCompra) {
        this.codigoOrdenCompra = codigoOrdenCompra;
    }

    public String getCodigoSuplidor() {
        return codigoSuplidor;
    }

    public void setCodigoSuplidor(String codigoSuplidor) {
        this.codigoSuplidor = codigoSuplidor;
    }

    public String getFechaOrden() {
        return fechaOrden;
    }

    public void setFechaOrden(String fechaOrden) {
        this.fechaOrden = fechaOrden;
    }

    public double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(double montoTotal) {
        this.montoTotal = montoTotal;
    }

    public List<articuloCompra> getArticulos() {
        return articulos;
    }

    public void setArticulos(List<articuloCompra> articulos) {
        this.articulos = articulos;
    }

    @Override
    public String toString() {
        return "ordenCompra{" +
                "codigoOrdenCompra='" + codigoOrdenCompra + '\'' +
                ", codigoSuplidor='" + codigoSuplidor + '\'' +
                ", fechaOrden='" + fechaOrden + '\'' +
                ", montoTotal=" + montoTotal +
                ", articulos=" + articulos +
                '}';
    }
}
