/* 
    Copyright (c) Marcelo Barros de Almeida <marcelobarrosalmeida@gmail.com>

    This work is licensed under a Creative Commons 
    Attribution-ShareAlike 3.0 Unported License:

    http://creativecommons.org/licenses/by-sa/3.0/
 
 */
package com.pactodigital.apiconnect.api;

import com.pactodigital.apiconnect.entries.IEntry;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.microedition.io.HttpConnection;
import org.json.me.JSONArray;
import org.json.me.JSONException;
import org.json.me.JSONObject;

/**
 *
 * @author marcelo
 */

public class APIResponse implements IHTTPResponseHandler {

    private IEntry data = null;
    private String response = null;
    
    public APIResponse(IEntry data) {
        this.data = data;
    }

    public boolean Response20X(HttpConnection connection) {
        
        boolean result = false;
        StringBuffer strf = null;
        response = null;
        
        try {
            InputStream is = connection.openInputStream();
            InputStreamReader isr = new InputStreamReader(is, "UTF-8");

            int ch;
            strf = new StringBuffer();
            while ((ch = isr.read()) != -1) {
                strf.append((char) ch);
            }
            
            response = strf.toString();
            
            strf = null;

            try {
                JSONArray objArray = new JSONArray(response);
                
                for(int i=0; i<objArray.length(); i++){
                    JSONObject jobj = objArray.getJSONObject(i);
                    data.fromJSONObject(jobj);
                }
                result = true;
            } catch (JSONException ex) {
                ex.printStackTrace();
                result = false;
            }
            
            response = null;
            isr.close();
            is.close();
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return result;
    }
    public boolean ResponseOthers(HttpConnection connection) {
        return false;
    }
}