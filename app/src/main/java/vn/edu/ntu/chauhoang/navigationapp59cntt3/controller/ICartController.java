package vn.edu.ntu.chauhoang.navigationapp59cntt3.controller;

import java.util.ArrayList;
import java.util.List;

import vn.edu.ntu.chauhoang.navigationapp59cntt3.model.Product;

public interface ICartController
{
        public List<Product> getListProduct();
        public boolean addToShoppingCart(Product p);
        public ArrayList<Product> getShoppingCart();
        public void clearShoppingCart();
        public String getCartQuantity();
        public void addproduct(Product p);
}

