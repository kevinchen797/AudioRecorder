package com.dreamfish.audiorecord;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.dreamfish.record.FileUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by HXL on 16/8/11.
 */
public class ListActivity extends Activity {
    ListView listView;
    List<File> list = new ArrayList<>();
    FileListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        listView = (ListView) findViewById(R.id.listView);
        if ("pcm".equals(getIntent().getStringExtra("type"))) {
            list = FileUtil.getPcmFiles();
        } else {
            list = FileUtil.getWavFiles();
        }

        adapter = new FileListAdapter(this, list);
        listView.setAdapter(adapter);

    }
}
