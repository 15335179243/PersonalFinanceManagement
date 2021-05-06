package com.chumu.jianzhimao.ui.utils;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.widget.TextView;
import android.widget.Toast;

import com.chumu.jianzhimao.RootApplication;


import static android.content.Context.CLIPBOARD_SERVICE;

public class CopyButtonLibrary {
    private ClipboardManager myClipboard;
    private ClipData myClip;

    public void init(String text) {
        myClipboard = (ClipboardManager) RootApplication.getApplication().getSystemService(CLIPBOARD_SERVICE);
        myClip = ClipData.newPlainText("text", text);
        myClipboard.setPrimaryClip(myClip);

        Toast.makeText(RootApplication.getApplication(), text + " 已复制", Toast.LENGTH_SHORT).show();
    }


}
