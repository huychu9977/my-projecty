package com.viking.spring.reponsitory;

import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Query;

import com.viking.spring.bo.StudentBO;
import com.viking.spring.util.Constants;

public class StudentReponsitory{
	@PersistenceContext
	private EntityManager em;

	public List<StudentBO> search(StudentBO st, Integer page, Integer check) {
		String hql = "SELECT s FROM StudentBO s WHERE 1=1";
		HashMap<String, Object> map = new HashMap<String, Object>();
		Integer count = -1;

		if (st.getName() != null) {
			hql += " AND s.name LIKE :name";
			count++;
			map.put("name", "%" + st.getName() + "%");
		}
		/*
		 * if (user.getGender() != null) { hql += " AND u.gender = :gender"; count++;
		 * map.put("gender", user.getGender()); }
		 */
		Query query = (Query) em.createQuery(hql);
		if (count == -1) {
			// args = new Object[] {};
		} else {
			// args = new Object[count+1];
			for (String key : map.keySet()) {
				// args[key] = map.get(key);
				query.setParameter(key, map.get(key));
			}
		}

		if (check == 1) {
			query.setFirstResult(page * Constants.PAGE_SIZE);
			query.setMaxResults(Constants.PAGE_SIZE);

		}

		List<StudentBO> listUser = query.list();
		return listUser;
	}
}
