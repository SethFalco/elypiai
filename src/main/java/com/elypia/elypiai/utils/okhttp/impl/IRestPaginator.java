package com.elypia.elypiai.utils.okhttp.impl;

import java.io.IOException;
import java.util.List;

public interface IRestPaginator<T> {

    List<T> next() throws IOException;
}
