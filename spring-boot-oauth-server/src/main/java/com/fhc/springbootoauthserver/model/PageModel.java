package com.fhc.springbootoauthserver.model;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;

import java.util.List;

/**
 * @author fuhongchao
 * @create 2020/6/14 14:51
 */
public class PageModel<T> {

    public IPage<T> pageModelView(List<T> records, long pageNum, long pageSize, long total, List<OrderItem> orders) {

        return new IPage<T>() {
            @Override
            public List<OrderItem> orders() {
                return orders;
            }

            @Override
            public List<T> getRecords() {
                return records;
            }

            @Override
            public IPage<T> setRecords(List<T> records) {
                return null;
            }

            @Override
            public long getTotal() {
                return total;
            }

            @Override
            public IPage<T> setTotal(long total) {
                return null;
            }

            @Override
            public long getSize() {
                return pageSize;
            }

            @Override
            public IPage<T> setSize(long size) {
                return null;
            }

            @Override
            public long getCurrent() {
                return pageNum;
            }

            @Override
            public IPage<T> setCurrent(long current) {
                return null;
            }
        };
    }
}
