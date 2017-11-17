package com.du.treeview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.du.treeview.adapter.SimpleTreeListAdapter;
import com.du.treeview.bean.ChapterBean;
import com.du.treeview.bean.FileBean;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.du.treeview.utiles.tools.ReadChapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

/**
 *  1.原理 listview 的item +paddingleft(level) +expand
    2.把系统的数据bean 转换成node
    3.反射+注解！！！！！
    4.绑定父子关系（设置节点间的关联关系）
    5.过滤可显示的节点数据
 **/

public class MainActivity extends AppCompatActivity {
    private ListView mTree;
    private SimpleTreeListAdapter<ChapterBean> mAdapter1;
    private SimpleTreeListAdapter<FileBean> mAdapter;
    private ArrayList<FileBean> mDatas;
    private static ArrayList<FileBean> results=new ArrayList<>();
    FileBean mbean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //实例化一个Logger
        Logger.addLogAdapter(new AndroidLogAdapter());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTree= (ListView) findViewById(R.id.id_listview);

        try {
            initDatasMy();
        } catch (IOException e) {
            e.printStackTrace();
        }
//            initDatas();

        try {
            mAdapter= new SimpleTreeListAdapter<>(mTree,MainActivity.this,mDatas,0);
            mTree.setAdapter(mAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void initDatasMy() throws IOException {
        mDatas=new ArrayList<FileBean>();
        mDatas=getallNames(initJson());
//        Logger.d(mDatas);


    }

    public List<ChapterBean>initJson() throws IOException {
        String context=null;
//        mDatas.addAll(ReadChapter.redjson());
        InputStream inputStream=getAssets().open("chapter.json");
        int size=inputStream.available();
        byte[] buffer=new byte[size];
        inputStream.read(buffer);
        inputStream.close();
        context=new String(buffer,"utf-8");
        Gson gson=new Gson();
        java.lang.reflect.Type type= new TypeToken<ArrayList<ChapterBean>>() {}.getType();
        List<ChapterBean> list=new ArrayList<ChapterBean>();
        list=gson.fromJson(context,type);
        return list;
    }


    /**
     * @param list
     * @return++
     */
    //    获取name存在一个数组中
    int i=1;
    public ArrayList<FileBean>  getallNames(List<ChapterBean> list) {
//             mDatas=new ArrayList<FileBean>();

           for (ChapterBean ch:list) {
                  ch.setId(i);
                  ch.setPid(i-1);
                 mbean=new FileBean(ch.getId(),ch.getPid(),ch.getName());
                results.add(mbean);
                i++;
    //			 System.out.println(ch.getName());
            if(ch.getChildren()!=null) {
                 getallNames(ch.getChildren());

            }

    }

     Logger.d(results);
        return results;

}

    private void initDatas() {
        mDatas= new ArrayList<>();
        FileBean bean=new FileBean(21,20,"第1章有理数");
        mDatas.add(bean);
        bean=new FileBean(22,20,"第2章正式的加减");
        mDatas.add(bean);
        bean=new FileBean(23,20,"第3章一元一次方程");
        mDatas.add(bean);
        bean=new FileBean(24,22,"1.1正式和负数");
        mDatas.add(bean);
        bean=new FileBean(25,26,"1.2有理数");
        mDatas.add(bean);
        Logger.d(mDatas.toString());


    }

}
