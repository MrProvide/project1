package javau9.ca.db.abc.finalprojectalternativehf.Repository;

import javau9.ca.db.abc.finalprojectalternativehf.Models.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {


}
