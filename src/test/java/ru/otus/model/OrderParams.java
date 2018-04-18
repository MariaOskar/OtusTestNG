package ru.otus.model;

import lombok.Data;
import ru.otus.util.TestHelper;

public @Data class OrderParams {
    public String name;
    public String address;
    public String city;
    public String state;
    public String zipCode;
    public String cardNumber;
    public String month;
    public String year;
    public String nameOnCard;


    public OrderParams() {

    }
    /**
     public String getName() {
         return name;
     }

     public void setName(String name) {
         this.name = name;
     }

     public String getAddress() {
         return address;
     }

     public void setAddress(String address) {
         this.address = address;
     }

     public String getCity() {
         return city;
     }

     public void setCity(String city) {
         this.city = city;
     }

     public String getState() {
         return state;
     }

     public void setState(String state) {
         this.state = state;
     }

     public String getZipCode() {
         return zipCode;
     }

     public void setZipCode(String zipCode) {
         this.zipCode = zipCode;
     }

     public String getCardNumber() {
         return cardNumber;
     }

     public void setCardNumber(String cardNumber) {
         this.cardNumber = cardNumber;
     }

     public String getMonth() {
         return month;
     }

     public void setMonth(String month) {
         this.month = month;
     }

     public String getYear() {
         return year;
     }

     public void setYear(String year) {
         this.year = year;
     }

     public String getNameOnCard() {
         return nameOnCard;
     }

     public void setNameOnCard(String nameOnCard) {
         this.nameOnCard = nameOnCard;
     }
 */
}
