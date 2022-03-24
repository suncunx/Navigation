package com.scx.navigation.navigationui;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.scx.navigation.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDeepLinkBuilder;

public class BFragment extends Fragment {
    private static final String TAG = "BFragment";

//    @Override
//    public void onAttach(@NonNull Context context) {
//        super.onAttach(context);
//        Log.d(TAG, "onAttach: ");
//    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: " + this + ", arguments = " + getArguments());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: " + this);
    }

//    @Override
//    public void onDetach() {
//        super.onDetach();
//        Log.d(TAG, "onDetach: ");
//    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_b, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView textView = view.findViewById(R.id.tv_from_who);
//        textView.setOnClickListener(v -> NavHostFragment.findNavController(this).navigate(BFragmentDirections.actionBFragmentSelf("B").setShowFromWho(true)));
        textView.setOnClickListener(v -> createNotification());
//        textView.setOnClickListener(v -> NavHostFragment.findNavController(this).navigate(R.id.eFragment));

    }

    @Override
    public void onResume() {
        super.onResume();

    }

    private void createNotification() {
        NotificationManager nm = (NotificationManager) getContext().getSystemService(Context.NOTIFICATION_SERVICE);


        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("media", "media notify", NotificationManager.IMPORTANCE_HIGH);
            nm.createNotificationChannel(channel);
        }


        //构造器需要传入一个context对象。
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getContext(), "media");
        builder.setSmallIcon(getNotificationIcon());//最为重要的一个参数，如果不设置，通知不会出现在状态栏中。
        builder.setTicker("有新消息！！！");
        builder.setLargeIcon(BitmapFactory.decodeResource(getContext().getResources(), R.drawable.ic_launcher_foreground));//设置状态栏下拉后显示的图标
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Log.d("Notification", "createNotification: setGroupSummary true");
            builder
                    .setGroupSummary(true)
                    .setGroup("group");
        }
        builder.setContentTitle("contentTitle");
        builder.setContentText("content");


        Bundle bundle = new Bundle();
        bundle.putString("fromWho", "B");
        bundle.putBoolean("showFromWho", true);
        PendingIntent pendingIntent = new NavDeepLinkBuilder(getContext().getApplicationContext())
                .setGraph(R.navigation.nav_graph)
                .setDestination(R.id.bFragment)
                .addDestination(R.id.cFragment)
                .setArguments(bundle)
                .createPendingIntent();
        builder.setContentIntent(pendingIntent);

        builder.setAutoCancel(true);//点击通知后，通知消失
        builder.setDefaults(Notification.DEFAULT_LIGHTS);
        nm.cancel(66);
        nm.notify(66, builder.build());
    }

    public static int getNotificationIcon() {
        boolean useWhiteIcon = (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP);
        return useWhiteIcon ? R.drawable.ic_launcher_foreground : R.mipmap.ic_launcher_round;
    }
}
