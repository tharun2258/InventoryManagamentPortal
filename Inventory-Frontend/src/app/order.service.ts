import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Order } from './order';

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  constructor(private http : HttpClient) { }

  apiUrl: string ="http://localhost:8082/order/";

  public createOrder(order: Order){
    return this.http.post<Order>(this.apiUrl+ "create",order);
  }

  public orderHistory(){
    return this.http.get<Order[]>(this.apiUrl+ "getAll");
  }

  public getOrderById(orderId:number){
    return this.http.get<Order>(this.apiUrl+ "getbyId/"+ orderId);
  }
  

  public orderAccept(orderId:number,order:Order){
    return this.http.put<Order>(this.apiUrl+ "orderAccepted/"+orderId, order);
  }

  public orderCancel(orderId:number){
    return this.http.put<Order[]>(this.apiUrl+ "cancelById/"+orderId, {});
  }
}
