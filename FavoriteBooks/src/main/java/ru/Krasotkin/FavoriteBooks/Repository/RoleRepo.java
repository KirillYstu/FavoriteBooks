package ru.Krasotkin.FavoriteBooks.Repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import ru.Krasotkin.FavoriteBooks.Model.RoleModel;

public interface RoleRepo extends PagingAndSortingRepository<RoleModel, Long> {
}
