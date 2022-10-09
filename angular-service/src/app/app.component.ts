import { Component, OnInit, ViewChild } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { RestService } from 'app/services';
import { ThemePalette } from '@angular/material/core';
import { Price } from './models/Price.model';

const DATE_FORMAT = "YYYY-MM-DDTHH:mm:ss";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.sass']
})
export class AppComponent implements OnInit {

  title = 'angular-service'

  @ViewChild('picker') picker: any;

  public date: any;
  public minDate: any;
  public maxDate: any;

  public disabled = false;
  public showSpinners = true;
  public showSeconds = true;
  public touchUi = false;
  public enableMeridian = false;

  public stepHour = 1;
  public stepMinute = 1;
  public stepSecond = 1;

  public color: ThemePalette = 'primary';

  productId: Number = 35455;
  brandId: Number = 1;
  priceSearchResul: any;

  constructor( private restService: RestService) { }

  ngOnInit() {
  }

  toggleMinDate(evt: any) {
    if (evt.checked) {
      this._setMinDate();
    } else {
      this.minDate = null;
    }
  }

  toggleMaxDate(evt: any) {
    if (evt.checked) {
      this._setMaxDate();
    } else {
      this.maxDate = null;
    }
  }

  closePicker() {
    this.picker.cancel();
  }

  private _setMinDate() {
    const now = new Date();
    this.minDate = new Date();
    this.minDate.setDate(now.getDate() - 1);
  }


  private _setMaxDate() {
    const now = new Date();
    this.maxDate = new Date();
    this.maxDate.setDate(now.getDate() + 1);
  }

  searchPrice() {
    const dateFormated = this.date.format(DATE_FORMAT);
    const params = {date: dateFormated, productId: this.productId, brandId: this.brandId};

    this.restService.queryPrices(params).subscribe((res: HttpResponse<Price>) => {
      this.priceSearchResul = res?.body;
    });
  }

}
