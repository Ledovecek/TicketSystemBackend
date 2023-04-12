package me.ledovec.ticketsystem.repositories.credentials;

import me.ledovec.ticketsystem.entities.credentials.Credentials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CredentialsRepository extends JpaRepository<Credentials, Long> {

    Credentials getCredentialsByRealName(String realName);

}
