package com.google.meetupdemo;


import android.app.Fragment;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class EnhancedNotificationsFragment extends Fragment implements View.OnClickListener {

    private NotificationManager mManager;
    private NotificationCompat.Builder mNotifBuilder;
    private NotificationCompat.Builder mEnhancedNotifBuilder;

    private final static int NOTIF_ID = 001;
    private final static int NOTIF_ENHANCED_ID = 002;

    public EnhancedNotificationsFragment() {}

    @Override
    public void onResume() {
        super.onResume();
        mManager = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);

        mNotifBuilder = new NotificationCompat.Builder(getActivity())
                .setSmallIcon(R.drawable.ic_notify_gsa)
                .setContentTitle("Demo notification")
                .setContentText("This is a standard notification")
                .setColor(getResources().getColor(R.color.teal_500));

        mEnhancedNotifBuilder = new NotificationCompat.Builder(getActivity())
                .setSmallIcon(R.drawable.ic_notify_gsa)
                .setContentTitle("Heads-Up notification")
                .setContentText("This is an important notification!")
                .setPriority(Notification.PRIORITY_MAX)
                .setDefaults(Notification.DEFAULT_VIBRATE)
                .setColor(getResources().getColor(R.color.teal_500));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_enhanced_notifications, container, false);

        Button showNotificationButton = (Button) view.findViewById(R.id.showNotificationButton);
        showNotificationButton.setOnClickListener(this);

        Button showHeadsUpNotificationButton = (Button) view.findViewById(R.id.showHeadsUpNotificationButton);
        showHeadsUpNotificationButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.showNotificationButton:
                showNotification();
                break;
            case R.id.showHeadsUpNotificationButton:
                showHeadsUpNotification();
                break;
        }
    }

    private void showNotification() {
        mManager.notify(NOTIF_ID,mNotifBuilder.build());
    }

    private void showHeadsUpNotification() {
        mManager.notify(NOTIF_ENHANCED_ID,mEnhancedNotifBuilder.build());
    }

}
