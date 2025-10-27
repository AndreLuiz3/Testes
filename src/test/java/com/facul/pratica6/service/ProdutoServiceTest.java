package com.facul.pratica6.service;

import com.facul.pratica6.model.Produto;
import com.facul.pratica6.repository.ProdutoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProdutoServiceTest {

    @Mock
    private ProdutoRepository repo;

    @InjectMocks
    private ProdutoService service;

    private Produto p1;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        p1 = new Produto(1L, "Produto A", "Desc A", new BigDecimal("10.0"), 5);
    }

    @Test
    void testCriar() {
        Produto toSave = new Produto(null, "Novo", "Desc", new BigDecimal("1.0"), 2);
        Produto saved = new Produto(2L, "Novo", "Desc", new BigDecimal("1.0"), 2);
        when(repo.save(any(Produto.class))).thenReturn(saved);

        Produto result = service.criar(toSave);

        assertNotNull(result);
        assertEquals(2L, result.getId());
        verify(repo, times(1)).save(any());
    }

    @Test
    void testListarTodos() {
        when(repo.findAll()).thenReturn(List.of(p1));
        List<Produto> all = service.listarTodos();
        assertFalse(all.isEmpty());
        assertEquals(1, all.size());
        verify(repo, times(1)).findAll();
    }

    @Test
    void testBuscarPorId() {
        when(repo.findById(1L)).thenReturn(Optional.of(p1));
        Optional<Produto> opt = service.buscarPorId(1L);
        assertTrue(opt.isPresent());
        assertEquals("Produto A", opt.get().getNome());
    }

    @Test
    void testAtualizar_Sucesso() {
        Produto novo = new Produto(null, "Produto A edit", "Desc edit", new BigDecimal("12.0"), 10);
        when(repo.findById(1L)).thenReturn(Optional.of(p1));
        when(repo.save(any())).thenAnswer(inv -> inv.getArgument(0));

        Produto updated = service.atualizar(1L, novo);

        assertEquals("Produto A edit", updated.getNome());
        assertEquals(10, updated.getEstoque());
        verify(repo).findById(1L);
        verify(repo).save(any());
    }

    @Test
    void testAtualizar_NaoEncontrado() {
        when(repo.findById(99L)).thenReturn(Optional.empty());
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> service.atualizar(99L, p1));
        assertTrue(ex.getMessage().contains("Produto não encontrado"));
    }

    @Test
    void testDeletar() {
        doNothing().when(repo).deleteById(1L);
        service.deletar(1L);
        verify(repo, times(1)).deleteById(1L);
    }
}
