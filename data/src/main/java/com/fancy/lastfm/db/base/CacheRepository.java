package com.fancy.lastfm.db.base;

import com.fancy.lastfm.CacheState;

/**
 * @author Oleg Mazhukin
 */

public class CacheRepository {

    protected CacheState cacheState;

    public CacheRepository(CacheState cacheState) {
        this.cacheState = cacheState;
    }

    public void setCacheState(CacheState cacheState) {
        this.cacheState = cacheState;
    }

    public CacheState getCacheState() {
        return cacheState;
    }
}
