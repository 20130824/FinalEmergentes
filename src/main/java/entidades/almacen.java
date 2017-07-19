package entidades;

/**
 * Created by Isaac Perez on 17/7/2017.
 */
public class almacen {

    private String codigoAlmacen;
    private int balanceActual;

    public almacen(String codigoAlmacen, int balanceActual) {
        this.codigoAlmacen = codigoAlmacen;
        this.balanceActual = balanceActual;
    }

    public String getCodigoAlmacen() {
        return codigoAlmacen;
    }

    public void setCodigoAlmacen(String codigoAlmacen) {
        this.codigoAlmacen = codigoAlmacen;
    }

    public int getBalanceActual() {
        return balanceActual;
    }

    public void setBalanceActual(int balanceActual) {
        this.balanceActual = balanceActual;
    }
}
