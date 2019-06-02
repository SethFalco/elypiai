package com.elypia.elypiai.common.core;

import java.util.ArrayList;
import java.util.List;

public abstract class ApiWrapper {

    protected List<WrapperExtension> exts;

    public ApiWrapper(WrapperExtension... exts) {
        this.exts = new ArrayList<>();

        if (exts != null)
            addExtensions(exts);
    }

    public void addExtensions(WrapperExtension... exts) {
        this.exts.addAll(List.of(exts));
    }

//    abstract void pre();
//    abstract void post();
}
