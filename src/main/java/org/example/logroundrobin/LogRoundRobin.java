/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package org.example.logroundrobin;

import java.io.IOException;
import static spark.Spark.*;
/**
 *
 * @author cesar.vasquez-m
 */
public class LogRoundRobin {

    public static void main(String[] args) {
        port(getPort());
        staticFiles.location("/public");
        get("/log", (req, pesp) -> {
            String val = req.queryParams("message").replace(" ", "%20");
            return LogMessage(val);
        });
        
    }

    private static int getPort() {
        if(System.getenv("PORT") != null){
           return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }

    private static String LogMessage(String value) throws IOException {
        
        return HttpRemoteCaller.remoteLogCall(value);
    }
}
