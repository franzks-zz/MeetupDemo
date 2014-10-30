package com.google.meetupdemo;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.ImageView;

public class RevealEffectFragment extends Fragment {

    private ImageView mGsaImage;
    private boolean revealed = false;

    public RevealEffectFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reveal_effect, container, false);

        mGsaImage = (ImageView) view.findViewById(R.id.gsaImage);
        view.findViewById(R.id.revealButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                revealButtonOnClick();
            }
        });

        return view;
    }

    private void revealButtonOnClick() {
        Animator animator = null;

        int cx = (mGsaImage.getLeft() + mGsaImage.getRight()) / 2;
        int cy = (mGsaImage.getTop() + mGsaImage.getBottom()) / 2;
        int radius = mGsaImage.getWidth();

        if (revealed) {
            animator = ViewAnimationUtils.createCircularReveal(mGsaImage, cx-cx/2, cy-cy/2, radius, 0);
            animator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    mGsaImage.setVisibility(View.INVISIBLE);
                }
            });
            revealed = false;
        } else {
            animator = ViewAnimationUtils.createCircularReveal(mGsaImage, cx-cx/2, cy-cy/2, 0, radius);
            animator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationStart(Animator animation) {
                    super.onAnimationEnd(animation);
                    mGsaImage.setVisibility(View.VISIBLE);
                }
            });
            revealed = true;
        }

        animator.start();
    }

}
