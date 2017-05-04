package com.taiping.app.model.base;

import java.util.ArrayList;

/**
 * Created by zhoujy on 2017/2/13.
 */

public class PageInfo<T>{

    /**
     * 页大小
     */
    public int size;

    /**
     * 总共数量
     */
    public int total;

    /**
     * 当前页
     */
    public int page;

    public ArrayList<T> data;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public ArrayList<T> getData() {
        return data;
    }

    public void setData(ArrayList<T> data) {
        this.data = data;
    }
}
