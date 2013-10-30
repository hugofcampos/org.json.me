/* 
    Copyright (c) Marcelo Barros de Almeida <marcelobarrosalmeida@gmail.com>

    This work is licensed under a Creative Commons 
    Attribution-ShareAlike 3.0 Unported License:

    http://creativecommons.org/licenses/by-sa/3.0/
 
 */
package com.pactodigital.apiconnect.api;
import javax.microedition.io.HttpConnection;

/**
 *
 * @author barros
 */
public interface IHTTPResponseHandler {
    public boolean Response20X(HttpConnection connection);
    public boolean ResponseOthers(HttpConnection connection);
}
