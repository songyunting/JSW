package com.syt.jsw.utils;

public abstract class PageManager extends IPage {


    public PageManager() {
    }

    public PageManager(int pageSize) {
        super(pageSize);
    }

    @Override
    public int handlePageIndex(int currPageIndex, int pageSize) {
        return ++currPageIndex;
    }

    @Override
    protected int handlePage(int currPageIndex, int pageSize) {
        return pageSize;
    }
}
