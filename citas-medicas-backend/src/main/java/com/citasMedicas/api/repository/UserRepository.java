package com.citasMedicas.api.repository;

import com.citasMedicas.api.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    
}