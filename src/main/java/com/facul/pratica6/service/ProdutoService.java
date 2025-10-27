package com.facul.pratica6.service;

import com.facul.pratica6.model.Produto;
import com.facul.pratica6.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    private final ProdutoRepository repo;

    public ProdutoService(ProdutoRepository repo) {
        this.repo = repo;
    }

    public Produto criar(Produto p) {
        p.setId(null); // garantir criação
        return repo.save(p);
    }

    public List<Produto> listarTodos() {
        return repo.findAll();
    }

    public Optional<Produto> buscarPorId(Long id) {
        return repo.findById(id);
    }

    public Produto atualizar(Long id, Produto novo) {
        return repo.findById(id).map(existing -> {
            existing.setNome(novo.getNome());
            existing.setDescricao(novo.getDescricao());
            existing.setPreco(novo.getPreco());
            existing.setEstoque(novo.getEstoque());
            return repo.save(existing);
        }).orElseThrow(() -> new IllegalArgumentException("Produto não encontrado: " + id));
    }

    public void deletar(Long id) {
        repo.deleteById(id);
    }
}
