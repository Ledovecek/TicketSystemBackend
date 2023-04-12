package me.ledovec.ticketsystem.entities.luckperms;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Table(name = "luckperms_players") @Getter @Entity
public class UserGroup {

    @Id @Column(name = "uuid") private String uid;
    @Column String username;
    @Column(name = "primary_group") String primaryGroup;

}
