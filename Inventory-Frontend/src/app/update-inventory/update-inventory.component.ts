import { Component, inject, OnInit } from '@angular/core';
import { InventoryService } from '../inventory.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Inventory } from '../inventory';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatDialogClose } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIcon } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatTableModule } from '@angular/material/table';

@Component({
  selector: 'app-update-inventory',
  standalone: true,
  imports: [CommonModule,
    FormsModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatTableModule,
    MatButtonModule,MatIcon,MatDialogClose,ReactiveFormsModule],
  templateUrl: './update-inventory.component.html',
  styleUrl: './update-inventory.component.css'
})
export class UpdateInventoryComponent implements OnInit{

  inventoryname!: string;
  inv : Inventory = new Inventory();

// constructor(){
//   this.inventoryname =  String(this.inventoryname) ;
// }
  
  ngOnInit(): void {
   this.inventoryname = this.route.snapshot.params['inventoryName'];
  //  this.inv.inventoryName = this.inventoryname;
   this.invService.getInventoryByName(this.inventoryname).subscribe((data=>{
    this.inv=data;
    console.log(this.inv);
   }))
  }

  invService = inject(InventoryService);
  route = inject(ActivatedRoute);
  router = inject(Router);

  updateInventory(){
    this.invService.updateInventory(this.inv.inventoryName , this.inv).subscribe((data=>{
     this.inv= data;
     console.log(data);
     this.router.navigateByUrl("/admin")
    }))
  }
}
