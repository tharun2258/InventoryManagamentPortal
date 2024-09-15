import { Component, inject, OnInit } from '@angular/core';
import { InventoryService } from '../inventory.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatTableModule } from '@angular/material/table';
import { RouterLink } from '@angular/router';
import { Inventory } from '../inventory';
import { MatIcon } from '@angular/material/icon';
import { MatChip, MatChipsModule } from '@angular/material/chips';

@Component({
  selector: 'app-admin',
  standalone: true,
  imports: [CommonModule,
    FormsModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatTableModule,
    MatButtonModule,RouterLink,MatIcon,MatChipsModule,MatChip],
  templateUrl: './admin.component.html',
  styleUrl: './admin.component.css'
})
export class AdminComponent implements OnInit{

  invlist : Inventory[] = [];

  userid!: number;


  filters={
    keyword:'',
  }




  
  displayedColumns: String []=['id' , 'inventoryName','invQuantity', 'invPrice','stockStatus','Update','Delete'];

  ngOnInit(): void {
  this.showallinventory();
  }


  InventoryService = inject(InventoryService);

  constructor(){
    const storedUserid = localStorage.getItem('userid');
    this.userid =  Number(storedUserid) ;
  }

  public showallinventory(){
    this.InventoryService.getallinventory().subscribe((data:any)=>{

      this.invlist= data;
     
      this.invlist = this.filterName(data);

      console.log(data);
    })
      
  }

  filterName(expenses:Inventory[]){
    return expenses.filter((e) =>{
     return e.inventoryName.toLowerCase().includes(this.filters.keyword.toLowerCase());
    })
    
   }
 

   public deleteInventory(id:number){
    this.InventoryService.deleteInventory(id).subscribe((data)=>{

    this.showallinventory();
    })
 
      
    }
}





