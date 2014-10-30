package com.google.meetupdemo;


import android.app.ActivityOptions;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

public class SharedElementsFragment extends Fragment {

    private View mSharedLayout;
    private View mSharedButton;

    public SharedElementsFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shared_elements, container, false);
        mSharedLayout = view.findViewById(R.id.sharedLayout);
        mSharedButton = view.findViewById(R.id.sharedButton);
        mSharedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedButtonOnClick();
            }
        });

        return view;
    }

    private void sharedButtonOnClick() {
        Intent intent = new Intent(getActivity(), SharedElementsActivity.class);
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(getActivity(),
                        Pair.create(mSharedButton, getResources().getString(R.string.shared_fab)),
                        Pair.create(mSharedLayout, getResources().getString(R.string.shared_layout))
                        );
        startActivity(intent, options.toBundle());
    }

}
