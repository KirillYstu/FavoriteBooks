package ru.Krasotkin.FavoriteBooks.Repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import ru.Krasotkin.FavoriteBooks.Model.BookmarkModel;
import ru.Krasotkin.FavoriteBooks.Model.UserModel;

import java.util.List;

public interface BookmarkerRepo extends PagingAndSortingRepository<BookmarkModel, Long> {
    List<BookmarkModel> getAllByUser(UserModel userModel);
}
