import {Product} from "../../products/data-access/product.model";

export interface Cart{
  id?: number;
  items?: CartItem[];
}

export interface CartItem{
  product: Product;
  quantity: number;
}
