package bg.softuni.mychoicepizza.model.binding;

import bg.softuni.mychoicepizza.model.entity.enums.DeliveryEnum;

import javax.validation.constraints.NotNull;

public class OrderAddBindingModel {

     @NotNull(message = "Полето е задължително!")
     private DeliveryEnum delivery;

     public DeliveryEnum getDelivery() {
          return delivery;
     }

     public OrderAddBindingModel setDelivery(DeliveryEnum delivery) {
          this.delivery = delivery;
          return this;
     }

}
