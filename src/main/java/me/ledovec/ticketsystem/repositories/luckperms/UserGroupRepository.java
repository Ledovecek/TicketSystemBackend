package me.ledovec.ticketsystem.repositories.luckperms;

import me.ledovec.ticketsystem.entities.luckperms.UserGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserGroupRepository extends JpaRepository<UserGroup, UUID> {

    UserGroup getUserGroupByUsername(String username);

    UserGroup findByUid(String uid);

}
