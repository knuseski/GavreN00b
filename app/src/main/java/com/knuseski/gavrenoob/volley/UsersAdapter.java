package com.knuseski.gavrenoob.volley;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.knuseski.gavrenoob.R;
import com.knuseski.gavrenoob.entity.UserResponse;

import java.util.ArrayList;
import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.ViewHolder> {

    private List<UserResponse.User> data = new ArrayList<>();
    private ClickListener clickListener;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_row, parent, false));
    }

    public void setData(List<UserResponse.User> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        UserResponse.User item = data.get(position);
        holder.setItem(item);
        holder.itemView.setOnClickListener(view -> {
            if (clickListener != null) clickListener.onClick(item);
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvName;
        private TextView tvNickName;
        private ImageView ivImage;

        private ViewHolder(View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tvName);
            tvNickName = itemView.findViewById(R.id.tvNickName);
            ivImage = itemView.findViewById(R.id.ivImage);
        }

        private void setItem(UserResponse.User item) {
            tvName.setText(item.getName());
            tvNickName.setText(item.getNickName());

            VolleySingleton.getInstance().getImageLoader().get(item.getImgUrl(), new ImageLoader.ImageListener() {
                @Override
                public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                    ivImage.setImageBitmap(response.getBitmap());
                }

                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                }
            });
        }
    }

    public interface ClickListener {
        void onClick(UserResponse.User item);
    }

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }
}