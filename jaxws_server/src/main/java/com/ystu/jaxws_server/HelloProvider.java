package com.ystu.jaxws_server;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class HelloProvider {
    @WebMethod(operationName = "sayHello")
    public String sayHello(@WebParam(name = "guestname") String guestname) {
        if (guestname == null) {
            return "Hello";
        }
        return "Hello " + guestname;
    }

    @WebMethod(operationName = "sum")
    public Integer sum(@WebParam(name = "x") Integer x,
                      @WebParam(name = "y") Integer y) {
        return x + y;
    }
}