package com.example.roomwordssample.database;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roomwordssample.R;

import java.util.List;

public class WordsListAdapter extends RecyclerView.Adapter<WordsListAdapter.MyViewHolder> {
    private List<Words> wordsList;
    public  WordsListAdapter(List<Words> wordList){
        this.wordsList = wordList;
    }

    public void setUserList(List<Words> wordsList){
        this.wordsList = wordsList;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.textViewWord.setText(wordsList.get(position).word);

    }

    @Override
    public int getItemCount() {
        return wordsList.size();
    }

    public  class MyViewHolder extends RecyclerView.ViewHolder{
        TextView textViewWord;

        public MyViewHolder(View view){
            super(view);
            textViewWord = view.findViewById(R.id.textViewWord);
        }
    }
}
