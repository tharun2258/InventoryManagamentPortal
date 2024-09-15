import { AfterViewInit, Component, inject, OnInit, ViewChild } from '@angular/core';
import { Inventory } from '../inventory';
import { InventoryService } from '../inventory.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { RouterLink } from '@angular/router';
import { MatPaginator, MatPaginatorModule } from '@angular/material/paginator';

@Component({
  selector: 'app-inventory',
  standalone: true,
  imports: [CommonModule,
    FormsModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatTableModule,
    MatButtonModule,RouterLink,MatPaginatorModule],
  templateUrl: './inventory.component.html',
  styleUrl: './inventory.component.css'
})
export class InventoryComponent implements OnInit, AfterViewInit{
  
  invlist : Inventory[] = [];

  userid!: number;
  dataSource = new MatTableDataSource<Inventory>();
  @ViewChild(MatPaginator) paginator!: MatPaginator;



  filters={
    keyword:'',
  }


   useriddata= localStorage.getItem('userid');


  
  displayedColumns: String []=['id' , 'inventoryName','invQuantity', 'invPrice', 'stockStatus'];

  ngOnInit(): void {
  this.showallinventory();
  
  }


  InventoryService = inject(InventoryService);

  constructor(){
    const storedUserid = localStorage.getItem('userid');
    this.userid =  Number(storedUserid) ;
  }
  ngAfterViewInit(): void {
    this.dataSource.paginator = this.paginator;
  }

  public showallinventory(){
    this.InventoryService.getallinventory().subscribe((data:any)=>{
      this.invlist = data;
      this.dataSource.data = this.filterName(data); // Update dataSource with filtered data
      this.dataSource.paginator = this.paginator;
     

    })
      
  }

  filterName(expenses:Inventory[]){
    return expenses.filter((e) =>{
     return e.inventoryName.toLowerCase().includes(this.filters.keyword.toLowerCase());
    })
    
   }
}




