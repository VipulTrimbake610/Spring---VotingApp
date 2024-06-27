package com.jforce.VotingApp.Repository;

import com.jforce.VotingApp.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRespository extends JpaRepository<User, String> {
    User findUserByUsername(String username);

    @Query(value = "select vote,count(vote) from users group by vote",nativeQuery = true)
    public List<Object[]> getVotes();

}
