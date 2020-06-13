package vn.edu.ntu.chauhoang.navigationapp59cntt3.controller;

import android.content.Context;

import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;

import vn.edu.ntu.chauhoang.navigationapp59cntt3.model.AppDatabase;
import vn.edu.ntu.chauhoang.navigationapp59cntt3.model.DAOProduct;
import vn.edu.ntu.chauhoang.navigationapp59cntt3.model.Product;

public class CartControllerDB implements ICartController
{
    AppDatabase database; //Luu tru
    List<Product> shoppingCart = new ArrayList<>();
    DAOProduct daoProduct;

    public CartControllerDB(Context context)
    {
        database = Room.databaseBuilder(context,
                AppDatabase.class, "appdb")
                .allowMainThreadQueries()
                .build();
        daoProduct = database.getProductDAO();
    }

    @Override
    public List<Product> getListProduct()
    {
        return daoProduct.getListProduct();
    }

    @Override
    public boolean addToShoppingCart(Product p)
    {
        return false;
    }

    @Override
    public ArrayList<Product> getShoppingCart() {
        return null;
    }

    @Override
    public void clearShoppingCart() {

    }

    @Override
    public String getCartQuantity() {
        return null;
    }

    @Override
    public void addproduct(Product p) {

    }
}
