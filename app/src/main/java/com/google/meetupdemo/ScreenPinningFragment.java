package com.google.meetupdemo;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

public class ScreenPinningFragment extends Fragment {

    private Switch mSwitch;

    private final static String SWITCH_IS_CHECKED = "switch_is_checked";

    public ScreenPinningFragment() {
        setArguments(new Bundle());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_screen_pinning, container, false);

        final View contentView = getActivity().findViewById(R.id.container);

        mSwitch = (Switch) view.findViewById(R.id.switchScreenPinning);
        mSwitch.setChecked(getArguments().getBoolean(SWITCH_IS_CHECKED));
        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    contentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE);

                    getActivity().startLockTask();
                } else {
                    contentView.setSystemUiVisibility(
                            View.SYSTEM_UI_FLAG_LAYOUT_STABLE);

                    getActivity().stopLockTask();
                }
            }
        });

        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        getArguments().putBoolean(SWITCH_IS_CHECKED, mSwitch.isChecked());
    }

    @Override
    public void onResume() {
        super.onResume();
        mSwitch.setChecked(getArguments().getBoolean(SWITCH_IS_CHECKED));
    }
}
