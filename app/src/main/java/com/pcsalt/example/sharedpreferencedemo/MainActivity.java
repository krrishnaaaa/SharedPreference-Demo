package com.pcsalt.example.sharedpreferencedemo;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private Context mContext = MainActivity.this;
    private EditText mEditTextKey;
    private EditText mEditTextValue;

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
