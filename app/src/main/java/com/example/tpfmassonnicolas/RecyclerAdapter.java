package com.example.tpfmassonnicolas;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerHolder> {
    private List<Movie> items;
    private List<Movie> originalItems;
    private RecyclerItemClick itemClick;

    //Method to create the constructor the recycler adapter
    public RecyclerAdapter(List<Movie> items, RecyclerItemClick itemClick) {
        this.items = items;
        this.itemClick = itemClick;
        this.originalItems = new ArrayList<>();
        originalItems.addAll(items);
    }

    //Method to inflate xml item list
    @NonNull
    @Override
    public RecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_view, parent, false);
        return new RecyclerHolder(view);
    }

    //Method to view object Movie
    @Override
    public void onBindViewHolder(@NonNull final RecyclerHolder holder, final int position) {
        final Movie item = items.get(position);

        if (item.getName()==null){
            holder.tvMovieDescription.setVisibility(View.GONE);
            holder.tvName.setText("UserId: " + item.getUserId());
            holder.tvType.setText("Id: " + item.getId());
            holder.tvMovieRating.setText("Title: " + item.getTitle());
        }else {
            holder.tvMovieDescription.setVisibility(View.VISIBLE);
            holder.tvName.setText(item.getName() + " (" + item.getYear() + ")");
            holder.tvType.setText("Category: " + item.getType());
            holder.tvMovieRating.setText("Ranking: " + item.getMovieRating());
            holder.tvMovieDescription.setText("Description: " + item.getMovieDescription());
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClick.itemClick(item);
            }
        });
    }

    //Method to get item size
    @Override
    public int getItemCount() {
        return items.size();
    }

    public class RecyclerHolder extends RecyclerView.ViewHolder {

        private TextView tvName;
        private TextView tvType;
        private TextView tvYear;
        private TextView tvMovieRating;
        private TextView tvMovieDescription;

        public RecyclerHolder(@NonNull View itemView_1) {
            super(itemView_1);

            tvName = itemView.findViewById(R.id.tvName);
            tvType = itemView.findViewById(R.id.tvType);
            tvYear = itemView.findViewById(R.id.tvYear);
            tvMovieRating = itemView.findViewById(R.id.tvMovieRating);
            tvMovieDescription = itemView.findViewById(R.id.tvMovieDescription);
        }
    }

    public interface RecyclerItemClick {
        void itemClick(Movie item);
    }
}


