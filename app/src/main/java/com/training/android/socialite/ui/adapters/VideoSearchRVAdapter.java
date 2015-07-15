package com.training.android.socialite.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.activeandroid.query.Select;
import com.squareup.picasso.Picasso;
import com.training.android.socialite.R;
import com.training.android.socialite.ui.PlayerActivity;
import com.training.android.socialite.ui.models.VideoItem;

import java.util.List;

/**
 * Created by chrisenabled on 6/23/15.
 */
public class VideoSearchRVAdapter extends RecyclerView.Adapter<VideoSearchRVAdapter.SearchViewHolder>  {


     List<VideoItem> itemList;
    private Context mContext;



    public static class SearchViewHolder extends RecyclerView.ViewHolder{

         CardView cv;
         TextView videoTitle;
         TextView videoDescription;
         ImageView thumbNails;

        public SearchViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            videoTitle = (TextView)itemView.findViewById(R.id.video_title);
            videoDescription = (TextView)itemView.findViewById(R.id.video_description);
            thumbNails = (ImageView)itemView.findViewById(R.id.video_thumbnail);

        }

    }

    public VideoSearchRVAdapter(Context context, List<VideoItem> v){
        mContext = context;
        itemList = v;
    }

    @Override
    public VideoSearchRVAdapter.SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_item, parent, false);
        SearchViewHolder searchViewHolder = new SearchViewHolder(v);
        return searchViewHolder;
    }

    @Override
    public void onBindViewHolder(final VideoSearchRVAdapter.SearchViewHolder holder, final int position) {

        holder.videoDescription.setText(itemList.get(position).getDescription());
        holder.videoTitle.setText(itemList.get(position).getTitle());
        Picasso.with(mContext)
                .load(itemList.get(position).getThumbnailURL())
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error)
                .into(holder.thumbNails);
        holder.thumbNails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext.getApplicationContext(), PlayerActivity.class);
                intent.putExtra("VIDEO_ID", itemList.get(position).getvideoId());
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public int addItem(List<VideoItem> v){

        boolean firstNewItemGotten = false;
        int firstPositionINdex = 0;
        for(VideoItem vi : v){
            if(!checkId(vi.getvideoId())){
                int count = itemList.size();
                //getVideoItemList().add(count, vi);
                itemList.add(count, vi);
                notifyItemInserted(count);
                if(!firstNewItemGotten){
                    firstPositionINdex = count;
                }
                notifyItemRangeChanged(count, count);
            }
        }

        return firstPositionINdex;

    }

    public boolean checkId(String id){


        for(VideoItem v : itemList){
            if(id.equals(v.getId())){
                return true;
            }
        }

        return false;
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }
}
