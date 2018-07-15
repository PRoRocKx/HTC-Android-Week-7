package com.example.evgeniy.week7.t1;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.evgeniy.week7.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ItemFragment extends Fragment {
    private static final String NAME = "name";

    private String name;

    @BindView(R.id.name)
    TextView tvName;
    @BindView(R.id.edit_text)
    TextView tvEditText;
    @BindView(R.id.spinner)
    TextView tvSpinner;
    @BindView(R.id.tv_switch)
    TextView tvSwitch;

    private Unbinder unbinder;


    public static ItemFragment newInstance(String name) {
        ItemFragment fragment = new ItemFragment();
        Bundle args = new Bundle();
        args.putString(NAME, name);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            name = getArguments().getString(NAME);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item, container, false);
        unbinder = ButterKnife.bind(this, view);
        tvName.setText(name);
        return view;
    }

    public void setData(String text, String textSpinner, String textSwitch) {
        tvEditText.setText(text);
        tvSpinner.setText(textSpinner);
        tvSwitch.setText(textSwitch);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }
}
