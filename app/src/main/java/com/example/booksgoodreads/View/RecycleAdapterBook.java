package com.example.booksgoodreads.View;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.example.booksgoodreads.Model.RoomPackage.Book;
import com.example.booksgoodreads.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class RecycleAdapterBook extends RecyclerView.Adapter<RecycleAdapterBook.viewHolder> {

    protected Context context;
    protected ArrayList<Book> list;

    public RecycleAdapterBook(Context context, ArrayList<Book> list) {
        this.context = context;
        this.list = list;
    }

    public RecycleAdapterBook(Context context) {
        this.context = context;
        this.list = new ArrayList<>();
    }

//    public void setList(ArrayList<Book> list) {
//        this.list = list;
//        notifyDataSetChanged();
//    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.book_row, parent, false);
        viewHolder holder = new viewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, final int position) {

            Glide
                    .with(context)
                    .load(list.get(position).getImageToUrl())
                    .centerCrop()
                    .placeholder(R.drawable.openbook)
                    .into(holder.ivBookPoster);

        holder.tvAuthor.setText(list.get(position).getAuthor());
        holder.tvGenre.setText(list.get(position).getGenre());
        holder.tvTitle.setText(list.get(position).getTitle());
        holder.rbRatingbar.setRating((float)(list.get(position).getRating()));

        //On item click listener
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               //TODO onclick event for each button
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public ArrayList<Book> getList() {
        return list;
    }

    static
    class viewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.tv_title)
        TextView tvTitle;

        @BindView(R.id.tv_author)
        TextView tvAuthor;

        @BindView(R.id.tv_genre)
        TextView tvGenre;

        @BindView(R.id.list_relativelayout)
        RelativeLayout listRelativelayout;

        @BindView(R.id.list_cardview)
        CardView listCardview;

        @BindView(R.id.list_linearlayout)
        LinearLayout listLinearlayout;

        @BindView(R.id.iv_bookposter)
        ImageView ivBookPoster;

        @BindView(R.id.rb_ratingbar)
        RatingBar rbRatingbar;

        viewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }


    }


}
