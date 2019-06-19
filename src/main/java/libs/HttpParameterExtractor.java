/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libs;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Lucy
 */
public class HttpParameterExtractor {
    public static HashMap<String, String> get(HttpServletRequest request) 
            throws ServletException, IOException {
        String body = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        
        body = URLDecoder.decode(body, "UTF-8");
        
        System.out.println(body);
        HashMap<String, String> parameters = new HashMap<>();
        
        for (String parameter : body.split("&")) {
            String[] pair = parameter.split("=");
            if (pair.length == 2)
                parameters.put(pair[0], pair[1]);
            else
                parameters.put(pair[0], "");
        }
        return parameters;
    }
}
