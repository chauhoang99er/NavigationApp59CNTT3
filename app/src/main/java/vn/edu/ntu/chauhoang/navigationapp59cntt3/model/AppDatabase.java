package vn.edu.ntu.chauhoang.navigationapp59cntt3.model;

import androidx.room.Database;
import androidx.room.Entity;
import androidx.room.RoomDatabase;

@Database(entities = {Product.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase
{
    public abstract DAOProduct getProductDAO();
}
