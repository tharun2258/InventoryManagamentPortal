import { CommonModule } from '@angular/common';
import { Component, inject, OnInit } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatTableModule } from '@angular/material/table';
import { RouterLink, ActivatedRoute, Router } from '@angular/router';
import { Inventory } from '../inventory';
import { InventoryService } from '../inventory.service';
import { Order } from '../order';
import { OrderService } from '../order.service';

@Component({
  selector: 'app-order',
  standalone: true,
  imports: [CommonModule,FormsModule,MatCardModule,MatFormFieldModule,MatInputModule,MatTableModule,MatButtonModule,RouterLink,ReactiveFormsModule],
  templateUrl: './order.component.html',
  styleUrl: './order.component.css'
})
export class OrderComponent implements OnInit{
  inventoryName !: string;

  orderQuantity !: number;
 
  order : Order = new Order ();
 
  inventory : Inventory= new Inventory();
 
  route = inject(ActivatedRoute);
  inventoryService = inject(InventoryService);
  orderService = inject(OrderService);
  router =inject(Router)
 
   ngOnInit(): void {
     this.inventoryName = this.route.snapshot.params['inventoryname'];
     this.inventoryService.getInventoryByName(this.inventoryName).subscribe((data: any)=>{
       this.order= data;
       this.inventory=data;
     })
   }
 
  createOrder(orderData: any){
   this.orderService.createOrder(orderData).subscribe((data=>{
     if(data.orderQty > this.inventory.invQuantity){
       alert("order number can't be more than available inventory");
     }
     console.log("test"+ data.orderQty+ this.inventory.invQuantity);
     console.log(data);
     this.router.navigateByUrl("/inventory");
   }))
  }
 
 
 
}
