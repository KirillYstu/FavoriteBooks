package ru.Krasotkin.FavoriteBooks.Repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import ru.Krasotkin.FavoriteBooks.Model.UserModel;

import java.util.List;
import java.util.Optional;

public interface UserRepo extends PagingAndSortingRepository<UserModel, Long> {

    Optional<UserModel> getByUsername(String username);

    List<UserModel> getAllBy();

    boolean existsByUsername(String username);
}
