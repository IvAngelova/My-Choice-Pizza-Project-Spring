package bg.softuni.mychoicepizza.model.binding;

import bg.softuni.mychoicepizza.model.entity.enums.DeliveryEnum;

import java.math.BigDecimal;

public class OrderAddBindingModel {

     private DeliveryEnum delivery;

     private BigDecimal total;

     public OrderAddBindingModel() {
     }

     public DeliveryEnum getDelivery() {
          return delivery;
     }

     public OrderAddBindingModel setDelivery(DeliveryEnum delivery) {
          this.delivery = delivery;
          return this;
     }

     public BigDecimal getTotal() {
          return total;
     }

     public OrderAddBindingModel setTotal(BigDecimal total) {
          this.total = total;
          return this;
     }
}
