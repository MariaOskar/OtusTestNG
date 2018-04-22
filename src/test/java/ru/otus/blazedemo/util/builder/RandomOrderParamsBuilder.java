package ru.otus.blazedemo.util.builder;

import ru.otus.blazedemo.util.Generator;
import ru.otus.utils.TestHelper;

public class RandomOrderParamsBuilder extends OrderParamsBuilder{



    @Override
    public void buildName() {
        orderParams.setName(Generator.generateName());
    }

    @Override
    public void buildAddress() {
        orderParams.setAddress(Generator.generateAddress());
    }

    @Override
    public void buildCity() {
        orderParams.setCity(Generator.generateCity());
    }

    @Override
    public void buildState() {
        orderParams.setState(Generator.generateState());
    }

    @Override
    public void buildZipCode() {
        orderParams.setZipCode(Generator.generateZipCode());
    }

    @Override
    public void buildCardNumber() {
        orderParams.setCardNumber(Generator.generateCardNum());
    }

    @Override
    public void buildMonth() {
        orderParams.setMonth(Integer.toString(TestHelper.random(1, 12)));
    }

    @Override
    public void buildYear() {
        orderParams.setYear(Integer.toString(TestHelper.random(2017, 2020)));
    }

    @Override
    public void buildNameOnCard() {
        orderParams.setNameOnCard(orderParams.getName().toUpperCase());
    }


}
