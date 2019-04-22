package com.testeSenior.testeSenior;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SolicitacaoEndpoint  {

	@Resource
	private EntityManager em;
	
	private SolicitacaoRepository repository;

	public SolicitacaoEndpoint(SolicitacaoRepository repository) {
		this.repository = repository;
	}
	
	@GetMapping(path = "/solicitacoes")
	public ResponseEntity<List<Solicitacao>> findAll() {
		return ResponseEntity.ok(StreamSupport.stream(repository.findAll().spliterator(),false).collect(Collectors.toList()));
	}
	
	@GetMapping(path = "/solicitacoesFilter")
	public ResponseEntity<List<Solicitacao>> findAllByStatusOrSolicitanteOrDescProduto(@RequestParam(required = false) String status, @RequestParam(required = false) String solicitante, @RequestParam(required = false) String descProduto) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Solicitacao> cq = cb.createQuery(Solicitacao.class);
		Root<Solicitacao> root = cq.from(Solicitacao.class);
		
		ArrayList<Predicate> preds = new ArrayList<>();
		
		if (status!=null && !status.isEmpty()) preds.add(cb.equal(root.get(Solicitacao.STATUS), status));
		if (solicitante!=null && !solicitante.isEmpty()) {
			Predicate exp = cb.like(root.get(Solicitacao.SOLICITANTE), solicitante);
			if (preds.size()==0) {
				preds.add(exp);
			} else {
				preds.add(cb.and(exp));
			}
		}
		if (descProduto!=null && !descProduto.isEmpty()) {
			Predicate exp = cb.like(root.get(Solicitacao.DESCPRODUTO), descProduto);
			if (preds.size()==0) {
				preds.add(exp);
			} else {
				preds.add(cb.and(exp));
			}
		}
		if (preds.size()>0) {
			cq.where(preds.toArray(new Predicate[]{}));
		}
		TypedQuery<Solicitacao> query = em.createQuery(cq);
		return ResponseEntity.ok(query.getResultList());
	}
	
	@GetMapping(path = "/solicitacoes/{status}")
	public ResponseEntity<List<Solicitacao>> findAllByStatus(@PathVariable String status) {
		return ResponseEntity.ok(StreamSupport.stream(repository.findAllByStatus(status).spliterator(),false).collect(Collectors.toList()));
	}
	
	@GetMapping(path = "/solicitacao/{id}")
	public ResponseEntity<Solicitacao> findById(@PathVariable Long id) throws Exception {
		return ResponseEntity.ok(repository.findById(id).orElseThrow(() -> new Exception("Id não encontrada")));
	}
	
	@PostMapping(path = "/solicitacao")
	public ResponseEntity<Solicitacao> cadastrar(@Valid @RequestBody Solicitacao solicitacao) {
		return ResponseEntity.ok(repository.save(solicitacao));
	}
	
	@PutMapping(path = "/solicitacao")
	public ResponseEntity<Solicitacao> atualizar(@Valid @RequestBody Solicitacao solicitacao) {
		return ResponseEntity.ok(repository.save(solicitacao));
	}
	
}
