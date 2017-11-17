package com.du.treeview.bean;

import com.du.treeview.utiles.Node;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.Set;


/**
 * Created by srt-k12001 on 2017/11/16.
 */

public class ChapterBean {
    private int id;
    private int pid;
    private String name;
    private List<ChapterBean> children;
    private List<ChapterBean> parents;

    public void setParents(List<ChapterBean> parents) {
        this.parents = parents;
    }

    public List<ChapterBean> getParents() {
        return parents;
    }
    //    private int question_num;

    public ChapterBean(int id,int pid,String name,List<ChapterBean> children){
        this.id=id;
        this.pid=pid;
        this.name=name;
        this.children=children;

    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getPid() {
        return pid;
    }
    public void setPid(int pid) {
        this.pid = pid;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<ChapterBean> getChildren() {
        return children;
    }
    public void setChildren(List<ChapterBean> children) {
        this.children = children;
    }


//    	public int getQuestion_num() {
//		return question_num;
//	}
//	public void setQuestion_num(int question_num) {
//		this.question_num = question_num;
//	}

    @Override
    public String toString() {
        return "ChapterBean{" +
                "id=" + id +
                ", pid=" + pid +
                ", name='" + name + '\'' +
                ", children=" + children +
                ", parents=" + parents +
                '}';
    }
}
