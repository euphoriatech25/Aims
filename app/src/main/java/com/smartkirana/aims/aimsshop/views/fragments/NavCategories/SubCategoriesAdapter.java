package com.smartkirana.aims.aimsshop.views.fragments.NavCategories;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.smartkirana.aims.aimsshop.R;
import com.smartkirana.aims.aimsshop.network.RetrofitInterface;

import java.util.List;

public class SubCategoriesAdapter extends RecyclerView.Adapter<SubCategoriesAdapter.RecyclerViewCartHolder> {
    Context context;
    List<CategoriesListModel.Child> categoryList;
    RetrofitInterface retrofitInterface;


    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position, View itemView);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }


    public SubCategoriesAdapter(Context context, List<CategoriesListModel.Child> categoryList) {
        this.context = context;
        this.categoryList = categoryList;
    }

    @NonNull
    @Override
    public RecyclerViewCartHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.categories_items_list, viewGroup, false);
        return new RecyclerViewCartHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewCartHolder holder, int position) {
        holder.categories_name.setText(categoryList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    class RecyclerViewCartHolder extends RecyclerView.ViewHolder {
        TextView categories_name;
        public RecyclerViewCartHolder(@NonNull View itemView) {
            super(itemView);
            categories_name=itemView.findViewById(R.id.categories_name);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            mListener.onItemClick(position, view);
                        }
                    }
                }
            });

        }
    }
}


