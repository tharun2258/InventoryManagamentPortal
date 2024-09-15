import { Component, inject, OnInit } from '@angular/core';
import { OrderService } from '../order.service';
import { Order } from '../order';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule, MatLabel } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatTableModule } from '@angular/material/table';
import { Router, RouterLink } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
import { OrderAcceptComponent } from '../order-accept/order-accept.component';
import { MatIcon } from '@angular/material/icon';

@Component({
  selector: 'app-orderhistory',
  standalone: true,
  imports: [CommonModule,
    FormsModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatTableModule,
    MatButtonModule,RouterLink,MatLabel,MatFormFieldModule,MatCardModule,MatIcon],
  templateUrl: './orderhistory.component.html',
  styleUrl: './orderhistory.component.css'
})
export class OrderhistoryComponent implements OnInit{

  
  orderObj : Order[] =[];
  orderdata :Order = new Order();
  orderSer = inject(OrderService);
  displayedColumns: string [] =['orderId',  'orderTime','inventoryName','orderQty','orderStatus','totalOrderAmount','orderQtyAccepted','Cancel','Accept'];
  router = inject(Router);
  matDialog =inject(MatDialog);

  ngOnInit(): void {
    this.orderhistory();
  }


  public orderhistory(){
    this.orderSer.orderHistory().subscribe((data=>{
    this.orderObj = data;
    console.log(data);
  
    }))
  }
//   public orderaccept(orderId: number){
//     const order = this.orderObj.find(order => order.orderId === orderId);
  
//     if (order) {
//     this.orderSer.orderAccept(order.orderId,this.orderdata).subscribe((data=>{
//       this.orderdata = data;
      
      
//     }))

//   }
// }

clickCount = 0; // Track number of clicks
  maxClicks = 1;
  disableIcon = false;

  public cancelOrder(orderId: number) {
    
    // Ensure `orderId` is of type `number`
    const order = this.orderObj.find(order => order.orderId === orderId);
  
    if (order) {
      
      if (order.orderStatus == "Order Placed!!! Waiting for Supplier to approve"){
      // Call `orderCancel` with the valid `order.id`
      this.orderSer.orderCancel(order.orderId).subscribe(data => {
        // Handle successful response
        console.log('Order cancelled successfully', data);
        this.orderhistory();
      }, error => {
        // Handle error response
        console.error('Error cancelling order', error);
      });
    } }else {
      // Handle the case where the order is not found
      console.warn('Order not found');
    }
  }

  
}

