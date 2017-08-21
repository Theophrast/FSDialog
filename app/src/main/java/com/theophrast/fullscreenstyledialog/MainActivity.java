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

        Button bt_showSimpleDialog = (Button) findViewById(R.id.bt_dialog_simple);
        bt_showSimpleDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSimpleDialog();
            }
        });
        Button bt_showInputDialog = (Button) findViewById(R.id.bt_dialog_input);
        bt_showInputDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showInputDialog();
            }
        });
    }


    private void showSimpleDialog() {
        FSDialog.FsDialogBuilder builder = new FSDialog.FsDialogBuilder(this)
                .setTitleStringColorRes(android.R.color.white)
                .setTitleBackgroundColorRes(R.color.colorPrimaryDark)
                .setBackgroundColorRes(android.R.color.white)
                .setTitle("")
                .setConfirmString("Ok")
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
                .setAutoDismiss(true)
                .setLayoutResource(R.layout.dialog_content);

        FSDialog dialog = builder.build();
        dialog.show();
    }

    private void showInputDialog() {
        FSDialog.FsDialogBuilder builder = new FSDialog.FsDialogBuilder(this)
                .setTitleStringColorRes(android.R.color.white)
                .setTitleBackgroundColorRes(R.color.colorPrimaryDark)
                .setBackgroundColorRes(android.R.color.white)
                .setTitle("Text input")
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
                .setLayoutResource(R.layout.dialog_content_input);

        FSDialog dialog = builder.build();
        dialog.show();
    }
}
