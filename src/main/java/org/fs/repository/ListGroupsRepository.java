package org.fs.repository;

import org.fs.entity.ListGroups;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListGroupsRepository extends JpaRepository<ListGroups, Long> {
}
