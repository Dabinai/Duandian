package com.dabin.www.yuekaob.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dabin.www.yuekaob.R;
import com.dabin.www.yuekaob.bean.NewBase;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

/**
 * Created by Dabin on 2017/11/23.
 */

public class HomeAdapter extends XRecyclerView.Adapter<HomeAdapter.MyViewHolder> {

    private Context context;
    private List<NewBase.SongListBean> song_list;

    private OnDanji onDanji;

    public interface OnDanji {
        void Danji(View view, int position);
    }

    public void setOnDanji(OnDanji onDanji) {
        this.onDanji = onDanji;
    }


    public HomeAdapter(Context context, List<NewBase.SongListBean> song_list) {
        this.context = context;
        this.song_list = song_list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder myViewHolder = new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.homeitem, parent, false));
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.image.setImageURI(song_list.get(position).getPic_big());
        holder.textView.setText(song_list.get(position).getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onDanji.Danji(view,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return song_list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView image;
        TextView textView;

        public MyViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.item_image);
            textView = itemView.findViewById(R.id.item_title);
        }
    }
}
