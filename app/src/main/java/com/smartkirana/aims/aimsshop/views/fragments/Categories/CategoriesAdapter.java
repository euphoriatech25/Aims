package com.smartkirana.aims.aimsshop.views.fragments.Categories;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.smartkirana.aims.aimsshop.R;

import java.util.List;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.RecyclerViewCartHolder> {
    Context context;
    List<CategoriesModel.Featuredcategory> categoryList;



    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position, View itemView);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }



    public CategoriesAdapter(Context context, List<CategoriesModel.Featuredcategory> categoryList) {
        this.context = context;
        this.categoryList = categoryList;
    }

    @NonNull
    @Override
    public RecyclerViewCartHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.categories_list, viewGroup, false);
        return new RecyclerViewCartHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewCartHolder holder, int position) {
        holder.categories_name.setText(categoryList.get(position).getName());
       Glide.with(context).load(categoryList.get(position).getThumb()).into(holder.categories_image_id);
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    class RecyclerViewCartHolder extends RecyclerView.ViewHolder {
        TextView categories_name;
        ImageView categories_image_id;
        public RecyclerViewCartHolder(@NonNull View itemView) {
            super(itemView);

            categories_name=itemView.findViewById(R.id.categories_name);
            categories_image_id=itemView.findViewById(R.id.categories_thumb_id);
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
