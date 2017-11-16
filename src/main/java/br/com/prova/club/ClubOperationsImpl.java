package br.com.prova.club;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.prova.customer.api.Club;

@Service
public class ClubOperationsImpl implements ClubOperations {

	@Autowired
	private ClubRepository clubRepository;
	
	@Override
	public Club findClubById(Long clubId) {
		return clubRepository.findOne(clubId);
	}

}
