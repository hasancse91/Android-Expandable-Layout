package com.hellohasan.expandablelayoutcode.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hellohasan.expandablelayoutcode.Model.ArticleModel;
import com.hellohasan.expandablelayoutcode.R;
import com.squareup.picasso.Picasso;


import net.cachapa.expandablelayout.ExpandableLayout;

import java.util.List;


public class ArticleReadingAdapter extends RecyclerView.Adapter<ArticleReadingAdapter.CustomViewHolder> {

    private Context context;
    private List<ArticleModel> articleModelList;
    private RecyclerView recyclerView;

    private static final int UNSELECTED = -1;
    private int selectedItem = UNSELECTED;

    public ArticleReadingAdapter(Context context, List<ArticleModel> articleModelList, RecyclerView recyclerView){
        this.context = context;
        this.articleModelList = articleModelList;
        this.recyclerView = recyclerView;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_article_reading, null);

        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {

        ArticleModel articleModel = articleModelList.get(position);


        Picasso.with(context)
                .load(articleModel.getImageUrl())
                .into(holder.imageView);
        holder.headlineTextView.setText(articleModel.getHeadline());
        holder.authorTextView.setText(articleModel.getAuthorName());
        holder.publishedDateTextView.setText(articleModel.getPublishedDate());
        holder.articleTextView.setText(articleModel.getArticle());


        holder.relativeLayoutHead.setSelected(false);
        holder.expandableLayout.collapse(false);
    }


    @Override
    public int getItemCount() {
        return articleModelList.size()>0 ? articleModelList.size() : 0;
    }


    /**
     * Custom View Holder Class
     */
    class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private RelativeLayout relativeLayoutHead;
        private ExpandableLayout expandableLayout;
        private int position;

        TextView headlineTextView;
        ImageView imageView;
        TextView authorTextView;
        TextView publishedDateTextView;
        TextView articleTextView;

        CustomViewHolder(View itemView) {
            super(itemView);

            relativeLayoutHead = (RelativeLayout) itemView.findViewById(R.id.recyclerHeader);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            headlineTextView = (TextView) itemView.findViewById(R.id.headline);
            authorTextView = (TextView) itemView.findViewById(R.id.author_name);
            publishedDateTextView = (TextView) itemView.findViewById(R.id.publishedDate);
            articleTextView = (TextView) itemView.findViewById(R.id.article);
            expandableLayout = (ExpandableLayout) itemView.findViewById(R.id.expandable_layout);

            relativeLayoutHead.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            CustomViewHolder holder = (CustomViewHolder) recyclerView.findViewHolderForAdapterPosition(selectedItem);
            if (holder != null) {
                holder.relativeLayoutHead.setSelected(false);
                holder.expandableLayout.collapse();
            }

            if (position == selectedItem) {
                selectedItem = UNSELECTED;
            } else {
                relativeLayoutHead.setSelected(true);
                expandableLayout.expand();
                selectedItem = position;
            }

        }
    }
}
