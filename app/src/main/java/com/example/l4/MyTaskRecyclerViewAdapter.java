package com.example.l4;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.l4.TaskFragment.OnListFragmentInteractionListener;
import com.example.l4.tasks.TaskListContent.Task;

import java.util.List;

public class MyTaskRecyclerViewAdapter extends RecyclerView.Adapter<MyTaskRecyclerViewAdapter.ViewHolder> {

    private final List<Task> mValues;
    private final OnListFragmentInteractionListener mListener;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mContentView;
        public final ImageView mItemImageView;
        public Task mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mItemImageView = view.findViewById(R.id.item_image);
            mContentView = view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }


    public MyTaskRecyclerViewAdapter(List<Task> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_task, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Task task = mValues.get(position);
        holder.mItem = task;
        holder.mContentView.setText(task.title);

        final String picPath = task.picPath;
        Context context = holder.mView.getContext();
        if (picPath != null && !picPath.isEmpty()) {
            if (picPath.contains("drawable")) {
                Drawable taskDrawable;
                switch (picPath) {
                    case "drawable 2":
                        taskDrawable = context.getResources().getDrawable(R.drawable.circle_drawable_orange);
                        break;
                    case "drawable 3":
                        taskDrawable = context.getResources().getDrawable(R.drawable.circle_drawable_red);
                        break;
                    default:
                        taskDrawable = context.getResources().getDrawable(R.drawable.circle_drawable_green);
                }
                holder.mItemImageView.setImageDrawable(taskDrawable);
            }
        } else {
            holder.mItemImageView.setImageDrawable(context.getResources().getDrawable(R.drawable.circle_drawable_green));
        }

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.onListFragmentClickInteraction(holder.mItem, position);
                }
            }
        });
        holder.mView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                // Notify the active callbacks interface (the activity, if the
                // fragment is attached to one) that an item has been selected.
                mListener.onListFragmentLongClickInteraction(position);
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }


}
