package com.elypia.elypiai.poe;

import com.elypia.elypiai.common.core.impl.RestPaginator;

import java.io.IOException;
import java.util.List;

public class StashPaginator implements RestPaginator<Stash> {

    private PathOfExile poe;
    private String cursor;

    public StashPaginator(PathOfExile poe) {
        this(poe, null);
    }

    public StashPaginator(PathOfExile poe, String cursor) {
        this.poe = poe;
        this.cursor = cursor;
    }

    @Override
    public List<Stash> next() throws IOException {
        StashTabs stashtabs = poe.getStashTabs(cursor).completeGet();
        List<Stash> stashes = stashtabs.getStashes();

        if (stashes.isEmpty())
            return null;

        cursor = stashtabs.getCursor();
        return stashes;
    }
}
