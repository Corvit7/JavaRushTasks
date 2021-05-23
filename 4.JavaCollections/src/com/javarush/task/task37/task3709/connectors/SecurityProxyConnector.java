package com.javarush.task.task37.task3709.connectors;




//Security Proxy
//        Необходимо создать класс SecurityProxyConnector в пакете connectors, который будет производить проверку безопасности перед подключением. В случае, если проверка не пройдена, соединение не должно быть установлено.
//
//        Для клиента (в данном случае класс Solution) использование SecurityProxyConnector ничем не должно отличаться от использования класса SimpleConnector.
//
//        P.S. Тебе понадобятся поля типов SecurityChecker и SimpleConnector в классе SecurityProxyConnector.
//
//
//        Requirements:
//        1. В классе SecurityProxyConnector должно быть создано поле типа SimpleConnector.
//        2. В классе SecurityProxyConnector должно быть создано поле типа SecurityChecker.
//        3. Конструктор класса SecurityProxyConnector должен принимать один параметр типа String и инициализировать поле типа SimpleConnector.
//        4. Метод connect класса SecurityProxyConnector должен выполнять проверку безопасности с помощью вызова метода performSecurityCheck у объекта типа SecurityChecker.
//        5. Если проверка безопасности была пройдена, должно быть выполнено подключение.
//        6. Если проверка безопасности не была пройдена, подключение не должно быть выполнено.
//        7. Класс SecurityProxyConnector должен поддерживать интерфейс Connector.

import com.javarush.task.task37.task3709.security.SecurityChecker;
import com.javarush.task.task37.task3709.security.SecurityCheckerImpl;

public class SecurityProxyConnector implements Connector{

    private SimpleConnector simpleConnector;
    private SecurityChecker securityChecker;
    private String resourceString;

    public SecurityProxyConnector(String resourceString) {
        this.resourceString = resourceString;
        simpleConnector = new SimpleConnector(resourceString);
        securityChecker = new SecurityCheckerImpl();
    }

    @Override
    public void connect() {
        if(securityChecker.performSecurityCheck())
            simpleConnector.connect();
    }
}
