import com.google.gson.Gson;
import com.google.gson.JsonElement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import static spark.Spark.*;

public class mainEj2 {

    public static void main(String[] args) {

        String url_base = "https://api.mercadolibre.com/";

        get("/agencias/sites/:sites_id/payment_methods/:payment_method_id/agencies",(request,response)-> {
            
            response.type("application/json");
            String sites_id = request.params("sites_id");
            String paymentMethodId = request.params("payment_method_id");
            String coordenadas = request.queryParams("near_to");

            return new Gson().toJson(new StandarResponse(StatusResponse.SUCCESS,obtenerAgencies(url_base + "sites/" + sites_id + "/payment_methods/" + paymentMethodId + "/agencies")));
        });


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
