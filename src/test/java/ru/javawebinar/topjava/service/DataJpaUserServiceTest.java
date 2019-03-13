package ru.javawebinar.topjava.service;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;

@ActiveProfiles(value = {Profiles.POSTGRES_DB, Profiles.DATAJPA}, inheritProfiles = false)
public class DataJpaUserServiceTest extends JdbcUserServiceTest {}
