package com.easyding.entity.po.indeedPo;

import java.io.Serializable;
import java.util.List;

public class IndeedJobRunHistoryResponseBodyPageData<T> implements Serializable {

    private String current;
    private String total;
    private String pages;
    private String size;
    private List<T> records;

    public String getCurrent() {
        return current;
    }

    public void setCurrent(String current) {
        this.current = current;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public List<T> getRecords() {
        return records;
    }

    public void setRecords(List<T> records) {
        this.records = records;
    }

    @Override
    public String toString() {
        return "IndeedResponseBodyPageData{" +
                "current='" + current + '\'' +
                ", total='" + total + '\'' +
                ", pages='" + pages + '\'' +
                ", size='" + size + '\'' +
                ", records=" + records +
                '}';
    }
}
