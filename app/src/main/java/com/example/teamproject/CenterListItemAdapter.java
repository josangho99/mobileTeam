package com.example.teamproject;
import android.view.*;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class CenterListItemAdapter extends RecyclerView.Adapter<CenterListItemAdapter.ItemViewHolder> {

    private ArrayList<CenterListItem> centerListItems;
    public CenterListItemAdapter(FragmentActivity requireActivity, ArrayList<CenterListItem> centerListItems) {
        this.centerListItems = centerListItems;
    }

    @NonNull
    @Override
    public CenterListItemAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.centerlist_item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CenterListItemAdapter.ItemViewHolder holder, int position) {
        holder.onBind(centerListItems.get(position));
    }

    @Override
    public int getItemCount() {
        return centerListItems.size();
    }

    void addItem(CenterListItem centerListItem) {
        // 외부에서 item을 추가시킬 함수입니다.
        centerListItems.add(centerListItem);
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{
        private TextView centerName;
        private TextView place;
        private TextView category;
        private ImageView centerImage;
        private RatingBar ratingBar;
        public ItemViewHolder(View view) {
            super(view);

            centerName = view.findViewById(R.id.center_name);
            centerImage = view.findViewById(R.id.center_image);
            place = view.findViewById(R.id.center_place);
            category = view.findViewById(R.id.center_category);
            ratingBar = view.findViewById(R.id.center_rating);
        }

        public void onBind(CenterListItem centerListItem) {
            centerName.setText(centerListItem.getCenterName());
            place.setText(centerListItem.getPlace());
            category.setText(centerListItem.getCategory());
            centerImage.setImageResource(centerListItem.getCenterImage());
            ratingBar.setRating(centerListItem.getRating());
        }
    }
}
