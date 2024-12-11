package net.tplusable.hotpack.repository;

import net.tplusable.hotpack.domain.SiteUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<SiteUser, Long> {

}
