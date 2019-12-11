package com.example.chatification;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MenuListAdapter extends RecyclerView.Adapter<MenuListAdapter.ViewHolder> {

    private ArrayList<ListItem> itemList;
    private String id;

    public MenuListAdapter(ArrayList<ListItem> arrayList, String id) {
        itemList = arrayList;
        this.id = id;
    }

    // 뷰홀더 객체를 생성하여 리턴
    @NonNull
    @Override
    public MenuListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.chatting_list_item, viewGroup, false);

        return new ViewHolder(v);
    }

    // position에 해당하는 데이터를 ViewHolder와 바인드
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {

        final ListItem listItem = itemList.get(position); // 해당 position에 대한 아이템

        viewHolder.tvTitle.setText(listItem.getTitle());
        viewHolder.tvEndDate.setText(listItem.getEndDate());

        if(listItem.isStatus()) {
            viewHolder.signInBtn.setText("신청");

            viewHolder.signInBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), ChatActivity.class); // 아닐 수도
                    intent.putExtra("roomName", listItem.getTitle());
                    intent.putExtra("u_id", id);

                    view.getContext().startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        TextView tvEndDate;
        Button signInBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.itemTvTitle);
            tvEndDate = itemView.findViewById(R.id.itemTvEndDate);
            signInBtn = itemView.findViewById(R.id.signInBtn);
        }
    }
}
