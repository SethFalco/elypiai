package com.elypia.elypiai.common.impl;

import java.io.IOException;
import java.util.List;

public interface IRestPaginator<T> {

    List<T> next() throws IOException;
}
