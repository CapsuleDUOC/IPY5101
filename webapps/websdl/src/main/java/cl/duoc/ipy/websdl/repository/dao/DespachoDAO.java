package cl.duoc.ipy.websdl.repository.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import cl.duoc.ipy.websdl.domain.Despacho;
import cl.duoc.ipy.websdl.repository.IDespachoDAO;
import cl.duoc.ipy.websdl.util.SearchCriteria;

@Repository
public class DespachoDAO implements IDespachoDAO{
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Despacho> search(List<SearchCriteria> params) {
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		final CriteriaQuery<Despacho> query = builder.createQuery(Despacho.class);
		final Root<Despacho> root = query.from(Despacho.class);

		Predicate predicate = builder.conjunction();
		List<Order> orderList = new ArrayList<>();

		for (SearchCriteria param : params) {

			switch (param.getOperation()) {
			case greaterThanOrEqualTo:
				predicate = builder.and(predicate,
						builder.greaterThanOrEqualTo(root.get(param.getKey()), param.getValue().toString()));
				break;
			case lessThanOrEqualTo:
				predicate = builder.and(predicate,
						builder.lessThanOrEqualTo(root.get(param.getKey()), param.getValue().toString()));
				break;
			case like:
				if (param.getKey2() == null) {
					if (root.get(param.getKey()).getJavaType() == String.class) {
						predicate = builder.and(predicate,
								builder.like(root.get(param.getKey()), "%" + param.getValue() + "%"));
					} else {
						predicate = builder.and(predicate, builder.equal(root.get(param.getKey()), param.getValue()));
					}
				} else {
					predicate = builder.and(predicate,
							builder.or(builder.like(root.get(param.getKey()), "%" + param.getValue() + "%"),
									builder.like(root.get(param.getKey2()), "%" + param.getValue() + "%")));
				}
				break;
			case between:
				predicate = builder.and(predicate, builder.between(root.get(param.getKey()),
						(LocalDate) param.getValue(), (LocalDate) param.getValue2()));
				break;
			case equal:
				predicate = builder.and(predicate, builder.equal(root.get(param.getKey()), param.getValue()));
				break;
			case in:
				predicate = builder.and(predicate, root.get(param.getKey()).in(param.getValue()));
				break;
			case exist:
				break;
			}
		}

		orderList.add(builder.asc(root.get("id")));
		query.where(predicate).orderBy(orderList);

		return entityManager.createQuery(query.select(root)).getResultList();
	}
}
