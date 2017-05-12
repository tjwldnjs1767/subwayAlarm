package subway.subwayalarm.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.List;

import subway.subwayalarm.R;
import subway.subwayalarm.data.SearchHistoryItem;


public class SearchHistoryAdapter extends RecyclerView.Adapter<SearchHistoryAdapter.ViewHolder> {

    private Context context;
    private List<SearchHistoryItem> items;
    int itemLayout;

    public SearchHistoryAdapter(Context context, List<SearchHistoryItem> items, int itemLayout) {
        this.context = context;
        this.items = items;
        this.itemLayout = itemLayout;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_search_history, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final SearchHistoryItem item = items.get(position);
        final Drawable drawable = ContextCompat.getDrawable(context, item.getBookMarkImg());
        holder.bookMarkBtn.setBackground(drawable);
        holder.stationName.setText(item.getStationName());
        holder.trainLineInfo.setText(item.getTrainLineInfo());

        holder.bookMarkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Drawable bookMarkOnBtn = ContextCompat.getDrawable(context, R.drawable.bookmark_on_btn);
                Drawable bookMarkOffBtn = ContextCompat.getDrawable(context, R.drawable.bookmark_off_btn);

                if (holder.bookMarkBtn.isChecked()) {
                    holder.bookMarkBtn.setBackground(bookMarkOnBtn);

                }
                else
                    holder.bookMarkBtn.setBackground(bookMarkOffBtn);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ToggleButton bookMarkBtn;
        TextView stationName;
        TextView trainLineInfo;
        CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            bookMarkBtn = (ToggleButton) itemView.findViewById(R.id.bookmark_btn);
            stationName = (TextView) itemView.findViewById(R.id.station_name);
            trainLineInfo = (TextView) itemView.findViewById(R.id.train_line_info);
            cardView = (CardView) itemView.findViewById(R.id.card_view);
        }
    }
}