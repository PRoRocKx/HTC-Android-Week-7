package com.example.evgeniy.week7.t2;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.evgeniy.week7.R;
import com.example.evgeniy.week7.t2.dummy.DummyContent.DummyItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder> {


    private final List<DummyItem> mValues;
    private final OnCheckBoxChangListener mListener;

    private static final int LEFT_VIEW = 1;
    private static final int RIGHT_VIEW = 2;

    MyItemRecyclerViewAdapter(List<DummyItem> items, OnCheckBoxChangListener listener) {
        mValues = items;
        mListener = listener;
    }

    public List<DummyItem> getValues() {
        return mValues;
    }

    public void moveDown(int pos, DummyItem item) {
        mValues.remove(item);
        mValues.add(item);
        notifyItemMoved(pos, mValues.size() - 1);
        item.setChecked(false);
        notifyItemChanged(mValues.size() - 1);
    }

    public void removeItem(int pos) {
        mValues.remove(pos);
        notifyItemRemoved(pos);
        notifyItemRangeChanged(pos, getItemCount());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == LEFT_VIEW) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.fragment_item_left, parent, false);
        } else {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.fragment_item_right, parent, false);
        }
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        holder.bindView(mValues.get(position));
    }


    @Override
    public int getItemViewType(int position) {
        return position % 2 == 0 ? LEFT_VIEW : RIGHT_VIEW;
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.name)
        TextView mName;
        @BindView(R.id.lname)
        TextView mLName;
        @BindView(R.id.phone)
        TextView mPhone;
        @BindView(R.id.view)
        View mViewColor;
        @BindView(R.id.checkBox)
        CheckBox mCheckBox;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        void bindView(DummyItem item) {
            mName.setText(item.getName());
            mLName.setText(item.getLname());
            mPhone.setText(item.getPhone());
            mViewColor.setBackgroundColor(item.getColor());
            mCheckBox.setChecked(item.isChecked());
            mCheckBox.setOnCheckedChangeListener((cb, b) -> {
                item.setChecked(b);
                if (null != mListener) {
                    mListener.onChange();
                }
            });
        }

    }

    public interface OnCheckBoxChangListener {
        void onChange();
    }
}
