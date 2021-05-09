package com.syt.jsw.utils;

/**
 * 分页
 *
 * @author hiphonezhu@gmail.com
 * @version [DX-AndroidLibrary, 2018-3-6]
 */
public abstract class IPage {
    /**
     * 默认起始页下标
     */
    public static final int DEFAULT_START_PAGE_INDEX = 1;
    /**
     * 默认分页大小
     */
    public static final int DEFAULT_PAGE_SIZE = 15;
    
    /**
     * 当前页下标
     */
    protected int currPageIndex;
    /**
     * 记录上一次的页下标
     */
    int lastPageIndex;
    /**
     * 分页大小
     */
    protected int pageSize;
    /**
     * 是否正在加载
     */
    boolean isLoading;
    /**
     * 锁
     */
    Object lock = new Object();

    public IPage() {
        initPageConfig();
    }

    public IPage(int pageSize){
        initPageConfig(pageSize);
    }

    /**
     * 加载分页数据
     * 分页策略1:[param1, param2] = [pageIndex, pageSize]
     * 分页策略2:[param1, param2] = [startIndex, endIndex]
     *
     * @param param1
     * @param param2
     */
    public abstract void load(int param1, int param2);

    /**
     * 根据分页策略,处理第一个分页参数
     *
     * @param currPageIndex
     * @param pageSize
     * @return
     */
    public abstract int handlePageIndex(int currPageIndex, int pageSize);

    /**
     * 根据分页策略,处理第二个分页参数
     *
     * @param currPageIndex
     * @param pageSize
     * @return
     */
    protected abstract int handlePage(int currPageIndex, int pageSize);

    /**
     * 初始化分页参数
     */
    private void initPageConfig() {
        currPageIndex = getStartPageIndex() - 1;
        lastPageIndex = currPageIndex;
        pageSize = getPageSize();
        isLoading = false;
    }

    private void initPageConfig(int pageSize) {
        currPageIndex = getStartPageIndex() - 1;
        lastPageIndex = currPageIndex;
        this.pageSize = pageSize;
        isLoading = false;
    }

    /**
     * 返回起始页下标
     *
     * @return
     */
    protected int getStartPageIndex() {
        return DEFAULT_START_PAGE_INDEX;
    }

    /**
     * 返回分页大小
     *
     * @return
     */
    protected int getPageSize() {
        return DEFAULT_PAGE_SIZE;
    }

    /**
     * 设置起始页下标
     *
     * @param startPageIndex
     * @return
     */
    public IPage setStartPageIndex(int startPageIndex) {
        currPageIndex = startPageIndex - 1;
        return this;
    }

    /**
     * 设置分页大小
     *
     * @param pageSize
     * @return
     */
    public IPage setPageSize(int pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    /**
     * 当前是否是第一页数据
     *
     * @return
     */
    public boolean isFirstPage() {
        return currPageIndex <= getStartPageIndex();
    }

    /**
     * 分页加载数据
     * [可能会抛出异常，请确认数据加载结束后，你已经调用了finishLoad(boolean success)方法]
     *
     * @param isFirstPage true: 第一页  false: 下一页
     */
    public void loadPage(boolean isFirstPage) {
        synchronized (lock) {
            // 如果正在加载数据，则抛出异常
            if (isLoading) {
                throw new RuntimeException();
            } else {
                isLoading = true;
            }
        }
        // 加载第一页数据
        if (isFirstPage){
            currPageIndex = getStartPageIndex();
        }else{
            currPageIndex = handlePageIndex(currPageIndex, pageSize);
        }
        load(currPageIndex, handlePage(currPageIndex, pageSize));
    }

    /**
     * 加载结束
     *
     * @param success true：加载成功  false：失败(无数据)
     */
    public void finishLoad(boolean success) {
        synchronized (lock) {
            isLoading = false;
        }
        if (success) {
            lastPageIndex = currPageIndex;
        } else {
            currPageIndex = lastPageIndex;
        }
    }

    /**
     * 起始下标递减
     */
    public void decreaseStartIndex() {
    }

    /**
     * 起始下标递减
     */
    public void decreaseStartIndex(int size) {
    }
}
