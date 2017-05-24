package com.fancy.lastfm.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @author Oleg Mazhukin
 */
@Entity
public class Country {

    private String name;

    @Generated(hash = 1470947952)
    public Country(String name) {
        this.name = name;
    }

    @Generated(hash = 668024697)
    public Country() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
