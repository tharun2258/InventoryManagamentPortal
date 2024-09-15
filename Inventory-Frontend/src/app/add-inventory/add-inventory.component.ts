import { Component, inject, OnInit } from '@angular/core';
import { InventoryService } from '../inventory.service';
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
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-inventory',
  standalone: true,
  imports: [CommonModule,
    FormsModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatTableModule,
    MatButtonModule,MatIcon,MatDialogClose,ReactiveFormsModule],
  templateUrl: './add-inventory.component.html',
  styleUrl: './add-inventory.component.css'
})
export class AddInventoryComponent implements OnInit {
  ngOnInit(): void {
    
  }

  inv : Inventory = new Inventory();
  invService = inject(InventoryService);
  router= inject(Router);

  public addInventory(){
    return this.invService.addInventory(this.inv).subscribe((data)=>{
      console.log(data);
    this.router.navigateByUrl("/admin")
    })
  }
  

}
