package com.study.security.model.dao;

import com.study.security.model.entity.Authority;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IAuthorityDao extends JpaRepository<Authority, Long>{

    @Modifying
    @Query(value = "DELETE FROM user_authority ua WHERE authority_id:=authorityId", nativeQuery = true)
    public void deleteAllByAuthorityId(@Param("authorityId") Long id);
    
}
