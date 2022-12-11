package framework.repository;

import framework.entities.Topics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface TopicsRepository extends JpaRepository<Topics, Integer> {

    boolean existsByName(String name);

    @Query(value = "select id from topics where name=?1", nativeQuery = true)
    int findIdByName(String name);

    @Transactional
    void deleteTopicsByNameContaining(String name);

    @Query(value = "SELECT * FROM topics where id = (SELECT MAX(id)  FROM topics)", nativeQuery = true)
    Topics getLastRecord();
}
