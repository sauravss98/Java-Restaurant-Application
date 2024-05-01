    package Orders;

    import Items.Item;
    import java.security.Timestamp;
    import java.util.ArrayList;
    import java.util.Date;

    /**
     * Class to create new object for the orders
     * @author Saurav
     */
    public class Order {
        private int orderId;
        private String orderType;
        private ArrayList<Integer> items = new ArrayList<>();
        private ArrayList<Item> itemsObjects = new ArrayList<>();
        private int price;

        private String orderStatus;
        private boolean isCompleted = false;
        private int customerId=0;
        private int managerId=0;
        private int waiterId=0;
        private int chefId=0;
        private int driverId=0;
        private Date orderDate;
        private Timestamp orderCreatedTime;
        private Timestamp orderCompletedTime;

        private final ArrayList<OrderItem> orderItems = new ArrayList<>();


        /**
         * Getter function for customer id
         * @return customerId
         */
        public int getCustomerId() {
            return customerId;
        }

        /**
         * Getter function for manager id
         * @return managerId
         */
        public int getManagerId() {
            return managerId;
        }

        /**
         * Setter function for setting the manager id
         * @param managerId the manager id is passed
         */
        public void setManagerId(int managerId) {
            this.managerId = managerId;
        }

        /**
         * Getter function for waiter id
         * @return waiterId
         */
        public int getWaiterId() {
            return waiterId;
        }

        /**
         * Setter function for setting the waiter id
         * @param waiterId the waiter id is passed
         */
        public void setWaiterId(int waiterId) {
            this.waiterId = waiterId;
        }

        /**
         * Getter function for chef id
         * @return chef
         */
        public int getChefId() {
            return chefId;
        }

        /**
         * Setter function for setting the chef id
         * @param chefId the chef id is passed
         */
        public void setChefId(int chefId) {
            this.chefId = chefId;
        }

        /**
         * Getter function for driver id
         * @return driverId
         */
        public int getDriverId() {
            return driverId;
        }

        /**
         * Setter function for setting the driver id
         * @param driverId the driver id is passed
         */
        public void setDriverId(int driverId) {
            this.driverId = driverId;
        }

        /**
         * Setter function for setting the items
         * @param items the items is passed
         */
        public void setItems(ArrayList<Integer> items) {
            this.items = items;
        }

        public ArrayList<Item> getItemsObjects() {
            return itemsObjects;
        }

        /**
         * Setter function for setting the item objects(items in the order)
         * @param itemsObjects the item objects is passed
         */
        public void setItemsObjects(ArrayList<Item> itemsObjects) {
            this.itemsObjects = itemsObjects;
        }

        /**
         * Getter function for order status
         * @return order status
         */
        public String getOrderStatus() {
            return orderStatus;
        }

        /**
         * Setter function for setting the order status
         * @param orderStatus the order status is passed
         */
        public void setOrderStatus(String orderStatus) {
            this.orderStatus = orderStatus;
        }

        /**
         * Getter function for price
         * @return price
         */
        public int getPrice() {
            return price;
        }

        /**
         * Setter function for setting the price
         * @param price the price is passed
         */
        public void setPrice(int price) {
            this.price = price;
        }

        /**
         * Getter function for order data
         * @return order date
         */
        public Date getOrderDate() {
            return orderDate;
        }

        /**
         * Setter function for setting the order date
         * @param orderDate the date is passed
         */
        public void setOrderDate(Date orderDate) {
            this.orderDate = orderDate;
        }

        /**
         * Getter function for order time
         * @return order time
         */
        public Timestamp getOrderCreatedTime() {
            return orderCreatedTime;
        }

        /**
         * Setter function for setting the order created time
         * @param orderCreatedTime the time is passed
         */
        public void setOrderCreatedTime(Timestamp orderCreatedTime) {
            this.orderCreatedTime = orderCreatedTime;
        }

        /**
         * Getter function for order completed time
         * @return time
         */
        public Timestamp getOrderCompletedTime() {
            return orderCompletedTime;
        }

        /**
         * Setter function for setting the order complete time
         * @param orderCompletedTime the time is passed
         */
        public void setOrderCompletedTime(Timestamp orderCompletedTime) {
            this.orderCompletedTime = orderCompletedTime;
        }

        /**
         * Constructor for creating empty order
         */
        public Order() {
        }

        /**
         * Getter function for order id
         * @return order id
         */
        public int getOrderId() {
            return orderId;
        }

        /**
         * Setter function for setting the order id
         * @param orderId the order id is passed
         */
        public void setOrderId(int orderId) {
            this.orderId = orderId;
        }

        /**
         * Getter function for order type
         * @return ordertype
         */
        public String getOrderType() {
            return orderType;
        }

        /**
         * Setter function for setting the order type
         * @param orderType the order type is passed
         */
        public void setOrderType(String orderType) {
            this.orderType = orderType;
        }

        /**
         * Getter function for items list
         * @return items
         */
        public ArrayList<Integer> getItems() {
            return items;
        }

        /**
         * Function to add item to the item arraylist
         * @param item the item is passed
         */
        public void addToItem(Integer item){
            items.add(item);
        }

        /**
         * Function to set the items into the order
         * @param itemId the item id
         * @param item the item object
         */
        public void setItems(int itemId, Item item) {
            items.add(itemId);
            boolean found = false;
            for (Item currentItem : itemsObjects) {
                if (currentItem.getItemID() == itemId) {
                    currentItem.addQuantity(); // Increase quantity if the item is already in the order
                    found = true;
                    break;
                }
            }
            if (!found) {
                itemsObjects.add(item); // Add new item if it's not in the order
            }
        }

        /**
         * Function to add item into the order item array list
         * @param item item object
         * @param quantity item quantity
         */
        public void addItem(Item item, int quantity) {
            boolean found = false;
            for (OrderItem orderItem : orderItems) {
                if (orderItem.getItem().getItemID() == item.getItemID()) {
                    orderItem.setQuantity(orderItem.getQuantity() + quantity);
                    found = true;
                    break;
                }
            }
            if (!found) {
                orderItems.add(new OrderItem(item, quantity));
            }
        }

        /**
         * Function to remove item from the list
         * @param itemId id of the item passed
         */
        public void removeItem(int itemId) {
            orderItems.removeIf(orderItem -> orderItem.getItem().getItemID() == itemId);
            for (int i = items.size() - 1; i >= 0; i--) {
                if (items.get(i) == itemId) {
                    items.remove(i);
                }
            }
        }

        /**
         * Getter function for order item
         * @return order item
         */
        public ArrayList<OrderItem> getOrderItems() {
            return orderItems;
        }


        /**
         * Getter function for completed status
         * @return is completed status
         */
        public boolean isCompleted() {
            return isCompleted;
        }

        /**
         * Setter function for setting the completion status
         * @param completed the completion status is passed
         */
        public void setCompleted(boolean completed) {
            isCompleted = completed;
        }

        // Customer direcly orders

        /**
         * Constructor for creating the order object
         * This constructor is used by the customer to order
         * @param orderId the order id is passed
         * @param orderType the order type is passed
         * @param items the items is passed
         * @param isCompleted the completion status is passed
         * @param orderStatus the order status is passed
         * @param customerId the customer id is passed
         */
        public Order(int orderId, String orderType, ArrayList<Integer> items, boolean isCompleted,String orderStatus, int customerId,int waiterId,int chefId,int driverId) {
            this.orderId = orderId;
            this.orderType = orderType;
            this.items = items;
            this.itemsObjects = itemsObjects;
            this.isCompleted = isCompleted;
            this.orderStatus = orderStatus;
            this.customerId = customerId;
            this.waiterId = waiterId;
            this.chefId = chefId;
            this.driverId = driverId;
        }

    //    @Override
    //    public String toStringtest() {
    //        return "Order{" +
    //                "orderId=" + orderId +
    //                ", orderType='" + orderType + '\'' +
    //                ", items=" + items +
    //                ", items=" + itemsObjects +
    //                ", isCompleted=" + isCompleted +
    //                '}';
    //    }

        /**
         * Function to return the order data as a string
         * @return string containing all the order details
         */
        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder("Order{");
            sb.append("orderId=").append(orderId).append(", ");
            sb.append("orderType='").append(orderType).append("', ");
            sb.append("items=").append(items).append(", ");
            sb.append("itemsObjects=[");
            for (Item item : itemsObjects) {
                sb.append("{").append(item.getItemName()).append(", Quantity=").append(item.getQuantity()).append("}, ");
            }
            sb.delete(sb.length() - 2, sb.length()); // Remove the trailing ", "
            sb.append("], ");
            sb.append("isCompleted=").append(isCompleted);
            sb.append(" CustomerId = ").append(customerId);
            sb.append("}");
            return sb.toString();
        }

        /**
         * Function to return the order details for the list views
         * @return String with order data
         */
        public String getDescriptionForOrderList() {
            return "ID: "+orderId+" Order Status : " + orderStatus + " - price: "+price;
        }
    }
