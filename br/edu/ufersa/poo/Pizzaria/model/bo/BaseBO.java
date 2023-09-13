package br.edu.ufersa.poo.Pizzaria.model.bo;

import java.util.List;

public interface BaseBO <BO> {
    public void create(BO bo) throws Exception;
    public void update(BO bo) throws Exception;
    public void deletar(BO bo) throws Exception;
    public BO buscar(BO bo) throws Exception ;
    public List<BO> buscarTodos() throws Exception;
}
