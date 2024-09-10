package com.rookie.stack.common.response.data;

import java.util.List;

/**
 * @author eumenides
 * @description 分页数据
 * @date 2024/9/10
 */
public class PageBean<T> {
    private Integer pageSize;
    private Integer total;
    private Integer page;
    private List<T> list;
    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
