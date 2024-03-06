package com.food.to.go.application.restaurantservice.domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import java.util.List;

@Access(AccessType.FIELD)
public class RestaurantMenu {

    @ElementCollection
    private List<MenuItem> menuItems;

    public RestaurantMenu(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }
}
