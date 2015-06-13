package com.zz.CriminalIntent;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.DatePicker;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;




/**
 * Created by zz on 2015/6/13.
 */
public class DatePickeFragment extends DialogFragment {
    public static final String EXTRA_DATE = "com.zz.CriminalIntent.date";
    private Date mDate;
    public static DatePickeFragment newInstance(Date date){
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_DATE, date);

        DatePickeFragment fragment = new DatePickeFragment();
        fragment.setArguments(args);

        return fragment;
    }
    private void sendResult(int resultCode){
        if (getTargetFragment()==null){
            return;
        }
        Intent i = new Intent();
        i.putExtra(EXTRA_DATE,mDate);
        getTargetFragment().onActivityResult(getTargetRequestCode(),resultCode,i);
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        mDate = (Date)getArguments().getSerializable(EXTRA_DATE);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(mDate);
        int year = calendar.get(Calendar.YEAR);
         int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        View view = getActivity().getLayoutInflater().inflate(R.layout.dailog_crime, null);
        DatePicker d = (DatePicker) view.findViewById(R.id.dialog_date_datePicker);
        d.init(year, month, day, new DatePicker.OnDateChangedListener() {
            public void onDateChanged(DatePicker datePicker, int year, int month, int day) {
                mDate = new GregorianCalendar(year, month, day).getTime();
                getArguments().putSerializable(EXTRA_DATE, mDate);
            }
        });
        return new AlertDialog.Builder(getActivity()).setTitle(R.string.date_picker_title)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        sendResult(Activity.RESULT_OK);
                    }
                }).setView(view).create();
    }

}
