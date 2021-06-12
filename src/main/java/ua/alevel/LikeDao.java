package ua.alevel;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
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

    public List<LikeEntity> showUsersWhoLikes(UserEntity user){
        Query query = entityManager.createQuery("SELECT likes FROM UserEntity WHERE name = :userName");
        query.setParameter("userName", user.getName());
        List<LikeEntity> listOfLikes = query.getResultList();
        System.out.println("Quantity of likes is: " + listOfLikes.size());

        for (int i = 0; i < listOfLikes.size(); i++) {
            int id = listOfLikes.get(i).getPhoto().getUserId();
            Query query1 = entityManager.createQuery("SELECT name from UserEntity where id = :userId").setParameter("userId", id);
            System.out.println(query1.getSingleResult());
        }

        return listOfLikes;
    }

}
