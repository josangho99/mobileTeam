package com.example.teamproject;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CommunityItemAdapter extends RecyclerView.Adapter<CommunityItemAdapter.BoardViewHolder> {
    // 해당 어댑터의 ViewHolder를 상속받는다.
     ArrayList<CommunityItem> items;
    public interface OnItemClickListener {
        void onItemClick(View v, int position) ;
    }
    private OnItemClickListener mListener = null ;

    // OnItemClickListener 리스너 객체 참조를 어댑터에 전달하는 메서드
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener ;
    }
    public CommunityItemAdapter(FragmentActivity requireActivity, ArrayList<CommunityItem> items) {
        this.items = items;
    }
    public void addList(ArrayList<CommunityItem> item) {
        this.items = item;
        Log.d(TAG, "addFaqList: add 확인: "+items);
    }
    @Override
    public BoardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // ViewHodler 객체를 생성 후 리턴한다.
        return new BoardViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.community_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BoardViewHolder holder, int position) {
        // ViewHolder 가 재활용 될 때 사용되는 메소드
        CommunityItem item = items.get(position);
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
            this.title = itemView.findViewById(R.id.item_title);
            this.writer = itemView.findViewById(R.id.item_writer);
            this.place = itemView.findViewById(R.id.item_place);
            this.date = itemView.findViewById(R.id.item_date);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition() ;
                    if (pos != RecyclerView.NO_POSITION) {
                        if (mListener != null) {
                            CommunityItem e = items.get(pos);
                            Intent intent = new Intent(v.getContext(), CommunityitemActivity.class);
                            intent.putExtra("title",e.getTitle());
                            //TODO 날짜와 위치 추가하기
                            intent.putExtra("content",e.getContent());
                            v.getContext().startActivity(intent);
                        }
                    }
                }
            });
        }
    }
}
