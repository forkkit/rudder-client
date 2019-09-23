var RudderProperty = require("./RudderProperty");
var ECommerceEvents = require("./constants").ECommerceEvents;
var ECommerceParamNames = require("./constants").ECommerceParamNames;

//Class representing "Product List Filtered" event
class ProductListFilteredEvent {
  constructor() {
    this.listId = null;
    this.filters = [];
    this.sorts = [];
    this.products = [];
  }

  //Setter methods in accordance to Builder pattern

  setListId(listId) {
    this.listId = listId;
    return this;
  }

  addProducts(products) {
    if (!this.products) {
      this.products = products;
    } else {
      this.products.pushValues(products);
    }
    return this;
  }

  addProduct(product) {
    if (!this.products) {
      this.products = [];
    }
    this.products.push(product);
    return this;
  }

  addFilters(filters) {
    if (!this.filters) {
      this.filters = filters;
    } else {
      this.filters.pushValues(filters);
    }
    return this;
  }

  addFilter(filter) {
    if (!this.filters) {
      this.filters = [];
    }
    this.filters.push(filter);
    return this;
  }

  addSorts(sorts) {
    if (!this.sorts) {
      this.sorts = sorts;
    } else {
      this.sorts.pushValues(sorts);
    }
    return this;
  }

  addSort(sort) {
    if (!this.sorts) {
      this.sorts = [];
    }
    this.sorts.push(sort);
    return this;
  }

  event() {
    return ECommerceEvents.PRODUCT_LIST_FILTERED;
  }

  build() {
    var eventProperty = new RudderProperty();
    eventProperty.setProperty(ECommerceParamNames.LIST_ID, this.listId);
    eventProperty.setProperty(ECommerceParamNames.FILTERS, this.filters);
    eventProperty.setProperty(ECommerceParamNames.PRODUCTS, this.products);
    eventProperty.setProperty(ECommerceParamNames.SORTS, this.sorts);
    return eventProperty;
  }
}

module.exports = {
  ProductListFilteredEvent: ProductListFilteredEvent
};
