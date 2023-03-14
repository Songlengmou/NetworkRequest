package com.anningtex.networkrequestlivedata.dialog;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.anningtex.networkrequestlivedata.R;

/**
 * @author Song
 */
public class LoadingDialog extends Dialog {
    private ProgressBar progressBar;
    private TextView tvProgress, tvLoadDialog;

    public LoadingDialog(Context context) {
        super(context, R.style.CustomDialog);
    }

    public LoadingDialog(Context context, int theme) {
        super(context, theme);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView(getContext());
    }

    private void initView(Context context) {
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        getWindow().setAttributes(params);
        setCancelable(true);
        setCanceledOnTouchOutside(false);
        setContentView(R.layout.dialog_loading);
        progressBar = findViewById(R.id.pb_load);
        tvProgress = findViewById(R.id.tv_progress);
        tvLoadDialog = findViewById(R.id.tv_load_dialog);
    }

    public void setMsg(String msg) {
        tvLoadDialog.setText(msg);
    }

    @SuppressLint("SetTextI18n")
    public void showProgressbar(int position) {
        tvProgress.setText(position + "%");
    }
}
