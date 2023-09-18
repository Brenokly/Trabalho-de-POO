package br.edu.ufersa.poo.Pizzaria.model.bo;

import java.util.List;

public interface BaseBO <BO> {
    public void create(BO bo);
    public void update(BO bo);
    public void deletar(BO bo);
    public BO buscar(BO bo);
    public List<BO> buscarTodos();
}
