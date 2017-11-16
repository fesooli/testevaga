package br.com.prova.club;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.prova.customer.api.Club;

@Repository
public interface ClubRepository extends JpaRepository<Club, Long> {

}
