package fr.eni.ENI_Encheres.dal;

import java.util.List;


//Ne rajouter que des methodes qui marchent pour tous les JdbcImpl
public interface DAO<T> {
	public T selectById(int id) throws DALException;
	public List<T> selectAll() throws DALException;
	public void update(T data) throws DALException;
	public void insert(T data) throws DALException;
	public void delete(T data) throws DALException;
}
