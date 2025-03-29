package org.errorlabel.auth.repository;

import org.errorlabel.persistence.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByEmail(String email);

    @Query(value = "select user_role.name FROM customer_to_role\n" +
            "    join user_role on user_role.id = customer_to_role.role_id\n" +
            "    join customer on customer.id = customer_to_role.customer_id\n" +
            "    where customer_id = :userId", nativeQuery = true)
    List<String> findUserRolesNames(@Param("userId")Long userId);
}
