package app.repositories;

import app.entities.search.SearchResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface SearchResultRepository  extends JpaRepository<SearchResult, Long> {
    @Query("SELECT sr FROM SearchResult sr WHERE sr.id = :id")
   SearchResultProjection findAllProjectedBy(@Param("id") Long id);
}
