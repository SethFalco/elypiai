package com.elypia.elypiai.nanowrimo;

class NanoRequester {

    private Nanowrimo nano;

    protected NanoRequester(Nanowrimo nano) {
        this.nano = nano;
    }



    protected Nanowrimo getNano() {
        return nano;
    }
}
