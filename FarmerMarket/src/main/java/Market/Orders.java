package Market;

import java.util.Date;

public class Orders {
	private int orderId;
    private String userId;
    private int productId;
    private int price;
    private Date orderDate;
    
    /*public Orders(int orderId, int userId, int productId, int price, Date orderDate) {
        this.orderId = orderId;
        this.userId = userId;
        this.productId = productId;
        this.price = price;
        this.orderDate = orderDate;
    }
    */
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
    
    
}
