package com.iztek.ayniyat.malzemehareketleri.persistence.usertypes;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.usertype.UserType;
import com.iztek.ayniyat.malzemehareketleri.util.BelgeNoGenerator;
import com.iztek.util.persistence.DAOFactory;
import com.iztek.util.persistence.HibernateUtil;
/**
 * @author Cagdas CIRIT
 **/
public class BelgeNoUserType implements UserType {

	public int[] sqlTypes() {
		return new int[] {Types.BIGINT};
	}

	public Class returnedClass() {
		return BelgeNoGenerator.class;
	}

	public boolean equals(Object x, Object y) throws HibernateException {
		if (x == y) return true;
		if (x == null || y == null) return false;
		return x.equals(y);
	}

	public int hashCode(Object belgeNo) throws HibernateException {
		return ((BelgeNoGenerator)belgeNo).hashCode();
	}

	public Object nullSafeGet(ResultSet resultSet, String[] names, Object owner) throws HibernateException, SQLException {
		
		long belgeNo = resultSet.getLong(names[0]);
		if (resultSet.wasNull()) return null;
		return new BelgeNoGenerator(new Long(belgeNo));
	}

	public void nullSafeSet(PreparedStatement statement, Object value, int index) throws HibernateException, SQLException {
		BelgeNoGenerator belgeNo = (BelgeNoGenerator) value;
		
		if (belgeNo.getBelgeNo()==null)
			loadBelgeNo(belgeNo);
		
		statement.setLong(index,belgeNo.getBelgeNo().longValue());
	}	
	
	public void loadBelgeNo(BelgeNoGenerator belgeNo){
		Session session = HibernateUtil.getSession();
     	BelgeNoGenerator belgeNoGenerator = DAOFactory.getBelgeNoGenDAO().getSiradakiBelgeNumarasi();

		if (belgeNoGenerator==null){
			belgeNoGenerator = new BelgeNoGenerator(new Long(0));
			session.save(belgeNoGenerator);
		}
		session.lock(belgeNoGenerator,LockMode.UPGRADE);
		belgeNoGenerator.setBelgeNo(new Long(belgeNoGenerator.getBelgeNo().longValue()+1)); 
		belgeNo.setBelgeNo(belgeNoGenerator.getBelgeNo());
	}

	public Object deepCopy(Object value) throws HibernateException { 
		//kopya bir nesne döndürmüyoruz çünkü nullSafeSet() metodunda demirbasNo nesnesinin attributelarýný
		//set etmemiz gerekiyor(demirbas numaralarýndan bi numara çektikten sonra)
		return value;
	}

	public boolean isMutable() { return false; }

	//TODO bu metodlar gerekliyse dolduralacak
	public Serializable disassemble(Object arg0) throws HibernateException {
		return (Serializable) deepCopy(arg0);

	}

	public Object assemble(Serializable arg0, Object arg1)
			throws HibernateException {
		return deepCopy(arg0);

	}

	public Object replace(Object arg0, Object arg1, Object arg2)throws HibernateException {
		return deepCopy(arg0);

	}
}

