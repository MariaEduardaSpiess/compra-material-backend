package com.testeSenior.testeSenior;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SolicitacaoRepository extends CrudRepository<Solicitacao, Long>{
	
	Iterable<Solicitacao> findAllByStatusAndSolicitanteAndDescProduto(String status, String solicitante, String descProduto);
	
	Iterable<Solicitacao> findAllByStatus(String status);

}
