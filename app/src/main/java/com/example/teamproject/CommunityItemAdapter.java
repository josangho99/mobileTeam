package com.example.teamproject;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class CommunityItemAdapter extends RecyclerView.Adapter<CommunityItemAdapter.BoardViewHolder> {
    // 해당 어댑터의 ViewHolder를 상속받는다.
     ArrayList<CommnunityItem> items;
    public CommunityItemAdapter(FragmentActivity requireActivity, ArrayList<CommnunityItem> items) {
        this.items = items;
    }

    @Override
    public BoardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // ViewHodler 객체를 생성 후 리턴한다.
        return new BoardViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.community_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BoardViewHolder holder, int position) {
        // ViewHolder 가 재활용 될 때 사용되는 메소드
        CommnunityItem item = items.get(position);
        holder.title.setText(item.getTitle());
        holder.writer.setText(item.getWriter());
        holder.place.setText(item.getPlace());
        holder.date.setText(item.getDate());
    }

    @Override
    public int getItemCount() {
        return items.size(); // 전체 데이터의 개수 조회
    }

    // 아이템 뷰를 저장하는 클래스
    public class BoardViewHolder extends RecyclerView.ViewHolder {
        // ViewHolder 에 필요한 데이터들을 적음.
        private TextView title;
        private TextView writer;
        private TextView place;
        private TextView date;

        public BoardViewHolder(@NonNull View itemView) {
            super(itemView);
            // 아이템 뷰에 필요한 View
            title = itemView.findViewById(R.id.item_title);
            writer = itemView.findViewById(R.id.item_writer);
            place = itemView.findViewById(R.id.item_place);
            date = itemView.findViewById(R.id.item_date);
        }
    }
}
