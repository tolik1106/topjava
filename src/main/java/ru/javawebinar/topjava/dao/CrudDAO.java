package ru.javawebinar.topjava.dao;

import java.io.Serializable;
import java.util.Collection;

public interface CrudDAO<Entity, ID extends Serializable> {

    void create(Entity entity);

    Entity read(ID id);

    void update(Entity entity);

    void delete(ID id);

    Collection<Entity> getAll();
}
