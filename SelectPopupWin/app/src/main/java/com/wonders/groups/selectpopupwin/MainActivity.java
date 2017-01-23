package com.wonders.groups.selectpopupwin;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.wonders.groups.selectpopupwin.adapter.SelectwinAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    TextView textView;
    TextView textView1;
    PopupWindow popupWindow;
    List<String> list;
    //当前选中的列表项位置
//    int clickPsition = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = getList();
        textView = (TextView)findViewById(R.id.textView);
        textView1 = (TextView)findViewById(R.id.textView1);
        textView.setText(list.get(0));
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(popupWindow == null){
                    showSubjectPopupWindow(view);
                }else{
                    popupWindow.dismiss();
                }
            }
        });
    }

    private void showSubjectPopupWindow(View view) {//想要选择框在哪个view下面，就把那个view作为参数传递进来
        // 一个自定义的布局，作为显示的内容
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.selectwinLayout);
        View contentView = LayoutInflater.from(MainActivity.this).inflate(R.layout.selectwin, linearLayout);
        final PopupWindow popupWindow = new PopupWindow(contentView,
                view.getWidth(), ViewGroup.LayoutParams.WRAP_CONTENT, true);
        // 如果不设置PopupWindow的背景，无论是点击外部区域还是Back键都无法dismiss弹框
        // 我觉得这里是API的一个bug
        popupWindow.setBackgroundDrawable(new ColorDrawable(0xb0000000));
        popupWindow.showAsDropDown(view, 0, 0);
        ListView lv = (ListView) contentView.findViewById(R.id.selectListView);
        lv.setAdapter(new SelectwinAdapter(MainActivity.this, list));
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                textView.setText(list.get(position));
                popupWindow.dismiss();
            }
        });
    }

    public List<String> getList() {
        List<String> list = new ArrayList<String>();
        list.add("每日吐槽");
        list.add("灵感笔记");
        list.add("爆笑王文");
        list.add("内涵段子");
        list.add("每日吐槽");
        list.add("灵感笔记");
        list.add("爆笑王文");
        list.add("内涵段子");
        list.add("每日吐槽");
        list.add("灵感笔记");
        list.add("爆笑王文");
        list.add("内涵段子");

        return list;

    }
}
