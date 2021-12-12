package com.example.mynotes;

import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mynotes.placeholder.PlaceholderContent.PlaceholderItem;
import com.example.mynotes.databinding.FragmentItemBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link PlaceholderItem}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyNoteRecyclerViewAdapter extends RecyclerView.Adapter<MyNoteRecyclerViewAdapter.ViewHolder> {

    private ArrayList<Note> notes;

    private NoteFragment.onNoteListInteractionListener listener;

    public MyNoteRecyclerViewAdapter(ArrayList<Note> notes, NoteFragment.onNoteListInteractionListener listener) {
        this.notes = notes;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = notes.get(position);
        holder.mHeaderView.setText(notes.get(position).getHeader());
        holder.mDateView.setText((new SimpleDateFormat("yyyy-MM-dd")).
                format(notes.get(position).getDate()));
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != listener) {
                    listener.onNoteSelected(holder.mItem);
                }
            }
        });
        if(position %2 == 1) {
            holder.itemView.setBackgroundColor(Color.YELLOW);
        }
        else{
            holder.itemView.setBackgroundColor(Color.WHITE);
        }
    }
    @Override
    public int getItemCount() {
        return notes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mHeaderView;
        public final TextView mDateView;
        public Note mItem;
        public ViewHolder(View view) {
            super(view);
            mView = view;
            mHeaderView = view.findViewById(R.id.note_header);
            mDateView = view.findViewById(R.id.note_date);
        }
        @Override
        public String toString() {
            return super.toString() + " '" + mHeaderView.getText() + "'";
        }
    }
}
