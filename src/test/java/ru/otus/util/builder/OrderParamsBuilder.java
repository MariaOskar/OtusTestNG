package ru.otus.util.builder;

import ru.otus.model.OrderParams;

abstract public class OrderParamsBuilder {

    protected OrderParams orderParams;

    public OrderParams getOrderParams(){
        return orderParams;
    }

    public void createOrderParams(){
        orderParams = new OrderParams();
    }

    public void build(){
        buildName();
        buildAddress();
        buildCity();
        buildState();
        buildZipCode();
        buildCardNumber();
        buildMonth();
        buildYear();
        buildNameOnCard();
    }

    abstract public void buildName();
    abstract public void buildAddress();
    abstract public void buildCity();
    abstract public void buildState();
    abstract public void buildZipCode();
    abstract public void buildCardNumber();
    abstract public void buildMonth();
    abstract public void buildYear();
    abstract public void buildNameOnCard();
}
