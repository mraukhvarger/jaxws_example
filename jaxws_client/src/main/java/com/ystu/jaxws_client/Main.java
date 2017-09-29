package com.ystu.jaxws_client;

import com.ystu.generated.HelloProviderService;

public class Main {

    public static void main(String[] args) {
        System.out.println(new HelloProviderService().getHelloProviderPort().sayHello("User"));
        System.out.println(new HelloProviderService().getHelloProviderPort().sum(10, 20));
    }

}
