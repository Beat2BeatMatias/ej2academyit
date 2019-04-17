import com.google.gson.Gson;
import com.google.gson.JsonElement;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Logger;

import static spark.Spark.*;

public class mainEj2 {

    public static void main(String[] args) {

        Logger logger=Logger.getLogger("Mi logger");
        String url_base = "https://api.mercadolibre.com/";

        get("/agencias/sites/:sites_id/payment_methods/:payment_method_id/order/:order/agencies",(request,response)-> {
            response.type("application/json");
            String sites_id = request.params("sites_id");
            String paymentMethodId = request.params("payment_method_id");
            String order = request.params("order");
            String coordenadas = request.queryParams("near_to");
            String limit = request.queryParams("limit");
            String offset = request.queryParams("offset");

            try {
                comprobacionParametros(coordenadas, paymentMethodId, sites_id, order);
            }catch (ParametrosException e){
                return new Gson().toJson(new StandarResponse(StatusResponse.ERROR,e.getMessage()));
            }

            switch (order){
                case "address_line":
                    Agency.criterio = Agency.Criterio.ADDRESS_LINE;
                    break;
                case "agency_code":
                    Agency.criterio = Agency.Criterio.AGENCY_CODE;
                    break;
                case "distance":
                    Agency.criterio = Agency.Criterio.DISTANCE;
                    break;
            }

            guardarLog(request.url());

            if(limit != null){
                if(offset!=null) {
                    return new Gson().toJson(new StandarResponse(StatusResponse.SUCCESS,new Gson().toJsonTree(Ordenador.ordenarArray(new Gson().fromJson(obtenerAgencies(url_base +
                            "sites/" + sites_id +
                            "/payment_methods/" + paymentMethodId +
                            "/agencies?near_to=" + coordenadas + "&limit=" + limit + "&offset=" + offset),Agency[].class)))));
                }else {
                    return new Gson().toJson(new StandarResponse(StatusResponse.SUCCESS,new Gson().toJsonTree(Ordenador.ordenarArray(new Gson().fromJson(obtenerAgencies(url_base +
                            "sites/" + sites_id +
                            "/payment_methods/" + paymentMethodId +
                            "/agencies?near_to=" + coordenadas + "&limit=" + limit),Agency[].class)))));
                }
            }else if (offset != null){
                return new Gson().toJson(new StandarResponse(StatusResponse.SUCCESS,new Gson().toJsonTree(Ordenador.ordenarArray(new Gson().fromJson(obtenerAgencies(url_base +
                        "sites/" + sites_id +
                        "/payment_methods/" + paymentMethodId +
                        "/agencies?near_to=" + coordenadas + "&offset=" + offset),Agency[].class)))));
            }else {
                return new Gson().toJson(new StandarResponse(StatusResponse.SUCCESS,new Gson().toJsonTree(Ordenador.ordenarArray(new Gson().fromJson(obtenerAgencies(url_base +
                        "sites/" + sites_id +
                        "/payment_methods/" + paymentMethodId +
                        "/agencies?near_to=" + coordenadas),Agency[].class)))));
                //return new Gson().toJson(new StandarResponse(StatusResponse.SUCCESS, obtenerAgencies(url_base + "sites/" + sites_id + "/payment_methods/" + paymentMethodId + "/agencies?near_to=" + coordenadas)));
            }
        });
    }

    private static void guardarLog(String url) {
        FileWriter fichero = null;
        PrintWriter pw = null;
        Date date= Calendar.getInstance().getTime();
        String time = date.toString();
        try
        {
            fichero = new FileWriter("/Users/mfariasfalki/IdeaProjects/ej2academyit/log.txt");
            pw = new PrintWriter(fichero);

            pw.println("Fecha: " + time);
            pw.println("Url: " + url);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // Nuevamente aprovechamos el finally para
                // asegurarnos que se cierra el fichero.
                if (null != fichero)
                    fichero.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    private static void comprobacionParametros(String coordenadas, String payment, String sites_id, String order) throws ParametrosException{
        if (coordenadas == null || payment == null || sites_id == null || order == null){
            throw new ParametrosException("Debe colocar los parámetros obligatorios: 'sites_id', 'payment_method_id' y 'near_to'");
        }else if (!(order.equals("address_line") || order.equals("agency_code") || order.equals("distance"))){
            throw new ParametrosException("Debe colocar algunos de los siguientes criterios de ordenamiento: 'address_line', 'agency_code' y 'distance'");
        }
    }

    private static JsonElement obtenerAgencies(String path){

        String data = null;
        try {
            data = readUrl(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Response respuestaApi=new Gson().fromJson(data, Response.class);
        return  new Gson().toJsonTree(respuestaApi.getResults());
    }

    private static String readUrl(String urlString) throws IOException {

        BufferedReader reader=null;

        try {
            URL url = new URL(urlString);
            URLConnection connection=url.openConnection();
            connection.setRequestProperty("Accept","application/json");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0");
            reader=new BufferedReader(new InputStreamReader(connection.getInputStream(),"UTF-8"));
            int read = 0;
            StringBuffer buffer = new StringBuffer();
            char[] chars = new char[1024];
            while((read = reader.read(chars)) != -1){
                buffer.append(chars,0,read);
            }
            return buffer.toString();
        } finally {
            if(reader != null){
                reader.close();
            }
        }

    }
}
