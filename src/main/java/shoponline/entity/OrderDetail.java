package shoponline.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Order_Details")
public class OrderDetail implements Serializable {
  private static final long serialVersionUID = 7550745928843183535L;
  private String id;
  private Order order;
  private Product product;
  private int quality;
  private double price;
  private double amount;
}
