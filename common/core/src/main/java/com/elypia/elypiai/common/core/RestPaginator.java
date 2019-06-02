package com.elypia.elypiai.common.core;

import java.io.IOException;
import java.util.List;

public interface RestPaginator<T> {
    List<T> next() throws IOException;
}
