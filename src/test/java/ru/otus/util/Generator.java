package ru.otus.util;

public class Generator {

    public static String generateZipCode(){
        StringBuffer sb = new StringBuffer();
        for(int i=0; i<6;i++){
            sb.append(TestHelper.random(1,9));
        }
        return sb.toString();
    }

    public static String generateCardNum(){
        StringBuffer sb = new StringBuffer();
        for (int i=0; i<4; i++){
            for (int k=0; k<4; k++){
                sb.append(TestHelper.random(0,9));
            }
            if (i!=3) sb.append(" ");
        }
        return sb.toString();
    }

    public static String generateState(){
        return TestHelper.getRandomArrayItem(DataHelper.getStates());
    }

    public static String generateCity(){
        return TestHelper.getRandomArrayItem(DataHelper.getCities());
    }

    public static String generateName(){
        return generateFirstname()+" "+generateLasttname();
    }

    public static String generateFirstname(){
        return TestHelper.getRandomArrayItem(DataHelper.getFirstnames());
    }

    public static String generateLasttname(){
        return TestHelper.getRandomArrayItem(DataHelper.getLastnames());
    }

    public static String generateAddress(){
        String street = TestHelper.getRandomArrayItem(DataHelper.getStreets());
        int building = TestHelper.random(1,250);
        int flat = TestHelper.random(1,120);
        return building+" "+street+", Flat."+flat;
    }



}
