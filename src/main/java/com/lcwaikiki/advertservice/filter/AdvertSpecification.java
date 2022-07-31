package com.lcwaikiki.advertservice.filter;

import com.lcwaikiki.advertservice.model.Advert;
import java.text.MessageFormat;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class AdvertSpecification implements Specification<Advert> {

  private AdvertFilterCriteria criteria;

  public AdvertSpecification(AdvertFilterCriteria criteria) {
    this.criteria = criteria;
  }

  @Override
  public Predicate toPredicate(Root<Advert> root, CriteriaQuery<?> query,
      CriteriaBuilder criteriaBuilder) {
    if (criteria.getOperation().equalsIgnoreCase(">")) {

      return criteriaBuilder.greaterThanOrEqualTo(
          root.<String>get(criteria.getKey()), criteria.getValue().toString());
    } else if (criteria.getOperation().equalsIgnoreCase("<")) {
      return criteriaBuilder.lessThanOrEqualTo(
          root.<String>get(criteria.getKey()), criteria.getValue().toString());
    } else if (criteria.getOperation().equalsIgnoreCase("@>")) {
      return criteriaBuilder.like(
          root.<String>get(criteria.getKey()), criteria.getValue().toString());
    } else if (criteria.getOperation().equalsIgnoreCase(":")) {
      if (root.get(criteria.getKey()).getJavaType() == String.class) {
        return criteriaBuilder.like(
            root.<String>get(criteria.getKey()), "%" + criteria.getValue() + "%");
      } else {
        return criteriaBuilder.equal(root.get(criteria.getKey()), criteria.getValue());
      }

    }
    return null;
  }

  public static Specification<Advert> nameContains(String expression) {
    return (root, query, builder) -> builder.like(root.get("name"), contains(expression));
  }

  public static Specification<Advert> departmentContains(String expression) {
    return (root, query, builder) -> builder.like(root.get("department"), contains(expression));
  }

  public static Specification<Advert> provinceContains(String expression) {
    return (root, query, builder) -> builder.like(root.get("province"), contains(expression));
  }

  public static Specification<Advert> positionContains(String expression) {
    return (root, query, builder) -> builder.like(root.get("position"), contains(expression));
  }

  private static String contains(String expression) {
    return MessageFormat.format("%{0}%", expression);
  }
}
