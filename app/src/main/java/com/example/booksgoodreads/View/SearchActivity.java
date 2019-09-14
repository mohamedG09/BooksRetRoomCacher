package com.example.booksgoodreads.View;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.booksgoodreads.Model.RetrofitPackage.BooksRespone;
import com.example.booksgoodreads.Model.RoomPackage.Book;
import com.example.booksgoodreads.R;
import com.example.booksgoodreads.ViewModel.BookViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchActivity extends AppCompatActivity {

    @BindView(R.id.et_searchBook)
    EditText etSearchBook;
    @BindView(R.id.btn_search)
    Button btnSearch;
    @BindView(R.id.rv_downloadBooks)
    RecyclerView rvDownloadBooks;

    ProgressDialog progressDialog;

    BookViewModel bookViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);


        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Fetching Data");

        bookViewModel = ViewModelProviders.of(this).get(BookViewModel.class);



    }

    @OnClick(R.id.btn_search)
    public void btn_search() {

        progressDialog.show();
        String searchItem = etSearchBook.getText().toString().trim();
        bookViewModel.init(searchItem);

        try {

            bookViewModel.getBooksFetched().observe(this, new Observer<BooksRespone>() {
                @Override
                public void onChanged(BooksRespone booksRespone) {

                    rvDownloadBooks.setAdapter(new RecycleAdapterVolume(SearchActivity.this,
                            (ArrayList<BooksRespone.Item>) booksRespone.getItems()));
                    rvDownloadBooks.setLayoutManager(new LinearLayoutManager(SearchActivity.this));

                }
            });

        } catch (Exception e) {
            Toast.makeText(SearchActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        } finally {
            progressDialog.dismiss();
        }


    }


}
