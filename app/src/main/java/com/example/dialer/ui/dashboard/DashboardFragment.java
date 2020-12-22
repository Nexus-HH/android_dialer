package com.example.dialer.ui.dashboard;

import android.content.Intent;
import android.net.Uri;
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
import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

public class DashboardFragment extends Fragment {

    private Button getContact;
    private TableLayout getTable;
    private ArrayList<MyContacts> contacts;
    private DashboardViewModel dashboardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel = ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        getContact = root.findViewById(R.id.button);
        getTable = root.findViewById(R.id.container);



        contacts = ContactUtils.getAllContacts(getActivity().getApplicationContext());
        getTable.removeAllViews();
        showContacts();

        initView();

        return root;
    }
    private void initView() {
        getContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getTable.removeAllViews();
                showContacts();
            }
        });
    }


    private void showContacts() {
        contacts = ContactUtils.getAllContacts(getActivity().getApplicationContext());
        for(int i=0;i<contacts.size();i++){
            TableRow row = new TableRow(getActivity().getApplicationContext());
            TextView t1 = new TextView(getActivity().getApplicationContext());
            TextView t2 = new TextView(getActivity().getApplicationContext());
            Button b1 = new Button(getActivity().getApplicationContext());
            t1.setText(contacts.get(i).name);
            t1.setTextAppearance(R.style.TextView);
            t2.setText(contacts.get(i).phone);
            t2.setTextAppearance(R.style.TextView);
            b1.setText("呼叫");
            b1.setId(i);
            final int finalI = i;
            b1.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){

                    EventBus.getDefault().postSticky(new Event(contacts.get(finalI).phone));

                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + contacts.get(finalI).phone));
                    startActivity(intent);

                }
            });

            row.addView(t1);
            row.addView(t2);
            row.addView(b1);
            getTable.addView(row);
        }
    }



    public static class Event {

        public String data;

        public Event(String data) {
            this.data= data;
        }

        public String getData() {
            return data;
        }

    }

}