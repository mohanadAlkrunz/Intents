package com.example.todolist;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ToDoListAdapter extends BaseAdapter {

    private static final String TAG = ToDoListAdapter.class.getSimpleName();
    private final List<ToDoItem> mItems = new ArrayList<>();
    private final Context mContext;
    public interface setOnCheckedChangeListener{
       void onCheckedChanged();
    }

    public ToDoListAdapter(Context context) {
        mContext = context;
    }

    // Add a ToDoItem to the adapter
    // Notify observers that the data set has changed
    public void add(ToDoItem item) {
        mItems.add(item);
        notifyDataSetChanged();
    }

    // Clears the list adapter of all items.
    public void clear() {
        mItems.clear();
        notifyDataSetChanged();
    }

    // Returns the number of ToDoItems
    @Override
    public int getCount() {
        return mItems.size();
    }

    // Retrieve the number of ToDoItems
    @Override
    public Object getItem(int pos) {
        return mItems.get(pos);
    }

    // Get the ID for the ToDoItem
    // In this case it's just the position

    @Override
    public long getItemId(int pos) {
        return pos;
    }

    // Create a View for the ToDoItem at specified position
    // Remember to check whether convertView holds an already allocated View
    // before created a new View.
    // Consider using the ViewHolder pattern to make scrolling more efficient
    // See:
    // http://developer.android.com/training/improving-layouts/smooth-scrolling.html

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // TODO - Get the current ToDoItem
        final ToDoItem toDoItem = (ToDoItem) getItem(position);
        final ViewHolder viewHolder;

        if (convertView == null) {

            // TODO - Inflate the View for this ToDoItem from todo_item.xml
            convertView = LayoutInflater.from(mContext).inflate(R.layout.todo_item,null);


            // TODO - Fill in specific ToDoItem data
            // Remember that the data that goes in this View
            // corresponds to the user interface elements defined
            // in the layout file

            viewHolder = new ViewHolder();
            viewHolder.titleView = convertView.findViewById(R.id.titleView);
            viewHolder.statusView = convertView.findViewById(R.id.status);;
            viewHolder.priorityView = convertView.findViewById(R.id.priority);;
            viewHolder.dateView = convertView.findViewById(R.id.dateView);;

            // TODO - set up an OnCheckedChangeListener, which is called when the user toggles the status checkbox
            final CheckBox statusView = convertView.findViewById(R.id.statusCheckBox);
            statusView.setChecked(ToDoItem.Status.DONE.equals(toDoItem.getStatus()));
            statusView.setOnCheckedChangeListener(new OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView,
                                             boolean isChecked) {

                    toDoItem.setStatus(isChecked ? ToDoItem.Status.DONE : ToDoItem.Status.NOT_DONE);


                }
            });
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }


        // TODO - Display Title in TextView
        final TextView titleView = convertView.findViewById(R.id.titleView);
        titleView.setText(toDoItem.getTitle());

        // TODO - Set up Status CheckBox
        final CheckBox statusView = convertView.findViewById(R.id.statusCheckBox);
        statusView.setChecked(ToDoItem.Status.DONE.equals(toDoItem.getStatus()));

        // TODO - Display Priority in a TextView
        final TextView priorityView =  convertView.findViewById(R.id.priorityView);
        priorityView.setText(toDoItem.getPriority().toString());

        // TODO - Display Time and Date.
        // Hint - use ToDoItem.FORMAT.format(toDoItem.getDate()) to get date and
        // time String
        final TextView dateView = convertView.findViewById(R.id.dateView);
        dateView.setText(ToDoItem.FORMAT.format(toDoItem.getDate()));


        // Return the View you just created
        return convertView;

    }

    static class ViewHolder {
        TextView titleView;
        CheckBox statusView;
        TextView priorityView;
        TextView dateView;
    }

}
