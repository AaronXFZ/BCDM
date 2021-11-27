package model.dataccess;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Session;
import model.dataccess.ConnectionFactory_Hibernate;
import model.entities.ItemOrder;
import model.entities.PriceHistory;
import model.entities.User;

public class PriceHistoryAccess {
	ConnectionFactory_Hibernate conn_factory = new ConnectionFactory_Hibernate();
	
	PriceHistory price_hist_obj;
	
	public PriceHistoryAccess()
	{}
	
	public PriceHistoryAccess(PriceHistory price_hist_obj)
	{
		this.price_hist_obj = price_hist_obj;
		
	}
	
	public List<PriceHistory> get_all_price_history() throws ClassNotFoundException, SQLException
	{
		Session session = conn_factory.getSession();
		
		session.beginTransaction();
		
		List<PriceHistory> list_all_prices_history = session.createQuery("from historical_price").getResultList();
		
		return list_all_prices_history;
	}
	
	public List<PriceHistory> get_price_history_by_item_id(int item_id) throws ClassNotFoundException, SQLException
	{
		Session session = conn_factory.getSession();
		
		session.beginTransaction();
		
		List<PriceHistory> list_prices_history_by_id = session.createQuery("from historical_price h where h.item_id=:item_id")
				                                                .setParameter("item_id", item_id).list();
		
		return list_prices_history_by_id;
	}
	
	
	public void set_price_hist(PriceHistory price_hist_obj)
	{
		this.price_hist_obj = price_hist_obj;
	}
}
