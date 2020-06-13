package vn.edu.ntu.chauhoang.navigationapp59cntt3.model;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

public interface DAOProduct
{
    @Insert
    public void insertProduct(Product...products);
    @Update
    public void updateProduct(Product...products);
    @Delete
    public void deleteProduct(Product...products);
    @Query("SELECT * FROM Product")
    List<Product> getListProduct();
    @Query("SELECT * FROM Product WHERE id = :id")
    public Product getProductById(Long id);
}
