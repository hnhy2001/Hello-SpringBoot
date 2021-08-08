package com.example.hellospringboot.RepoSitory;

import com.example.hellospringboot.controller.request.FindUserRequest;
import com.example.hellospringboot.model.QUser;
import com.example.hellospringboot.model.Role;
import com.example.hellospringboot.model.User;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.lang.annotation.Annotation;
import java.util.List;


@Repository
public class CustomUserRepositoryImpl implements CustomUserRepository{

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<User> findAllByConditions(FindUserRequest findUserRequest) {
        JPAQuery<User> queryUser = new JPAQuery<>(entityManager);
        QUser qUser = QUser.user;

        BooleanBuilder where = buildFindUser(findUserRequest);
        List<User> userList = queryUser.from(qUser).where(where).fetch();

        for (int i=0 ; i<userList.size() ; i++){
            if (!userList.get(i).getIsActive())
                userList.remove(userList.get(i));
        }


        return userList;
    }

    @Override
    public String value() {
        return null;
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return null;
    }

    public BooleanBuilder buildFindUser(FindUserRequest findUserRequest){
        QUser qUser = QUser.user;
        BooleanBuilder booleanBuilder = new BooleanBuilder();

        Integer id = findUserRequest.getId();
        if(id != null && id > 0)
            booleanBuilder.and(qUser.id.eq(id));

        String email = findUserRequest.getEmail();
        if(email != null)
            booleanBuilder.and(qUser.email.eq(email));

        String firstName = findUserRequest.getFirstName();
        if(firstName != null )
            booleanBuilder.and(qUser.firstName.eq(firstName));

        String lastName = findUserRequest.getLastName();
        if (lastName != null)
            booleanBuilder.and(qUser.lastName.eq(lastName));

        String phone = findUserRequest.getPhone();
        if (phone != null)
            booleanBuilder.and(qUser.phone.eq(phone));

        String address = findUserRequest.getAddress();
        if (address != null)
            booleanBuilder.and(qUser.address.eq(address));

        if(findUserRequest.getRole() != null){
            Role role = roleRepository.findAllByName(findUserRequest.getRole());
            booleanBuilder.and(qUser.role.eq(role));
        }
        return booleanBuilder;
    }
}
