package ResumeSpringPackage;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by student on 6/21/17.
 */
public interface HouseRepository extends CrudRepository<House, Long> {

}
