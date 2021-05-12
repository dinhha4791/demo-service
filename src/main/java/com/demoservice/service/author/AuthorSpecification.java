package com.demoservice.service.author;

import com.demoservice.constants.AuthorConstants;
import com.demoservice.entity.Author;
import com.demoservice.rest.payload.author.AuthorSearchCriteria;
import com.demoservice.utils.StringFormatUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthorSpecification implements Specification<Author> {
    private AuthorSearchCriteria authorSearchCriteria;

    @Override
    public Predicate toPredicate(Root<Author> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        String email = authorSearchCriteria.getEmail();
        String phoneNumber = authorSearchCriteria.getPhoneNumber();
        if(authorSearchCriteria.getRequireMatch()) {
            return Specification
                    .where(buildAuthorEqualQuery(AuthorConstants.EMAIL, email))
                    .and(buildAuthorEqualQuery(AuthorConstants.PHONE_NUMBER, phoneNumber))
                    .toPredicate(root, criteriaQuery, criteriaBuilder);
        } else {
            return Specification
                    .where(buildAuthorLikeQuery(AuthorConstants.EMAIL, email))
                    .and(buildAuthorLikeQuery(AuthorConstants.PHONE_NUMBER, phoneNumber))
                    .toPredicate(root, criteriaQuery, criteriaBuilder);
        }
    }

    private Specification<Author> buildAuthorEqualQuery(String data, String value) {
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.equal(root.get(data), value);
    }

    private Specification<Author> buildAuthorLikeQuery(String data, String value) {
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(root.get(data)), StringFormatUtil.convertToLikeQueryFormat(value));
    }
}
