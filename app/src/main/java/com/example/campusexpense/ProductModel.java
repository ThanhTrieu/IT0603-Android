package com.example.campusexpense;

public class ProductModel {
    public int idProduct;
    public String nameProduct;
    public String imageProduct;
    public  int priceProduct;

    public ProductModel(int id, String name, String image, int price){
        this.idProduct = id;
        this.nameProduct = name;
        this.imageProduct = image;
        this.priceProduct = price;
    }
}
