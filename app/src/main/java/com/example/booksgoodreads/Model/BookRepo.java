package com.example.booksgoodreads.Model;

import android.app.Application;
import android.os.AsyncTask;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.booksgoodreads.Model.RetrofitPackage.BooksRespone;
import com.example.booksgoodreads.Model.RetrofitPackage.RetrofitBookApi;
import com.example.booksgoodreads.Model.RetrofitPackage.RetrofitService;
import com.example.booksgoodreads.Model.RoomPackage.Book;
import com.example.booksgoodreads.Model.RoomPackage.BookDao;
import com.example.booksgoodreads.Model.RoomPackage.BookDatabase;

import java.util.List;
import java.util.concurrent.ExecutionException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class BookRepo {

    private BookDao bookDao;
    private LiveData<List<Book>> allBooks;
    Application application;

    RetrofitBookApi retrofitBookApi;


    public BookRepo(Application application) {

        //Room Part of the architecture
        bookDao = BookDatabase.getInstance(application).bookDao();
        this.application = application;
        allBooks = bookDao.getAllBooks();

        //Retrofit Part of the architecture
        retrofitBookApi = RetrofitService.cteateService(RetrofitBookApi.class);

    }

    //Fetching books


    public MutableLiveData<BooksRespone> getResponeBooks(String searchItem) {

        MutableLiveData<BooksRespone> bookData = new MutableLiveData<>();

        retrofitBookApi.getBaseJsonObject(searchItem).enqueue(new Callback<BooksRespone>() {
            @Override
            public void onResponse(Call<BooksRespone> call, Response<BooksRespone> response) {
                if (response.isSuccessful()) {
                    bookData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<BooksRespone> call, Throwable t) {
                bookData.setValue(null);
            }
        });
        return bookData;


    }


    public void insertBook(Book book) {
        new insertBookTask(bookDao).execute(book);
    }

    public void updateBook(Book book) {
        new updateBookTask(bookDao).execute(book);
    }

    public void deleteBook(Book book) {
        new deleteBookTask(bookDao).execute(book);
    }

    public void deleteAllBooks() {
        new deleteAllBooks(bookDao).execute();
    }

    public Integer getCountBook() throws ExecutionException, InterruptedException {
        return new getCountBooks(bookDao).execute().get();
    }


    public LiveData<List<Book>> getAllBooks() {
        return allBooks;
    }

    //static class to avoid memory leak
    private static class insertBookTask extends AsyncTask<Book, Void, Void> {

        private BookDao bookDao;

        public insertBookTask(BookDao bookDao) {
            this.bookDao = bookDao;
        }

        @Override
        protected Void doInBackground(Book... books) {

            bookDao.insertBook(books[0]);
            return null;
        }
    }

    private static class updateBookTask extends AsyncTask<Book, Void, Void> {

        private BookDao bookDao;

        public updateBookTask(BookDao bookDao) {
            this.bookDao = bookDao;
        }

        @Override
        protected Void doInBackground(Book... books) {

            bookDao.updateBook(books[0]);
            return null;
        }
    }


    private static class deleteBookTask extends AsyncTask<Book, Void, Void> {

        private BookDao bookDao;

        public deleteBookTask(BookDao bookDao) {
            this.bookDao = bookDao;
        }

        @Override
        protected Void doInBackground(Book... books) {

            bookDao.deleteBook(books[0]);
            return null;
        }
    }

    private static class deleteAllBooks extends AsyncTask<Void, Void, Void> {

        private BookDao bookDao;

        public deleteAllBooks(BookDao bookDao) {
            this.bookDao = bookDao;
        }


        @Override
        protected Void doInBackground(Void... voids) {
            bookDao.deleteAllBooks();
            return null;
        }
    }

    private static class getCountBooks extends AsyncTask<Void, Void, Integer> {

        private BookDao bookDao;

        public getCountBooks(BookDao bookDao) {
            this.bookDao = bookDao;
        }


        @Override
        protected Integer doInBackground(Void... voids) {
            return bookDao.getCount();
        }
    }


}
