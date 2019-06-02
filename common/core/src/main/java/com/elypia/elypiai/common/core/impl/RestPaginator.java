package com.elypia.elypiai.common.core.impl;

import java.io.IOException;
import java.util.List;

public interface RestPaginator<T> {
    List<T> next() throws IOException;
}
