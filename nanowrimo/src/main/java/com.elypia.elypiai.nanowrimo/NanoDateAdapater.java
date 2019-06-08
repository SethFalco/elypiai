package com.elypia.elypiai.nanowrimo;

import com.elypia.elypiai.common.jaxb.adapters.DateAdapter;

public class NanoDateAdapater extends DateAdapter {

    public NanoDateAdapater() {
        super("yyyy-MM-dd");
    }
}
