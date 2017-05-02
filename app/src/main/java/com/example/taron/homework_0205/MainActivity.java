package com.example.taron.homework_0205;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener,View.OnClickListener {

    TextView progressBarMaxValue;
    TextView getProgress;
    TextView seekBarMaxValue;
    TextView getSeekbar;

    ProgressBar progressBar;
    SeekBar seekBar;

    CheckBox checkBox;

    ToggleButton btn_2x;
    ToggleButton btn_4x;
    ToggleButton btn_6x;
    ToggleButton btn_8x;

    RadioGroup radGroup;

    RadioButton radBtn_divBy2;
    RadioButton radBtn_divBy4;
    RadioButton radBtn_divBy8;

    Button clearRadios;

    Switch switch_add10;
    Switch switch_add20;
    Switch switch_add30;

    SharedPreferences sharedPreferencesProgressBar;
    SharedPreferences sharedPreferencesSeekBar;
    SharedPreferences sharedPreferencesCheckbox;
    SharedPreferences sharedPreferencesToggleButton1;
    SharedPreferences sharedPreferencesToggleButton2;
    SharedPreferences sharedPreferencesToggleButton3;
    SharedPreferences sharedPreferencesToggleButton4;
    SharedPreferences sharedPreferencesRadioButton1;
    SharedPreferences sharedPreferencesRadioButton2;
    SharedPreferences sharedPreferencesRadioButton3;
    SharedPreferences sharedPreferencesSwitch1;
    SharedPreferences sharedPreferencesSwitch2;
    SharedPreferences sharedPreferencesSwitch3;


    final String PROGRESS_VALUE = "Progress bar value";
    final String SEEK_VALUE = "Seek bar value";
    final String CHECKBOX_STATE = "CheckBox state";
    final String TOGGLRBTN1_STATE = "Toggle button1 state";
    final String TOGGLRBTN2_STATE = "Toggle button2 state";
    final String TOGGLRBTN3_STATE = "Toggle button3 state";
    final String TOGGLRBTN4_STATE = "Toggle button4 state";
    final String RADIOBTN1_STATE = "Radio button1 state";
    final String RADIOBTN2_STATE = "Radio button2 state";
    final String RADIOBTN3_STATE = "Radio button3 state";
    final String SWITCH_1 = "Switch1 state";
    final String SWITCH_2 = "Switch2 state";
    final String SWITCH_3 = "Switch3 state";


    int pressingCount;

    boolean checked_2x;
    boolean checked_4x;
    boolean checked_6x;
    boolean checked_8x;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();

        sharedPreferencesProgressBar = getPreferences(MODE_PRIVATE);
        progressBar.setProgress(sharedPreferencesProgressBar.getInt(PROGRESS_VALUE, 0));

        sharedPreferencesSeekBar = getPreferences(MODE_PRIVATE);
        seekBar.setProgress(sharedPreferencesSeekBar.getInt(SEEK_VALUE,0));

        sharedPreferencesCheckbox = getPreferences(MODE_PRIVATE);
        checkBox.setChecked(sharedPreferencesCheckbox.getBoolean(CHECKBOX_STATE,false));

        sharedPreferencesToggleButton1 = getPreferences(MODE_PRIVATE);
        btn_2x.setChecked(sharedPreferencesToggleButton1.getBoolean(TOGGLRBTN1_STATE,false));

        sharedPreferencesToggleButton2 = getPreferences(MODE_PRIVATE);
        btn_4x.setChecked(sharedPreferencesToggleButton2.getBoolean(TOGGLRBTN2_STATE,false));

        sharedPreferencesToggleButton3 = getPreferences(MODE_PRIVATE);
        btn_6x.setChecked(sharedPreferencesToggleButton3.getBoolean(TOGGLRBTN3_STATE,false));

        sharedPreferencesToggleButton4 = getPreferences(MODE_PRIVATE);
        btn_8x.setChecked(sharedPreferencesToggleButton4.getBoolean(TOGGLRBTN4_STATE,false));

        sharedPreferencesRadioButton1 = getPreferences(MODE_PRIVATE);
        radBtn_divBy2.setChecked(sharedPreferencesRadioButton1.getBoolean(RADIOBTN1_STATE,false));

        sharedPreferencesRadioButton2 = getPreferences(MODE_PRIVATE);
        radBtn_divBy4.setChecked(sharedPreferencesRadioButton2.getBoolean(RADIOBTN2_STATE,false));

        sharedPreferencesRadioButton3 = getPreferences(MODE_PRIVATE);
        radBtn_divBy8.setChecked(sharedPreferencesRadioButton3.getBoolean(RADIOBTN3_STATE,false));

        sharedPreferencesSwitch1 = getPreferences(MODE_PRIVATE);
        switch_add10.setChecked(sharedPreferencesSwitch1.getBoolean(SWITCH_1,false));

        sharedPreferencesSwitch2 = getPreferences(MODE_PRIVATE);
        switch_add20.setChecked(sharedPreferencesSwitch2.getBoolean(SWITCH_2,false));

        sharedPreferencesSwitch3 = getPreferences(MODE_PRIVATE);
        switch_add30.setChecked(sharedPreferencesSwitch3.getBoolean(SWITCH_2,false));

        initTextViews();

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChangedValue = 0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChangedValue = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                progressBar.setProgress(progressChangedValue);
                sharedPreferencesProgressBar = getPreferences(MODE_PRIVATE);
                SharedPreferences.Editor ed = sharedPreferencesProgressBar.edit();
                ed.putInt(PROGRESS_VALUE, progressBar.getProgress());
                ed.commit();
                sharedPreferencesSeekBar = getPreferences(MODE_PRIVATE);
                SharedPreferences.Editor ed1 = sharedPreferencesSeekBar.edit();
                ed1.putInt(SEEK_VALUE, seekBar.getProgress());
                ed1.commit();
                initTextViews();
            }
        });

        btn_2x.setOnCheckedChangeListener(this);
        btn_4x.setOnCheckedChangeListener(this);
        btn_6x.setOnCheckedChangeListener(this);
        btn_8x.setOnCheckedChangeListener(this);

        clearRadios.setOnClickListener(this);

        radBtn_divBy2.setOnCheckedChangeListener(this);
        radBtn_divBy4.setOnCheckedChangeListener(this);
        radBtn_divBy8.setOnCheckedChangeListener(this);

        switch_add10.setOnCheckedChangeListener(this);
        switch_add20.setOnCheckedChangeListener(this);
        switch_add30.setOnCheckedChangeListener(this);
    }

    public void initViews() {
        progressBarMaxValue = (TextView) findViewById(R.id.act_tv_prog_max_value);
        getProgress = (TextView) findViewById(R.id.act_tv_current_progress);

        seekBarMaxValue = (TextView) findViewById(R.id.act_tv_seek_max_value);
        getSeekbar = (TextView) findViewById(R.id.act_current_seekbar);

        progressBar = (ProgressBar) findViewById(R.id.act_progress);

        seekBar = (SeekBar) findViewById(R.id.act_seekbar);

        checkBox = (CheckBox) findViewById(R.id.chekbox);
        checkBox.setOnCheckedChangeListener(this);

        btn_2x = (ToggleButton) findViewById(R.id.act_tog2x_btn);
        btn_4x = (ToggleButton) findViewById(R.id.act_tog4x_btn);
        btn_6x = (ToggleButton) findViewById(R.id.act_tog6x_btn);
        btn_8x = (ToggleButton) findViewById(R.id.act_tog8x_btn);

        radGroup = (RadioGroup) findViewById(R.id.act_radio_group);

        radBtn_divBy2 = (RadioButton) findViewById(R.id.act_rad_btn1);
        radBtn_divBy4 = (RadioButton) findViewById(R.id.act_rad_btn2);
        radBtn_divBy8 = (RadioButton) findViewById(R.id.act_rad_btn3);

        clearRadios = (Button) findViewById(R.id.act_clear_btn);

        switch_add10 = (Switch)findViewById(R.id.act_switch1);
        switch_add20 = (Switch)findViewById(R.id.act_switch2);
        switch_add30 = (Switch)findViewById(R.id.act_switch3);
    }

    public void initTextViews() {
        String progMaxValue = "" + progressBar.getMax();
        progressBarMaxValue.setText(progMaxValue);
        String progresText = "Progres: " + progressBar.getProgress();
        getProgress.setText(progresText);
        String seekMaxValue = "" + seekBar.getMax();
        seekBarMaxValue.setText(seekMaxValue);
        String seekText = "Seekbar: " + seekBar.getProgress();
        getSeekbar.setText(seekText);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        int bet = 1;
        switch (buttonView.getId()) {
            case R.id.chekbox:
                if (isChecked) {
                    btn_2x.setEnabled(true);
                    btn_4x.setEnabled(true);
                    btn_6x.setEnabled(true);
                    btn_8x.setEnabled(true);
                } else {
                    btn_2x.setEnabled(false);
                    btn_4x.setEnabled(false);
                    btn_6x.setEnabled(false);
                    btn_8x.setEnabled(false);
                }
                sharedPreferencesCheckbox = getPreferences(MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferencesCheckbox.edit();
                editor.putBoolean(CHECKBOX_STATE,isChecked);
                editor.commit();
                break;
            case R.id.act_tog2x_btn:
                if (isChecked) {
                    bet *= 2;
                    pressingCount++;
                    checked_2x = true;
                }
                if (checked_2x && !isChecked) {
                    progressBar.setProgress(progressBar.getProgress() / 2);
                }
                Log.d("tag", "" + pressingCount);
                if (pressingCount == 1) {
                    progressBar.setProgress(seekBar.getProgress() * bet);
                }
                if (pressingCount > 1 && isChecked) {
                    progressBar.setProgress(2 * progressBar.getProgress());
                }
                sharedPreferencesToggleButton1 = getPreferences(MODE_PRIVATE);
                SharedPreferences.Editor editor1 = sharedPreferencesToggleButton1.edit();
                editor1.putBoolean(TOGGLRBTN1_STATE,isChecked);
                editor1.commit();
                break;
            case R.id.act_tog4x_btn:
                if (isChecked) {
                    bet *= 4;
                    pressingCount++;
                    checked_4x = true;
                }
                if (checked_4x && !isChecked) {
                    progressBar.setProgress(progressBar.getProgress() / 4);
                }
                Log.d("tag", "" + pressingCount);
                if (pressingCount == 1) {
                    progressBar.setProgress(seekBar.getProgress() * bet);
                }
                if (pressingCount > 1 && isChecked) {
                    progressBar.setProgress(4 * progressBar.getProgress());
                }
                sharedPreferencesToggleButton2 = getPreferences(MODE_PRIVATE);
                SharedPreferences.Editor editor2 = sharedPreferencesToggleButton2.edit();
                editor2.putBoolean(TOGGLRBTN2_STATE,isChecked);
                editor2.commit();
                break;
            case R.id.act_tog6x_btn:
                if (isChecked) {
                    bet *= 6;
                    pressingCount++;
                    checked_6x = true;
                }
                if (checked_6x && !isChecked) {
                    progressBar.setProgress(progressBar.getProgress() / 6);
                }
                Log.d("tag", "" + pressingCount);
                if (pressingCount == 1) {
                    progressBar.setProgress(seekBar.getProgress() * bet);
                }
                if (pressingCount > 1 && isChecked) {
                    progressBar.setProgress(6 * progressBar.getProgress());
                }
                sharedPreferencesToggleButton3 = getPreferences(MODE_PRIVATE);
                SharedPreferences.Editor editor3 = sharedPreferencesToggleButton3.edit();
                editor3.putBoolean(TOGGLRBTN3_STATE,isChecked);
                editor3.commit();
                break;
            case R.id.act_tog8x_btn:
                if (isChecked) {
                    bet *= 8;
                    pressingCount++;
                    checked_8x = true;
                }
                if (checked_8x && !isChecked) {
                    progressBar.setProgress(progressBar.getProgress() / 8);
                }
                Log.d("tag", "" + pressingCount);
                if (pressingCount == 1) {
                    progressBar.setProgress(seekBar.getProgress() * bet);
                }
                if (pressingCount > 1 && isChecked) {
                    progressBar.setProgress(8 * progressBar.getProgress());
                }
                sharedPreferencesToggleButton4 = getPreferences(MODE_PRIVATE);
                SharedPreferences.Editor editor4 = sharedPreferencesToggleButton4.edit();
                editor4.putBoolean(TOGGLRBTN4_STATE,isChecked);
                editor4.commit();
                break;
            case R.id.act_rad_btn1:
                if (isChecked) {
                    Log.d("jannn", "micnev " + progressBar.getProgress());
                    progressBar.setProgress(progressBar.getProgress() / 2);
                    Log.d("jannn", "heto " + progressBar.getProgress());
                }
                sharedPreferencesRadioButton1 = getPreferences(MODE_PRIVATE);
                SharedPreferences.Editor editor5 = sharedPreferencesRadioButton1.edit();
                editor5.putBoolean(RADIOBTN1_STATE,isChecked);
                editor5.commit();
                break;
            case R.id.act_rad_btn2:
                if (isChecked) {
                    Log.d("jannn", "micnev " + progressBar.getProgress());
                    progressBar.setProgress(progressBar.getProgress() / 4);
                    Log.d("jannn", "heto " + progressBar.getProgress());
                }
                sharedPreferencesRadioButton2 = getPreferences(MODE_PRIVATE);
                SharedPreferences.Editor editor6 = sharedPreferencesRadioButton2.edit();
                editor6.putBoolean(RADIOBTN2_STATE,isChecked);
                editor6.commit();
                break;
            case R.id.act_rad_btn3:
                if (isChecked) {
                    Log.d("jannn", "micnev " + progressBar.getProgress());
                    progressBar.setProgress(progressBar.getProgress() / 8);
                    Log.d("jannn", "heto " + progressBar.getProgress());
                }
                sharedPreferencesRadioButton3 = getPreferences(MODE_PRIVATE);
                SharedPreferences.Editor editor7 = sharedPreferencesRadioButton3.edit();
                editor7.putBoolean(RADIOBTN1_STATE,isChecked);
                editor7.commit();
                break;
            case R.id.act_switch1:
                if (isChecked) {
                    progressBar.setProgress(progressBar.getProgress() + 10);
                }
                else {
                    progressBar.setProgress(progressBar.getProgress() - 10);
                }
                sharedPreferencesSwitch1 = getPreferences(MODE_PRIVATE);
                SharedPreferences.Editor editor8 = sharedPreferencesSwitch1.edit();
                editor8.putBoolean(SWITCH_1,isChecked);
                editor8.commit();
                break;
            case R.id.act_switch2:
                if (isChecked) {
                    progressBar.setProgress(progressBar.getProgress() + 20);
                }
                else {
                    progressBar.setProgress(progressBar.getProgress() - 20);
                }
                sharedPreferencesSwitch2 = getPreferences(MODE_PRIVATE);
                SharedPreferences.Editor editor9 = sharedPreferencesSwitch2.edit();
                editor9.putBoolean(SWITCH_2,isChecked);
                editor9.commit();
                break;
            case R.id.act_switch3:
                if (isChecked) {
                    progressBar.setProgress(progressBar.getProgress() + 30);
                }
                else {
                    progressBar.setProgress(progressBar.getProgress() - 30);
                }
                sharedPreferencesSwitch3 = getPreferences(MODE_PRIVATE);
                SharedPreferences.Editor editor10 = sharedPreferencesSwitch3.edit();
                editor10.putBoolean(SWITCH_3,isChecked);
                editor10.commit();
                break;
        }

        initTextViews();
        if (progressBar.getProgress() > 1000)
            progressBar.setProgress(1000);
        sharedPreferencesProgressBar = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferencesProgressBar.edit();
        editor.putInt(PROGRESS_VALUE, progressBar.getProgress());
        editor.commit();

    }

    @Override
    public void onClick(View v) {
        radBtn_divBy2.setChecked(false);
        radBtn_divBy4.setChecked(false);
        radBtn_divBy8.setChecked(false);
    }

}
