package ua.alevel;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.xml.bind.SchemaOutputResolver;
import java.util.ArrayList;
import java.util.List;

public class LikeDao {

    private final EntityManager entityManager;

    public LikeDao(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public LikeEntity setLike(LikeEntity likeEntity){
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        if(entityManager.find(LikeEntity.class, likeEntity.getId()) == null){
            entityManager.merge(likeEntity);
            System.out.println("Like is set");
            transaction.commit();
            return likeEntity;
        } else {
            entityManager.remove(entityManager.contains(likeEntity) ? likeEntity : entityManager.merge(likeEntity));
            System.out.println("Like is deleted");
            transaction.commit();
            return likeEntity;
        }

    }

    public List<UserEntity> showUsersWhoLikes(UserEntity user){
        List<LikeEntity> likes = user.getLikes();
        System.out.println("Quantity of likes: " + likes.size());

        List<UserEntity> usersWhoLikes = null;
        for (int i = 0; i < likes.size(); i++) {
            usersWhoLikes.add(likes.get(i).getUser());
        }

        for (int i = 0; i < usersWhoLikes.size(); i++) {
            System.out.println(usersWhoLikes.get(i));
        }
        return usersWhoLikes;
    }

}
