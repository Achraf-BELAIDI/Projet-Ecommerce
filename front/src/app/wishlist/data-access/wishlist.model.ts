import {Product} from "../../products/data-access/product.model";

export interface WishList{
  id?: number;
  products?: Product[];
}

