/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author Sindre
 */

@ControllerAdvice
public class ErrorKonroller {
    // Se http://docs.spring.io/autorepo/docs/spring-framework/3.2.6.RELEASE/javadoc-api/org/springframework/http/HttpStatus.html for flere vi vil støtte
    /* Klarer ikke flere på en gang
    @ResponseStatus(HttpStatus.NOT_FOUND)  // 404
    @ExceptionHandler(Exception.class)
    public String handleNoTFound() {
        return "Error";
    }
    
    @ResponseStatus(HttpStatus.BAD_REQUEST)  // 400
    @ExceptionHandler(Exception.class)
    public String handleBadRequest() {
        return "Error";
    }
    */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)  // 500
    @ExceptionHandler(Exception.class)
    public String handlInternalServerError() {
        return "Error";
    }
    

}
