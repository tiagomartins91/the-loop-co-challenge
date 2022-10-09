import { Moment } from "moment";

export interface Price {
  brandId: Number;
  curr: String;
  finalPrice: Number;
  price: Number;
  priceDate: Moment;
  productId: Number;
  vat: Number;
  vatAmount: Number;
}
