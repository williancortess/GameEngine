package com.willian.cortes.simplegameenginev1;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

/**
 * Created by Willian on 19/03/2017.
 */

public class SGDialog {
    private AlertDialog mDialog;

    public SGDialog(Context context, int messageId, int okId, int cancelId)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setMessage(messageId)
                .setPositiveButton(okId, new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int id) {
                        onOk();
                    }
                })
                .setNegativeButton(cancelId, new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int id) {
                        onCancel();
                    }
                });
        mDialog = builder.create();
        mDialog.setCancelable(false);
        mDialog.setCanceledOnTouchOutside(false);
    }

    public void onOk() { }

    public void onCancel() { }

    public void show()
    {
        mDialog.show();
    }
}
