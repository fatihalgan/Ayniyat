package com.iztek.ayniyat.malzemehareketleri.persistence.usertypes;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.StringTokenizer;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.usertype.UserType;
import com.iztek.ayniyat.malzemehareketleri.domain.*;
import com.iztek.ayniyat.malzemehareketleri.util.DemirbasNoGenerator;
import com.iztek.util.persistence.DAOFactory;
import com.iztek.util.persistence.HibernateUtil;
/**
 * @author Cagdas CIRIT
 **/
public class DemirbasNoUserType1 implements UserType {

	public int[] sqlTypes() {
		return new int[] {Types.VARCHAR};
	}

	public Class returnedClass() {
		return DemirbasNo.class;
	}

	public boolean equals(Object x, Object y) throws HibernateException {
		if (x == y) return true;
		if (x == null || y == null) return false;
		return x.equals(y);
	}

	public int hashCode(Object demirbasNo) throws HibernateException {
		return ((DemirbasNo)demirbasNo).hashCode();
	}

	public Object nullSafeGet(ResultSet resultSet, String[] names, Object owner) throws HibernateException, SQLException {
		if (resultSet.wasNull()) return null;
		String demirbasNo = resultSet.getString(names[0]);
		String siraNo = "";
		String altSiraNo = "";
		
		StringTokenizer st = new StringTokenizer(demirbasNo,"-");
   		int numOfTokens = st.countTokens();
        
   		siraNo = st.nextToken();      
   		if (numOfTokens>1){
   			for(int i=0;i<numOfTokens-1;i++)
   				altSiraNo=altSiraNo+st.nextToken();
   			
   			for(int i=0;i<numOfTokens-2;i++)
   				siraNo = siraNo+"-"+st.nextToken();
       		altSiraNo = st.nextToken();
  		}
		
//		if(altSiraNo.equals(""))
//			return new DemirbasNo(Long.valueOf(siraNo));
//		else
//			return new DemirbasNo(Long.valueOf(siraNo),altSiraNo);*/
   		return null;
	}

	public void nullSafeSet(PreparedStatement statement, Object value, int index) throws HibernateException, SQLException {
		DemirbasNo demirbasNo = (DemirbasNo) value;
		
		if (demirbasNo.getSiraNo()==null)
			loadDemirbasNo(demirbasNo);
		
//		if (demirbasNo.getAltSiraNo().equals("") || demirbasNo.getAltSiraNo() == null ) //TODO burasi bir sonraki adýmda siraNumaralari isin icine girdiginde degistirilecek!!
			statement.setString(index,demirbasNo.getSiraNo().toString());
	//	else
			statement.setString(index,demirbasNo.toString());
	}
	
	public void loadDemirbasNo(DemirbasNo demirbasNo){
		Session session = HibernateUtil.getSession();
     	DemirbasNoGenerator demirbasNoGenerator = DAOFactory.getDemirbasNoGenDAO().getSiradakiDemirbasNumarasi();

		if (demirbasNoGenerator==null){
//			demirbasNoGenerator = new DemirbasNoGenerator(new Long(0),"");
			session.save(demirbasNoGenerator);
		}
		session.lock(demirbasNoGenerator,LockMode.UPGRADE);
		demirbasNoGenerator.setSiraNo(new Long(demirbasNoGenerator.getSiraNo().longValue()+1)); 
//		demirbasNo.setSiraNo(demirbasNoGenerator.getSiraNo());
//		demirbasNo.setAltSiraNo(demirbasNoGenerator.getAltSiraNo());
	}

	public Object deepCopy(Object value) throws HibernateException { 
		//kopya bir nesne döndürmüyoruz çünkü nullSafeSet() metodunda demirbasNo nesnesinin attributelarýný
		//set etmemiz gerekiyor(demirbas numaralarýndan bi numara çektikten sonra)
		return value;
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

