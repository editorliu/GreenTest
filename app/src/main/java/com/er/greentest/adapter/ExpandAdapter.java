package com.er.greentest.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import com.er.greentest.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/8/14.
 */

public class ExpandAdapter extends RecyclerView.Adapter<ExpandAdapter.MyViewHolder> {
    public static final int MAX_LINE = 3;
    public static final int S_UN = 0;//位置状态
    public static final int S_NORMAL = 1;//正常状态
    public static final int S_COL = 2;//折叠
    public static final int S_EXP = 3;//展开


    private Context context;
    private ArrayList<StrBean> listData;
    private SparseArray<Integer> textStates;

    public ExpandAdapter(Context context, ArrayList<StrBean> listData) {
        this.context = context;
        this.listData = listData;
        textStates = new SparseArray<>();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_expand,null));
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        Integer state = textStates.get(listData.get(position).getId(), S_UN);
        if (state == S_UN) {
            holder.content.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                @Override
                public boolean onPreDraw() {
                    holder.content.getViewTreeObserver().removeOnPreDrawListener(this);
                    Log.w("EX", "position:" + position + "  holder.content.getLineCount()=" + holder.content.getLineCount());
                    if (holder.content.getLineCount() > MAX_LINE) {
                        holder.content.setMaxLines(MAX_LINE);
                        holder.ex.setVisibility(View.VISIBLE);
                        holder.ex.setText("点击展开");
                        textStates.put(listData.get(position).getId(), S_COL);
                    } else {
                        holder.ex.setVisibility(View.GONE);
                        textStates.put(listData.get(position).getId(), S_NORMAL);
                    }
                    return true;
                }
            });
            holder.content.setMaxLines(Integer.MAX_VALUE);
            holder.content.setText(listData.get(position).getName());
        } else {
            switch (state) {
                case S_NORMAL:
                    holder.ex.setVisibility(View.INVISIBLE);
                    break;
                case S_COL:
                    holder.ex.setVisibility(View.VISIBLE);
                    holder.ex.setText("点击展开");
                    break;
                case S_EXP:
                    holder.ex.setVisibility(View.VISIBLE);
                    holder.ex.setText("点击折叠");
                    break;
            }
            holder.content.setText(listData.get(position).getName());
        }

        holder.ex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer state = textStates.get(listData.get(position).getId(), S_UN);
                switch (state) {
                    case S_COL:
                        holder.ex.setText("点击折叠");
                        holder.content.setMaxLines(Integer.MAX_VALUE);
                        textStates.put(listData.get(position).getId(),S_EXP);
                        break;
                    case S_EXP:
                        holder.ex.setText("点击展开");
                        holder.content.setMaxLines(MAX_LINE);
                        textStates.put(listData.get(position).getId(),S_COL);
                        break;
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return listData == null?0:listData.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView content;
        private TextView ex;
        public MyViewHolder(View itemView) {
            super(itemView);
            content = ((TextView) itemView.findViewById(R.id.content));
            ex = ((TextView) itemView.findViewById(R.id.ex));
        }
    }
}
