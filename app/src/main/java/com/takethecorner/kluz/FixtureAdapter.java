package com.takethecorner.kluz;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by kluz on 11/27/16.
 */
public class FixtureAdapter extends RecyclerView.Adapter<FixtureAdapter.MyViewHolder> {

    private Context mContext;
    private List<Fixture> articleList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, awayteam,hometeam, matchday,time;
        public ImageView  overflow;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            hometeam = (TextView) view.findViewById(R.id.hometeam);
            awayteam = (TextView) view.findViewById(R.id.awayteam);
            matchday = (TextView) view.findViewById(R.id.matchday);
            time = (TextView) view.findViewById(R.id.textView3);
            overflow = (ImageView) view.findViewById(R.id.overflow);
        }
    }


    public FixtureAdapter(Context mContext, List<Fixture> articleList) {
        this.mContext = mContext;
        this.articleList = articleList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fixture_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final Fixture article = articleList.get(position);
        holder.title.setText(article.getLeague());
        holder.hometeam.setText(article.getHometeam());
        holder.awayteam.setText(article.getAwayteam());
        holder.matchday.setText(article.getDateCreated());
        holder.time.setText(article.getTime());

        // loading article cover using Glide library
        //Glide.with(mContext).load(article.getThumbnail()).centerCrop().crossFade().diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.thumbnail);

        holder.overflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenu(holder.overflow);


            }
        });


    }

    /**
     * Showing popup menu when tapping on 3 dots
     */
    private void showPopupMenu(View view) {
        // inflate menu
        PopupMenu popup = new PopupMenu(mContext, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_articles, popup.getMenu());
        popup.setOnMenuItemClickListener(new MyMenuItemClickListener());
        popup.show();
    }

    /**
     * Click listener for popup menu items
     */
    class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {

        public MyMenuItemClickListener() {
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.action_add_favourite:
                    Toast.makeText(mContext, "Share", Toast.LENGTH_SHORT).show();
                    return true;
                default:
            }
            return false;
        }
    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }
}

