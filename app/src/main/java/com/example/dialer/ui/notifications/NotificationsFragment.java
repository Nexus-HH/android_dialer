package com.example.dialer.ui.notifications;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import com.example.dialer.R;
import com.example.dialer.ui.dashboard.DashboardFragment;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;
    private TableLayout tableLayout;
    private Button button;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel = ViewModelProviders.of(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);

        tableLayout = root.findViewById(R.id.tableLayout);
        button = root.findViewById(R.id.button_del_history);


        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }


        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //historys.clear();
                tableLayout.removeAllViews();
            }
        });

        return root;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void onEvent(DashboardFragment.Event d) {
        DateFormat df1 = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.CHINA);
        DateFormat df2 = DateFormat.getTimeInstance(DateFormat.MEDIUM, Locale.CHINA);
        String date = df1.format(new Date());
        String time = df2.format(new Date());

        TableRow row = new TableRow(getActivity().getApplicationContext());
        TextView t1 = new TextView(getActivity().getApplicationContext());
        TextView t2 = new TextView(getActivity().getApplicationContext());

        t1.setText(d.getData()+"  ");
        t1.setTextAppearance(R.style.TextView);
        t2.setText(date+" "+time);
        t2.setTextAppearance(R.style.TextView);

        row.addView(t1);
        row.addView(t2);
        tableLayout.addView(row);

    }


}
