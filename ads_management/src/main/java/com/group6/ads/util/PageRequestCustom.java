package com.group6.ads.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

public record PageRequestCustom(PageRequest pageRequest) {

    public static PageRequestCustom of(int page, int size, Sort sort) {
        return new PageRequestCustom(PageRequest.of(page - 1, size, sort));
    }

    public static PageRequestCustom of(int page, int size) {
        return new PageRequestCustom(PageRequest.of(page - 1, size, Sort.unsorted()));
    }

    public int current() {
        return pageRequest.getPageNumber() + 1;
    }
}