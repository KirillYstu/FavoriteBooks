package ru.Krasotkin.FavoriteBooks.Repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import ru.Krasotkin.FavoriteBooks.Model.BookModel;

public interface BookRepo extends PagingAndSortingRepository<BookModel, Long> {
}
