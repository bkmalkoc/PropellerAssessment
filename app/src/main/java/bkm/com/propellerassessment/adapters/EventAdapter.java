package bkm.com.propellerassessment.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

import bkm.com.propellerassessment.R;
import bkm.com.propellerassessment.model.Event;
import butterknife.BindView;
import butterknife.ButterKnife;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {

    Context mContext;
    List<Event> mUserEvents;

    public EventAdapter(Context context, List<Event> userEvents) {
        this.mContext = context;
        this.mUserEvents = userEvents;
        sortData(mUserEvents);
        setHasStableIds(true);
    }

    @NonNull
    @Override
    public EventAdapter.EventViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.event_view_holder_layout, viewGroup, false);

        return new EventViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull EventAdapter.EventViewHolder eventViewHolder, int i) {
        Event event = mUserEvents.get(i);
        eventViewHolder.idView.setText(String.valueOf(event.getId()));
        eventViewHolder.nameView.setText(String.valueOf(event.getMedication()));
        eventViewHolder.typeView.setText(String.valueOf(event.getMedicationtype()));
        eventViewHolder.timeView.setText(String.valueOf(event.getDatetime()));
    }

    @Override
    public long getItemId(int position) {
        return mUserEvents.get(position).getId();
    }

    public void insert(Event data) {
        int position = mUserEvents.size() + 1;
        data.setId(position);
        mUserEvents.add(data);
        sortData(mUserEvents);
        notifyItemInserted(position);
    }

    public void sortData(List<Event> userEvents) {
        Collections.sort(userEvents, new Comparator<Event>() {
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault());

            @Override
            public int compare(Event lhs, Event rhs) {
                try {
                    return format.parse(rhs.getDatetime()).compareTo(format.parse(lhs.getDatetime()));
                } catch (ParseException e) {
                    throw new IllegalArgumentException(e);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mUserEvents.size();
    }

    public static class EventViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.idView)
        TextView idView;
        @BindView(R.id.nameView)
        TextView nameView;
        @BindView(R.id.typeView)
        TextView typeView;
        @BindView(R.id.timeView)
        TextView timeView;

        public EventViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
