import { Moment } from "moment";

export interface Price {
  brandId: Number;
  curr: String;
  finalPrice: Number;
  priceDate: Moment
  productId: Number
}
