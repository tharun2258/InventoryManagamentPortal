import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Inventory } from './inventory';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class InventoryService {

  constructor(private http: HttpClient) {

  }

  baseUrl : string = "http://localhost:8081/inventory/"


  public getallinventory (){
   return this.http.get<Inventory[]>(this.baseUrl+"getAll");
  }

  public getInventoryByName(inventoryName:string){
   return this.http.get<Inventory>(this.baseUrl+"getbyName/"+inventoryName)
  }

  public addInventory(inv:Inventory){
    return this.http.post<Inventory>(this.baseUrl+"create",inv)
   }
   

   public updateInventory(invname:string,inv:Inventory){
    return this.http.put<Inventory>(this.baseUrl+"updatebyId/"+invname,inv)
   }

   public deleteInventory(id:number){
    return this.http.delete<Inventory>(this.baseUrl+"deletebyId/"+id)
   }
}
