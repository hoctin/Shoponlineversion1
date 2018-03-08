package shoponline.dao;

import java.util.List;

import shoponline.model.CartInfo;
import shoponline.model.OrderDetailInfo;
import shoponline.model.OrderInfo;
import shoponline.model.PaginationResult;

public interface OrderDAO {
  public void saveOrder(CartInfo cartInfo);

  public PaginationResult<OrderInfo> listOrderInfo(int page, int maxResult, int maxNavigationPage);

  public OrderInfo getOrderInfo(String orderId);

  public List<OrderDetailInfo> listOrderDetailInfos(String orderId);
}
