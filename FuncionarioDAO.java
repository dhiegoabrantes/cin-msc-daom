/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hextra.dao;

import br.com.hextra.beans.Funcionario;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Dhiego Abrantes
 */
public class FuncionarioDAO extends AbstractDAO {
	
    public Funcionario primeiroRegistro() {
        Session sessao = HibernateUtility.getSession();
        Criteria select = sessao.createCriteria(Funcionario.class);
        List<Funcionario> funcionarios = select.list();
        if( funcionarios.size() == 0 ){
            return null;
        }
        return funcionarios.get(0);
    }

    public Funcionario retornaFuncionario(Funcionario fun) {
        Session sessao = HibernateUtility.getSession();
        Funcionario funP = (Funcionario) sessao.createSQLQuery(
                "select * from funcionario where codigo = '" + fun.getCodigo() + "' and nome = '" + fun.getNome() + "'").addEntity(Funcionario.class).uniqueResult();
        return funP;
    }
    
    public Funcionario ultimoRegistro() {
        Session sessao = HibernateUtility.getSession();
        Criteria select = sessao.createCriteria(Funcionario.class);
        List<Funcionario> alunos = select.list();
        return alunos.get(alunos.size()-1);
    }

    public List<Funcionario> allRegistros() {
        Session sessao = HibernateUtility.getSession();
        Criteria select = sessao.createCriteria(Funcionario.class);
        select.addOrder(Order.asc("codigo"));
        List<Funcionario> funcionarios = select.list();
        return funcionarios;
    }
    
    public List<Funcionario> registrosPorNome() {
        Session sessao = HibernateUtility.getSession();
        Criteria select = sessao.createCriteria(Funcionario.class);
        select.addOrder(Order.asc("nome"));
        List<Funcionario> funcionarios = select.list();
        return funcionarios;
    }
    
    public List<Funcionario> registrosPorCodigo() {
        Session sessao = HibernateUtility.getSession();
        Criteria select = sessao.createCriteria(Funcionario.class);
        select.addOrder(Order.asc("codigo"));
        List<Funcionario> funcionarios = select.list();
        return funcionarios;
    }
    
    	public List<Funcionario> buscaParteNome(String nome) {
        Session sessao = HibernateUtility.getSession();
        Criteria select = sessao.createCriteria(Funcionario.class);
        select.add(Restrictions.like("nome", nome, MatchMode.START));
        List<Funcionario> funcionarios = select.list();
        return funcionarios;
    }
        
    public List<Funcionario> buscaParteCodigo(int cod) {
        Session sessao = HibernateUtility.getSession();
        List<Funcionario> funcionarios = sessao.createSQLQuery(
                "select * from funcionario where codigo >= " + cod).addEntity(
                Funcionario.class).list();

        return funcionarios;
    }
}
