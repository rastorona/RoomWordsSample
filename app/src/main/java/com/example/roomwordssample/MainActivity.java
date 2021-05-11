package com.example.roomwordssample;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.roomwordssample.database.Words;
import com.example.roomwordssample.database.WordsDatabase;
import com.example.roomwordssample.database.WordsListAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private WordsListAdapter wordsListAdapter;
    static WordsDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initRecyclerView();
        loadWordsList();

        FloatingActionButton button = findViewById(R.id.floatingActionButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddWordsActivity.class);
                startActivity(intent);
            }
        });
    }
    private void initRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.RV);
        recyclerView.setLayoutManager((new LinearLayoutManager(this)));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

        db = WordsDatabase.getDbInstance(getApplicationContext());
        List<Words> wordsList = db.wordsDao().getAllWords();
        wordsListAdapter = new WordsListAdapter(wordsList);
        recyclerView.setAdapter(wordsListAdapter);
    }
    private void loadWordsList(){
        WordsDatabase db = WordsDatabase.getDbInstance(this.getApplicationContext());
        List<Words> wordsList = db.wordsDao().getAllWords();
        wordsListAdapter.setUserList(wordsList);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == 100){
            loadWordsList();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadWordsList();
    }
}