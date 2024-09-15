import { Component, inject, OnInit } from '@angular/core';
import { Order } from '../order';
import { OrderService } from '../order.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule, MatLabel } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatTableModule } from '@angular/material/table';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { MatIcon } from '@angular/material/icon';
import { MatDialog, MatDialogActions } from '@angular/material/dialog';

@Component({
  selector: 'app-order-accept',
  standalone: true,
  imports: [CommonModule,
    FormsModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatTableModule,
    MatButtonModule,RouterLink,MatLabel,MatFormFieldModule,MatCardModule,MatIcon,MatDialogActions],
  templateUrl: './order-accept.component.html',
  styleUrl: './order-accept.component.css'
})
export class OrderAcceptComponent implements OnInit {


  orderId!: number;
  order: Order = new Order();
  route= inject(ActivatedRoute);
  orderSer = inject(OrderService);
  router= inject(Router);

  ngOnInit(): void {
    this.orderId = this.route.snapshot.params['orderId'];

    console.log(this.orderId);
    //  this.inv.inventoryName = this.inventoryname;
     this.orderSer.getOrderById(this.orderId).subscribe((data=>{
    this.order = data;
     }))
  }



  public acceptOrder(){
    if(this.order.orderStatus=="Order Placed!!! Waiting for Supplier to approve"){
    this.orderSer.orderAccept(this.orderId , this.order).subscribe((data=>{
      
      this.order=data;
      this.router.navigateByUrl("/orderhistory")
    }))
  }

  }


  
}
