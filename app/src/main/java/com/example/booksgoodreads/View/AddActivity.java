package com.example.booksgoodreads.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.example.booksgoodreads.Model.RoomPackage.Book;
import com.example.booksgoodreads.R;
import com.example.booksgoodreads.ViewModel.BookViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnFocusChange;

public class AddActivity extends AppCompatActivity {

    @BindView(R.id.et_title)
    EditText etTitle;
    @BindView(R.id.et_author)
    EditText etAuthor;
    @BindView(R.id.et_genre)
    EditText etGenre;
    @BindView(R.id.et_ImgToUrl)
    EditText etImgToUrl;
    @BindView(R.id.rb_ratingbarAddActivity)
    RatingBar rbRatingbarAddActivity;
    @BindView(R.id.et_rating)
    EditText etRating;

    private BookViewModel bookViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        ButterKnife.bind(this);

        bookViewModel = ViewModelProviders.of(this).get(BookViewModel.class);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        setTitle("Add Book");

        rbRatingbarAddActivity.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                float rating = ratingBar.getRating();

                etRating.setText(rating+"");

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        //Lovely Infilator but the menu one
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_book_menu, menu);

        //Return true to show menu
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {

            case R.id.mi_save:
                saveBookInsertion();
                return true;

            case R.id.mi_search:
                searchBookInsertion();
                return true;

            default:
                return super.onOptionsItemSelected(item);


        }
    }

    private void searchBookInsertion() {

        startActivity(new Intent(AddActivity.this,SearchActivity.class));

    }

    private void saveBookInsertion() {

        Book book = new Book(
                etTitle.getText().toString().trim(),
                etAuthor.getText().toString().trim(),
                etGenre.getText().toString().trim(),
                etImgToUrl.getText().toString().trim(),
                rbRatingbarAddActivity.getRating()
        );


        //If any of the fields empty abort insertion
        if (book.getTitle().equals("") || book.getAuthor().equals("")
                || book.getGenre().equals("") || book.getImageToUrl().equals("")) {

            Toast.makeText(this, "One of the fields is left empty", Toast.LENGTH_SHORT).show();
            return;
        }

        bookViewModel.insert(book);
        startActivity(new Intent(AddActivity.this, MainActivity.class));


    }


    @OnFocusChange(R.id.et_rating)
    void et_ratingOnFocusChange() {


        if(etRating.getText().toString().equals("")){
           etRating.setText("0");
        } else{
         rbRatingbarAddActivity.setRating(Float.parseFloat(etRating.getText().toString()));
        }
    }


}



