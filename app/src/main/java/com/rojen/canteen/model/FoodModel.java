package com.rojen.canteen.model;


public class FoodModel {
    private String itemName;
    private String itemPrice;
    private int itemQuantity;
    private String itemPicture;
    private String itemType;
    private int id;

    public FoodModel() {

    }

    public FoodModel(String itemName, String itemPrice, int itemQuantity, String itemPicture, String itemType, int id) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemQuantity = itemQuantity;
        this.itemPicture = itemPicture;
        this.itemType = itemType;
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public String getItemPicture() {
        return itemPicture;
    }

    public void setItemPicture(String itemPicture) {
        this.itemPicture = itemPicture;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
