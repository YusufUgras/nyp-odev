package repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;

public class Repository<T extends Entity> {

    public List<T> entities = new ArrayList<>();

    public List<T> get(Predicate<T> predicate) {
        return entities.stream().filter(predicate).toList();
    }

    public void remove(Predicate<T> predicate) {
        entities.removeIf(predicate);
    }

    public T add(T entity) {
        entity.setId(UUID.randomUUID().toString());
        while (!get(x -> x.getId() == entity.getId()).isEmpty()) {
            entity.setId(UUID.randomUUID().toString());
        }
        entities.add(entity);
        return entity;
    }

    public boolean doesExist(String id) {
        return !this.get(x -> x.getId().equals(id)).isEmpty();
    }

    public T getById(String id) {
        List<T> list = this.get(x -> x.getId().equals(id));
        if (list.isEmpty())
            return null;
        return list.getFirst();
    }

}
