package com.example.evgeniy.week7.t2;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.evgeniy.week7.R;
import com.example.evgeniy.week7.t2.dummy.DummyContent;
import com.example.evgeniy.week7.t2.dummy.DummyContent.DummyItem;

import java.util.List;


public class RVFragment extends Fragment implements MyItemRecyclerViewAdapter.OnCheckBoxChangListener {


    public static RVFragment newInstance() {
        return new RVFragment();
    }

    private OnListFragmentInteractionListener mListener;

    private MyItemRecyclerViewAdapter myItemRecyclerViewAdapter;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_item_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));

            myItemRecyclerViewAdapter = new MyItemRecyclerViewAdapter(DummyContent.ITEMS, this);
            recyclerView.setAdapter(myItemRecyclerViewAdapter);
            ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
                @Override
                public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                    return false;
                }

                @Override
                public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                    int pos = viewHolder.getAdapterPosition();
                    myItemRecyclerViewAdapter.removeItem(pos);
                }
            };

            new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if ((context instanceof OnListFragmentInteractionListener)) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    private boolean moveDownOne() {
        List<DummyItem> items = myItemRecyclerViewAdapter.getValues();
        for (int i = 0; i < myItemRecyclerViewAdapter.getItemCount(); i++) {
            DummyItem item = items.get(i);
            if (item.isChecked()) {
                myItemRecyclerViewAdapter.moveDown(i, item);
                return true;
            }
        }
        return false;
    }

    void moveDown() {
        while (true) {
            if (!(moveDownOne())) {
                break;
            }
        }
        onChange();
    }

    @Override
    public void onChange() {
        if (mListener != null) {
            for (DummyItem dummyItem : myItemRecyclerViewAdapter.getValues()) {
                if (dummyItem.isChecked()) {
                    mListener.onShowButton();
                    return;
                }
            }
            mListener.onHideButton();
        }
    }


    public interface OnListFragmentInteractionListener {
        void onShowButton();

        void onHideButton();
    }
}
