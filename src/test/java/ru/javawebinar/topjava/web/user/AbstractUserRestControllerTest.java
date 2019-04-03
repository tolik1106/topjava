package ru.javawebinar.topjava.web.user;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import ru.javawebinar.topjava.repository.JpaUtil;
import ru.javawebinar.topjava.service.UserService;
import ru.javawebinar.topjava.web.AbstractControllerTest;

public class AbstractUserRestControllerTest extends AbstractControllerTest {

    @Autowired
    private CacheManager cacheManager;

    @Autowired(required = false)
    private JpaUtil jpaUtil;

    @Autowired
    protected UserService userService;

    @BeforeEach
    void setUp() {
        cacheManager.getCache("users").clear();
        if (jpaUtil != null) {
            jpaUtil.clear2ndLevelHibernateCache();
        }
    }
}
