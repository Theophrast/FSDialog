package com.theophrast.fullscreenstyledialog;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.theophrast.fsdialog.callbacks.FSDialogButtonClickListener;
import com.theophrast.fsdialog.ui.FSDialog;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bt_showdialog = (Button) findViewById(R.id.button);
        bt_showdialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });
    }


    private void showDialog() {
        FSDialog.FsDialogBuilder builder = new FSDialog.FsDialogBuilder(this)
                .setTitleStringColorRes(android.R.color.white)
                .setTitleBackgroundColorRes(R.color.colorPrimaryDark)
                .setBackgroundColorRes(android.R.color.white)
                .setTitle("Title")
                .setConfirmString("Save")
                .setConfirmListener(new FSDialogButtonClickListener() {
                    @Override
                    public void OnButtonClick() {
                        Toast.makeText(MainActivity.this, "Confirm clicked", Toast.LENGTH_LONG).show();
                    }
                })
                .setDiscardListener(new FSDialogButtonClickListener() {
                    @Override
                    public void OnButtonClick() {
                        Toast.makeText(MainActivity.this, "Discard clicked", Toast.LENGTH_LONG).show();
                    }
                })
                .setLayoutResource(R.layout.dialog_content);

        FSDialog dialog = builder.build();
        dialog.show();
    }
}
