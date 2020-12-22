package com.example.dialer.ui.home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import com.example.dialer.R;
import com.example.dialer.ui.dashboard.DashboardFragment;
import org.greenrobot.eventbus.EventBus;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    EditText et;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);


        et = (EditText) root.findViewById(R.id.edit_text_1);

        Button button0 = root.findViewById(R.id.Button0);
        Button button1 = root.findViewById(R.id.Button1);
        Button button2 = root.findViewById(R.id.Button2);
        Button button3 = root.findViewById(R.id.Button3);
        Button button4 = root.findViewById(R.id.Button4);
        Button button5 = root.findViewById(R.id.Button5);
        Button button6 = root.findViewById(R.id.Button6);
        Button button7 = root.findViewById(R.id.Button7);
        Button button8 = root.findViewById(R.id.Button8);
        Button button9 = root.findViewById(R.id.Button9);
        ImageButton buttonCall = root.findViewById(R.id.ButtonCall);
        ImageButton buttonDel = root.findViewById(R.id.ButtonDel);
        button0.setOnClickListener(listener);
        button1.setOnClickListener(listener);
        button2.setOnClickListener(listener);
        button3.setOnClickListener(listener);
        button4.setOnClickListener(listener);
        button5.setOnClickListener(listener);
        button6.setOnClickListener(listener);
        button7.setOnClickListener(listener);
        button8.setOnClickListener(listener);
        button9.setOnClickListener(listener);
        buttonCall.setOnClickListener(listener1);
        buttonDel.setOnClickListener(listener2);
        return root;
    }


    Button.OnClickListener listener = new Button.OnClickListener(){//创建监听对象
        public void onClick(View view){
            String str = et.getText().toString();
            str += view.getTag().toString();
            et.setText(str);
        }

    };
    Button.OnClickListener listener1 = new Button.OnClickListener(){//创建监听对象
        public void onClick(View view){
            String str = et.getText().toString();
            if((str != null) && !str.trim().equals("")) {

                EventBus.getDefault().postSticky(new DashboardFragment.Event(str));

                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + str));
                startActivity(intent);

            }else{
                Toast.makeText(getActivity().getApplicationContext(),"请点击拨号按钮", Toast.LENGTH_LONG).show();
            }
        }
    };
    ImageButton.OnClickListener listener2 = new Button.OnClickListener(){//创建监听对象
        public void onClick(View view){
            String str = et.getText().toString();
            if((str != null) && !str.trim().equals("")){
                str = str.substring(0,str.length() - 1);
                et.setText(str);
            }
        }
    };

//    public class Event {
//
//        public String data;
//
//        public Event(String data) {
//            this.data= data;
//        }
//
//        public String getData() {
//            return data;
//        }
//
//        public void setData(String data) {
//            this.data= data;
//        }
//
//    }

}