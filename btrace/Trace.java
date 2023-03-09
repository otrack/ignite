import com.sun.btrace.BTraceUtils.Aggregations;
import com.sun.btrace.aggregation.Aggregation;
import com.sun.btrace.aggregation.AggregationFunction;

import org.openjdk.btrace.core.annotations.*;
import static org.openjdk.btrace.core.BTraceUtils.*;

@BTrace public class Trace {

    private static Aggregation methodDuration = Aggregations
	.newAggregation(AggregationFunction.AVERAGE);
   
    @OnMethod(clazz="/org\\.apache\\.ignite\\.internal\\.util\\.StripedExecutor",
	method="/take/",
	location = @Location(Kind.RETURN))
    public static void addMethodDuration(@Duration long duration) {
	Aggregations.addToAggregation(methodDuration, duration);
    }

    @OnTimer(value = 1000)
    public static void printAvgMethodDuration() {
	Aggregations.printAggregation("Average method duration (ms)", methodDuration);
    }
    
}
