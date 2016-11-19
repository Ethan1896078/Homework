package team.t9001.saad.common;

/**
 * desc:
 * Created by huangzhe on 2016/11/19.
 */
public class Page {
    private static final int DEFAULT_PAGE_SIZE = 10;
    /** 当前页 */
    private int currentPage;
    /** 总页数 */
    private int totalPage;
    /** 总数 */
    private int totalCount;
    /** 每页数量 */
    private int pageSize;

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
