package com.elypia.elypiai.pathofexile;

import com.elypia.elypiai.utils.okhttp.impl.IRestPaginator;

import java.io.IOException;
import java.util.List;

public class StashPaginator implements IRestPaginator<Stash> {

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
        StashTabs stashtabs = poe.getStashTabs(cursor).complete();
        List<Stash> stashes = stashtabs.getStashes();

        if (stashes.isEmpty())
            return null;

        cursor = stashtabs.getCursor();
        return stashes;
    }
}
