package ru.javawebinar.topjava;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestTimeRule implements TestRule {

    private static final Logger log = LoggerFactory.getLogger(TestTimeRule.class);
    public static long testTime;
    @Override
    public Statement apply(Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                long begin = System.currentTimeMillis();
                base.evaluate();
                long end = System.currentTimeMillis();
                log.debug("Test " +
                        description.getMethodName() +
                        " executed in {}ms", (end - begin));
                testTime += end - begin;
            }
        };
    }
}
