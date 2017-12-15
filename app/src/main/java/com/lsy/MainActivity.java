package com.lsy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.TimeUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private EditText etBj , etLl , etDate;
    private Button btnCal;
    private TextView tvOne , tvTwo , tvThree , tvFour;
    private LinearLayout mLayout;
    private Date dateOne , dateTwo , dateThree , dateFour , dateNow;
    private static final java.text.DateFormat DEFAULT_FORMAT = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etBj = (EditText) findViewById(R.id.main_et_bj);
        etLl = (EditText) findViewById(R.id.main_et_ll);
        etDate = (EditText) findViewById(R.id.main_et_date);
        btnCal = (Button) findViewById(R.id.main_btn_cal);
        mLayout = (LinearLayout) findViewById(R.id.main_ll_jieguo);
        tvOne = (TextView) findViewById(R.id.main_tv_one);
        tvTwo = (TextView) findViewById(R.id.main_tv_two);
        tvThree = (TextView) findViewById(R.id.main_tv_three);
        tvFour = (TextView) findViewById(R.id.main_tv_four);

        dateOne = TimeUtils.string2Date("20180320", DEFAULT_FORMAT);
        dateTwo = TimeUtils.string2Date("20180620", DEFAULT_FORMAT);
        dateThree = TimeUtils.string2Date("20180920", DEFAULT_FORMAT);
        dateFour = TimeUtils.string2Date("20181220", DEFAULT_FORMAT);

        btnCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = etDate.getText().toString().trim();
                String bj = etBj.getText().toString().trim();
                String ll = etLl.getText().toString().trim();
                String ss = "20171230";
                if (!TextUtils.isEmpty(s) && s.length() == ss.length()) {
                    mLayout.setVisibility(View.VISIBLE);
                    dateNow = TimeUtils.string2Date(etDate.getText().toString().trim(), DEFAULT_FORMAT);
                    //LogUtils.e(getGapCount(TimeUtils.string2Date(etDate.getText().toString().trim(), DEFAULT_FORMAT) , TimeUtils.string2Date("20180320", DEFAULT_FORMAT)));

                    tvOne.setText(getJieguo(bj , ll , getGapCount(dateNow , dateOne)));
                    tvTwo.setText(getJieguo(bj , ll , getGapCount(dateOne , dateTwo)));
                    tvThree.setText(getJieguo(bj , ll , getGapCount(dateTwo , dateThree)));
                    tvFour.setText(getJieguo(bj , ll , getGapCount(dateThree , dateFour)));
                }
            }
        });

    }

    /**
     * 获取两个日期之间的间隔天数
     * @return
     */
    public static int getGapCount(Date startDate, Date endDate) {
        Calendar fromCalendar = Calendar.getInstance();
        fromCalendar.setTime(startDate);
        fromCalendar.set(Calendar.HOUR_OF_DAY, 0);
        fromCalendar.set(Calendar.MINUTE, 0);
        fromCalendar.set(Calendar.SECOND, 0);
        fromCalendar.set(Calendar.MILLISECOND, 0);

        Calendar toCalendar = Calendar.getInstance();
        toCalendar.setTime(endDate);
        toCalendar.set(Calendar.HOUR_OF_DAY, 0);
        toCalendar.set(Calendar.MINUTE, 0);
        toCalendar.set(Calendar.SECOND, 0);
        toCalendar.set(Calendar.MILLISECOND, 0);

        return (int) ((toCalendar.getTime().getTime() - fromCalendar.getTime().getTime()) / (1000 * 60 * 60 * 24));
    }

    public String getJieguo(String bj , String ll , int days){
        float bjf = Float.parseFloat(bj);
        float llf = Float.parseFloat(ll);
        float f = bjf * llf / 360 * days;
        return String.valueOf(f);
    }
}
