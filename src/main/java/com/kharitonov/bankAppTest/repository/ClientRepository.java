package com.kharitonov.bankAppTest.repository;
import com.kharitonov.bankAppTest.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ClientRepository extends JpaRepository<Client, Long>{
}
