package com.example.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.entity.Account;

// @Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

//     @PersistenceContext
//     private EntityManager entityManager;

//     public 

//     @Query("INSERT INTO account(username, password) values (:usernameVar, :passwordVar)")
//     public Account insertAccount(@Param("usernameVar") String username, @Param("passwordVar") String password);


//     @Transactional
// public void insertWithQuery(Person person) {
//     entityManager.createNativeQuery("INSERT INTO person (id, first_name, last_name) VALUES (?,?,?)")
//       .setParameter(1, person.getId())
//       .setParameter(2, person.getFirstName())
//       .setParameter(3, person.getLastName())
//       .executeUpdate();
// }

    // @Query("FROM account WHERE username = :usernameVar")
    // public Account findDuplicates(@Param("usernameVar") String username);

        public Account findAccountByUsername(String username);

        public Account findAccountByUsernameAndPassword(String username, String password);


}


// @Query("FROM Pet WHERE name = :nameVar AND species = :speciesVar")
// List<Pet> example2(@Param("nameVar") String name, @Param("speciesVar") String species);

// @Query("FROM Pet WHERE species = :species")
// List<Pet> lab1(@Param("species") String species);

// @Query("FROM Pet WHERE name = :nameVar")
// List<Pet> example1(@Param("nameVar") String name);