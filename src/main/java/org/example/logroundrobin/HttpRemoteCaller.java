/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example.logroundrobin;

/**
 *
 * @author cesar.vasquez-m
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpRemoteCaller {

    private static final String USER_AGENT = "Mozilla/5.0";
    private static final String[] LOG_SERVERS = new String[]{
                                                    "http://logservices1:4568",
                                                    "http://logservices2:4568",
                                                    "http://logservices3:4568"};
    
    private static int currentServer = 0;
    
    public static String remoteLogCall(String value) throws IOException{
        //System.out.println("URL: " + remoteHttpCall(LOG_SERVERS[currentServer] + "/logservice?message=" + message));
        return remoteHttpCall(LOG_SERVERS[currentServer] + "/logservice?message=" + value);
    }

    public static String remoteHttpCall(String url) throws IOException {
        System.out.println("URL: " + url);
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);
        
        //The following invocation perform the connection implicitly before getting the code
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);
        StringBuffer response = new StringBuffer();
        
        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // print result
            System.out.println(response.toString());
        } else {
            System.out.println("GET request not worked");
            return "404 Error";
        }
        System.out.println("GET DONE");
        rotateRoundRobinServer();
        return response.toString();
    }
    
    public static void rotateRoundRobinServer(){
        currentServer = (currentServer + 1) % LOG_SERVERS.length;
    }

} 
