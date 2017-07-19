package main;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.lt;
import static spark.Spark.staticFiles;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import entidades.*;
import freemarker.template.Configuration;
import org.bson.Document;
import org.bson.conversions.Bson;
import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.*;
import static spark.Spark.*;
import static spark.debug.DebugScreen.enableDebugScreen;

public class main {
    private static ArrayList<articuloCompra> articulosOrdenes = new ArrayList<>();
    private static ArrayList<ordenCompra> ordenesCompras = new ArrayList<>();
    private static HashMap<String, articuloSuplidor> suplidoresList = new HashMap<>();
    private static HashMap<String, ArrayList<orden>> ordenes= new HashMap<>();
    public static void main(String[] args) {

        port(5678);
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        MongoDatabase database = mongoClient.getDatabase("final");
        MongoCollection<Document> articulosDoc = database.getCollection("articulos");
        MongoCollection<Document> suplidoresDoc = database.getCollection("suplidores");
        MongoCollection<Document> ordenesDoc = database.getCollection("ordenes");
        MongoCursor<Document> articulosCursor = articulosDoc.find().iterator();
        ArrayList<Articulo> articulos = new ArrayList<>();
        while(articulosCursor.hasNext()){
            Document art = articulosCursor.next();
            Articulo artObj = new Articulo();
            artObj.setCodigoArticulo(art.getString("codigoArticulo"));
            artObj.setDescripcion(art.getString("descripcion"));

            Document h = ((ArrayList<Document>) art.get("almacen")).get(0);
            artObj.setCodigoAlmacen(h.getString("codigoAlmacen"));
            artObj.setBalanceActual(h.getDouble("balanceActual"));
            articulos.add(artObj);
        }


        staticFiles.location("/");
        Configuration configuration=new Configuration(Configuration.VERSION_2_3_23);
        configuration.setClassForTemplateLoading(main.class, "/templates");
        FreeMarkerEngine freeMarkerEngine = new FreeMarkerEngine(configuration);

        enableDebugScreen();
        List<almacen> almacenes = new ArrayList<>();
        almacenes.add(new almacen("ALM-01", 10));
        almacenes.add(new almacen("ALM-02", 5));

        get("/crearOrden", (request, response) -> {

            Map<String, Object> attributes = new HashMap<>();
            attributes.put("articulosOrden", articulosOrdenes);
            attributes.put("articulos", articulos);
            return new ModelAndView(attributes, "crearOrden.ftl");
        }, freeMarkerEngine);


        get("/ordenesGeneradas", (request, response) -> {

            Map<String, Object> attributes = new HashMap<>();

            return new ModelAndView(attributes, "ordenesGeneradas.ftl");
        }, freeMarkerEngine);


        get("/movimiento", (request, response) -> {

            Map<String, Object> attributes = new HashMap<>();
            attributes.put("articulos", articulos);

            return new ModelAndView(attributes, "movimientos.ftl");
        }, freeMarkerEngine);

        post("/movimiento", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            String codigoMovimiento = request.queryParams("codigoMovimiento");
            String tipoMovimiento = request.queryParams("tipoMovimiento");
            int cantidad = Integer.parseInt(request.queryParams("cantidad"));
            Articulo art1 = articulos.get(Integer.parseInt(request.queryParams("articuloIndex")));
            String fecha = request.queryParams("fecha");
            movimientoInventario movimiento = new movimientoInventario(codigoMovimiento, art1.getCodigoAlmacen(), art1.getCodigoArticulo(), tipoMovimiento, cantidad, art1.getUnidadCompra(), fecha);
            insertarMovimiento(database, movimiento);
            response.redirect("/movimiento");
            return null;
        }, freeMarkerEngine);

