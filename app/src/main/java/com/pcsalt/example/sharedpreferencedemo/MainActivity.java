package com.pcsalt.example.sharedpreferencedemo;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.pcsalt.example.sharedpreferencedemo.listener.RecyclerClickListener;
import com.pcsalt.example.sharedpreferencedemo.listener.RecyclerTouchListener;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Context mContext = MainActivity.this;
    private EditText mEditTextKey;
    private EditText mEditTextValue;
    private List<PrefData> mPrefDataList;
    private PrefAdapter mPrefAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        mEditTextKey = (EditText) findViewById(R.id.edit_text_key);
        mEditTextValue = (EditText) findViewById(R.id.edit_text_value);
        Button buttonSave = (Button) findViewById(R.id.button_save);
        buttonSave.setOnClickListener(mOnClickListener);

        RecyclerView recyclerViewPrefValues = (RecyclerView) findViewById(R.id.recycler_view_pref_values);
        recyclerViewPrefValues.setLayoutManager(new LinearLayoutManager(mContext));
        mPrefDataList = PrefUtil.getAllValues(mContext);
        mPrefAdapter = new PrefAdapter(mContext, mPrefDataList);
        recyclerViewPrefValues.setAdapter(mPrefAdapter);
        RecyclerTouchListener recyclerTouchListener = new RecyclerTouchListener(mContext, recyclerViewPrefValues, new RecyclerClickListener() {
            @Override
            public void onClick(View view, int position) {
                PrefData prefData = mPrefDataList.get(position);
                PrefUtil.removeString(mContext, prefData.key);
                mPrefDataList.remove(position);
                mPrefAdapter.notifyItemRemoved(position);
            }
        });
        recyclerViewPrefValues.addOnItemTouchListener(recyclerTouchListener);
    }

    private void saveValueInPref() {
        String key = Utility.getText(mEditTextKey);
        String value = Utility.getText(mEditTextValue);
        if (key.isEmpty()) {
            mEditTextKey.setError("Enter key");
        } else if (value.isEmpty()) {
            mEditTextValue.setError("Enter value");
        } else {
            PrefUtil.saveString(mContext, key, value);
            mEditTextKey.setText("");
            mEditTextValue.setText("");
            PrefData newPrefData = new PrefData(key, value);
            if (mPrefDataList.contains(newPrefData)) {
                int index = mPrefDataList.indexOf(newPrefData);
                mPrefDataList.remove(index);
                mPrefAdapter.notifyItemRemoved(index);
            }
            mPrefDataList.add(newPrefData);
            int position = mPrefAdapter.getItemCount() - 1;
            mPrefAdapter.notifyItemInserted(position);
        }
    }

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.button_save:
                    saveValueInPref();
                    break;
            }
        }
    };
}
