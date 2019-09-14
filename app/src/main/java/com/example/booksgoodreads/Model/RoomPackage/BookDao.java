package com.example.booksgoodreads.Model.RoomPackage;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface BookDao {

    @Insert
    void insertBook(Book book);

    @Update
    void updateBook(Book book);

    @Delete
    void deleteBook(Book book);

    @Query("DELETE FROM book_table")
    void deleteAllBooks();

    @Query("SELECT * FROM book_table ORDER BY rating")
    LiveData<List<Book>> getAllBooks();



    @Query("SELECT COUNT(id) FROM book_table")
    int getCount();


}
