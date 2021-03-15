package me.kaimson.arialclient.gui.screen.smoothscrolling;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class RunSixtyTimesEverySecImpl {
    public static final List<RunSixtyTimesEverySec> TICKS_LIST;
    private static final ScheduledExecutorService EXECUTOR_SERVICE;

    static
    {
        TICKS_LIST = Lists.newCopyOnWriteArrayList();
        (EXECUTOR_SERVICE = Executors.newSingleThreadScheduledExecutor()).scheduleAtFixedRate(() ->
        {
            RunSixtyTimesEverySecImpl.TICKS_LIST.removeIf(Objects::isNull);
            RunSixtyTimesEverySecImpl.TICKS_LIST.iterator().forEachRemaining(Runnable::run);
        }, 0L, 16L, TimeUnit.MILLISECONDS);
    }
}
