package com.example.ticketbooking;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.ticketbooking.model.Event;
import java.util.ArrayList;
import java.util.List;

// Adapter class for handling a list of events in a RecyclerView
class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.ViewHolder> {

    // List of events to display
    private List<Event> events;

    // Constructor to initialize the list of events, ensuring it is not null
    EventsAdapter(List<Event> events) {
        this.events = events != null ? events : new ArrayList<>();
    }

    // Creates new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

    // Inflate the custom layout for each item
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.eventscardview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    // Get the event at the events position
        Event event = events.get(position);

    //Picasso library to load images efficiently
        Picasso.get()
                .load(event.getImageUrl())
                .into(holder.imageView);

        holder.textView.setText(event.getDescription());
    }

    // Returns the size of the dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return events != null ? events.size() : 0;
    }

    // Updates the list of events and notifies the adapter to refresh
    public void setEvents(List<Event> eventsList) {
        events.clear();
        events.addAll(eventsList);
        notifyDataSetChanged();
    }

    // Provide a reference to the views for each data item
    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;

        ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image2);
            textView = itemView.findViewById(R.id.text2);
        }
    }
}
