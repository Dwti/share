package com.du.treeview.utiles.tools;
import  com.du.treeview.bean.ChapterBean;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.apache.commons.io.FileUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


/**
 * Created by srt-k12001 on 2017/11/16.
 */

public class ReadChapter {
    private static List<String> results=new ArrayList<String>();


    public static void main(String[] args) throws JSONException, IOException {
        List<String> results=new ArrayList<String>();
//		System.out.println("hhhhhhh");
        System.out.println(redjson());
        getallNames(redjson());
        System.out.println(getallNames(redjson()));

//		System.out.println(results);
//
//		redjson();
//		reddatas();

    }
    public static List<ChapterBean> redjson() throws IOException {
        File file =new File(ReadChapter.class.getResource("/chapter.json").getFile());
        String context=FileUtils.readFileToString(file);
        Gson gson=new Gson();
        List<ChapterBean> list=new ArrayList<ChapterBean>();
        java.lang.reflect.Type type= new TypeToken<ArrayList<ChapterBean>>() {}.getType();
        list=gson.fromJson(context,type);
        return list;
    }

    public static List<String> getallNames(List<ChapterBean> list) {

        for (ChapterBean ch:list) {
            ReadChapter.results.add(ch.getName());
			 System.out.println(ch.getName());
            if(ch.getChildren()!=null) {
                getallNames(ch.getChildren());
            }
        }
        return results;
    }
    public  static List<ChapterBean> redjsonlocal(){
        List<ChapterBean> list=new ArrayList<ChapterBean>();
        InputStream inputStream;

      return list;
    }

}