        post("/agregarArticulo", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            String codigoArt = articulos.get(Integer.parseInt(request.queryParams("articuloIndex"))).getCodigoArticulo();
            String fecha = request.queryParams("fecha");
            int cantidad = Integer.parseInt(request.queryParams("cantidad"));
            System.out.println("Cantidad" + cantidad);
            Bson[] arr = new Bson[]{eq("codigoArticulo" , codigoArt)};
            MongoCursor<Document> suplidoresCursor = suplidoresDoc.find(and(arr)).limit(1).sort(new BasicDBObject("tiempoEntrega", 1)).iterator();
            double precio = 0;

            while(articulosCursor.hasNext()){
                Document art = suplidoresCursor.next();
                precio = art.getDouble("precioCompra");
            }

             while(suplidoresCursor.hasNext()){
                articuloSuplidor supObj = new articuloSuplidor();
                Document sup = suplidoresCursor.next();
                supObj.setCodigoArticulo(sup.getString("codigoArticulo"));
                supObj.setCodigoSuplidor(sup.getString("codigoSuplidor"));
                supObj.setPrecioCompra(sup.getDouble("precioCompra"));
                 System.out.println("El precio que coje es " + sup.getDouble("precioCompra"));
                supObj.setTiempoEntrega(sup.getDouble("tiempoEntrega"));

                suplidoresList.put(codigoArt, supObj);
            }
            articulosOrdenes.add(new articuloCompra(codigoArt,cantidad, fecha, precio));
            response.redirect("/crearOrden");
            return null;


        }, freeMarkerEngine);

        post ("/generarOrden", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            double balance =0;
            for(articuloCompra art : articulosOrdenes){
                double promedioConsumo = calcularPromedioConsumo(database, art.getCodigoArticulo());
                int dias = (int) ((formato.parse(art.getFecha()).getTime() - new Date().getTime())/86400000) + 1;
                Calendar fechaTermino = Calendar.getInstance();
                MongoCursor<Document> art1 = articulosDoc.find(and(new Bson[]{eq("codigoArticulo", art.getCodigoArticulo())})).limit(1).iterator();
                while(art1.hasNext()) {
                    Document documento = art1.next();
                    Document h = ((ArrayList<Document>) documento.get("almacen")).get(0);
                    balance = (h.getDouble("balanceActual"));
                }

                fechaTermino.add(Calendar.DAY_OF_MONTH, (int) ((float)balance/promedioConsumo));
                System.out.println(formato.format(fechaTermino.getTime()));
                System.out.println(promedioConsumo);
                Date seAcabo = fechaTermino.getTime();
                System.out.println(seAcabo);
                System.out.println(art.getFecha());
                if (seAcabo.compareTo(formato.parse(art.getFecha())) < 0) {
                    System.out.println(1);
                    articuloSuplidor suplidor = suplidoresList.get(art.getCodigoArticulo());

                    Calendar fechaPedido = Calendar.getInstance();
                    fechaPedido.setTime(seAcabo);
                    System.out.println(suplidor.toString());
                    fechaPedido.add(Calendar.DATE, -(int) suplidor.getTiempoEntrega());
                    Date fechaP = fechaPedido.getTime();
                    Calendar fechaOrden = Calendar.getInstance();
                    fechaOrden.add(Calendar.DAY_OF_MONTH, (int) (dias - suplidor.getTiempoEntrega()));

                    Date fechaDef;
                    Date now = formato.parse(formato.format(new Date()));
                    if (fechaOrden.getTime().compareTo(fechaP) < 0){
                        System.out.println(2);
                        if (fechaP.compareTo(now) < 0){
                            fechaDef = formato.parse(formato.format(new Date()));
                        } else {
                            fechaDef = fechaP;
                        }

                        art.setCantidadOrdenada((int)Double.parseDouble(String.valueOf(art.getCantidadOrdenada()) + (promedioConsumo * dias)) );
                    } else {
                        fechaDef = fechaOrden.getTime();

                    }

                    if (ordenes.containsKey(suplidoresList.get(art.getCodigoArticulo()).getCodigoSuplidor())){
                        ordenes.get(suplidoresList.get(art.getCodigoArticulo()).getCodigoSuplidor()).add(new orden(art, formato.format(fechaDef.getTime())));
                    } else {
                        ArrayList<orden> ordenesList = new ArrayList<>();
                        ordenesList.add(new orden(art, formato.format(fechaDef.getTime())));
                        ordenes.put(suplidoresList.get(art.getCodigoArticulo()).getCodigoSuplidor(), ordenesList);
                    }
                }


            }



            for (String suplidor : ordenes.keySet()){
                for (orden Orden : ordenes.get(suplidor)){
                         for (ordenCompra ordencompraaa : ordenesCompras){
                            System.out.println("Entro a total");
                            double total = 0;
                            for (articuloCompra a : ordencompraaa.getArticulos() ){
                                total += a.getPrecioCompra() * a.getCantidadOrdenada();
                            }
                            break;
                    }

                        ArrayList<articuloCompra> componentes = new ArrayList<>();
                        componentes.add(Orden.getArticulo());
                        ordenCompra oc = new ordenCompra(String.valueOf(ordenesDoc.count() + ordenesCompras.size()+1),
                                suplidor, Orden.getFecha(),Orden.getArticulo().getPrecioCompra() * Orden.getArticulo().getCantidadOrdenada() , componentes);
                        ordenesCompras.add(oc);

                }
            }


            System.out.println(5);
            System.out.println("Size "+ ordenesCompras.size());
            List<Document> docsToInsert = new ArrayList<>();
            for (ordenCompra oc : ordenesCompras){
                System.out.println(6);
                System.out.println(oc);
                List<Document> subArticulos = new ArrayList<>();
                for (articuloCompra subComp : oc.getArticulos()){
                    articuloSuplidor articuloSuplidor = suplidoresList.get(subComp.getCodigoArticulo());
                    subArticulos.add(new Document().append("codigoArticulo", subComp.getCodigoArticulo()).append("cantidadOrdenada", subComp.getCantidadOrdenada()).append("precioCompra", subComp.getPrecioCompra()));
                }

                Document document = new Document().append("codigoOrdenCompra", oc.getCodigoOrdenCompra()).append("codigoSuplidor", oc.getCodigoSuplidor()).append("fechaOrden", oc.getFechaOrden()).append("montoTotal", oc.getMontoTotal()).append("articulos", subArticulos);
                docsToInsert.add(document);
            }

            if (docsToInsert.size() > 0)
                ordenesDoc.insertMany(docsToInsert);
            attributes.put("ordenes", ordenesCompras);

            articulosOrdenes = new ArrayList<>();
            suplidoresList = new HashMap<>();
            suplidoresList = new HashMap<>();
            ordenesCompras = new ArrayList<>();


            return new ModelAndView(attributes,"ordenesGeneradas.ftl");
        }, freeMarkerEngine);


    }



    public static void insertarMovimiento(MongoDatabase database, movimientoInventario movimiento){
        MongoCollection<Document> movimientos = database.getCollection("movimientos");
        Document docMovimiento = new Document();
        docMovimiento.append("codigoMovimiento", movimiento.getCodigoMovimiento());
        docMovimiento.append("codigoAlmacen", movimiento.getCodigoAlmacen());
        docMovimiento.append("tipoMovimiento", movimiento.getTipoMovimiento());
        docMovimiento.append("codigoArticulo", movimiento.getCodigoArticulo());
        docMovimiento.append("cantidad", movimiento.getCantidad());
        docMovimiento.append("unidad", movimiento.getUnidad());
        docMovimiento.append("fecha", movimiento.getFecha());
        MongoCollection<Document> articulosDoc = database.getCollection("articulos");
        switch (movimiento.getTipoMovimiento()) {
            case "ENTRADA":

           articulosDoc.updateOne(new Document().append("codigoArticulo", movimiento.getCodigoArticulo()).append("almacen.codigoAlmacen",movimiento.getCodigoAlmacen() ),
                   new Document().append("$inc", new Document().append("almacen.$.balanceActual", movimiento.getCantidad())));
           break;

            case "SALIDA":
                articulosDoc.updateOne(new Document().append("codigoArticulo", movimiento.getCodigoArticulo()).append("almacen.codigoAlmacen",movimiento.getCodigoAlmacen() ),
                        new Document().append("$inc", new Document().append("almacen.$.balanceActual", -movimiento.getCantidad())));
                break;

        }


        movimientos.insertOne(docMovimiento);

    }

    public static double calcularPromedioConsumo(MongoDatabase database, String codigoArticulo){
        MongoCollection<Document> movimientos = database.getCollection("movimientos");
        double promedio = 0;
        List<Document> documents = new ArrayList<>();
        //documents.add(new Document().append("$match", new Document().append("codigoArticulo", codigoArticulo).append("tipoMovimiento", "SALIDA")));
        //documents.add(new Document().append("$group", new Document().append("_id", new Document().append("$dayOfMonth", "$fecha")).append("total", new Document().append("$sum", "$cantidad"))));
        //documents.add(new Document().append("$group", new Document().append("_id", 1).append("promedio", new Document("$avg", "$total"))));
        //for (Document d: movimientos.aggregate(documents)) {
           // promedio = d.getDouble("promedio")/30;
       // }
        return 10;
    }

}