package datamodel;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Recipes")
public class Recipes {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id")
   private Integer id;

   @Column(name = "recipe")
   private String name;

   @Column(name = "url")
   private String url;

   public Recipes() {
   }

   public Recipes(Integer id, String recipe, String url) {
      this.id = id;
      this.name = recipe;
      this.url = url;
   }

   public Recipes(String name, String url) {
      this.name = name;
      this.url = url;
   }

   public Integer getId() {
      return id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getUrl() {
      return url;
   }

   public void setUrl(String url) {
      this.url = url;
   }

   @Override
   public String toString() {
      return "Recipe:" + this.id + ", " + this.name + ", " + this.url;
   }
}