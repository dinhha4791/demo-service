package com.demoservice.service.book;

import com.demoservice.constants.AuthorConstants;
import com.demoservice.constants.BookConstants;
import com.demoservice.entity.Author;
import com.demoservice.entity.Book;
import com.demoservice.rest.payload.book.BookSearchCriteria;
import com.demoservice.utils.StringFormatUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookSpecification implements Specification<Book> {
    private BookSearchCriteria bookSearchCriteria;

    @Override
    public Predicate toPredicate(Root<Book> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        Join<Book, Author> authors = root.join(BookConstants.AUTHORS, JoinType.LEFT);
        criteriaQuery.distinct(true);
        if (bookSearchCriteria.getRequireMatch())
            return criteriaBuilder.equal(authors.get(AuthorConstants.NAME), bookSearchCriteria.getAuthorName());
        else
            return criteriaBuilder.like(criteriaBuilder.lower(authors.get(AuthorConstants.NAME)), StringFormatUtil.convertToLikeQueryFormat(bookSearchCriteria.getAuthorName()));
    }
}
