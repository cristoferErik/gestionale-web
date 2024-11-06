package com.gestionale.web.app.gestionale_web.models.token.repository;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gestionale.web.app.gestionale_web.models.token.Token;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long>{

    @Query("""
        select t from Token t inner join UserEntity u on t.userEntity.id = u.id
        where u.id =:userEntityId and (t.revoked=false)
    """)
    Optional<Token> findAllValidTokensByUser(Long userEntityId);

    Optional<Token> findByToken(String token);

    @Modifying
    @Query("""
            delete from Token t where t.expiration < :today
            """)
    void deleteTokensByDate(Date today);
}
