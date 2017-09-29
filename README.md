# jaxws_example
Пример серверной и клиентской частей SOAP-сервиса

## Сервер

### pom.xml

```
...
    <dependencies>
        <dependency>
            <groupId>com.sun.xml.ws</groupId>
            <artifactId>jaxws-rt</artifactId>
            <version>2.1.3</version>
        </dependency>
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>servlet-api</artifactId>
            <version>2.5</version>
        </dependency>
    </dependencies>

...
```

### HelloProvider

```
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
}
```

### sun-jaxws.xml

```
<?xml version="1.0" encoding="UTF-8"?>
<endpoints xmlns="http://java.sun.com/xml/ns/jax-ws/ri/runtime" version="2.0">
    <endpoint name="HelloProvider" implementation="com.ystu.jaxws_server.HelloProvider"
              url-pattern="/helloProvider"></endpoint>
</endpoints>
```

### web.xml

```
...
    <listener>
        <listener-class>com.sun.xml.ws.transport.http.servlet.WSServletContextListener</listener-class>
    </listener>
    <servlet>
        <servlet-name>helloProvider</servlet-name>
        <servlet-class>com.sun.xml.ws.transport.http.servlet.WSServlet</servlet-class>
        <load-on-startup>1</load-on-startup>
    </servlet>
    <servlet-mapping>
        <servlet-name>helloProvider</servlet-name>
        <url-pattern>/helloProvider</url-pattern>
    </servlet-mapping>
...
```

## Клиент

### pom.xml

```
...
    <build>
        <plugins>
            <plugin>
                <groupId>org.jvnet.jax-ws-commons</groupId>
                <artifactId>jaxws-maven-plugin</artifactId>
                <version>2.1</version>
                <executions>
                    <execution>
                        <goals>
                            <goal>wsimport</goal>
                        </goals>
                        <configuration>
                            <packageName>com.ystu.generated</packageName>
                            <sourceDestDir>${project.basedir}/src/main/java</sourceDestDir>
                            <wsdlUrls>
                                <wsdlUrl>http://win:8080/jaxws_server/helloProvider?wsdl</wsdlUrl>
                            </wsdlUrls>
                        </configuration>
                    </execution>
                </executions>
            </plugin>
            <plugin>
                <artifactId>maven-clean-plugin</artifactId>
                <version>3.0.0</version>
                <configuration>
                    <filesets>
                        <fileset>
                            <directory>${project.basedir}/src/main/java/com/ystu/generated</directory>
                        </fileset>
                    </filesets>
                </configuration>
            </plugin>
        </plugins>
    </build>
...
```

### main()

```
...
    public static void main(String[] args) {
        System.out.println(new HelloProviderService().getHelloProviderPort().sayHello("User"));
        System.out.println(new HelloProviderService().getHelloProviderPort().sum(10, 20));
    }
...
```
