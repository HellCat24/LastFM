package com.fancy.lastfm.rx;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * @author Oleg Mazhukin
 */

public class ObservableSchedulerStrategy implements Function<Observable, Observable> {

    @Override
    public Observable apply(@NonNull Observable o) throws Exception {
        return o;
    }
}
