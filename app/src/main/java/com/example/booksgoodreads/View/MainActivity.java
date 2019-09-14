package com.example.booksgoodreads.View;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.booksgoodreads.Model.RoomPackage.Book;
import com.example.booksgoodreads.R;
import com.example.booksgoodreads.ViewModel.BookViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;

    private BookViewModel bookViewModel;

    @BindView(R.id.fab_addNote)
    FloatingActionButton fabAddNote;

    List<Book> booklist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);



        bookViewModel = ViewModelProviders.of(this).get(BookViewModel.class);


        bookViewModel.getListLiveData().observe(this, new Observer<List<Book>>() {
            @Override
            public void onChanged(List<Book> books) {

                recyclerview.setAdapter(new RecycleAdapterBook(MainActivity.this, (ArrayList<Book>) books));
                recyclerview.setLayoutManager(new LinearLayoutManager(MainActivity.this));
            }

        });

        








    }


    @OnClick(R.id.fab_addNote)
    public void fabAddNoteOnClick() {

        startActivity(new Intent(MainActivity.this, AddActivity.class));

    }


}
