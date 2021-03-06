package com.zz.CriminalIntent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import java.util.*;
/**
 * Created by zz on 2015/6/10.
 */
public class CrimeFragment  extends Fragment {
    private Crime mCrime;
    private EditText mEditText;
    private Button mDateButton;
    private CheckBox mSolvedChecBox;
    private static final String DIALOG_DATE = "date";
    public static final String EXTRA_CRIME_ID = "com.zz.CriminalItent.crime_id";
    public static final int REQUEST_DATE = 0;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID crimeID = (UUID)getArguments().getSerializable(EXTRA_CRIME_ID);
        mCrime = CrimeLab.get(getActivity()).getCrime(crimeID);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_crime,container,false);
        mEditText = (EditText)v.findViewById(R.id.crime_title);
        mEditText.setText(mCrime.getTitle());
        mEditText.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mCrime.setTitle(charSequence.toString());
            }

            public void afterTextChanged(Editable editable) {

            }
        });
        mDateButton = (Button)v.findViewById(R.id.crime_date);
        updateDate();
        mDateButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                FragmentManager fm = getActivity()
                        .getSupportFragmentManager();
                DatePickeFragment datePickeFragment = DatePickeFragment.newInstance(mCrime.getDate());
                datePickeFragment.setTargetFragment(CrimeFragment.this,REQUEST_DATE);
                datePickeFragment.show(fm, DIALOG_DATE);
            }
        });
        mSolvedChecBox = (CheckBox)v.findViewById(R.id.crime_solved);
        mSolvedChecBox.setChecked(mCrime.isSolved());
        mSolvedChecBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                mCrime.setSolved(b);
            }
        });
        return v;
    }
    public static CrimeFragment newInstance(UUID crimeId){
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_CRIME_ID, crimeId);

        CrimeFragment fragment = new CrimeFragment();
        fragment.setArguments(args);
        return fragment;
    }
    public void returnResult(){
        getActivity().setResult(Activity.RESULT_OK,null);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode!=Activity.RESULT_OK)return;
        if (requestCode == REQUEST_DATE){
            Date date = (Date)data.getSerializableExtra(DatePickeFragment.EXTRA_DATE);
            mCrime.setDate(date);
            updateDate();
        }
    }
    public void updateDate(){
        mDateButton.setText(mCrime.getDate().toString());
    }
}
