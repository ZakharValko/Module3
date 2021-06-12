package ua.alevel;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class UsersDao {

    private final EntityManager entityManager;

    public UsersDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public boolean addUser(UserEntity user){
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.merge(user);
        transaction.commit();
        return true;
    }
}
