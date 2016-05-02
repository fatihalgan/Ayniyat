package com.iztek.ayniyat.malzemehareketleri.persistence.usertypes;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import org.hibernate.HibernateException;
import org.hibernate.usertype.UserType;
import com.iztek.ayniyat.malzemehareketleri.domain.*;
/**
 * @author Cagdas CIRIT
 **/
public class MalzemeStateUserType implements UserType {

	public int[] sqlTypes() {
		return new int[] {Types.VARCHAR};
	}

	public Class returnedClass() {
		return IMalzemeState.class;
	}

	public boolean equals(Object x, Object y) throws HibernateException {
		if (x == y) return true;
		if (x == null || y == null) return false;
		return x.equals(y);
	}

	public int hashCode(Object state) throws HibernateException {
		return ((IMalzemeState)state).getType().hashCode();
	}

	public Object nullSafeGet(ResultSet resultSet, String[] names, Object owner) throws HibernateException, SQLException {
		if (resultSet.wasNull()) return null;
		String type = resultSet.getString(names[0]);
		
		if(type.equals("Zayi"))
			return new StateZayi();
		if(type.equals("Terkin"))
			return new StateTerkin();
		if(type.equals("Askida"))
			return new StateAskida();
		if(type.equals("Stokta"))
			return new StateStokta();
		if(type.equals("Bozuk"))
			return new StateBozuk();
		if(type.equals("Zimmetli"))
			return new StateZimmetli();
		
		return null;
	}

	public void nullSafeSet(PreparedStatement statement, Object value, int index) throws HibernateException, SQLException {
		if (value == null) {
			statement.setNull(index, Types.VARCHAR);
		} else {
			String type="";
				
			if(value instanceof StateZayi)
				type = "Zayi";
			if(value instanceof StateTerkin)
				type = "Terkin";
			if(value instanceof StateAskida)
				type = "Askida";
			if(value instanceof StateStokta)
				type = "Stokta";
			if(value instanceof StateBozuk)
				type = "Bozuk";
			if(value instanceof StateZimmetli)
				type = "Zimmetli";
				
			statement.setString(index,type);
		}
	}

	public Object deepCopy(Object value) throws HibernateException { 
		if (value instanceof StateStokta)
			return new StateStokta();
		if (value instanceof StateAskida)
			return new StateAskida();
		if (value instanceof StateBozuk)
			return new StateBozuk();
		if (value instanceof StateZimmetli)
			return new StateZimmetli();
		if (value instanceof StateZayi)
			return new StateZayi();
		if (value instanceof StateTerkin)
			return new StateTerkin();
		return null;
	}

	public boolean isMutable() { return false; }

	//TODO bu metodlar gerekliyse dolduralacak
	public Serializable disassemble(Object arg0) throws HibernateException {
		return null;
	}

	public Object assemble(Serializable arg0, Object arg1)
			throws HibernateException {
		return null;
	}

	public Object replace(Object arg0, Object arg1, Object arg2)throws HibernateException {
		return null;
	}
}
