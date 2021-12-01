package zone.rong.lolilib.util;

import com.google.common.base.Function;
import com.google.common.base.Stopwatch;
import zone.rong.lolilib.LoliLogger;

import org.apache.logging.log4j.Logger;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class LoliTimer {

    public static LoliTimer createAndStart() {
        return new LoliTimer();
    }

    public static LoliTimer createAndStart(Consumer<Logger> msg) {
        msg.accept(LoliLogger.INSTANCE);
        return new LoliTimer();
    }

    private final Stopwatch stopwatch;

    private LoliTimer() {
        this.stopwatch = Stopwatch.createStarted();
    }

    public String stop() {
        this.stopwatch.stop();
        return this.stopwatch.toString();
    }

    public void stop(BiConsumer<Logger, String> msg) {
        this.stopwatch.stop();
        msg.accept(LoliLogger.INSTANCE, this.stopwatch.toString());
    }

}
