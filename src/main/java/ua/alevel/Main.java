package ua.alevel;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        EntityManager entityManager = HibernateUtils.getEntityManager();

        LikeDao likeDao = new LikeDao(entityManager);
        UsersDao usersDao = new UsersDao(entityManager);

        UserEntity user1 = new UserEntity();
        user1.setName("John");
        user1.setId(1);

        UserEntity user2 = new UserEntity();
        user2.setName("Alex");
        user2.setId(2);

        PhotoEntity photo1 = new PhotoEntity();
        photo1.setId(1);
        photo1.setTitle("Photo title");
        photo1.setUserId(user2.getId());

        LikeEntity like1 = new LikeEntity();
        like1.setUser(user1);
        like1.setPhoto(photo1);

        // Получаем юзера из лайка
        likeDao.setLike(like1);
        UserEntity firstUser = like1.getUser();

        //Тут получаем Null, хотя выше видим, что у юзера есть лайк :((
        List<LikeEntity> firstUserLikes = user1.getLikes();

    }
}
