package com.peakmain.gankzhihu.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.peakmain.gankzhihu.R;
import com.peakmain.gankzhihu.bean.daily.Options;
import com.peakmain.gankzhihu.di.scope.ContextLife;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author ：Peakmain
 * version ：1.0
 * createTime ：2018/11/12 0012 上午 11:28
 * mail : 2726449200@qq.com
 * describe ：
 */
public class DailyFeedAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<Options> options;
    @Inject
    public DailyFeedAdapter(@ContextLife Context context, List<Options> options) {
        this.context = context;
        this.options = options;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        View rootView = View.inflate(parent.getContext(), R.layout.item_daily_feed_option,null);
        return new FeedOptionViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        FeedOptionViewHolder feedOptionViewHolder = (FeedOptionViewHolder) holder;
        feedOptionViewHolder.bindItem(options.get(position),position);
    }

    @Override
    public int getItemCount() {
        return options.size();
    }

     class FeedOptionViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_feed_author_icon)
        ImageView iv_feed_author_icon;
        @BindView(R.id.tv_feed_author_name)
        TextView tv_feed_author_name;
        @BindView(R.id.tv_feed_content)
        TextView tv_feed_content;
        @BindView(R.id.card_option)
        CardView card_option;

        public FeedOptionViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
        private void bindItem(Options options,int position){
            tv_feed_author_name.setText(options.getAuthor().getName());
            tv_feed_content.setText(options.getContent());
            Glide.with(context).load(options.getAuthor().getAvatar()).centerCrop().into(iv_feed_author_icon);

            if((position)%4==0){
                card_option.setCardBackgroundColor(context.getResources().getColor(R.color.card_1));
            }else if((position-1)%4==0){
                card_option.setCardBackgroundColor(context.getResources().getColor(R.color.card_2));
            }else if((position-2)%4==0){
                card_option.setCardBackgroundColor(context.getResources().getColor(R.color.card_3));
            }else if((position-3)%4==0){
                card_option.setCardBackgroundColor(context.getResources().getColor(R.color.card_4));
            }
        }
    }

}
