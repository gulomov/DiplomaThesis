package com.deploma.work.data.database;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\'\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&\u00a8\u0006\u0005"}, d2 = {"Lcom/deploma/work/data/database/DiplomaThesisDatabase;", "Landroidx/room/RoomDatabase;", "()V", "ProductListDao", "Lcom/deploma/work/data/dao/ProductListDao;", "data_debug"})
@androidx.room.Database(entities = {com.deploma.work.data.entity.ProductListEntity.class}, version = 1)
public abstract class DiplomaThesisDatabase extends androidx.room.RoomDatabase {
    
    public DiplomaThesisDatabase() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public abstract com.deploma.work.data.dao.ProductListDao ProductListDao();
}