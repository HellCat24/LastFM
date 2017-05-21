package com.fancy.lastfm.db.base;

import com.fancy.lastfm.CacheState;

/**
 * Created by Oleg on 21.05.2017.
 */

public class CacheRepository {

    protected CacheState cacheState;

    public CacheRepository(CacheState cacheState) {
        this.cacheState = cacheState;
    }

    public void setCacheState(CacheState cacheState) {
        this.cacheState = cacheState;
    }
}
