package com.example.tugas_firebase;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tugas_firebase.adapter.BookListAdapter;
import com.example.tugas_firebase.model.Book;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    ArrayList<Book> bookList;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    BookListAdapter bookListAdapter;
    RecyclerView rvBooks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponents();

        bookList = prepareData();

        bookListAdapter = new BookListAdapter(bookList, MainActivity.this);
        LinearLayoutManager manager = new LinearLayoutManager(MainActivity.this);
        rvBooks.setLayoutManager(manager);
        rvBooks.setAdapter(bookListAdapter);
    }

    private void initComponents() {
        rvBooks = findViewById(R.id.rvBooks);
    }

    private ArrayList<Book> prepareData() {
        ArrayList<Book> bookList = new ArrayList<>();

        db.collection("books")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            Log.d(TAG, "Success loading " + document.getId() + " => " + document.getData());
                            Book book = new Book();
                            book.title = document.getString("title");
                            book.author = document.getString("author");
                            book.description = document.getString("description");
                            book.cover = document.getString("cover");
                            book.image = document.getString("image");
                            bookList.add(book);
                        }
                        bookListAdapter.notifyDataSetChanged();
                    } else {
                        Log.w(TAG, "Error getting documents.", task.getException());
                    }
                });

        return bookList;
    }
}