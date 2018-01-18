package shoponline.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Products")
public class Product implements Serializable {
  private static final long serialVersionUID = -1000119078147252957L;
  private String code;
  private String name;
  private double price;
  private byte[] image;

  //For sort
}
