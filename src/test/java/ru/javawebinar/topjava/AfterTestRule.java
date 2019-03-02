package ru.javawebinar.topjava;

import org.junit.rules.ExternalResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AfterTestRule extends ExternalResource {

    private static final Logger log = LoggerFactory.getLogger(AfterTestRule.class);

    private final Class<?> classForRule;

    public <T> AfterTestRule(Class<T> clazz) {
        classForRule = clazz;
    }

    @Override
    protected void after() {
        log.debug("Test " +
                classForRule.getSimpleName() +
                " executed in {}ms", TestTimeRule.testTime);
    }
}
