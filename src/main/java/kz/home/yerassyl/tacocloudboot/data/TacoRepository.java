package kz.home.yerassyl.tacocloudboot.data;

import kz.home.yerassyl.tacocloudboot.models.Taco;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TacoRepository extends CrudRepository<Taco, Long> {}
