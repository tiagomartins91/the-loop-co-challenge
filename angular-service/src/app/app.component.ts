import { Component, OnInit, ViewChild } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { RestService } from 'app/services';
import { ThemePalette } from '@angular/material/core';
import { Price } from './models/Price.model';

const DATE_FORMAT = "YYYY-MM-DDTHH:mm:ss";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
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
  showErrorMessage = false;

  constructor( private restService: RestService) { }

  ngOnInit() {
  }

  searchPrice() {
    this.showErrorMessage = false;
    const dateFormated = this.date.format(DATE_FORMAT);
    const params = {date: dateFormated, productId: this.productId, brandId: this.brandId};

    /*
    this.restService.queryPrices(params).subscribe((res: HttpResponse<Price>) => {
      this.priceSearchResul = res?.body;
    },
    (error: any) => {

    });
    */

    this.restService.queryPrices(params).subscribe({
      next: (val) => this.priceSearchResul = val?.body,
      error: (err) => this.showErrorMessage = true
    });
  }

}
