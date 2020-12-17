package org.example.confectionery.services.entities;

public class OrderProfile {

    int id_saleCustomer;
    int id_userCustomer;
    String dateCustomer;
    int priceCustomer;

    int id_prod;
    String name_prod;
    int price_prod;

    int id_saleFactory;
    int id_userFactory;
    String dateFactory;
    int priceFactory;

    int id_raq;
    String name_raw;
    int price_raw;

    public int getId_saleCustomer() {
        return id_saleCustomer;
    }
    public void setId_saleCustomer(int id_saleCustomer) {
        this.id_saleCustomer = id_saleCustomer;
    }

    public int getId_userCustomer() {
        return id_userCustomer;
    }
    public void setId_userCustomer(int id_userCustomer) {
        this.id_userCustomer = id_userCustomer;
    }

    public String getDateCustomer() {
        return dateCustomer;
    }
    public void setDateCustomer(String dateCustomer) {
        this.dateCustomer = dateCustomer;
    }

    public int getPriceCustomer() {
        return priceCustomer;
    }
    public void setPriceCustomer(int priceCustomer) {
        this.priceCustomer = priceCustomer;
    }

    public int getId_prod() {
        return id_prod;
    }
    public void setId_prod(int id_prod) {
        this.id_prod = id_prod;
    }

    public String getName_prod() {
        return name_prod;
    }
    public void setName_prod(String name_prod) {
        this.name_prod = name_prod;
    }

    public int getPrice_prod() {
        return price_prod;
    }
    public void setPrice_prod(int price_prod) {
        this.price_prod = price_prod;
    }

    public int getId_saleFactory() {
        return id_saleFactory;
    }
    public void setId_saleFactory(int id_saleFactory) {
        this.id_saleFactory = id_saleFactory;
    }

    public int getId_userFactory() {
        return id_userFactory;
    }
    public void setId_userFactory(int id_userFactory) {
        this.id_userFactory = id_userFactory;
    }

    public String getDateFactory() {
        return dateFactory;
    }
    public void setDateFactory(String dateFactory) {
        this.dateFactory = dateFactory;
    }

    public int getPriceFactory() {
        return priceFactory;
    }
    public void setPriceFactory(int priceFactory) {
        this.priceFactory = priceFactory;
    }

    public int getId_raq() {
        return id_raq;
    }
    public void setId_raq(int id_raq) {
        this.id_raq = id_raq;
    }

    public String getName_raw() {
        return name_raw;
    }
    public void setName_raw(String name_raw) {
        this.name_raw = name_raw;
    }

    public int getPrice_raw() {
        return price_raw;
    }
    public void setPrice_raw(int price_raw) {
        this.price_raw = price_raw;
    }
}
