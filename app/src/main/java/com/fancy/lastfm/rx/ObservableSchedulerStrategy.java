package com.fancy.lastfm.rx;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * Created by Oleg on 21.05.2017.
 */

public class ObservableSchedulerStrategy implements Function<Observable, Observable> {

    @Override
    public Observable apply(@NonNull Observable o) throws Exception {
        return o;
    }
}
