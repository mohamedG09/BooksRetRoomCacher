package com.example.booksgoodreads.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import com.example.booksgoodreads.Model.RetrofitPackage.BooksRespone;
import com.example.booksgoodreads.Model.RoomPackage.Book;
import com.example.booksgoodreads.Model.BookRepo;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class BookViewModel extends AndroidViewModel {

    private BookRepo bookRepo;
    private LiveData<List<Book>> listLiveData;

    private MutableLiveData<BooksRespone> mutableLiveData;



    public BookViewModel(@NonNull Application application) {
        super(application);
        bookRepo = new BookRepo(application);
        listLiveData = bookRepo.getAllBooks();

    }

    public void init(String searchItem) {
        mutableLiveData = bookRepo.getResponeBooks(searchItem);
    }

    public void insert(Book book){
        bookRepo.insertBook(book);
    }

    public void delete(Book book){
        bookRepo.deleteBook(book);
    }

    public void update(Book book){
        bookRepo.updateBook(book);
    }

    public void deleteAll() {
        bookRepo.deleteAllBooks();
    }

    public int getCount() throws ExecutionException, InterruptedException {
       return bookRepo.getCountBook();
    }

    public LiveData<BooksRespone> getBooksFetched() {
        return mutableLiveData;
    }


    public LiveData<List<Book>> getListLiveData(){
        return listLiveData;
    }

}
