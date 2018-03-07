package shoponline.model;

import java.util.Date;
import java.util.List;

/**
 * @author hieu.tpk
 */
public class OrderInfo {
  private String Id;
  private Date orderDate;
  private int orderNum;
  private double amount;
  private String customerName;
  private String customerAddress;
  private String customerEmail;
  private String customerPhone;
  private List<OrderDetailInfo> details;

  public OrderInfo() {

  }

  /**
   * Using for Hibernate Query
   * 
   * @param id
   * @param orderDate
   * @param orderNum
   * @param amount
   * @param customerName
   * @param customerAddress
   * @param customerEmail
   * @param customerPhone
   */
  public OrderInfo(String id, Date orderDate, int orderNum, double amount, String customerName, String customerAddress,
      String customerEmail, String customerPhone) {
    super();
    Id = id;
    this.orderDate = orderDate;
    this.orderNum = orderNum;
    this.amount = amount;
    this.customerName = customerName;
    this.customerAddress = customerAddress;
    this.customerEmail = customerEmail;
    this.customerPhone = customerPhone;

  }

  public String getId() {
    return Id;
  }

  public void setId(String id) {
    Id = id;
  }

  public Date getOrderDate() {
    return orderDate;
  }

  public void setOrderDate(Date orderDate) {
    this.orderDate = orderDate;
  }

  public int getOrderNum() {
    return orderNum;
  }

  public void setOrderNum(int orderNum) {
    this.orderNum = orderNum;
  }

  public double getAmount() {
    return amount;
  }

  public void setAmount(double amount) {
    this.amount = amount;
  }

  public String getCustomerName() {
    return customerName;
  }

  public void setCustomerName(String customerName) {
    this.customerName = customerName;
  }

  public String getCustomerAddress() {
    return customerAddress;
  }

  public void setCustomerAddress(String customerAddress) {
    this.customerAddress = customerAddress;
  }

  public String getCustomerEmail() {
    return customerEmail;
  }

  public void setCustomerEmail(String customerEmail) {
    this.customerEmail = customerEmail;
  }

  public String getCustomerPhone() {
    return customerPhone;
  }

  public void setCustomerPhone(String customerPhone) {
    this.customerPhone = customerPhone;
  }

  public List<OrderDetailInfo> getDetails() {
    return details;
  }

  public void setDetails(List<OrderDetailInfo> details) {
    this.details = details;
  }

}
