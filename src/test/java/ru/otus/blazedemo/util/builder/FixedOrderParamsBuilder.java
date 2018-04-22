package ru.otus.blazedemo.util.builder;

public class FixedOrderParamsBuilder extends OrderParamsBuilder{
    @Override
    public void buildName() {
        orderParams.setName("Ben Johnson");
    }

    @Override
    public void buildAddress() {
        orderParams.setAddress("432 Main St.");
    }

    @Override
    public void buildCity() {
        orderParams.setCity("Los Angeles");
    }

    @Override
    public void buildState() {
        orderParams.setState("California");
    }

    @Override
    public void buildZipCode() {
        orderParams.setZipCode("123456");
    }

    @Override
    public void buildCardNumber() {
        orderParams.setCardNumber("1234 5678 9123 4567");
    }

    @Override
    public void buildMonth() {
        orderParams.setMonth("07");
    }

    @Override
    public void buildYear() {
        orderParams.setYear("2018");
    }

    @Override
    public void buildNameOnCard() {
        orderParams.setNameOnCard("BEN JOHNSON");
    }
}
