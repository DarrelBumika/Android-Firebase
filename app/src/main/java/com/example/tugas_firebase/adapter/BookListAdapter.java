package com.example.tugas_firebase.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.tugas_firebase.R;
import com.squareup.picasso.Picasso;

import com.example.tugas_firebase.model.Book;

import java.util.ArrayList;

public class BookListAdapter extends RecyclerView.Adapter<BookListAdapter.CustomViewHolder> {

    private Context context;
    private ArrayList<Book> books;
    private LayoutInflater inflater;

    public BookListAdapter(ArrayList<Book> books, Context context) {
        this.context = context;
        this.books = books;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = inflater.inflate(R.layout.single_book, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        Book book = books.get(position);

        holder.tvBookTitle.setText(book.title);
        holder.tvBookAuthor.setText(book.author);
        holder.tvBookDescription.setText(book.description);

        Picasso.get()
                .load(book.cover)
                .into(holder.ivBookCover);

        Picasso.get()
                .load(book.image)
                .into(holder.ivBookImage);
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        public TextView tvBookTitle;
        public TextView tvBookAuthor;
        public TextView tvBookDescription;
        public ImageView ivBookCover;
        public ImageView ivBookImage;

        public CustomViewHolder(View itemView) {
            super(itemView);

            tvBookTitle = itemView.findViewById(R.id.tvBookTitle);
            tvBookAuthor = itemView.findViewById(R.id.tvBookAuthor);
            tvBookDescription = itemView.findViewById(R.id.tvBookDescription);
            ivBookCover = itemView.findViewById(R.id.ivBookCover);
            ivBookImage = itemView.findViewById(R.id.ivBookImage);
        }
    }
}
