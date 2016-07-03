/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package middleware;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author MarcoA
 */
public class HTTPRequest {
    
    //Peticiones y respuestas de Vuelos
    public static List<Vuelo> doGetVuelos() throws IOException{
        String result;
        String url = "http://localhost:8084/MVIv2/webapi/vuelos";
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpGet getRequest = new HttpGet(url);
        getRequest.addHeader("Accept", "application/json");
        
        HttpResponse response = httpClient.execute(getRequest);
        
        if(response.getStatusLine().getStatusCode() != 200){
            throw new RuntimeException("ERROR AL CONSULTAR DEL TIPO: " + response.getStatusLine().getStatusCode());
        }
        
                HttpEntity entity = response.getEntity();
                result = EntityUtils.toString(entity);
                List<Vuelo> listaVuelos = getArrayVuelo(result);
                
                
                
		httpClient.getConnectionManager().shutdown();
                return listaVuelos;
    }
    
    public static void doPostVuelo(String jsonRequest) throws UnsupportedEncodingException, IOException{
        String url = "http://localhost:8084/MVIv2/webapi/vuelos";
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpPost postRequest = new HttpPost(url);
        StringEntity input = new StringEntity(jsonRequest);
        input.setContentType("application/json");
        postRequest.setEntity(input);
        
        HttpResponse response = httpClient.execute(postRequest);
        
        if(response.getStatusLine().getStatusCode() != 200){
            throw new RuntimeException("ERROR AL INSERTAR DEL TIPO: " + response.getStatusLine().getStatusCode());
        }
        
        BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));

        String output;
        System.out.println("Output from Server .... \n");
        while ((output = br.readLine()) != null) {
                System.out.println(output);
        }

        httpClient.getConnectionManager().shutdown();
        
    }
    
    public static String createJsonRequestVuelo(String linea,String origen, String destino, String fecha, String hora, 
        String capacidad, String precio){
        JSONObject obj = new JSONObject();
        obj.put("linea", linea);
        obj.put("origen", origen);
        obj.put("destino", destino);
        obj.put("fecha", fecha);
        obj.put("hora", hora);
        obj.put("capacidad", capacidad);
        obj.put("precio", precio);
        return obj.toString();
    }
    
    public static List<Vuelo> getArrayVuelo(String result){
        JSONArray jsonResult = new JSONArray(result);
        List<Vuelo> listaVuelos = new ArrayList<Vuelo>();
        
        for(int i = 0; i < jsonResult.length(); i++)
        {
            Vuelo vuelo = new Vuelo();
            JSONObject json = jsonResult.getJSONObject(i);
            vuelo.setId(json.getString("id"));
            vuelo.setFecha(json.getString("fecha"));
            vuelo.setCapacidad(json.getString("capacidad"));
            vuelo.setDestino(json.getString("destino"));
            vuelo.setOrigen(json.getString("origen"));
            vuelo.setLinea(json.getString("linea"));
            vuelo.setHora(json.getString("hora"));
            vuelo.setPrecio(json.getString("precio"));
            
            listaVuelos.add(vuelo);
        }
    
        return listaVuelos;
    }
    
    //Peticiones y Respuestas con Reservas
       public static List<Reserva> doGetReservas() throws IOException{
        String result;
        String url = "http://localhost:8084/MVIv2/webapi/reservas";
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpGet getRequest = new HttpGet(url);
        getRequest.addHeader("Accept", "application/json");
        
        HttpResponse response = httpClient.execute(getRequest);
        
        if(response.getStatusLine().getStatusCode() != 200){
            throw new RuntimeException("ERROR AL CONSULTAR DEL TIPO: " + response.getStatusLine().getStatusCode());
        }
        
                HttpEntity entity = response.getEntity();
                result = EntityUtils.toString(entity);
                List<Reserva> listaReservas = getArrayReserva(result);
                
                
                
		httpClient.getConnectionManager().shutdown();
                return listaReservas;
    }
       
    public static void doPostReserva(String jsonRequest) throws UnsupportedEncodingException, IOException{
        String url = "http://localhost:8084/MVIv2/webapi/reservas";
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpPost postRequest = new HttpPost(url);
        StringEntity input = new StringEntity(jsonRequest);
        input.setContentType("application/json");
        postRequest.setEntity(input);
        
        HttpResponse response = httpClient.execute(postRequest);
        
        if(response.getStatusLine().getStatusCode() != 200){
            throw new RuntimeException("ERROR AL INSERTAR DEL TIPO: " + response.getStatusLine().getStatusCode());
        }
        
        BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));

        String output;
        System.out.println("Output from Server .... \n");
        while ((output = br.readLine()) != null) {
                System.out.println(output);
        }

        httpClient.getConnectionManager().shutdown();
        
    }
    
    public static String createJsonRequestReserva(String vuelo,String nombre){
        JSONObject obj = new JSONObject();
        obj.put("vuelo", vuelo);
        obj.put("nombre", nombre);
        return obj.toString();
    }
    
    public static List<Reserva> getArrayReserva(String result){
        JSONArray jsonResult = new JSONArray(result);
        List<Reserva> listaReservas = new ArrayList<Reserva>();
        
        for(int i = 0; i < jsonResult.length(); i++)
        {
            Reserva reserva = new Reserva();
            JSONObject json = jsonResult.getJSONObject(i);
            reserva.setVuelo(json.getString("vuelo"));
            reserva.setNombre(json.getString("nombre"));
            reserva.setReserva(json.getString("reserva"));
            listaReservas.add(reserva);
        }
    
        return listaReservas;
    }
    
    //Peticiones y respuestas de Replicacion y Restauracion
    public static void doPostReplica() throws UnsupportedEncodingException, IOException{
        String url = "http://localhost:8084/MVIv2/webapi/rr/replicar";
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpPost postRequest = new HttpPost(url);
        
        HttpResponse response = httpClient.execute(postRequest);
        
        if(response.getStatusLine().getStatusCode() != 200){
            throw new RuntimeException("ERROR AL REPLICAR DEL TIPO: " + response.getStatusLine().getStatusCode());
        }
        
        httpClient.getConnectionManager().shutdown();
        
    }

    public static void doPostRestaura() throws UnsupportedEncodingException, IOException{
        String url = "http://localhost:8084/MVIv2/webapi/rr/restaurar";
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpPost postRequest = new HttpPost(url);
        
        HttpResponse response = httpClient.execute(postRequest);
        
        if(response.getStatusLine().getStatusCode() != 200){
            throw new RuntimeException("ERROR AL RESTAURAR DEL TIPO: " + response.getStatusLine().getStatusCode());
        }

        httpClient.getConnectionManager().shutdown();
        
    }

    
}
